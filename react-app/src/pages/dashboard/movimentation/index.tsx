import MovimentationBox from "components/movimenation-box";
import "./styles.scss";
import { MovimentationType } from "lib/enums";
import { Area, AreaChart, CartesianGrid, Cell, Label, Pie, PieChart, ResponsiveContainer, Tooltip, XAxis, YAxis } from "recharts";
import { formatMoney } from "lib/utils";
import { format } from "date-fns";
import { useEffect, useState } from "react";
import MoneyBox from "components/money-box";
import BarChart, { BarChartType } from "components/bar-chart";
import { Dialog, DialogContent, DialogTrigger } from "components/dialog";
import { ComboboxForm } from "./components/new-movimentation-form";
import { getMovimentation } from "lib/services/MovimentationService";
import { getLoggedUser } from "lib/localstorage";
import IMovimentation from "lib/interfaces/IMovimentation";
import IGrouping from "lib/interfaces/IGroupping";

interface ICustomTooltipProps {
  active?: boolean,
  payload?: any[],
  label?: Date
}

function CustomTooltip({ active, payload, label }: ICustomTooltipProps) {
  if (active) {
    if (!payload || payload.length <= 0) return <></>;

    return (
      <div className="tooltip">
        <h4>{format(label!, "MMM, yyyy")}</h4>
        <p>{formatMoney(payload![0].payload.amount)}</p>
      </div>
    );
  }
  return null;
}

