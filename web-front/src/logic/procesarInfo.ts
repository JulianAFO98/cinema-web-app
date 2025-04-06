import { FuncionData } from "../types";



export const procesarForm = (form: HTMLFormElement) => {
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    const diaUtil = procesarDia(data.dia as string);

    const usefulData: FuncionData = {
        nombre: data.nombre as string,
        horaStr: data.hora as string,
        precio: parseFloat(data.precio as string),
        dia: Number(diaUtil),
    };
    return usefulData as FuncionData;
}

const procesarDia = (dia: string) => {
    const regex = /\d+-\d+-0?(\d{1,2})/;
    const match = regex.exec(dia);
    const diaUtil = match?.[1];
    return diaUtil;

}