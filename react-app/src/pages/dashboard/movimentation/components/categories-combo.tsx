import * as React from "react";
import { Check, ChevronsUpDown } from "lucide-react";

import { cn } from "../../../../lib/utils";
import { Button } from "../../../../components/button";
import {
  Command,
  CommandEmpty,
  CommandGroup,
  CommandInput,
  CommandItem,
} from "../../../../components/command";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "../../../../components/popover";

const categories = [
  {
    value: "FOOD",
    label: "Comida",
  },
  {
    value: "BILL",
    label: "Contas",
  },
  {
    value: "RECREATION",
    label: "Lazer",
  }
];

export function CategoryComboBox(props: {onChange: (value: string) => void}) {
  const [open, setOpen] = React.useState(false);
  const [value, setValue] = React.useState("");

  return (
    <Popover open={open} onOpenChange={setOpen}>
      <PopoverTrigger asChild>
        <Button
          variant="outline"
          role="combobox"
          aria-expanded={open}
          className="w-[200px] justify-between"
        >
          {value
            ? categories.find((framework) => framework.value.toLowerCase() == value.toLowerCase())?.label
            : "Selecione categoria..."}
          <ChevronsUpDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
        </Button>
      </PopoverTrigger>
      <PopoverContent className="w-[200px] p-0">
        <Command>
          <CommandInput placeholder="Pesquise uma categoria..." />
          <CommandEmpty>Não Encontrado</CommandEmpty>
          <CommandGroup>
            {categories.map((framework) => (
              <CommandItem
                key={framework.value}
                value={framework.value}
                onSelect={(currentValue) => {
                  props.onChange(currentValue);
                  setValue(currentValue === value ? "" : currentValue);
                  setOpen(false);
                }}
              >
                <Check
                  className={cn(
                    "mr-2 h-4 w-4",
                    value === framework.value ? "opacity-100" : "opacity-0"
                  )}
                />
                {framework.label}
              </CommandItem>
            ))}
          </CommandGroup>
        </Command>
      </PopoverContent>
    </Popover>
  );
}
