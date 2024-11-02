package service;

import modelo.Anuncio;
import modelo.Usuario;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AnuncioService {
    private static final ModeradorService moderador = new ModeradorService();
    // Método para crear un anuncio con los datos proporcionados
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
        boolean nombreRestauranteOfensivo = moderador.verificarOfensivo(nombreRestaurante);
        boolean descripcionOfertasOfensivo = moderador.verificarOfensivo(descripcionOfertas);
        return nombreRestauranteOfensivo || descripcionOfertasOfensivo;
    }


    public boolean verificarContenidoMax200(String nombreRestaurante, String descripcionOfertas) {
        return moderador.esMenorOIgualA200(nombreRestaurante) &&
                moderador.esMenorOIgualA200(descripcionOfertas) ;
    }


    // Método para filtrar anuncios por ubicación (recibe una lista de anuncios)
    public List<Anuncio> filtrarAnunciosPorUbicacion(List<Anuncio> todosLosAnuncios, String ubicacion) {
        if (ubicacion == null || ubicacion.equals("Todas")) {
            return todosLosAnuncios;
        }
        return todosLosAnuncios.stream()
                .filter(anuncio -> anuncio.getUbicacion().equalsIgnoreCase(ubicacion))
                .collect(Collectors.toList());
    }

    // Método para filtrar anuncios por nombre del restaurante (recibe una lista de anuncios)
    public List<Anuncio> filtrarAnunciosPorNombre(List<Anuncio> todosLosAnuncios, String nombreRestaurante) {
        if (nombreRestaurante == null || nombreRestaurante.trim().isEmpty()) {
            return todosLosAnuncios;
        }
        return todosLosAnuncios.stream()
                .filter(anuncio -> anuncio.getNombreRestaurante().toLowerCase().contains(nombreRestaurante.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Método para obtener todos los anuncios de una fuente externa (proporcionado como argumento)
    public List<Anuncio> obtenerAnuncios(List<Anuncio> fuenteDeAnuncios) {
        return fuenteDeAnuncios;
    }

    // Método para guardar un anuncio en una fuente externa
    // Este método devuelve el anuncio guardado, y sería implementado en una capa de persistencia fuera del servicio
    public Anuncio guardarAnuncio(Anuncio anuncio, List<Anuncio> fuenteDeAnuncios) {
        fuenteDeAnuncios.add(anuncio);
        return anuncio;
    }
}
