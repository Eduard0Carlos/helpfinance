import { UUID } from "crypto";
import IMovCategoryInfo from "./IMovCategoryInfo";

interface IMovimentation {
  userId: UUID,
  title: string,
  amount: number,
  category: string,
  movType: string,
  date: Date,
  categoryInfo?: IMovCategoryInfo
}

export default IMovimentation;