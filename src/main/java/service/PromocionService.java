package service;

import modelo.Promocion;
import modelo.Usuario;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PromocionService {

    private static final ModeradorService moderador = new ModeradorService();

    public Promocion crearPromocion(String titulo, String nombreRestaurante, String ubicacion,
                                    String tipoPromocion, String condiciones, Usuario usuario) {

        if (camposInvalidos(titulo, nombreRestaurante, ubicacion, tipoPromocion, condiciones)) {
            return null;
        }

        Promocion promocion = new Promocion();
        promocion.setTitulo(titulo);
        promocion.setNombreRestaurante(nombreRestaurante);
        promocion.setUbicacion(ubicacion);
        promocion.setTipoPromocion(tipoPromocion);
        promocion.setCondiciones(condiciones);
        promocion.setFechaPublicacion(new Date());
        promocion.setUsuario(usuario);
        return promocion;
    }

    private boolean camposInvalidos(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.isEmpty()) return true;
        }
        return false;
    }

    public List<Promocion> filtrarPromocionesPorTipo(List<Promocion> todasLasPromociones, String tipoPromocion) {
        return esTipoPromocionInvalido(tipoPromocion)
                ? todasLasPromociones
                : todasLasPromociones.stream()
                .filter(promocion -> promocion.getTipoPromocion().equalsIgnoreCase(tipoPromocion))
                .collect(Collectors.toList());
    }

    private boolean esTipoPromocionInvalido(String tipoPromocion) {
        return tipoPromocion == null || tipoPromocion.equalsIgnoreCase("Todos");
    }

    public boolean verificarContenidoOfensivo(String titulo, String nombreRestaurante, String condiciones) {
        return esOfensivo(titulo) || esOfensivo(nombreRestaurante) || esOfensivo(condiciones);
    }

    private boolean esOfensivo(String texto) {
        return moderador.verificarOfensivo(texto);
    }

    public boolean verificarContenidoCaracteresEspeciales(String titulo, String nombreRestaurante) {
        return contieneCaracteresEspeciales(titulo) || contieneCaracteresEspeciales(nombreRestaurante);
    }

    private boolean contieneCaracteresEspeciales(String texto) {
        return moderador.verificarCaracteresEspeciales(texto);
    }

    public boolean verificarContenidoMax200(String tituloPromocion, String nombreRestaurante, String condiciones) {
        return textoDentroDeLimite(tituloPromocion) && textoDentroDeLimite(nombreRestaurante) && textoDentroDeLimite(condiciones);
    }

    private boolean textoDentroDeLimite(String texto) {
        return moderador.esMenorOIgualA200(texto);
    }
}
