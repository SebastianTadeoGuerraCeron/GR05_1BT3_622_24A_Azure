package service;

import modelo.Reaccion;
import modelo.Resena;
import modelo.Usuario;
import persistencia.ReaccionJpaController;

public class ReaccionService {
    ReaccionJpaController reaccionController = new ReaccionJpaController();
    public Reaccion crearReaccion(Usuario usuario, Resena resena, String tipoReaccion) {
        Reaccion reaccion = new Reaccion();
        reaccion.setUsuario(usuario);
        reaccion.setResena(resena);
        reaccion.setTipo(tipoReaccion);
        //resena.getReacciones().add(reaccion);
        return reaccion;
    }

    public boolean usuarioHaReaccionado(Usuario usuario, Resena resena) {
        return reaccionController.hasUserReactedToResena(usuario.getId(), resena.getId());
    }
}
