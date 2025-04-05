import { useState } from "react";
import { fetchPelis } from "../logic/fetchPelis";
import { Cine } from "./Cine";
import { Funcion } from "../types";


export function CineBoard() {
    const [peliculas, setPeliculas] = useState<null | Funcion[]>(null);
    const conseguirPelis = async () => {
        const pelis = await fetchPelis();
        setPeliculas(pelis);
    }
    conseguirPelis();
    return (
        <main>
            <div>
                <h2>
                    Peliculas:
                </h2>
                <select className="opciones">
                    {
                        peliculas && (
                            peliculas.map(pelicula => {
                                return (
                                    <option key={pelicula.id}>{pelicula.nombreFuncion}</option>
                                )
                            })
                        )
                    }
                </select>
            </div>
            <Cine />
        </main >
    )

}