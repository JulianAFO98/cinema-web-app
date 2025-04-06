import { CineProps } from "../types";



export function Cine({ peliculaFiltrada, reservarAsiento, liberarAsiento }: CineProps) {

    const handleClick = (id: number, numeroAsiento: number, ocupado: boolean) => {
        ocupado ? liberarAsiento(id, numeroAsiento) : reservarAsiento(id, numeroAsiento);
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
                            <div className={`asiento ${asiento.ocupado ? "red" : "green"}`} onClick={() => handleClick(peliculaFiltrada.id, asiento.numero, asiento.ocupado)} key={index}>{asiento.numero}</div>
                        )
                    })
                )
                }
            </div>
        </div>
    )
}