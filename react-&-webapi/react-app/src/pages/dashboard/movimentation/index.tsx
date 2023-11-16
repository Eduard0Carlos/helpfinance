import MovimentationBox from "components/movimenation-box";
import "./styles.scss";
import { MovimentationType } from "lib/enums";
import { Area, AreaChart, CartesianGrid, Cell, Label, Pie, PieChart, ResponsiveContainer, Tooltip, XAxis, YAxis } from "recharts";
import { formatMoney, formatPaymentCardNumber, isString } from "lib/utils";
import { addDays, addMonths, addYears, format, parseISO, setDate } from "date-fns";
import { useEffect, useState } from "react";
import MoneyBox from "components/money-box";
import BarChart, { BarChartType } from "components/bar-chart";
import { Dialog, DialogContent, DialogTrigger } from "components/dialog";
import { ComboboxForm } from "./components/movimentation/new-movimentation-form";
import { deleteMovimentation, getMovimentation } from "lib/services/MovimentationService";
import { getLoggedUser } from "lib/localstorage";
import IMovimentation from "lib/interfaces/IMovimentation";
import IGrouping from "lib/interfaces/IGroupping";
import IUser from "lib/interfaces/IUser";
import { getById } from "lib/services/UserService";
import { UUID } from "crypto";
import { toast } from "components/toast/use-toast";
import DatePickerWithRange from "components/date-range-picker";
import PaymentCardBox from "components/paymentcard-box";
import { PaymentCardCreationForm } from "./components/card/new-card";
import { IPaymentCard } from "lib/interfaces/IPaymentCard";
import { deletePaymentCard, getAllCards } from "lib/services/PaymentCardService";

interface ICustomTooltipProps {
  active?: boolean,
  payload?: any[],
  label?: string
}

function CustomTooltip({ active, payload, label }: ICustomTooltipProps) {
  if (active) {
    if (!payload || payload.length <= 0) return <></>;

    const date = parseISO(label!);

    return (
      <div className="tooltip">
        <h4>{format(date, "MMM, yyyy")}</h4>
        <p>{formatMoney(payload![0].payload.amount)}</p>
      </div>
    );
  }
  return null;
}

