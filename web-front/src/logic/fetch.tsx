import { ApiResponse } from "../types";




export async function fetchPelis() {

    try {
        const response = await fetch("http://localhost:8080/api/v1/funcion");
        if (!response.ok) {
            throw new Error("Fetch failed");
        }
        const peliculas: ApiResponse = await response.json();
        return peliculas.object;
    } catch (error) {
        console.error((error as Error).message);
        return null;
    }
}


export async function fetchAsiento(idPelicula: number, numeroAsiento: number) {

    try {
        const response = await fetch(`http://localhost:8080/api/v1/funcion/${idPelicula}/asiento/${numeroAsiento}`, {
            method: "POST"
        }

        );
        if (!response.ok) {
            throw new Error("No se pudo reservar el asiento");
        }
        const feedback: ApiResponse = await response.json();
        return feedback.message;
    } catch (error) {
        console.error((error as Error).message);
        return null;
    }
}