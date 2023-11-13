import { formatMoney } from "lib/utils";
import "./styles.scss";

const BarChartType = Object.freeze({
  BuildIn: "build-in",
  Inline: "inline"
});

interface IBarChartProps {
  value: number,
  total: number,
  name: string,
  color: string,
  type: "build-in" | "inline"
}

const BarChart = (props: IBarChartProps) => {
  const width = ((props.value / props.total) * 100).toFixed(0);

  const getStyle = () => {
    return <style>
      {`@keyframes width-animation${width} {
          0% { width: 0%; }
          100% { width: ${width}%; }
      }`}
    </style>;
  };

  const getInlineBarChart = () => (
    <div className={"bar-chart inline"}>
      <div className="bar-chart-bars">
        {getStyle()}
        <rect style={{ animationName: `width-animation${width}`, animationDuration: "2s", animationDirection: "forwards", width: `${width}%`, backgroundColor: props.color, filter: `drop-shadow(0 0 5px ${props.color})` }} className="value" />
        <rect className="background" />
      </div>
      <h1 className="porcentage">{width}% {props.type == "inline" ? props.name : ""}</h1>
    </div>
  );

  const getBuildinBarChart = () => (
    <div className={"bar-chart build-in"}>
      <div className="bar-chart-bars">
        {getStyle()}
        <rect style={{ animationName: `width-animation${width}`, animationDuration: "2s", animationDirection: "forwards", width: `${width}%`, backgroundColor: props.color, filter: `drop-shadow(0 0 5px ${props.color})` }} className="value" />
        <rect className="background" />
        <div className="infos">
          <h1 className="current-value">{formatMoney(props.value)}</h1>
          <h1 className="porcentage">{width}%</h1>
          <h1 className="total-value">{formatMoney(props.total)}</h1>
        </div>
      </div>
    </div>
  );

  return props.type == BarChartType.BuildIn ? getBuildinBarChart() : getInlineBarChart();
};

export { BarChartType };

export default BarChart;