interface IApiReturn<T> {
    success: boolean,
    data: T,
    errors: string[]
}

export default IApiReturn;