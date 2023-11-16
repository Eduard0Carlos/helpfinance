import { isDate, parseISO } from "date-fns";
import IGrouping from "lib/interfaces/IGroupping";

declare global {
  interface Array<T> {
    distinct<U>(predicate: (value: T) => U): T[];
    groupBy<U>(predicate: (value: T) => U): IGrouping<U, T>[];
    sum(predicate: (value: T) => number): number;
    orderBy<U>(predicate: (value: T) => U): T[];
    orderByDescending<U>(predicate: (value: T) => U): T[];
    any(predicate?: (value: T) => boolean): boolean;
  }
}

if (!Array.prototype.distinct) {
  Array.prototype.distinct = function <T extends object, U>(
    this: Array<T>,
    predicate: (value: T) => keyof U
  ) {
    const data = new Map();

    for (const obj of this) {
      data.delete(obj);
      data.set(predicate(obj), obj);
    }

    return [...data.values()];
  };
}

if (!Array.prototype.groupBy) {
  Array.prototype.groupBy = function <T, U>(
    this: Array<T>,
    predicate: (value: T) => U
  ) {
    if (this.length <= 0) return [];

    const groupsBy: IGrouping<U, T>[] = [];

    for (const obj of this) {
      if (groupsBy.find((x) => x.key == predicate(obj))) continue;

      groupsBy.push({
        key: predicate(obj),
        values: this.filter((z) => predicate(obj) == predicate(z)),
      });
    }

    return groupsBy;
  };
}

if (!Array.prototype.sum) {
  Array.prototype.sum = function <T>(
    this: Array<T>,
    predicate: (value: T) => number
  ) {
    if (this.length <= 0) return 0;

    let total = 0;

    for (const obj of this) total += predicate(obj);

    return total;
  };
}

if (!Array.prototype.orderBy) {
  Array.prototype.orderBy = function <T extends object, U>(
    this: Array<T>,
    predicate: (value: T) => keyof U
  ) {
    if (this.length <= 0) return this;

    if (isDate(predicate(this[0]))) {
      return this.toSorted((a, b) => parseISO(predicate(a).toString()).getTime() - parseISO(predicate(b).toString()).getTime());
    }

    if (isNaN(Number(predicate(this[0])))) {
      return this.toSorted((a, b) =>
        predicate(a).toString().localeCompare(predicate(b).toString(), "pt-BR")
      );
    }

    return this.toSorted((a, b) => Number(predicate(a)) - Number(predicate(b)));
  };
}

if (!Array.prototype.orderByDescending) {
  Array.prototype.orderByDescending = function <T extends object, U>(
    this: Array<T>,
    predicate: (value: T) => keyof U
  ) {
    if (this.length <= 0) return this;

    if (isNaN(Number(predicate(this[0])))) {
      return this.toSorted((a, b) =>
        predicate(b).toString().localeCompare(predicate(a).toString(), "pt-BR")
      );
    }

    return this.toSorted((a, b) => Number(predicate(b)) - Number(predicate(a)));
  };
}

if (!Array.prototype.any) {
  Array.prototype.any = function <T>(
    this: Array<T>,
    predicate?: (value: T) => boolean
  ) {
    if (this.length <= 0) return false;

    if (predicate) return this.filter((x) => predicate(x)).length > 0;

    return this.length > 0;
  };
}

export {};
