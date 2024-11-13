package service;

import modelo.Resena;
import modelo.Usuario;
import persistencia.ResenaJpaController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ResenaService {
    private final List<Resena> listaResenas = new ArrayList<>();
    private final ResenaJpaController resenaController = new ResenaJpaController();
    private static final ModeradorService moderador = new ModeradorService();


    public Resena crearResena(String restaurante, String tipoComida, String descripcion, Usuario usuario) {
        Resena resena = new Resena();
        resena.setRestaurante(restaurante);
        resena.setTipoComida(tipoComida);
        resena.setDescripcion(descripcion);
        resena.setFechaCreacion(new Date());
        resena.setUsuario(usuario);
        //usuario.getResenas().add(resena);
        return resena;
    }

    public List<Resena> filtrarResenasPorTipo(List<Resena> todasLasResenas, String tipoComida) {
        if (tipoComida == null || tipoComida.equals("Todas")) {
            return todasLasResenas;
        }
        return todasLasResenas.stream()
                .filter(resena -> resena.getTipoComida().equalsIgnoreCase(tipoComida))
                .toList();
    }

    public List<Resena> obtenerResenas() {
        return resenaController.findResenaEntities();
    }

    public List<Resena> obtenerResenasPorTipo(String tipoComida) {
        return resenaController.findResenasByTipoComidaWithReactions(tipoComida);
    }

    public boolean verificarContenidoOfensivo(String nombreRestaurante, String descripcionResena) {
        return contieneOfensivo(nombreRestaurante) || contieneOfensivo(descripcionResena);
    }

    private boolean contieneOfensivo(String texto) {
        return moderador.verificarOfensivo(texto);
    }

    public boolean verificarContenidoMax200(String nombreRestaurante, String descripcionResena) {
        return esTextoConLongitudValida(nombreRestaurante) && esTextoConLongitudValida(descripcionResena);
    }

    private boolean esTextoConLongitudValida(String texto) {
        return moderador.esMenorOIgualA200(texto);
    }
}
