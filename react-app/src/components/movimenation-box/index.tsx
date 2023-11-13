import "./styles.scss";
import { UpIcon, DownIcon } from "lib/assets";
import { MovimentationType } from "lib/enums";
import { formatMoney } from "lib/utils";

interface IMovimentationBoxProps {
  title: string,
  value: number,
  type: string
}

const MovimentationBox = (props: IMovimentationBoxProps) => {
  return (
    <div className={"movimentation-box " + (props.type == MovimentationType.INCOMING ? "incoming" : "outgoing")}>
      <div className="left">
        {props.type == MovimentationType.INCOMING ? <UpIcon className="icon" /> : <DownIcon className="icon" />}
        <h1>{props.title}</h1>
      </div>
      <h1 className="movimentation-value">{props.type == MovimentationType.INCOMING ? "+" : "-"}{formatMoney(props.value)}</h1>
    </div>
  );
};

export default MovimentationBox;