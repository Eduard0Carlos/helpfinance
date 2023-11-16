import "./styles.scss";
import { Container, Overlay, OverlayContainer, OverlayContentLeft, OverlayContentRight } from "./components";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Md5 } from "ts-md5";

import SignUpMainPage from "./signup/page-one";

import SignUpAddressPage from "./signup/page-two";
import SignUpJobPage from "./signup/page-three";
import SignUpPasswordPage from "./signup/page-four";
import SignInPage from "./signin";
import IInput from "lib/interfaces/IInput";
import IUser from "lib/interfaces/IUser";
import { add as addNewUser } from "lib/services/UserService";
import { getLoggedUser, saveLoggedUser } from "lib/localstorage";
import { Logo } from "lib/assets";

const LoginPage = () => {

  const navigate = useNavigate();
  const [isRegister, toggle] = useState(false);

  const [isAddressPageInUse, toggleAddressPage] = useState(false);
  const [isAddressPageComplete, completeAddress] = useState(false);

  const [isJobPageInUse, toggleJobPage] = useState(false);
  const [isJobPageComplete, completeJobPage] = useState(false);

  const [isPasswordPageInUse, togglePasswordPage] = useState(false);

  const [inputs, setInputs] = useState<IInput[]>([]);

  const [isLogged, setIsLogged] = useState<boolean>(false);

  useEffect(() => {
    if (getLoggedUser())
      navigate("/dashboard/loading");
  }, []);

  const createNewAccount = async (event: IInput[]) => {

    const allInputs = inputs.concat(event);

    const emailField = allInputs.find(x => x.name == "email");
    const nameField = allInputs.find(x => x.name == "name")!;
    const birthDateField = allInputs.find(x => x.name == "birthdate");
    const streetField = allInputs.find(x => x.name == "street");
    const numberField = allInputs.find(x => x.name == "number");
    const countryField = allInputs.find(x => x.name == "country");
    const ufField = allInputs.find(x => x.name == "uf");
    const districtField = allInputs.find(x => x.name == "district");
    const cepField = allInputs.find(x => x.name == "cep");
    const cityField = allInputs.find(x => x.name == "city");
    const companyNameField = allInputs.find(x => x.name == "enterprise");
    const roleField = allInputs.find(x => x.name == "role");
    const netSallaryField = allInputs.find(x => x.name == "net_sallary");
    const passwordField = allInputs.find(x => x.name == "password");

    const hashedPassword = Md5.hashStr(passwordField!.value!);

    const newUser: IUser = {
      name: nameField.value!,
      birthdate: new Date(birthDateField!.value!),
      email: emailField!.value!,
      password: hashedPassword,
      balance: 0,
      monthlySpendingsLimit: 300,
      address: {
        cep: cepField!.value!,
        city: cityField!.value!,
        country: countryField!.value!,
        houseNumber: numberField!.value!,
        state: ufField!.value!,
        street: streetField!.value!,
        district: districtField!.value!
      }
    };

    if (companyNameField?.value && roleField?.value && netSallaryField?.value)
      newUser.job = {
        companyName: companyNameField!.value!,
        netSallary: +netSallaryField!.value!,
        title: roleField!.value!
      };

    addNewUser(newUser).then(x => {
      if (typeof (x) == typeof (newUser))
      {
        saveLoggedUser(x as IUser);
        setIsLogged(true);
      }
    });
  };

  useEffect(() => {
    if (isLogged)
      setTimeout(() => { navigate("/dashboard/loading"); }, 700);
  }, [isLogged]);

  return (
    <div className="login-page">
      <div className={"transition " + (isLogged ? "active" : "")} />
      <Container>
        <SignUpMainPage isInUse={isRegister} isComplete={isAddressPageInUse} onComplete={(event) => { setInputs([...inputs, ...event]); toggleAddressPage(true); }} />

        <SignUpAddressPage isInUse={isAddressPageInUse} isComplete={isAddressPageComplete} onComplete={(event) => { setInputs([...inputs, ...event]); completeAddress(true); toggleJobPage(true); }} back={() => toggleAddressPage(false)} />

        <SignUpJobPage isInUse={isJobPageInUse} isComplete={isJobPageComplete} onComplete={(event) => { setInputs([...inputs, ...event]); completeJobPage(true); togglePasswordPage(true); }} back={() => { completeAddress(false); toggleJobPage(false); }} />

        <SignUpPasswordPage isInUse={isPasswordPageInUse} onComplete={async (event) => { await createNewAccount(event); }} back={() => { completeJobPage(false); togglePasswordPage(false); }} />

        <SignInPage isInUse={isRegister} onLogged={() => { setIsLogged(true); }} />

        <OverlayContainer isRegister={isRegister}>
          <Overlay isRegister={isRegister}>

            <OverlayContentLeft isRegister={isRegister}>
              <Logo className="logo" />
              <section className="texts">
                <h1>Bem vindo</h1>
                <p>Preencha seus detalhes pessoais e <br />comece a controlar suas finanças ainda hoje!</p>
              </section>
              <button className=".ghost-button" onClick={() => toggle(!isRegister)}>
                Entrar
              </button>
            </OverlayContentLeft>

            <OverlayContentRight isRegister={isRegister}>
              <Logo className="logo" />
              <section className="texts">
                <h1>Bem vindo novamente</h1>
                <p>Para continuar conectado conosco, <br />entre com suas informações pessoais</p>
              </section>
              <button className=".ghost-button" onClick={() => toggle(!isRegister)}>
                Criar Conta
              </button>
            </OverlayContentRight>
          </Overlay>
        </OverlayContainer>

      </Container>
    </div>
  );
};

export default LoginPage;