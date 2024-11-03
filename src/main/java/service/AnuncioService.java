package service;

import modelo.Anuncio;
import modelo.Usuario;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AnuncioService {

    private static final ModeradorService moderador = new ModeradorService();

    public Anuncio crearAnuncio(String nombreRestaurante, String tipoComida, String ubicacion, String descripcionOfertas, Usuario usuario) {
        Anuncio anuncio = new Anuncio();
        anuncio.setNombreRestaurante(nombreRestaurante);
        anuncio.setTipoComida(tipoComida);
        anuncio.setUbicacion(ubicacion);
        anuncio.setDescripcionOfertas(descripcionOfertas);
        anuncio.setFechaPublicacion(new Date());
        anuncio.setUsuario(usuario); // Asignación directa del usuario al anuncio
        return anuncio;
    }

    public boolean verificarContenidoOfensivo(String nombreRestaurante, String descripcionOfertas) {
        return esOfensivo(nombreRestaurante) || esOfensivo(descripcionOfertas);
    }

    private boolean esOfensivo(String texto) {
        return moderador.verificarOfensivo(texto);
    }


    public boolean verificarContenidoMax200(String nombreRestaurante, String descripcionOfertas) {
        return esTextoValido(nombreRestaurante) && esTextoValido(descripcionOfertas);
    }

    private boolean esTextoValido(String texto) {
        return moderador.esMenorOIgualA200(texto);
    }

}
