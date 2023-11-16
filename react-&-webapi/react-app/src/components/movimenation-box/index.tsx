import { UUID } from "crypto";
import "./styles.scss";
import { UpIcon, DownIcon } from "lib/assets";
import { MovimentationType } from "lib/enums";
import { formatMoney } from "lib/utils";
import { TrashIcon } from "lucide-react";

interface IMovimentationBoxProps {
  title: string,
  value: number,
  type: string,
  id: UUID
  onDelete: (id: UUID, title: string) => void
}

const MovimentationBox = (props: IMovimentationBoxProps) => {
  return (
    <div className="movimentation-box-grid">
      <div className={"movimentation-box " + (props.type == MovimentationType.INCOMING ? "incoming" : "outgoing")}>
        <div className="left">
          {props.type == MovimentationType.INCOMING ? <UpIcon className="icon" /> : <DownIcon className="icon" />}
          <h1>{props.title}</h1>
        </div>
        <h1 className="movimentation-value">{props.type == MovimentationType.INCOMING ? "+" : "-"}{formatMoney(props.value)}</h1>
      </div>
      <div className="delete-box" onClick={() => props.onDelete(props.id, props.title)}>
        <TrashIcon onClick={() => props.onDelete(props.id, props.title)} />
      </div>
    </div>
  );
};

export default MovimentationBox;