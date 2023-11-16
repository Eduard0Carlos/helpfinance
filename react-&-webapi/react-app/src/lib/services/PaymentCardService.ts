import axios from "axios";
import { apiUrl } from "./ApiService";
import { UUID } from "crypto";
import { IPaymentCard } from "lib/interfaces/IPaymentCard";

const cardPrefix = "v1/card";

const formatPaymentCards = (cards: IPaymentCard[]): any => {
  cards.forEach((x) => {
    x.expirationDate = new Date(x.expirationDate);
  });

  return cards;
};

const formatPaymentCard = (card: IPaymentCard): any => {
  card.expirationDate = new Date(card.expirationDate);

  return card;
};

const addCard = async (
  card: IPaymentCard
): Promise<IPaymentCard | string> => {
  try {
    const addCardApiUrl = `${apiUrl}/${cardPrefix}`;

    const requestReturn = await axios.post(
      addCardApiUrl,
      card
    );

    if (requestReturn.status != 200) {
      return requestReturn.data.errors[0];
    }

    return formatPaymentCard(requestReturn.data.data);
  } catch (error) {
    console.log(error);
    return "Algo deu errado. Tente novamente mais tarde!";
  }
};

const deletePaymentCard = async (id: UUID): Promise<string> => {
  try {
    const deleteMovimentationApiUrl = `${apiUrl}/${cardPrefix}/${id}`;

    const requestReturn = await axios.delete(deleteMovimentationApiUrl);

    if (requestReturn.status != 200) {
      return requestReturn.data.errors[0];
    }

    return "";
  } catch (error) {
    console.log(error);
    return "Algo deu errado. Tente novamente mais tarde!";
  }
};

const getAllCards = async (
  userId: UUID,
): Promise<IPaymentCard[] | string> => {
  try {
    const getCardsUrl = `${apiUrl}/${cardPrefix}/${userId}`;

    const requestReturn = await axios.get(getCardsUrl);

    return formatPaymentCards(requestReturn.data.data);
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

export { addCard, getAllCards, deletePaymentCard };
