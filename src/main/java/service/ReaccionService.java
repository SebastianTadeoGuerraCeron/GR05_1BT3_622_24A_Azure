package service;

import modelo.Reaccion;
import modelo.Resena;
import modelo.Usuario;

public class ReaccionService {

    public Reaccion crearReaccion(Usuario usuario, Resena resena, String tipoReaccion) {
        Reaccion reaccion = new Reaccion();
        reaccion.setUsuario(usuario);
        reaccion.setResena(resena);
        reaccion.setTipo(tipoReaccion);
        //resena.getReacciones().add(reaccion); // Añadir la reacción a la reseña
        return reaccion;
    }

    public boolean usuarioHaReaccionado(Usuario usuario, Resena resena) {
        return resena.getReacciones().stream()
                .anyMatch(reaccion -> reaccion.getUsuario().equals(usuario));
    }
}
