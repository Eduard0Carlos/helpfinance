import axios from "axios";
import { apiUrl } from "./ApiService";
import { UUID } from "crypto";
import IMovimentation from "lib/interfaces/IMovimentation";
import { format } from "date-fns";
import { formatMovimentationCategory } from "lib/utils";

const movimentationPrefix = "v1/movimentation";

const formatMovimentations = (movimentations: IMovimentation[]): any => {
  movimentations.forEach(x => {
    x.date = new Date(x.date);
    x.categoryInfo = formatMovimentationCategory(x.category);
  });

  return movimentations;
};

const formatMovimentation = (movimentation: IMovimentation): any => {
  movimentation.date = new Date();
  movimentation.categoryInfo = formatMovimentationCategory(movimentation.category);

  return movimentation;
};

const addMovimentation = async (
  movimentation: IMovimentation
): Promise<IMovimentation | string> => {
  try {
    const addMovimentationApiUrl = `${apiUrl}/${movimentationPrefix}`;

    const requestReturn = await axios.post(
      addMovimentationApiUrl,
      movimentation
    );

    if (requestReturn.status != 200) {
      return requestReturn.data.errors[0];
    }

    return formatMovimentation(requestReturn.data.data);
  } catch (error) {
    console.log(error);
    return "Algo deu errado. Tente novamente mais tarde!";
  }
};

const getMovimentation = async (
  userId: UUID,
  from: Date,
  to: Date
): Promise<IMovimentation[] | string> => {
  try {
    const formattedFrom = format(from, "yyyy-MM-dd") + "T" + format(from, "HH:mm:ss");
    const formattedTo = format(to, "yyyy-MM-dd") + "T" + format(to, "HH:mm:ss");

    const getMovimentationsUrl = `${apiUrl}/${movimentationPrefix}/${userId}?from=${formattedFrom}&to=${formattedTo}`;

    const requestReturn = await axios.get(getMovimentationsUrl);

    return formatMovimentations(requestReturn.data.data);
  } catch (error: any) {
    if (error.code == "ERR_BAD_REQUEST") {
      if (
        error?.response?.data?.errors != null &&
        (error.response.data.errors as string[]).length > 0
      )
        return error.response.data.errors[0];
      else return "Algo deu errado. Tente novamente mais tarde!";
    }

    return "Algo deu errado. Tente novamente mais tarde!";
  }
};

export { addMovimentation, getMovimentation };
