package service;

import modelo.Anuncio;
import modelo.Usuario;
import persistencia.AnuncioJpaController;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AnuncioService {
    private final AnuncioJpaController anuncioController = new AnuncioJpaController();

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

    public void guardarAnuncio(Anuncio anuncio) {
        anuncioController.create(anuncio);
    }

    public List<Anuncio> obtenerAnuncios() {
        return anuncioController.findAnuncioEntities();
    }

    
    public List<Anuncio> filtrarAnunciosPorUbicacion(List<Anuncio> todosLosAnuncios, String ubicacion) {
        if (ubicacion == null || ubicacion.equals("Todas")) {
            return todosLosAnuncios;
        }
        return todosLosAnuncios.stream()
                .filter(anuncio -> anuncio.getUbicacion().equalsIgnoreCase(ubicacion))
                .collect(Collectors.toList());
    }

    public List<Anuncio> filtrarAnunciosPorNombre(List<Anuncio> todosLosAnuncios, String nombreRestaurante) {
        if (nombreRestaurante == null || nombreRestaurante.trim().isEmpty()) {
            return todosLosAnuncios;
        }
        return todosLosAnuncios.stream()
                .filter(anuncio -> anuncio.getNombreRestaurante().toLowerCase().contains(nombreRestaurante.toLowerCase()))
                .collect(Collectors.toList());
    }


}
