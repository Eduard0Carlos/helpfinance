import { Logo } from "lib/assets";
import { Link } from "react-router-dom";
import { useState } from "react";
import "./styles.scss";
import { getLoggedUser } from "lib/localstorage";

const HomePage = () => {

  const [loggedUser] = useState(getLoggedUser());

  return (
    <div className="home-page">
      <div className="header">
        <section className="logo">
          <Logo className="logo" />
        </section>
        <section className="pages">
          <Link to={"#"}><h1>Início</h1> </Link>
          <Link to={"#"}><h1>Ajuda</h1> </Link>
          <Link to={"#"}><h1>Sobre</h1> </Link>
        </section>
        {!loggedUser ?
          <Link to={"/login"}>
            <section className="login">
              <h1>Entrar</h1>
            </section>
          </Link>
          :
          <Link to={"/dashboard/loading"}>
            <section className="dashboard-button hidden">
              <h1>Dashboard</h1>
            </section>
          </Link>}
      </div>
      <div className="content">
        {/* <img className="desktop-mockup" src={desktopMockup}/> */}
      </div>
      <div className="content">
        <div className="texts">
          <h1>CONTROLE TUDO</h1>
          <h1>A QUALQUER HORA</h1>
          <h1>EM QUALQUER LUGAR</h1>
        </div>
        <Link to="/login">
          <div className="start-button">{!loggedUser ? "Comece Agora" : "Ir para o Dashboard"}</div>
        </Link>
      </div>
    </div>
  );
};


export default HomePage;