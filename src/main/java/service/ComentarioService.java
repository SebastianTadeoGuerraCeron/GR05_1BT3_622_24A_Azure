package service;

import modelo.Comentario;
import modelo.Resena;
import modelo.Usuario;

import java.util.Date;

public class ComentarioService {

    public Comentario crearComentario(String contenido, Usuario usuario, Resena resena) {
        Comentario comentario = new Comentario();
        comentario.setContenido(contenido);
        comentario.setFechaCreacion(new Date());
        comentario.setUsuario(usuario);
        comentario.setResena(resena);
        resena.getComentarios().add(comentario);
        return comentario;
    }
}
