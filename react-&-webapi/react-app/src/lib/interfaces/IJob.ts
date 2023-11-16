import { UUID } from "crypto";

interface IJob {
  userId?: UUID,
  companyName: string,
  title: string,
  netSallary: number
}

export default IJob;