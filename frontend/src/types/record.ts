import { Category } from "./category";
import { User } from "./user";

export interface Record {
  recordId: number;
  title: string;
  hours: number;
  date: number[];
  user: User;
  category: Category;
}
