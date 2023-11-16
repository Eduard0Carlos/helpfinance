interface IComponentProps {
  children?: React.ReactNode,
  isRegister?: boolean
}

interface IPageProps {
  children?: React.ReactNode,
  isInUse?: boolean,
  isComplete?: boolean
}

export const Container: React.FunctionComponent<IComponentProps> = props => (
  <div className="container">
    {props.children}
  </div>
);

export const SignUpContainer: React.FunctionComponent<IPageProps> = props => (
  <div className="signup"
    style={props.isComplete ? { transform: "translateX(-100%)", opacity: 0 } : props.isInUse ? {
      transform: "translateX(100%)",
      opacity: 1,
      zIndex: 5
    } : {}}>
    {props.children}
  </div >
);

export const SignUpPage: React.FunctionComponent<IPageProps> = props => (
  <div className="signup-page"
    style={props.isComplete ? { transform: "translateX(-100%)", opacity: 0 } : props.isInUse ? {
      transform: "translateX(100%)",
      opacity: 1,
    } : {}}>
    {props.children}
  </div >
);

export const SignInContainer: React.FunctionComponent<IComponentProps> = props => (
  <div className="signin"
    style={props.isRegister ? {
      transform: "translateX(100%)",
      opacity: 0,
      zIndex: -1
    } : {}}>
    {props.children}
  </div >
);

export const Form: React.FunctionComponent<IComponentProps> = props => (
  <form onSubmit={(e) => e.preventDefault()}>
    {props.children}
  </form>
);

export const OverlayContainer: React.FunctionComponent<IComponentProps> = props => (
  <div className="overlay-container"
    style={props.isRegister ? {
      transform: "translateX(-100%)",
    } : {}}>
    {props.children}
  </div>
);

export const Overlay: React.FunctionComponent<IComponentProps> = props => (
  <div className="overlay"
    style={props.isRegister ? {
      transform: "translateX(0)",
    } : {}}>
    {props.children}
  </div>
);

export const OverlayPanel: React.FunctionComponent<IComponentProps> = props => (
  <div className="overlay-panel">
    {props.children}
  </div>
);

export const OverlayContentRight: React.FunctionComponent<IComponentProps> = props => (
  <div className="overlay-content right"
    style={props.isRegister ? {
      transform: "translateX(100%)"
    } : {}}>
    {props.children}
  </div >
);

export const OverlayContentLeft: React.FunctionComponent<IComponentProps> = props => (
  <div className="overlay-content left"
    style={props.isRegister ? {
      transform: "translateX(0)"
    } : {}}>
    {props.children}
  </div >
);