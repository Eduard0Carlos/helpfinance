import { DownIcon, UpIcon } from "lib/assets";
import { formatMoney } from "lib/utils";
import "./styles.scss";

interface IValueInfoBox {
  value: number,
  type: "up" | "down"
}

const ValueInfoBox = (props: IValueInfoBox) => {
  return (
    <div className={`value-info-box ${props.type == "up" ? "up" : "down"}`}>
      {props.type == "up" ? <UpIcon className="up"/> : <DownIcon className="down"/>}
      <h1>{formatMoney(props.value)}</h1>
    </div>
  );
};

export default ValueInfoBox;