const MovimentationPage = () => {
  const loggedUser = getLoggedUser()!;

  const [user, setUser] = useState<IUser>();
  const [movimentations, setMovimentations] = useState<IMovimentation[]>([]);
  const [cards, setCards] = useState<IPaymentCard[]>([]);
  const [isClosed, setIsClosed] = useState(false);
  // const [isBankMenuSelected, setIsBankMenuSelected] = useState(false);
  const [isOutputGraphSelected, setIsOutputGraphSelected] = useState<boolean>(true);
  const [diaryLimit, setDiaryLimit] = useState(0);
  const [period, setPeriod] = useState({
    from: setDate(new Date(), 1),
    to: setDate(new Date(), 31)
  });
  const [graphData, setGraphData] = useState({
    outgoing: [] as { key: string, amount: number }[],
    incoming: [] as { key: string, amount: number }[]
  });
  const [movimentationsFields, setMovimentationsFields] = useState({
    outgoingTotal: 0,
    movimentationsGroupped: [] as IGrouping<string, IMovimentation>[],
    outgoingData: [] as IMovimentation[],
    incomingData: [] as IMovimentation[],
    pizzaGraphData: [] as { description: string, color: string, amount: number }[],
  });

  const getGraphData = () => {
    const today = new Date();
    const initialYearDate = new Date(today.getFullYear().toString());
    const finalYearDate = addYears(new Date(today.getFullYear().toString()), 1);

    getMovimentation(loggedUser.id!, initialYearDate, finalYearDate).then(apiResult => {
      if (isString(apiResult))
        return;

      const apiResultMovimentations = apiResult as IMovimentation[];

      setGraphData({
        outgoing: apiResultMovimentations
          .filter(x => x.movType == MovimentationType.OUTGOING)
          .groupBy(x => setDate(x.date, 1).toISOString().substring(0, 10))
          .map(x => ({ key: x.key, amount: x.values.sum(z => z.amount) } as any))
          .orderBy(x => x.key),

        incoming: apiResultMovimentations
          .filter(x => x.movType == MovimentationType.INCOMING)
          .groupBy(x => setDate(x.date, 1).toISOString().substring(0, 10))
          .map(x => ({ key: x.key, amount: x.values.sum(z => z.amount) } as any))
          .orderBy(x => x.key)
      });
    });
  };

  const getCards = () => {
    getAllCards(loggedUser.id!).then(apiResult => {
      if (isString(apiResult))
        return;

      setCards(apiResult as IPaymentCard[]);
    });
  };

  const getDiaryLimit = () => {
    const today = new Date();
    const todayStart = new Date(format(today, "yyyy-MM-dd") + "T00:00:00");
    const todayEnd = new Date(format(addDays(today, 1), "yyyy-MM-dd") + "T00:00:00");

    getMovimentation(loggedUser.id!, todayStart, todayEnd).then(apiResult => {
      if (isString(apiResult))
        return;

      setDiaryLimit((apiResult as IMovimentation[]).sum(x => x.amount));
    });
  };

  const getMovimentationsFromPeriod = () => {
    getMovimentation(loggedUser.id!, period.from, period.to).then(apiResult => {
      if (isString(apiResult))
        return;

      setMovimentations(apiResult as IMovimentation[]);
    });
  };

  const getUserInfo = () => {
    getById(loggedUser.id!).then(apiResult => {
      if (isString(apiResult))
        return;

      setUser(apiResult as IUser);
    });
  };

  useEffect(() => {
    const loggedUserId = loggedUser.id;

    if (!loggedUserId)
      return;

    getCards();

    getUserInfo();

    getDiaryLimit();

    getMovimentationsFromPeriod();
  }, []);

  useEffect(() => {
    const loggedUserId = loggedUser.id;

    if (!loggedUserId)
      return;

    getGraphData();

    getUserInfo();

    getDiaryLimit();

    const allCategories = movimentations.distinct(x => x.category).map(x => x.categoryInfo!);

    setMovimentationsFields({
      outgoingTotal: movimentations.filter(x => x.movType == MovimentationType.OUTGOING).sum(x => x.amount),
      movimentationsGroupped: movimentations.groupBy(x => format(x.date, "MMM, yyyy") ?? "").orderByDescending(x => x.values[0]?.date?.getFullYear() ?? 0),
      outgoingData: movimentations.filter(x => x.movType == MovimentationType.OUTGOING).orderByDescending(x => x.amount),
      incomingData: movimentations.filter(x => x.movType == MovimentationType.INCOMING).orderByDescending(x => x.amount),
      pizzaGraphData: movimentations.filter(x => x.movType == MovimentationType.OUTGOING).groupBy(x => x.categoryInfo?.description ?? "").map(x => ({ description: allCategories.find(z => z.description == x.key)!.description!, color: allCategories!.find(z => z.description == x.key)!.color, amount: x.values.sum(z => z.amount) })).orderByDescending(x => x.amount),
    });
  }, [movimentations]);

  useEffect(() => {
    getMovimentationsFromPeriod();
  }, [period]);

  const onMovimentationDelete = (id: UUID, title: string) => {
    deleteMovimentation(id).then(apiResult => {
      if (apiResult as string != "") {
        toast({
          title: "Ops, algo deu errado!",
          description: (
            <p>{apiResult as string}</p>
          ),
        });

        return;
      }

      setMovimentations([...movimentations.filter(x => x.id != id)]);
      toast({
        title: "Sucesso!",
        description: (
          <p>Movimentação <strong>{title}</strong> removida.</p>
        ),
      });
    });
  };

  const onDeletePaymentCard = (id: UUID, cardNumber: string) => {
    deletePaymentCard(id).then(apiResult => {
      if (apiResult as string != "") {
        toast({
          title: "Ops, algo deu errado!",
          description: (
            <p>{apiResult as string}</p>
          ),
        });

        return;
      }

      setCards([...cards.filter(x => x.id != id)]);

      toast({
        title: "Sucesso!",
        description: (
          <p>Cartão <strong>{formatPaymentCardNumber(cardNumber)}</strong> deletado.</p>
        ),
      });
    });
  };

  const onPeriodSelected = (from?: Date, to?: Date) => {
    if (!from || !to)
      return;

    setPeriod({ from: from, to: addDays(to, 1) });
  };

  return (
    <div className="grid">
      <div className="movimentations">
        <div className="title">
          <h1>Movimentações</h1>
          <div>Período Atual: {format(period.from, "dd/MM/yyyy")} até {format(period.to, "dd/MM/yyyy")}</div>
        </div>
        <div className="movimentations-box">
          {movimentationsFields.movimentationsGroupped.any() ? movimentationsFields.movimentationsGroupped.map(x =>
            <div key={x.key} className="movimentations-year-group">
              <div className="divider">
                <h3>{x.key}</h3>
              </div>
              {x.values.groupBy(z => z.date.getDate()).orderByDescending(z => z.key).map(z =>
                <div key={z.key} className="movimentations-group">
                  <div className="divider">
                    <hr />
                    <h3>Dia {z.key}</h3>
                    <hr />
                  </div>
                  {z.values.map((z, index) => <MovimentationBox id={z.id} onDelete={(id, title) => onMovimentationDelete(id, title)} key={z.title + index} title={z.title} type={z.movType} value={z.amount} />)}
                </div>)}
            </div>
          ) : <h1 className="not-found-text">Crie sua primeira movimentação</h1>}
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
        <div className="filter-container">
          <div className="presets">
            <button onClick={() => onPeriodSelected(addDays(new Date(), -7), new Date())}>Últimos 7 Dias</button>
            <button onClick={() => onPeriodSelected(addMonths(new Date(), -1), new Date())}>Último Mês</button>
            <button onClick={() => onPeriodSelected(addMonths(new Date(), -6), new Date())}>Últimos 6 Meses</button>
          </div>
          <DatePickerWithRange className="calendar" onSelected={(dateRange) => onPeriodSelected(dateRange.from, dateRange.to)} />
        </div>
      </div>

      <div className="cards">
        <div className="title">
          <h1>Integrações</h1>
        </div>
        <div className="content-box">
          {
            cards.any() ?
              cards.map(x =>
                <PaymentCardBox
                  type={x.paymentType}
                  network={x.paymentNetwork}
                  number={x.cardNumber}
                  onDelete={(id) => onDeletePaymentCard(id, x.cardNumber)}
                  id={x.id}
                  key={x.id}
                />
              ) :
              <h1 className="not-found-text">Adicione seu cartão</h1>
          }
        </div>
        <Dialog open={isClosed ? false : undefined}>
          <DialogTrigger className="add-button" onClick={() => setIsClosed(false)}>Adicionar</DialogTrigger>
          <DialogContent className="dialog-content">
            <div className="title">
              <h1>Novo Cartão</h1>
            </div>
            <PaymentCardCreationForm onDone={(newCard) => { setIsClosed(true); setCards([...cards, newCard]); }} />
          </DialogContent>
        </Dialog>
      </div>

      <div className="graph">
        <div className="options">
          <h1 className={isOutputGraphSelected ? "active" : ""} onClick={() => setIsOutputGraphSelected(!isOutputGraphSelected)}>Despesas</h1>
          <h1 className={isOutputGraphSelected ? "" : "active"} onClick={() => setIsOutputGraphSelected(!isOutputGraphSelected)}>Ganhos</h1>
        </div>
        <div className="graph-container">
          <ResponsiveContainer width={"90%"} height={"100%"} className={"responsive-container"}>
            <AreaChart data={isOutputGraphSelected ? graphData.outgoing : graphData.incoming}>
              <defs>
                <linearGradient id="color" x1="0" y1="0" x2="0" y2="1">
                  <stop offset="0%" stopColor="#39DAC5" stopOpacity={0.4} />
                  <stop offset="75%" stopColor="#39DAC5" stopOpacity={0.05} />
                </linearGradient>
              </defs>

              <Area type={"monotone"} dataKey="amount" className="overview-graph-line" stroke="#39DAC5" fill="url(#color)" />

              <XAxis
                dataKey="key"
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
          <h1>Despesas no Período</h1>
        </div>
        {movimentationsFields.pizzaGraphData.any() ? <div className="charts-area">
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
        </div> : <div className="not-found-text">Nada por aqui ainda</div>}

      </div>

      <div className="balance">
        <div className="actual-balance">
          <div className="title">
            <h1>Saldo</h1>
          </div>
          <div className="money-box-background" >
            <MoneyBox amount={user?.balance ?? 0} />
          </div>
        </div>

        <div className="diary-limit">
          <div className="title">
            <h1>Limite Diário</h1>
          </div>
          <div className="bar-chart-container">
            <BarChart value={diaryLimit} color="#39DAC5" type={BarChartType.BuildIn} name="Limite Diário" total={user?.monthlySpendingsLimit ?? 1} />
          </div>
        </div>

      </div>
    </div>
  );
};

export default MovimentationPage;