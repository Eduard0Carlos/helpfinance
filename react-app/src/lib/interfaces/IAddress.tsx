import { UUID } from "crypto";
import IEntityBase from "./IEntityBase";

interface IAddress extends IEntityBase {
  userId?: UUID,
  cep: string,
  houseNumber: string,
  street: string,
  city: string,
  state: string,
  country: string,
  district: string
}

export default IAddress;