import { DownIcon, UpIcon } from "lib/assets";
import { formatMoney } from "lib/utils";
import "./styles.scss";

interface IInvestmentCardBox {
  value: number,
  porcentage: number,
  type: "up" | "down" | string
  name: string,
  gridColumn?: number,
  AnimationDelay?: number
}

const InvestmentCard: React.FunctionComponent<IInvestmentCardBox> = (props: IInvestmentCardBox) => {
  return (
    <div className="investment-card" style={{ gridColumn: props.gridColumn ?? "", animationDuration: `${1 + props.AnimationDelay!}s` ?? "" }}>
      <h1 className="investment-card-title">{props.name}</h1>
      <div className="investment-card-porcentage">
        {props.type == "up" ? <UpIcon className="up" /> : <DownIcon className="down" />}
        <h1 className={props.type == "up" ? "up" : "down"}>{`${props.type == "up" ? "+" : "-"}${props.porcentage.toFixed(2)}%`}</h1>
      </div>
      <h1 className={`investment-card-value ${props.type == "up" ? "up" : "down"}`}>{formatMoney(props.value)}</h1>
    </div >
  );
};

export default InvestmentCard;