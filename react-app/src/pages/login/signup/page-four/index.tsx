import TextBox from "components/textbox";
import IInput from "lib/interfaces/IInput";

import { SignUpPage } from "pages/login/components";
import { useState } from "react";
import { specialChars, validateField } from "lib/utils";

interface ISignUpPasswordPageProps {
  isInUse: boolean,
  isComplete?: boolean,
  onComplete: (values: IInput[]) => void,
  back: () => void
}

const SignUpPasswordPage: React.FunctionComponent<ISignUpPasswordPageProps> = props => {

  const [inputs, setInputs] = useState<IInput[]>([
    {
      id: 1,
      name: "password",
      placeholder: "Senha",
      type: "password",
      noEmpty: true,
      minLength: 6
    },
    {
      id: 2,
      name: "confirm-password",
      placeholder: "Confirme sua senha",
      type: "password",
      noEmpty: true,
      minLength: 6
    }
  ]);

  const refreshInputs = () => setInputs([...inputs]);

  const removeAllErrors = () => {
    for (let index = 0; index < inputs.length; index++) {
      inputs[index].hasError = false;
    }

    refreshInputs();
  };

  const validatePassword = (password: IInput, confirmedPassword: IInput) => {
    if (password.value != confirmedPassword.value) {
      password.hasError = true;
      password.errorMessage = "";
      confirmedPassword.hasError = true;
      confirmedPassword.errorMessage = "As senhas não conhecidem";

      return false;
    }

    if (!/[0-9]/.test(password.value!)) {
      password.hasError = true;
      password.errorMessage = "";
      confirmedPassword.hasError = true;
      confirmedPassword.errorMessage = "A senha deve ter ao menos um número";

      return false;
    }

    if (!/[A-Za-z]/.test(password.value!)) {
      password.hasError = true;
      password.errorMessage = "";
      confirmedPassword.hasError = true;
      confirmedPassword.errorMessage = "A senha deve ter ao menos uma letra";

      return false;
    }

    if (!/[A-Z]/.test(password.value!)) {
      password.hasError = true;
      password.errorMessage = "";
      confirmedPassword.hasError = true;
      confirmedPassword.errorMessage = "A senha deve ter ao menos uma letra maíuscula";

      return false;
    }

    if (!specialChars.test(password.value!)) {
      password.hasError = true;
      password.errorMessage = "";
      confirmedPassword.hasError = true;
      confirmedPassword.errorMessage = "A senha deve ter ao menos um caracter especial";

      return false;
    }

    return true;
  };

  const submitForm = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    refreshInputs();

    let hasError = false;

    for (let index = 0; index < inputs.length; index++) {
      const element = inputs[index];

      if (!validateField(element)) hasError = true;
    }

    const password = inputs[0];
    const confirmedPassword = inputs[1];

    if (!validatePassword(password, confirmedPassword))
      hasError = true;

    refreshInputs();

    if (hasError) return;

    props.onComplete([password]);
  };

  return (
    <SignUpPage isInUse={props.isInUse} isComplete={props.isComplete}>
      <form onSubmit={submitForm}>
        <section className="title">
          <h1>Senha</h1>
          <p>4/4</p>
          <p>Sua senha deve conter:</p>
          <div className="divider">
            <hr />
          </div>
          <ul>
            <li>Ao menos um caracter especial</li>
            <li>Ao menos uma letra</li>
            <li>Ao menos um número</li>
          </ul>
          <div className="divider">
            <hr />
          </div>
        </section>
        <section className="inputs">
          {inputs.map(input => <TextBox key={input.id} type={input.type} width={input.width} value={input.value} placeholder={input.placeholder} maxLength={input.maxLength} hasError={input.hasError} onChange={(e) => { input.value = e.target.value; refreshInputs(); if (input.onChange) input.onChange(input); }} errorMessage={input.errorMessage} onFocus={() => removeAllErrors()} />)}
        </section>
        <section className="buttons">
          <div className="back" onClick={props.back}>Voltar</div>
          <button className="forward">Criar Conta</button>
        </section>
      </form>
    </SignUpPage>
  );
};

export default SignUpPasswordPage;