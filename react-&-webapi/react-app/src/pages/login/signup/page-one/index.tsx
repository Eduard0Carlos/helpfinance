import TextBox from "components/textbox";
import IInput from "lib/interfaces/IInput";

import { SignUpContainer } from "pages/login/components";
import { useState } from "react";
import { appleIcon, facebookIcon, googleIcon } from "lib/assets";
import { validateField } from "lib/utils";

interface ISignUpMainPageProps {
  isInUse: boolean,
  isComplete: boolean,
  onComplete: (values: IInput[]) => void
}

const SignUpMainPage: React.FunctionComponent<ISignUpMainPageProps> = props => {

  const [inputs, setInputs] = useState<IInput[]>([
    {
      id: 1,
      name: "email",
      placeholder: "Email",
      type: "text",
      hasError: false,
      value: "",
      errorMessage: "",
      noEmpty: true,
      noSpecialCaracters: true,
      charsAllowed: "@.",
      isEmail: true,
      minLength: 5
    },
    {
      id: 2,
      name: "name",
      placeholder: "Nome",
      type: "text",
      hasError: false,
      errorMessage: "",
      value: "",
      noEmpty: true,
      noSpecialCaracters: true,
      charsAllowed: " ",
      minLength: 3
    },
    {
      id: 3,
      name: "birthdate",
      placeholder: "Data de Nascimento - dd/mm/yyyy",
      type: "text",
      noEmpty: true,
      noLetters: true,
      noSpecialCaracters: true,
      charsAllowed: "/",
      minLength: 8,
      maxLength: 10
    }
  ]);

  const refreshInputs = () => setInputs([...inputs]);

  const removeError = (input: IInput) => {
    input.hasError = false;
    refreshInputs();
  };

  const submitForm = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    refreshInputs();

    let hasError = false;

    for (let index = 0; index < inputs.length; index++) {
      const element = inputs[index];

      if (!validateField(element)) hasError = true;
    }

    refreshInputs();

    if (hasError) return;

    props.onComplete(inputs);
  };

  return (
    <SignUpContainer isInUse={props.isInUse} isComplete={props.isComplete}>
      <form onSubmit={submitForm}>
        <h1>Criar uma Conta</h1>
        <section className="plataforms">
          <a className="platform"><img src={facebookIcon} /></a>
          <a className="platform"><img src={googleIcon} /></a>
          <a className="platform"><img src={appleIcon} /></a>
        </section>
        <div className="divider">
          <hr />
          <h3>ou</h3>
          <hr />
        </div>
        <section className="inputs">
          {inputs.map(input => <TextBox key={input.id} type={input.type} maxLength={input.maxLength} placeholder={input.placeholder} hasError={input.hasError} onChange={(e) => input.value = e.target.value} errorMessage={input.errorMessage} onFocus={() => removeError(input)} />)}
        </section>
        <button className="confirm">Continuar</button>
      </form>
    </SignUpContainer>
  );
};

export default SignUpMainPage;