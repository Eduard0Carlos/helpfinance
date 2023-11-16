import { UUID } from "crypto";
import "./styles.scss";
import { TrashIcon } from "lucide-react";
import { PaymentCardNetwork } from "lib/enums";
import { AmericanExpressIcon, MastercardIcon, VisaIcon } from "lib/assets";
import { formatPaymentCardNumber, translatePaymentCardType } from "lib/utils";

interface IPaymentCardBoxProps {
  id: UUID,
  network: string,
  number: string,
  type: string,
  onDelete: (id: UUID, number: string) => void
}

const getNetworkIcon = (network: string) => {
  switch (network) {
  case PaymentCardNetwork.MASTERCARD:
    return <MastercardIcon className="icon" />;

  case PaymentCardNetwork.VISA:
    return <VisaIcon className="icon" />;

  case PaymentCardNetwork.AMERICAN_EXPRESS:
    return <AmericanExpressIcon className="icon" />;

  default:
    return <></>;
  }
};

const PaymentCardBox = (props: IPaymentCardBoxProps) => {
  return (
    <div className="paymentcard-box-grid">
      <div className="paymentcard-box">
        <div className="left">
          {getNetworkIcon(props.network)}
          <h1>{formatPaymentCardNumber(props.number)}</h1>
        </div>
        <h1 className="paymentcard-value">{translatePaymentCardType(props.type)}</h1>
      </div>
      <div className="delete-box" onClick={() => props.onDelete(props.id, props.number)}>
        <TrashIcon onClick={() => props.onDelete(props.id, props.number)} />
      </div>
    </div>
  );
};

export default PaymentCardBox;