import { FetchAllApiResponse, FetchOneApiResponse, FuncionData } from "../types";




export async function fetchPelis() {

    try {
        const response = await fetch("http://localhost:8080/api/v1/funcion");
        if (!response.ok) {
            throw new Error("Fetch failed");
        }
        const peliculas: FetchAllApiResponse = await response.json();
        return peliculas.object;
    } catch (error) {
        console.error((error as Error).message);
        return null;
    }
}


export async function fetchAsiento(idPelicula: number, numeroAsiento: number, operacion: string) {

    try {
        const response = await fetch(`http://localhost:8080/api/v1/funcion/${idPelicula}/asiento/${operacion}/${numeroAsiento}`, {
            method: "POST"
        }

        );
        if (!response.ok) {
            throw new Error("No se pudo cambiar el estado el asiento");
        }
        const feedback: FetchOneApiResponse = await response.json();
        return feedback.message;
    } catch (error) {
        console.error((error as Error).message);
        return null;
    }
}

export const insertarPeli = async (funcion: FuncionData) => {
    try {
        const response = await fetch("http://localhost:8080/api/v1/funcion", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(funcion)
        });
        const data: FetchOneApiResponse = await response.json();
        if (!response.ok) {
            throw new Error(data.message);
        }
        return data;
    } catch (error) {
        console.error((error as Error).message);
        return null;
    }
}