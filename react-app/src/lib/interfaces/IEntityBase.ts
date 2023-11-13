import { UUID } from "crypto";

interface IEntityBase {
  id?: UUID,
  active?: boolean,
  createdAt?: Date,
  updatedAt?: Date
}

export default IEntityBase;