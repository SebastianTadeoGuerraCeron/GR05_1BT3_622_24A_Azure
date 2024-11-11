package service;

import modelo.Anuncio;
import modelo.Promocion;
import modelo.Usuario;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PromocionService {

    private static final ModeradorService moderador = new ModeradorService();

    public Promocion crearPromocion(String titulo, String nombreRestaurante, String ubicacion, String tipoPromocion, String condiciones, Usuario usuario) {

        // Validar campos obligatorios para que no sean null ni vacíos
        if (titulo == null || titulo.isEmpty() ||
                nombreRestaurante == null || nombreRestaurante.isEmpty() ||
                ubicacion == null || ubicacion.isEmpty() ||
                tipoPromocion == null || tipoPromocion.isEmpty() ||
                condiciones == null || condiciones.isEmpty() ||
                usuario == null) {
            return null;
        }

        Promocion promocion = new Promocion();
        promocion.setTitulo(titulo);
        promocion.setNombreRestaurante(nombreRestaurante);
        promocion.setUbicacion(ubicacion);
        promocion.setTipoPromocion(tipoPromocion);
        promocion.setCondiciones(condiciones);
        promocion.setFechaPublicacion(new Date());
        promocion.setUsuario(usuario); // Asignación directa del usuario a la promoción
        return promocion;
    }

    public List<Promocion> filtrarPromocionesPorTipo(List<Promocion> todasLasPromociones, String tipoPromocion) {
        if (esTipoPromocionInvalido(tipoPromocion)) {
            return todasLasPromociones;
        }
        return todasLasPromociones.stream()
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
        return moderador.verificarCaracteresEspeciales(titulo) || moderador.verificarCaracteresEspeciales(nombreRestaurante);
    }

    public boolean verificarContenidoMax200(String tituloPromocion, String nombreRestaurante,String condiciones) {
        return esTextoValido(tituloPromocion) && esTextoValido(nombreRestaurante) && esTextoValido(condiciones);
    }
    private boolean esTextoValido(String texto) {
        return moderador.esMenorOIgualA200(texto);
    }
}
