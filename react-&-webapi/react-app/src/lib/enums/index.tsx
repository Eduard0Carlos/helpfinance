const MovimentationType = Object.freeze({
  INCOMING: "INCOMING",
  OUTGOING: "OUTGOING"
});

const MovimentationCategory = Object.freeze({
  FOOD: "FOOD",
  BILL: "BILL",
  RECREATION: "RECREATION"
});

const PaymentCardNetwork = Object.freeze({
  MASTERCARD: "MASTERCARD",
  VISA: "VISA",
  AMERICAN_EXPRESS: "AMERICAN_EXPRESS",
});

const PaymentCardType = Object.freeze({
  DEBIT: "DEBIT",
  CREDIT: "CREDIT"
});

export { MovimentationType, MovimentationCategory, PaymentCardNetwork, PaymentCardType };