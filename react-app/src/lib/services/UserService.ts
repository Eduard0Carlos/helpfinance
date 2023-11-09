import axios from "axios";
import { apiUrl } from "./ApiService";
import IUser from "lib/interfaces/IUser";

const usersPrefix = "v1/users";

const add = async (user: IUser): Promise<IUser | string> => {
  try {
    const addUserApiUrl = `${apiUrl}/${usersPrefix}`;

    console.log(addUserApiUrl);

    const requestReturn = await axios.post(addUserApiUrl, user);

    if (requestReturn.status != 200) {
      return requestReturn.data.data;
    }

    return requestReturn.data.data;
    
  } catch (error) {
    console.log(error);
    return "Algo deu errado. Tente novamente mais tarde!";
  }
};

const get = async (email: string, password: string): Promise<IUser | string> => {
  try {
    const getUserApiUrl = `${apiUrl}/${usersPrefix}?email=${email}&password=${password}`;

    console.log(getUserApiUrl);

    const requestReturn = await axios.get(getUserApiUrl);

    return requestReturn.data.data;
    
  } catch (error: any) {

    if (error.code == "ERR_BAD_REQUEST") {
      return error.response.data.errors[0];
    }

    return "Algo deu errado. Tente novamente mais tarde!";
  }
};


export { add, get };
