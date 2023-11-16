import IAddress from "./IAddress";
import IEntityBase from "./IEntityBase";
import IJob from "./IJob";

interface IUser extends IEntityBase{
  email: string,
  password: string,
  name: string,
  birthdate: Date,
  address?: IAddress,
  job?: IJob,
  balance: number,
  monthlySpendingsLimit: number
}

export default IUser;