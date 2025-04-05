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