import "./styles.scss";

interface ITextBoxProps {
  className?: string,
  children?: React.ReactNode,
  type?: string,
  placeholder?: string,
  value?: string | number | undefined,
  hasError?: boolean,
  errorMessage?: string,
  maxLength?: number,
  width?: string,
  onChange?: (event: React.ChangeEvent<HTMLInputElement>) => void,
  onFocus?: (event: React.FocusEvent<HTMLInputElement, Element>) => void
}

const TextBox: React.FunctionComponent<ITextBoxProps> = props => (

  <div className={"textbox" + (props.hasError ? " error" : "")} style={props.width ? {width: props.width } : {}}>
    <input
      type={props.type}
      placeholder={props.placeholder}
      value={props.value}
      onChange={props.onChange}
      maxLength={props.maxLength}
      onFocus={props.onFocus}
    >
      {props.children}
    </input>
    {props.hasError ? <p>{props.errorMessage}</p> : <></>}
  </div>
);

export default TextBox;