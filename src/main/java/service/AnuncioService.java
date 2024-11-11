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
        anuncio.setUsuario(usuario);
        return anuncio;
    }

    public List<Anuncio> filtrarAnunciosPorUbicacion(List<Anuncio> todosLosAnuncios, String ubicacion) {
        return esUbicacionValida(ubicacion)
                ? todosLosAnuncios.stream()
                .filter(anuncio -> anuncio.getUbicacion().equalsIgnoreCase(ubicacion))
                .collect(Collectors.toList())
                : todosLosAnuncios;
    }

    private boolean esUbicacionValida(String ubicacion) {
        return ubicacion != null && !ubicacion.equals("Todas");
    }

    public List<Anuncio> filtrarAnunciosPorNombre(List<Anuncio> todosLosAnuncios, String nombreRestaurante) {
        return esNombreValido(nombreRestaurante)
                ? todosLosAnuncios.stream()
                .filter(anuncio -> anuncio.getNombreRestaurante().toLowerCase().contains(nombreRestaurante.toLowerCase()))
                .collect(Collectors.toList())
                : todosLosAnuncios;
    }

    private boolean esNombreValido(String nombreRestaurante) {
        return nombreRestaurante != null && !nombreRestaurante.trim().isEmpty();
    }

    public boolean verificarContenidoOfensivo(String nombreRestaurante, String descripcionOfertas) {
        return contieneOfensivo(nombreRestaurante) || contieneOfensivo(descripcionOfertas);
    }

    private boolean contieneOfensivo(String texto) {
        return moderador.verificarOfensivo(texto);
    }

    public boolean verificarContenidoCaracteresEspeciales(String nombreRestaurante) {
        return moderador.verificarCaracteresEspeciales(nombreRestaurante);
    }

    public boolean verificarContenidoMax200(String nombreRestaurante, String descripcionOfertas) {
        return esTextoConLongitudValida(nombreRestaurante) && esTextoConLongitudValida(descripcionOfertas);
    }

    private boolean esTextoConLongitudValida(String texto) {
        return moderador.esMenorOIgualA200(texto);
    }
}
