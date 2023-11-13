import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import * as z from "zod";
import { Button } from "../../../../components/button";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormMessage,
} from "../../../../components/form";

import { toast } from "../../../../components/toast/use-toast";
import { Input } from "components/input";
import { CategoryComboBox } from "./categories-combo";
import { MovimentationTypeComboBox } from "./type-combo";
import IMovimentation from "lib/interfaces/IMovimentation";
import { Calendar } from "components/calendar";
import { Popover, PopoverContent, PopoverTrigger } from "components/popover";
import { CalendarIcon } from "lucide-react";
import { cn } from "lib/utils";
import { format } from "date-fns";
import { getLoggedUser } from "lib/localstorage";
import { addMovimentation } from "lib/services/MovimentationService";

const FormSchema = z.object({
  title: z.string({
    required_error: "Por favor digite um título.",
  }),
  value: z.string({
    required_error: "Por favor digite um valor.",
  }),
  category: z.string({
    required_error: "Por favor informe uma categoria.",
  }),
  type: z.string({
    required_error: "Por favor informe um tipo.",
  }),
  date: z.string({
    required_error: "Por favor informe uma data.",
  })
});

export function ComboboxForm(props: { onDone: (newMov: IMovimentation) => void }) {
  const form = useForm<z.infer<typeof FormSchema>>({
    resolver: zodResolver(FormSchema),
  });

  function createMovimentation(title: string, value: number, category: string, type: string, date: Date) {
    const newMovimentation: IMovimentation = {
      amount: value,
      category: category.toUpperCase(),
      date: date,
      title: title,
      movType: type.toUpperCase(),
      userId: getLoggedUser()!.id!
    };

    addMovimentation(newMovimentation).then(x => {
      if (typeof x == typeof String) {
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
          <p>Movimentação <strong>{title}</strong> criada.</p>
        ),
      });

      props.onDone(x as IMovimentation);
    });
  }

  function onSubmit(data: z.infer<typeof FormSchema>) {
    createMovimentation(data.title, parseInt(data.value), data.category, data.type, new Date(data.date));
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
                <Input type="text" placeholder="Título" value={field.value} onChange={(x) => form.setValue(field.name, x.target.value)} />
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
                <Input type="number" placeholder="Valor" value={field.value} onChange={(x) => form.setValue(field.name, x.target.value)} />
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
                      {field.value ? format(new Date(field.value), "dd/MMM/yyyy") : <span>Pick a date</span>}
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
                  <CategoryComboBox onChange={(x) => form.setValue(field.name, x)} />
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
                  <MovimentationTypeComboBox onChange={(x) => form.setValue(field.name, x)} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
        </div>

        <Button variant={"secondary"} className="w-full" type="submit">Criar</Button>
      </form>
    </Form>
  );
}