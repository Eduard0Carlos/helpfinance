import { UUID } from "crypto";

export interface IPaymentCard {
  id: UUID,
  userId: UUID,
  cardNumber: string,
  nickname: string,
  expirationDate: Date,
  paymentNetwork: string,
  paymentType: string
}