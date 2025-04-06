import { useState } from "react"
import { procesarForm } from "../logic/procesarInfo";
import { CineStore } from "../store/CineStore";



export function Navbar() {
    const [modal, showModal] = useState(false);
    const insertarPelicula = CineStore(state => state.insertarPelicula);

    const handleClick = () => {
        showModal(!modal);
    }

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const datosPelicula = procesarForm(e.target as HTMLFormElement);
        insertarPelicula(datosPelicula);
    }
    return (
        <>
            <nav>
                <ul className="botones">
                    <li onClick={handleClick} className="boton">Agregar Función</li>
                </ul>
            </nav>
            {
                modal && (
                    <div id="modal" className="modal">
                        <div className="modal-content">
                            <span className="close" onClick={handleClick}>❌</span>
                            <h3>Datos de la Función</h3>
                            <form id="formFuncion" onSubmit={handleSubmit}>
                                <input type="text" placeholder="Nombre de función" name="nombre" required />
                                <input type="time" placeholder="Hora" name="hora" required />
                                <input type="number" placeholder="Precio" name="precio" step="0.01" required />
                                <input type="date" placeholder="Día" name="dia" required />
                                <button type="submit" className="boton-modal">Guardar</button>
                            </form>
                        </div>
                    </div>
                )
            }

        </>


    )
}