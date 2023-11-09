import TextBox from "components/textbox";
import IInput from "lib/interfaces/IInput";
import axios from "axios";

import { validateField } from "lib/utils";
import { SignUpPage } from "pages/login/components";
import { useState } from "react";
import ICepInfo from "lib/interfaces/ICepInfo";

interface ISignUpAddressPageProps {
  isInUse: boolean,
  isComplete: boolean,
  onComplete: (values: IInput[]) => void,
  back: () => void
}

const SignUpAddressPage: React.FunctionComponent<ISignUpAddressPageProps> = props => {

  const [firstLineInputs] = useState<IInput[]>([
    {
      id: 1,
      name: "country",
      placeholder: "País",
      type: "text",
      noEmpty: true,
      noSpecialCaracters: true,
      charsAllowed: " ",
      minLength: 5
    },
    {
      id: 2,
      name: "uf",
      placeholder: "UF",
      type: "text",
      noEmpty: true,
      noSpecialCaracters: true,
      charsAllowed: " ",
      minLength: 2,
      maxLength: 3
    }
  ]);

  const [secondLineInputs] = useState<IInput[]>([
    {
      id: 3,
      name: "city",
      placeholder: "Cidade",
      type: "text",
      noEmpty: true,
      noSpecialCaracters: true,
      charsAllowed: " ",
      minLength: 3,
      width: "200%"
    },
    {
      id: 4,
      name: "cep",
      placeholder: "CEP",
      type: "text",
      noEmpty: true,
      noSpecialCaracters: true,
      charsAllowed: " ",
      minLength: 8,
      maxLength: 9,
      onChange: (input) => onCepChange(input)
    }
  ]);

  const [thirdLineInputs] = useState<IInput[]>([
    {
      id: 5,
      name: "district",
      placeholder: "Bairro",
      type: "text",
      noEmpty: true,
      noSpecialCaracters: true,
      charsAllowed: " ",
      minLength: 5,
      width: "250%"
    },
    {
      id: 6,
      name: "number",
      placeholder: "Número",
      type: "number",
      noEmpty: true,
      noSpecialCaracters: true,
      charsAllowed: " ",
      minLength: 1
    }
  ]);

  const [forthinLineInputs] = useState<IInput[]>([
    {
      id: 7,
      name: "street",
      placeholder: "Rua",
      type: "text",
      noEmpty: true,
      noSpecialCaracters: true,
      charsAllowed: " ",
      minLength: 5
    }
  ]);

  const [inputs, setInputs] = useState([...firstLineInputs, ...secondLineInputs, ...thirdLineInputs, ...forthinLineInputs]);

  const refreshInputs = () => setInputs([...firstLineInputs, ...secondLineInputs, ...thirdLineInputs, ...forthinLineInputs]);

  const removeError = (input: IInput) => {
    input.hasError = false;
    refreshInputs();
  };

  const onCepChange = async (input: IInput) => {
    const formatedText = input?.value?.replace("-", "")?.trim();

    if (!formatedText || formatedText.length < 8)
      return;

    try {
      const apiCepInfo: ICepInfo = (await axios.get(`https://viacep.com.br/ws/${formatedText}/json/`)).data;

      if (!apiCepInfo) return;

      firstLineInputs.find(x => x.name == "country")!.value = "Brasil";
      firstLineInputs.find(x => x.name == "uf")!.value = apiCepInfo.uf;
      secondLineInputs.find(x => x.name == "city")!.value = apiCepInfo.localidade;
      secondLineInputs.find(x => x.name == "cep")!.value = formatedText;
      thirdLineInputs.find(x => x.name == "district")!.value = apiCepInfo.bairro;
      forthinLineInputs.find(x => x.name == "street")!.value = apiCepInfo.logradouro;

      refreshInputs();

    } catch (error) {
      return;
    }
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
          <h1>Endereço</h1>
          <p>2/4</p>
        </section>
        <section className="inputs">
          <div className="union">
            {firstLineInputs.map(input => <TextBox key={input.id} type={input.type} width={input.width} value={input.value} placeholder={input.placeholder} maxLength={input.maxLength} hasError={input.hasError} onChange={(e) => {input.value = e.target.value; refreshInputs(); if(input.onChange) input.onChange(input);}} errorMessage={input.errorMessage} onFocus={() => removeError(input)} />)}
          </div>
          <div className="union">
            {secondLineInputs.map(input => <TextBox key={input.id} type={input.type} width={input.width} value={input.value} placeholder={input.placeholder} maxLength={input.maxLength} hasError={input.hasError} onChange={(e) => {input.value = e.target.value; refreshInputs(); if(input.onChange) input.onChange(input);}} errorMessage={input.errorMessage} onFocus={() => removeError(input)} />)}
          </div>
          <div className="union">
            {thirdLineInputs.map(input => <TextBox key={input.id} type={input.type} width={input.width} placeholder={input.placeholder} maxLength={input.maxLength} hasError={input.hasError} value={input.value} onChange={(e) => {input.value = e.target.value; refreshInputs(); if(input.onChange) input.onChange(input);}} errorMessage={input.errorMessage} onFocus={() => removeError(input)} />)}
          </div>
          {forthinLineInputs.map(input => <TextBox key={input.id} type={input.type} placeholder={input.placeholder} value={input.value} maxLength={input.maxLength} hasError={input.hasError} onChange={(e) => {input.value = e.target.value; refreshInputs(); if(input.onChange) input.onChange(input);}} errorMessage={input.errorMessage} onFocus={() => removeError(input)} />)}
        </section>
        <section className="buttons">
          <div className="back" onClick={props.back}>Voltar</div>
          <button className="forward">Continuar</button>
        </section>
      </form>
    </SignUpPage>
  );
};

export default SignUpAddressPage;