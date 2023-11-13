import { LogoIcon, DashboardIcon, DocumentIcon, GraphIcon, ProfileIcon, NotificationIcon } from "lib/assets";
import { Link, Outlet } from "react-router-dom";
import { useState } from "react";

import "./styles.scss";
import DashboardLoadingPage from "./loading";
import MoneyBox from "components/money-box";

interface IIcon {
  name: string,
  icon: React.ReactNode,
  title: string,
  to: string
}

const DashboardNavBar = () => {

  const [icons] = useState<IIcon[]>([
    {
      name: "dashboard",
      icon: <DashboardIcon />,
      title: "Dashboard",
      to: "/dashboard"
    },
    {
      name: "document",
      icon: <DocumentIcon />,
      title: "Movimentações",
      to: "movimentations"
    },
    {
      name: "graph",
      icon: <GraphIcon />,
      title: "Investimentos",
      to: "investments"
    },
    {
      name: "profile",
      icon: <ProfileIcon />,
      title: "Perfil",
      to: "profile"
    },
  ]);

  const [currentActive, setCurrentActive] = useState<IIcon>(icons[0]);

  const iconClicked = (icon: IIcon) => {

    setCurrentActive(icon);
  };

  return (
    <div className="dashboard">
      <DashboardLoadingPage />
      <div className="screen">
        <div className="left-navbar">
          <Link className="logo" to="/">
            <LogoIcon />
          </Link>
          {icons.map(x => <Link to={x.to} key={x.name} className={currentActive == x ? "active" : ""} onClick={() => iconClicked(x)}>{x.icon}</Link>)}
        </div>
        <div className="container">
          <div className="header">
            <section className="page-title">
              <h1>{currentActive.title}</h1>
            </section>
            <section>
              <div className="profile">
                <NotificationIcon className="notification-icon" />
                <MoneyBox amount={2000} />
                <img src="https://ui-avatars.com/api/?name=Carlos+Eduardo+Vieira&background=random" />
              </div>
            </section>
          </div>
          <Outlet />
        </div>
      </div>
    </div>
  );
};

export default DashboardNavBar;