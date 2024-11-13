package service;

import modelo.Comentario;
import modelo.Resena;
import modelo.Usuario;

import java.util.Date;

public class ComentarioService {
    private static final ModeradorService moderador = new ModeradorService();

    public Comentario crearComentario(String contenido, Usuario usuario, Resena resena) {
        Comentario comentario = new Comentario();
        comentario.setContenido(contenido);
        comentario.setFechaCreacion(new Date());
        comentario.setUsuario(usuario);
        comentario.setResena(resena);
        //resena.getComentarios().add(comentario); // Asegura que el comentario se añade a la reseña
        return comentario;
    }
    public boolean verificarContenidoOfensivo( String contenidoComentario) {
        return  contieneOfensivo(contenidoComentario);
    }

    private boolean contieneOfensivo(String texto) {
        return moderador.verificarOfensivo(texto);
    }

    public boolean verificarContenidoMax200( String contenidoComentario) {
        return  esTextoConLongitudValida(contenidoComentario);
    }

    private boolean esTextoConLongitudValida(String texto) {
        return moderador.esMenorOIgualA200(texto);
    }
}