const MovimentationPage = () => {
  const from = new Date();
  from.setDate(1);

  const to = new Date();
  to.setDate(31);

  const [isClosed, setIsClosed] = useState(false);
  const [isOutputGraphSelected, setIsOutputGraphSelected] = useState<boolean>(true);
  const [movimentations, setMovimentations] = useState<IMovimentation[]>([]);
  const [movimentationsFields, setMovimentationsFields] = useState({
    outgoingTotal: 0,
    movimentationsGroupped: [] as IGrouping<number, IMovimentation>[],
    outgoingData: [] as IMovimentation[],
    incomingData: [] as IMovimentation[],
    pizzaGraphData: [] as { description: string, color: string, amount: number }[],
  });

  useEffect(() => {
    const loggedUserId = getLoggedUser()!.id!;

    getMovimentation(loggedUserId, from, to).then(x => {
      if (typeof x == typeof String)
        return;

      const tempMovimentations = x as IMovimentation[];

      setMovimentations(tempMovimentations);

      console.log(tempMovimentations);
    });
  }, []);

  useEffect(() => {
    const allCategories = movimentations.distinct(x => x.category).map(x => x.categoryInfo!);

    setMovimentationsFields({
      outgoingTotal: movimentations.filter(x => x.movType == MovimentationType.OUTGOING).sum(x => x.amount),
      movimentationsGroupped: movimentations.groupBy(x => x.date.getDate()).orderByDescending(x => x.key),
      outgoingData: movimentations.filter(x => x.movType == MovimentationType.OUTGOING).orderByDescending(x => x.amount),
      incomingData: movimentations.filter(x => x.movType == MovimentationType.INCOMING).orderByDescending(x => x.amount),
      pizzaGraphData: movimentations.filter(x => x.movType == MovimentationType.OUTGOING).groupBy(x => x.categoryInfo?.description ?? "").map(x => ({description: allCategories.find(z => z.description == x.key)!.description!, color: allCategories!.find(z => z.description == x.key)!.color, amount: x.values.sum(z => z.amount)})).orderByDescending(x => x.amount),
    });
  }, [movimentations]);

  return (
    <div className="grid">
      <div className="movimentations">
        <div className="title">
          <h1>Movimentações</h1>
        </div>
        <div className="movimentations-box">
          {movimentationsFields.movimentationsGroupped.map(x => <div key={x.key} className="movimentations-group">
            <div className="divider">
              <hr />
              <h3>Dia {x.key}</h3>
              <hr />
            </div>
            {x.values.map((z, index) => <MovimentationBox key={z.title + index} title={z.title} type={z.movType} value={z.amount} />)}
          </div>)}
        </div>
        <Dialog open={isClosed ? false : undefined}>
          <DialogTrigger className="add-button" onClick={() => setIsClosed(false)}>Adicionar</DialogTrigger>
          <DialogContent className="dialog-content">
            <div className="title">
              <h1>Criar nova Movimentação</h1>
            </div>
            <ComboboxForm onDone={(newMov) => { setIsClosed(true); setMovimentations([...movimentations, newMov]); }} />
          </DialogContent>
        </Dialog>
      </div>

      <div className="month">
        <div className="title">
          <h1>Filtro</h1>
        </div>

      </div>

      <div className="cards">
      </div>

      <div className="graph">
        <div className="options">
          <h1 className={isOutputGraphSelected ? "active" : ""} onClick={() => setIsOutputGraphSelected(!isOutputGraphSelected)}>Despesas</h1>
          <h1 className={isOutputGraphSelected ? "" : "active"} onClick={() => setIsOutputGraphSelected(!isOutputGraphSelected)}>Ganhos</h1>
        </div>
        <div className="graph-container">
          <ResponsiveContainer width={"90%"} height={"100%"} className={"responsive-container"}>
            <AreaChart data={isOutputGraphSelected ? movimentationsFields.outgoingData : movimentationsFields.incomingData}>
              <defs>
                <linearGradient id="color" x1="0" y1="0" x2="0" y2="1">
                  <stop offset="0%" stopColor="#39DAC5" stopOpacity={0.4} />
                  <stop offset="75%" stopColor="#39DAC5" stopOpacity={0.05} />
                </linearGradient>
              </defs>

              <Area type={"monotone"} dataKey="amount" className="overview-graph-line" stroke="#39DAC5" fill="url(#color)" />

              <XAxis
                dataKey="date"
                axisLine={false}
                tickLine={false}
                tickCount={12}
                tickMargin={10}
                tickFormatter={(date) => {
                  return format(date, "MMM");
                }}
              />

              <YAxis
                dataKey="amount"
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
      </div>

      <div className="pizza-resume">
        <div className="title">
          <h1>Visualização de Gastos</h1>
        </div>
        <div className="charts-area">
          <ResponsiveContainer width={"75%"} height={"75%"}>
            <PieChart>
              <Pie
                data={movimentationsFields.pizzaGraphData.map(x => ({ title: x.description, amount: x.amount }))}
                dataKey={"amount"}
                innerRadius="97%"
                outerRadius="100%"
                fill="#8884d8"
              >
                <Label className="pie-label"
                  value={formatMoney(movimentationsFields.outgoingTotal)} position="centerBottom" fontSize='18px'
                />
                {
                  movimentationsFields.pizzaGraphData.map((entry, index: number) => <Cell className="overview-graph-cell" style={{ filter: `drop-shadow(0 0 5px ${entry.color})` }} stroke="none" radius={10} key={index} fill={entry.color} />)
                }
              </Pie>
              <Tooltip />
            </PieChart>
          </ResponsiveContainer>
          <div className="bars">
            {movimentationsFields.pizzaGraphData.map((entry, index) => <BarChart type={BarChartType.Inline} name={entry.description} total={movimentationsFields.outgoingTotal} value={entry.amount} key={entry.description + index} color={entry.color} />)}
          </div>
        </div>
      </div>

      <div className="balance">
        <div className="actual-balance">
          <div className="title">
            <h1>Saldo</h1>
          </div>
          <div className="money-box-background" >
            <MoneyBox amount={2000} />
          </div>
        </div>

        <div className="diary-limit">
          <div className="title">
            <h1>Limite Diário</h1>
          </div>
          <div className="bar-chart-container">
            <BarChart value={200} color="#39DAC5" type={BarChartType.BuildIn} name="Limite Diário" total={300} />
          </div>
        </div>

      </div>
    </div>
  );
};

export default MovimentationPage;