import { clsx, type ClassValue } from "clsx";
import IInput from "lib/interfaces/IInput";
import { twMerge } from "tailwind-merge";

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

const specialChars = /[ `!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?~]/;

const isNullOrWhitespace = (value: string | undefined) => {
  return (value == null || value == undefined || value == "" || value.trim() == "");
};

const validateField = (input: IInput) => {
  let allspecialschars = "";
  const numbersValids = /[0123456789/]/g;

  for (let index = 0; index < specialChars.source.length; index++) {
    const element = specialChars.source[index];

    if (!input.charsAllowed || !input.charsAllowed.includes(element))
      allspecialschars += element;
  }

  const specialCharsRegex = new RegExp(allspecialschars);

  if (input.noEmpty && isNullOrWhitespace(input.value)) {
    input.hasError = true;
    input.errorMessage = "Não pode estar vazio!";

    return false;
  }

  if (input.noLetters && input.value && Array.from(input.value.matchAll(numbersValids), m => m[0]).length != input.value.length) {
    input.hasError = true;
    input.errorMessage = "Apenas números" + (input.charsAllowed ? " ou " + input.charsAllowed : "");

    return false;
  }

  if (input.minLength && (!input.value || input.value.length < input.minLength)) {
    input.hasError = true;
    input.errorMessage = "Deve conter no mínimo " + input.minLength + " caracteres";
    return false;
  }

  if (input.isEmail && input.value && (!input.value.includes("@") || !input.value.includes(".com"))) {
    input.hasError = true;
    input.errorMessage = "Informe um email válido";
    return false;
  }

  if (input.noSpecialCaracters && input.value && specialCharsRegex.test(input.value)) {
    input.hasError = true;
    input.errorMessage = "Não pode conter caracteres especiais";
    return false;
  }

  return true;
};

export {
  validateField,
  isNullOrWhitespace,
  specialChars
};