package service;

import modelo.Promocion;
import modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PromocionServiceTest {
    private PromocionService promocionService;

    @BeforeEach
    void setUp() {
        promocionService = new PromocionService();
    }

    @Test
    void given_ValidDetails_when_CreatingPromocion_then_PromocionIsCreatedSuccessfully() {
        String titulo = "Promoción Especial";
        String nombreRestaurante = "Restaurante La Valentina";
        String ubicacion = "Chillogallo";
        String tipoPromocion = "Cortesia";
        String condiciones = "Válido solo los fines de semana";
        Usuario usuario = new Usuario(); // Asegúrate de que la clase Usuario esté correctamente definida

        Promocion promocion = promocionService.crearPromocion(titulo, nombreRestaurante, ubicacion, tipoPromocion, condiciones, usuario);

        assertNotNull(promocion);
        assertEquals(titulo, promocion.getTitulo());
        assertEquals(nombreRestaurante, promocion.getNombreRestaurante());
        assertEquals(ubicacion, promocion.getUbicacion());
        assertEquals(tipoPromocion, promocion.getTipoPromocion());
        assertEquals(condiciones, promocion.getCondiciones());
        assertEquals(usuario, promocion.getUsuario());
        assertNotNull(promocion.getFechaPublicacion());
    }

    @Test
    void given_AllNullFields_when_CreatingPromocion_then_PromocionIsNotCreated() {
        // Todos los parámetros son null
        Promocion promocion = promocionService.crearPromocion(null, null, null, null, null, null);

        // No se debe crear la promoción, se espera null
        assertNull(promocion);
    }

    @Test
    void given_IncompleteDetails_when_CreatingPromocion_then_PromocionIsNotCreated() {
        // Falta un campo obligatorio (nombreRestaurante) y otro campo (tipoPromocion)
        String titulo = "No te lo puedes perder este 31 de Diciembre";
        String nombreRestaurante = "";
        String ubicacion = "Chillogallo";
        String tipoPromocion = "Cortesia";
        String condiciones = "Válido cualquier día";
        Usuario usuario = new Usuario();

        Promocion promocion = promocionService.crearPromocion(titulo, nombreRestaurante, ubicacion, tipoPromocion, condiciones, usuario);

        // No se debe crear la promoción, se espera null
        assertNull(promocion);
    }
}

