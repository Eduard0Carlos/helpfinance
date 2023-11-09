interface IInput {
  id: number,
  name: string,
  placeholder: string,
  type: string,
  noSpecialCaracters?: boolean,
  noEmpty?: boolean,
  noLetters?: boolean,
  charsAllowed?: string,
  isEmail?: boolean,
  isPassword?: boolean,
  maxLength?: number,
  minLength?: number
  value?: string,
  errorMessage?: string,
  hasError?: boolean,
  width?: string,
  onChange?: (input: IInput) => void
}

export default IInput;