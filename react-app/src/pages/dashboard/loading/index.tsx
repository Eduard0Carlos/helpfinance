import { Logo } from "lib/assets";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getLoggedUser } from "lib/localstorage";

import "./styles.scss";

const DashboardLoadingPage = () => {
  const navigate = useNavigate();

  const [isLoadComplete, setIsLoadComplete] = useState(false);

  useEffect(() => {
    if (!getLoggedUser())
      navigate("/login");

    setTimeout(() => setIsLoadComplete(true), 1000);
    
  }, []);

  useEffect(() => {
    if (isLoadComplete)
      navigate("/dashboard", { replace: true });
  }, [isLoadComplete]);

  return (
    <div className="transition active">
      <Logo className="logo" />
      <div className="texts">
        <h1>Entrando</h1>
        <div className="circles">
          <div className="circle" id="a" />
          <div className="circle" id="b" />
          <div className="circle" id="c" />
        </div>
      </div>
    </div>
  );
};

export default DashboardLoadingPage;