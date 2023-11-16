interface IGrouping<TKey, TValue> {
  key: TKey
  values: Array<TValue>
}

export default IGrouping;