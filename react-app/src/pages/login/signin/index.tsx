import TextBox from "components/textbox";
import IInput from "lib/interfaces/IInput";

import { SignInContainer } from "pages/login/components";
import { useState } from "react";
import { validateField } from "lib/utils";
import { appleIcon, facebookIcon, googleIcon } from "lib/assets";
import { get as getUser } from "lib/services/UserService";
import { Md5 } from "ts-md5";
import IUser from "lib/interfaces/IUser";
import { saveLoggedUser } from "lib/localstorage";

interface ISignInPageProps {
  isInUse: boolean,
  onLogged: () => void
}

const SignInPage: React.FunctionComponent<ISignInPageProps> = props => {

  const [inputs, setInputs] = useState<IInput[]>([
    {
      id: 1,
      name: "email",
      placeholder: "Email",
      type: "text",
      noEmpty: true,
      isEmail: true,
      noSpecialCaracters: true,
      charsAllowed: "@.",
      minLength: 6
    },
    {
      id: 2,
      name: "password",
      placeholder: "Senha",
      type: "password",
      noEmpty: true,
      minLength: 6
    }
  ]);

  const refreshInputs = () => setInputs([...inputs]);

  const removeAllErrors = async () => {
    for (let index = 0; index < inputs.length; index++) {
      inputs[index].hasError = false;
    }

    refreshInputs();
  };

  const submitForm = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    refreshInputs();

    let hasError = false;

    for (let index = 0; index < inputs.length; index++) {
      const element = inputs[index];

      if (!validateField(element)) hasError = true;
    }

    refreshInputs();

    if (hasError) return;

    const email = inputs[0].value!;
    const password = Md5.hashStr(inputs[1].value!);

    getUser(email, password).then(x => {
      if (typeof(x) == typeof("")) {
        console.log(x);
        console.log("PASSEI POR AQUI");

        inputs[0].hasError = true;
        inputs[1].hasError = true;
        inputs[1].errorMessage = x as string;

        refreshInputs();
        return;
      }
      
      saveLoggedUser(x as IUser);
      props.onLogged();
    });
  };

  return (
    <SignInContainer isRegister={props.isInUse}>
      <form onSubmit={submitForm}>
        <h1>Entrar</h1>
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
          {inputs.map(input => <TextBox key={input.id} type={input.type} width={input.width} value={input.value} placeholder={input.placeholder} maxLength={input.maxLength} hasError={input.hasError} onChange={(e) => { input.value = e.target.value; refreshInputs(); if (input.onChange) input.onChange(input); }} errorMessage={input.errorMessage} onFocus={() => removeAllErrors()} />)}
        </section>
        <button className="confirm">Entrar</button>
      </form>
    </SignInContainer>
  );
};

export default SignInPage;