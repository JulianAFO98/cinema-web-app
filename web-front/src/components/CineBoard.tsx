import { useCineHook } from "../hooks/useCineHook";
import { Cine } from "./Cine";



export function CineBoard() {
    const { peliculas, peliculaFiltrada, setFilterId, reservarAsiento, liberarAsiento } = useCineHook();
    return (
        <main>
            <div>
                <h2>
                    Peliculas:
                </h2>
                <select className="opciones" onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setFilterId(Number(e.target.value))}>
                    <option key={"No value"}>Select a film</option>
                    {
                        peliculas && (
                            peliculas.map(pelicula => {
                                return (
                                    <option key={pelicula.id} value={pelicula.id}>{pelicula.nombreFuncion}</option>
                                )
                            })
                        )
                    }
                </select>
            </div>
            <Cine peliculaFiltrada={peliculaFiltrada} reservarAsiento={reservarAsiento} liberarAsiento={liberarAsiento} />
        </main >
    )

}