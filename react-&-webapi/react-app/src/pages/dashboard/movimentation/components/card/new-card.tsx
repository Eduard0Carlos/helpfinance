import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import * as z from "zod";
import { Button } from "../../../../../components/button";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormMessage,
} from "../../../../../components/form";

import { toast } from "../../../../../components/toast/use-toast";
import { Input } from "components/input";
import { NetworkComboBox } from "./network-combo";
import { PaymentCardTypeComboBox } from "./type-combo";
import { Calendar } from "components/calendar";
import { Popover, PopoverContent, PopoverTrigger } from "components/popover";
import { CalendarIcon, Loader2 } from "lucide-react";
import { cn } from "lib/utils";
import { format } from "date-fns";
import { getLoggedUser } from "lib/localstorage";
import { useState } from "react";
import { IPaymentCard } from "lib/interfaces/IPaymentCard";
import { addCard } from "lib/services/PaymentCardService";

const FormSchema = z.object({
  title: z.string({
    required_error: "Por favor digite o nome no cartão.",
  }),
  value: z.string({
    required_error: "Por favor digite o número.",
  }),
  category: z.string({
    required_error: "Por favor informe uma rede.",
  }),
  type: z.string({
    required_error: "Por favor informe um tipo.",
  }),
  date: z.string({
    required_error: "Por favor informe uma data.",
  })
});

export function PaymentCardCreationForm(props: { onDone: (newCard: IPaymentCard) => void }) {
  const form = useForm<z.infer<typeof FormSchema>>({
    resolver: zodResolver(FormSchema),
  });

  const [isLoading, setIsLoading] = useState(false);

  function createPaymentCard(nickname: string, cardNumber: string, network: string, type: string, date: Date) {
    setIsLoading(true);

    const newCard: IPaymentCard = {
      id: "00000000-0000-0000-0000-000000000000",
      cardNumber: cardNumber,
      userId: getLoggedUser()!.id!,
      expirationDate: date,
      nickname: nickname,
      paymentNetwork: network,
      paymentType: type
    };

    addCard(newCard).then(x => {
      setIsLoading(false);

      if (typeof x == typeof "") {
        toast({
          title: "Ops, algo deu errado!",
          description: (
            <p>Por favor, tente novamente mais tarde!</p>
          ),
        });

        return;
      }

      toast({
        title: "Sucesso!",
        description: (
          <p>Cartão <strong>{cardNumber}</strong> criada.</p>
        ),
      });

      props.onDone(x as IPaymentCard);
    });
  }

  function onSubmit(data: z.infer<typeof FormSchema>) {
    createPaymentCard(data.title, data.value, data.category, data.type, new Date(data.date));
  }

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6">
        <FormField
          control={form.control}
          name="title"
          render={({ field }) => (
            <FormItem className="flex flex-col">
              <FormControl>
                <Input type="text" placeholder="Nome no Cartão" value={field.value} onChange={(x) => form.setValue(field.name, x.target.value)} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
        <FormField
          control={form.control}
          name="value"
          render={({ field }) => (
            <FormItem className="flex flex-col">
              <FormControl>
                <Input type="text" placeholder="Número" value={field.value} onChange={(x) => form.setValue(field.name, x.target.value)} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
        <FormField
          control={form.control}
          name="date"
          render={({ field }) => (
            <FormItem className="flex flex-col w-full">
              <FormControl>
                <Popover>
                  <PopoverTrigger asChild>
                    <Button
                      variant={"outline"}
                      className={cn(
                        "w-full justify-start text-left font-normal",
                        !field.value && "text-muted-foreground"
                      )}
                    >
                      <CalendarIcon className="mr-2 h-4 w-4" />
                      {field.value ? format(new Date(field.value), "dd/MMM/yyyy") : <span>Selecione a data de expiração</span>}
                    </Button>
                  </PopoverTrigger>
                  <PopoverContent className="w-auto p-0" align="start">
                    <Calendar
                      mode="single"
                      selected={new Date(field.value)}
                      onSelect={(x) => { form.setValue(field.name, x?.toDateString() ?? ""); }}
                      initialFocus
                    />
                  </PopoverContent>
                </Popover>
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
        <div className="flex w-full row gap-10 justify-between">
          <FormField
            control={form.control}
            name="category"
            render={({ field }) => (
              <FormItem className="flex flex-col">
                <FormControl>
                  <NetworkComboBox onChange={(x) => form.setValue(field.name, x)} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="type"
            render={({ field }) => (
              <FormItem className="flex flex-col">
                <FormControl>
                  <PaymentCardTypeComboBox onChange={(x) => form.setValue(field.name, x)} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
        </div>

        {isLoading ?
          <Button disabled className="w-full">
            <Loader2 className="mr-2 h-4 w-4 animate-spin" />
            Aguarde
          </Button> :
          <Button variant={"secondary"} className="w-full" type="submit">Criar</Button>}
      </form>
    </Form>
  );
}