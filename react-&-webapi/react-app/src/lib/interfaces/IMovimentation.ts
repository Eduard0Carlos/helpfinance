import { UUID } from "crypto";
import IMovCategoryInfo from "./IMovCategoryInfo";

interface IMovimentation {
  id: UUID,
  userId: UUID,
  title: string,
  amount: number,
  category: string,
  movType: string,
  date: Date,
  categoryInfo?: IMovCategoryInfo
}

export default IMovimentation;