import "./styles.scss";
import { useState } from "react";
import {
  ResponsiveContainer,
  AreaChart,
  XAxis,
  YAxis,
  Area,
  Tooltip,
  CartesianGrid,
  Pie,
  Label,
  PieChart,
  Cell
} from "recharts";
import { format, parseISO } from "date-fns";
import BarChart, { BarChartType } from "components/bar-chart";
import ValueInfoBox from "components/value-info-box";
import InvestmentCard from "components/investment-card";
import { formatMoney } from "lib/utils";

const COLORS = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042"];

const getData = (quantity: number) => {
  const tempData = [];

  for (let num = 0; num <= quantity; num++) {
    const date = new Date();
    date.setMonth(num);
    tempData.push({
      date: date.toISOString().substring(0, 10),
      value: 1000 * Math.random(),
      color: COLORS[num % COLORS.length]
    });
  }

  return tempData;
};

const outputdata = getData(11);
const gainData = getData(4);
const pieTotal = 3000;
const pieData = [
  {
    name: "Comida",
    value: 1200,
    color: "#8AFFF9"
  },
  {
    name: "Contas",
    value: 1200,
    color: "#E1A1FF"
  },
  {
    name: "Lazer",
    value: 600,
    color: "#57FFAE"
  }
];

const resumePieData = [
  {
    name: "Gasto",
    value: 150,
    color: "#8AFFF9"
  },
  {
    name: "Restante",
    value: 200,
    color: "rgba(255, 255, 255, 0.10)"
  }
];

interface ICustomTooltipProps {
  active?: boolean,
  payload?: any[],
  label?: string
}

function CustomTooltip({ active, payload, label }: ICustomTooltipProps) {
  if (active) {
    return (
      <div className="tooltip">
        <h4>{format(parseISO(label!), "MMM, yyyy")}</h4>
        <p>{formatMoney(payload![0].value)}</p>
      </div>
    );
  }
  return null;
}

const getCardsData = (quantity: number) => {
  const tempData = [];

  for (let num = 1; num <= quantity; num++) {
    tempData.push({
      name: "MXRF11",
      value: 2.24,
      porcentage: 0.24,
      type: "up"
    });
  }

  return tempData;
};

const cards = getCardsData(5);

const OverviewPage = () => {
  const [isOutputGraphSelected, setIsOutputGraphSelected] = useState<boolean>(true);

  return (
    <div style={{ gridTemplateColumns: "50% 50%", gridTemplateRows: "50% 50%" }} className="grid">
      <div className="resume">
        <div className="charts-area">
          <div className="resume-area">
            <section className="resume-area-value">
              <div className="title">
                <h1>Total de Entradas neste Mês</h1>
              </div>
              <ValueInfoBox type="up" value={4000} />
            </section>
            <section className="resume-area-value">
              <div className="title">
                <h1>Total de Saídas neste Mês</h1>
              </div>
              <ValueInfoBox type="down" value={3000} />
            </section>
          </div>
          <div className="pie-chart-area">
            <div className="title">
              <h1>Limite de Gastos Diário</h1>
            </div>
            <ResponsiveContainer width={"75%"} height={"75%"}>
              <PieChart>
                <Pie
                  cornerRadius={50}
                  data={resumePieData}
                  dataKey={"value"}
                  innerRadius="97%"
                  outerRadius="100%"
                  fill="#8884d8"
                >
                  <Label className="pie-label"
                    value="R$ 150 / 350" position="centerBottom" fontSize='27px'
                  />
                  {
                    resumePieData.map((entry: any, index: number) => <Cell className="overview-graph-cell" style={{ filter: `drop-shadow(0 0 5px ${entry.color})` }} stroke="none" key={index} fill={entry.color} />)
                  }
                </Pie>
                <Tooltip />
              </PieChart>
            </ResponsiveContainer>
          </div>
        </div>
      </div>

      <div className="overview-graph" >
        <div className="options">
          <h1 className={isOutputGraphSelected ? "active" : ""} onClick={() => setIsOutputGraphSelected(!isOutputGraphSelected)}>Despesas</h1>
          <h1 className={isOutputGraphSelected ? "" : "active"} onClick={() => setIsOutputGraphSelected(!isOutputGraphSelected)}>Ganhos</h1>
        </div>
        <ResponsiveContainer width={"90%"} height={"75%"}>
          <AreaChart data={isOutputGraphSelected ? outputdata : gainData}>
            <defs>
              <linearGradient id="color" x1="0" y1="0" x2="0" y2="1">
                <stop offset="0%" stopColor="#39DAC5" stopOpacity={0.4} />
                <stop offset="75%" stopColor="#39DAC5" stopOpacity={0.05} />
              </linearGradient>
            </defs>

            <Area type={"monotone"} dataKey="value" className="overview-graph-line" stroke="#39DAC5" fill="url(#color)" />

            <XAxis
              dataKey="date"
              axisLine={false}
              tickLine={false}
              tickCount={12}
              tickMargin={10}
              tickFormatter={(str) => {
                const date = parseISO(str);
                return format(date, "MMM");
              }}
            />

            <YAxis
              dataKey="value"
              axisLine={false}
              tickLine={false}
              tickCount={5}
              tickFormatter={(number) => `${formatMoney(number)}`}
              opacity={0.5}
            />

            <Tooltip content={<CustomTooltip />} />

            <CartesianGrid opacity={0.1} vertical={false} />
          </AreaChart>
        </ResponsiveContainer>
      </div>

      <div className="pie-chart">
        <div className="title">
          <h1>Visualização de Gastos</h1>
        </div>
        <div className="charts-area">
          <ResponsiveContainer width={"75%"} height={"75%"}>
            <PieChart>
              <Pie
                data={pieData}
                dataKey={"value"}
                innerRadius="97%"
                outerRadius="100%"
                fill="#8884d8"
              >
                <Label className="pie-label"
                  value="R$ 3.000,00" position="centerBottom" fontSize='27px'
                />
                {
                  pieData.map((entry: any, index: number) => <Cell className="overview-graph-cell" style={{ filter: `drop-shadow(0 0 5px ${entry.color})` }} stroke="none" radius={10} key={index} fill={entry.color} />)
                }
              </Pie>
              <Tooltip />
            </PieChart>
          </ResponsiveContainer>
          <div className="bars">
            {pieData.map((entry) => <BarChart type={BarChartType.Inline} name={entry.name} total={pieTotal} value={entry.value} key={entry.name} color={entry.color} />)}
          </div>
        </div>
      </div>

      <div className="investment-area">
        <div className="title">
          <h1>Investimentos</h1>
        </div>
        <div className="investments">
          {cards.map((entry, index) => <InvestmentCard name={entry.name} AnimationDelay={(0.1 * index)} porcentage={entry.porcentage} type={entry.type} value={entry.value} key={index} gridColumn={index + 1} />)}
        </div>
      </div>
    </div >
  );
};

export default OverviewPage;