import { useState } from "react";
import { VisibilityOffIcon, VisibilityOnIcon } from "../../lib/assets";
import "./styles.scss";

interface IMoneyBoxProps {
  amount: number
}

const MoneyBox = (props: IMoneyBoxProps) => {

  const [isVisible, setIsVisible] = useState(false);

  const toggleVisible = () => setIsVisible(!isVisible);

  let notVisibleText = "";

  function formatMoney(number: number) {
    return number.toLocaleString("pt-BR", { style: "currency", currency: "BRL" });
  }

  for (let index = 0; index < formatMoney(props.amount).length; index++) {
    notVisibleText += "*";
  }

  return (
    <div className="money-box">
      {isVisible ? <VisibilityOnIcon className="eye-icon" onClick={toggleVisible} /> : <VisibilityOffIcon onClick={toggleVisible} className="eye-icon" />}
      <h1 className="money-box-balance">{isVisible ? formatMoney(props.amount) : notVisibleText}</h1>
    </div>
  );
};

export default MoneyBox;