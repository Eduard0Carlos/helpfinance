import TextBox from "components/textbox";
import IInput from "lib/interfaces/IInput";

import { SignUpPage } from "pages/login/components";
import { useState } from "react";
import { validateField } from "lib/utils";

interface ISignUpJobPageProps {
  isInUse: boolean,
  isComplete?: boolean,
  onComplete: (values: IInput[]) => void,
  back: () => void
}

const SignUpJobPage: React.FunctionComponent<ISignUpJobPageProps> = props => {

  const [inputs, setInputs] = useState<IInput[]>([
    {
      id: 1,
      name: "enterprise",
      placeholder: "Nome da Empresa",
      type: "text",
      noSpecialCaracters: true,
      charsAllowed: "- "
    },
    {
      id: 2,
      name: "role",
      placeholder: "Cargo Atual",
      type: "text",
      noSpecialCaracters: true,
      charsAllowed: "- "
    },
    {
      id: 3,
      name: "net_sallary",
      placeholder: "Salário Liquido",
      type: "number",
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
    <SignUpPage isInUse={props.isInUse} isComplete={props.isComplete}>
      <form onSubmit={submitForm}>
        <section className="title">
          <h1>Trabalho</h1>
          <p>3/4</p>
          <p>Opcional - Caso não possua, basta criar a conta</p>
        </section>
        <section className="inputs">
          {inputs.map(input => <TextBox key={input.id} type={input.type} width={input.width} value={input.value} placeholder={input.placeholder} maxLength={input.maxLength} hasError={input.hasError} onChange={(e) => { input.value = e.target.value; refreshInputs(); if (input.onChange) input.onChange(input); }} errorMessage={input.errorMessage} onFocus={() => removeError(input)} />)}
        </section>
        <section className="buttons">
          <div className="back" onClick={props.back}>Voltar</div>
          <button className="forward">Continuar</button>
        </section>
      </form>
    </SignUpPage>
  );
};

export default SignUpJobPage;