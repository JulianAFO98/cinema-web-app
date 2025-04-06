import { Funcion } from "../types"


interface CineProps {
    peliculaFiltrada: Funcion | undefined,
    reservarAsiento: (idPelicula: number, numeroAsiento: number) => void;
}



export function Cine({ peliculaFiltrada, reservarAsiento }: CineProps) {

    const handleClick = (id: number, numeroAsiento: number) => {
        reservarAsiento(id, numeroAsiento);
    }

    return (
        <div className="cine">
            <div className="contenedor-pantalla">
                <div className="pantalla"><h2>Pantalla 😎</h2></div>
            </div>
            <div className="asientos">
                {peliculaFiltrada && (
                    peliculaFiltrada.asientos.map((asiento, index) => {
                        return (
                            <div className={`asiento ${asiento.ocupado ? "red" : "green"}`} onClick={() => handleClick(peliculaFiltrada.id, asiento.numero)} key={index}>{asiento.numero}</div>
                        )
                    })
                )
                }
            </div>
        </div>
    )
}