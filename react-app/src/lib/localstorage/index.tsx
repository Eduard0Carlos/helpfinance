import IUser from "lib/interfaces/IUser";
import { useState, useEffect } from "react";

function useLocalStorage(key:string, initialValue:string): [value: string | null, setValue: React.Dispatch<React.SetStateAction<string | null>>] {
  // Get the initial value from localStorage or the default value
  const [value, setValue] = useState<string | null>(() => {
    const storedValue = localStorage.getItem(key);
    return storedValue ? JSON.parse(storedValue) : initialValue;
  });

  // Update localStorage whenever the value changes
  useEffect(() => {
    localStorage.setItem(key, JSON.stringify(value));
  }, [key, value]);

  return [value, setValue];
}

function saveLoggedUser(user: IUser) {
  localStorage.setItem("loggedUserInfo", JSON.stringify(user));
}

function getLoggedUser(): IUser | null {
  if (localStorage.getItem("loggedUserInfo"))
    return JSON.parse(localStorage.getItem("loggedUserInfo")!);

  return null;
}

export { saveLoggedUser, getLoggedUser };

export default useLocalStorage;