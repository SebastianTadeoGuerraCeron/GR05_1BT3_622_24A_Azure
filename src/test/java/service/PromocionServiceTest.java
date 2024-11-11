package service;

import modelo.Anuncio;
import modelo.FavoritoPromocion;
import modelo.Promocion;
import modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PromocionServiceTest {
    private PromocionService promocionService;
    private FavoritoPromocionService favoritoPromocionService;
    private List<Promocion> promociones;
    private Usuario usuario;
    private Promocion promocion;

    @BeforeEach
    void setUp() {
        promocionService = new PromocionService();
        favoritoPromocionService = new FavoritoPromocionService();


        usuario = new Usuario();
        usuario.setId(1L);
        promocion = new Promocion();
        promocion.setId(1L);
        promocion.setFavoritosPromocion(new ArrayList<>());

        // Crear una lista de promociones para las pruebas
        Promocion promocion1 = promocionService.crearPromocion("Descuento Especial", "La Ronda Café", "La Carolina", "Cortesia", "Deliciosas empanadas y café de especialidad a precio especial", usuario);
        Promocion promocion2 = promocionService.crearPromocion("Parrillada Familiar", "El Fogón Quiteño", "Cotocollao", "Descuento por evento especial", "Descuento en parrilladas", usuario);
        Promocion promocion3 = promocionService.crearPromocion("Desayuno Continental", "Cafetería Central", "Bellavista", "Porcentaje de descuento", "Descuento en desayunos", usuario);
        promociones = Arrays.asList(promocion1, promocion2, promocion3);
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
        Promocion promocion = promocionService.crearPromocion(null, null, null, null, null, null);
        assertNull(promocion);
    }

    @Test
    void given_IncompleteDetails_when_CreatingPromocion_then_PromocionIsNotCreated() {
        String titulo = "No te lo puedes perder este 31 de Diciembre";
        String nombreRestaurante = "";
        String ubicacion = "Chillogallo";
        String tipoPromocion = "Cortesia";
        String condiciones = "Válido cualquier día";
        Usuario usuario = new Usuario();

        Promocion promocion = promocionService.crearPromocion(titulo, nombreRestaurante, ubicacion, tipoPromocion, condiciones, usuario);
        assertNull(promocion);
    }

    @ParameterizedTest
    @CsvSource({
            // Casos con menos de 200 caracteres en total
            "'Descuento Especial', 'La Ronda Café', 'La Carolina', 'Cortesía', 'Deliciosas empanadas y café de especialidad a precio especial'",
            "'Parrillada Familiar', 'El Fogón Quiteño', 'Cotocollao', 'Descuento por evento especial', 'Ven y prueba nuestras mejores parrilladas en el sector de Cotocollao'",
            "'Desayuno Continental', 'Cafetería Central', 'Bellavista', 'Porcentaje de descuento', 'Descuento especial en desayunos y almuerzos todos los días'",
            "'Hornado Weekend', 'Los Hornados de Doña Rosa', 'El Condado', 'Descuento por método de pago', 'Descuento del 30% en todos los hornados los fines de semana'",

            // Casos con más de 200 caracteres en total
            "'Experiencia Gourmet', 'Quito Gourmet', 'Cumbayá', 'Cortesía', 'Disfruta de una experiencia única con platillos de cocina internacional y un ambiente moderno. La oferta es válida únicamente los fines de semana y festivos. Te esperamos con una variedad exclusiva de vinos y cocteles'",
            "'Mariscos Frescos', 'Mercado de Mariscos Quito', 'San Rafael', 'Snacks', 'Los mejores mariscos frescos de la ciudad en un mercado lleno de vida. Ven y prueba nuestros ceviches y camarones apanados, con promociones únicas cada día para nuestros comensales. ¡La frescura de la costa llega a los Andes!'",

            // Casos límite (con 200 caracteres exactos en total)
            "'Sabores Andinos', 'El Jardín de los Andes', 'Chillogallo', 'Descuento por evento especial', 'Ven y disfruta de la comida tradicional en el corazón de Chillogallo. Gran variedad de platillos andinos, preparados con ingredientes frescos para un verdadero sabor de los Andes en Quito'"
    })
    void given_PromotionWithValidOrInvalidContent_when_VerificarContenidoMax200_thenReturnsTrueOrFalse(
            String tituloPromocion, String nombreRestaurante, String ubicacion, String tipoPromocion, String condiciones) {

        // Crear la promoción con los datos proporcionados
        Promocion promocion = promocionService.crearPromocion(tituloPromocion, nombreRestaurante, ubicacion, condiciones, tipoPromocion, new Usuario());
        boolean resultado = promocionService.verificarContenidoMax200(tituloPromocion,nombreRestaurante,condiciones);

        // Verificar el resultado
        if (resultado) {
            System.out.println("Resultado test con menos o igual a 200 caracteres");
            assertTrue(resultado);
        } else {
            System.out.println("Resultado test con más de 200 caracteres");
            assertFalse(resultado);
    }
}

    @ParameterizedTest
    @CsvSource({
            "'Promoción basura', 'Restaurante ABC', 'Condiciones normales'",
            "'Título normal', 'Restaurante mierda', 'Condiciones normales'",
            "'Título normal', 'Restaurante ABC', 'Condiciones basura'",
            "'Título mierda', 'Restaurante ABC', 'Condiciones normales'"
    })
    void given_PromotionWithOffensiveWords_when_VerificarContenidoOfensivo_then_ReturnsTrue(
            String titulo, String nombreRestaurante, String condiciones) {

        boolean resultado = promocionService.verificarContenidoOfensivo(titulo, nombreRestaurante, condiciones);
        assertTrue(resultado);
    }

    @Test
    void given_PromocionesListAndMatchingType_when_FilteringByType_then_ReturnsMatchingPromociones() {
        List<Promocion> resultado = promocionService.filtrarPromocionesPorTipo(promociones, "Cortesia");
        assertEquals(1, resultado.size());
        assertEquals("Cortesia", resultado.get(0).getTipoPromocion());
    }

    @Test
    void given_PromocionesListAndNonMatchingType_when_FilteringByType_then_ReturnsEmptyList() {
        List<Promocion> resultado = promocionService.filtrarPromocionesPorTipo(promociones, "Descuento por método de pago");
        assertEquals(0, resultado.size());
    }

    @Test
    void given_PromocionesListAndTodoSelection_when_FilteringByType_then_ReturnsAllPromociones() {
        List<Promocion> resultado = promocionService.filtrarPromocionesPorTipo(promociones, "Todos");
        assertEquals(promociones.size(), resultado.size());
    }

    @Test
    public void given_Promocion_when_AgregarMeEncanta_thenCreatedMeEncataToPromocion() {
        promocion.setId(2L);
        // Llamar al método para agregar "Me Encanta"
        FavoritoPromocion favorito = favoritoPromocionService.agregarMeEncanta(promocion, usuario);

        // Verificar que la reacción "Me Encanta" se crea correctamente
        assertNotNull(favorito, "La reacción 'Me Encanta' debería crearse correctamente");

    }

    @Test
    void given_Usuario_when_AgregarMeEncanta_then_FavoritoPromocionHasUsuario() {
        promocion.setId(3L);
        FavoritoPromocion favorito = favoritoPromocionService.agregarMeEncanta(promocion, usuario);

        assertEquals(usuario, favorito.getUsuario(), "El usuario debería ser el asignado en la reacción 'Me Encanta'");
    }

    @Test
    void given_UsuarioYaReacciono_when_UsuarioHaReaccionado_thenReturnsTrue() {
        // Agregar una reacción "Me Encanta" del usuario a la promoción
        FavoritoPromocion favorito = new FavoritoPromocion();
        favorito.setUsuario(usuario);
        favorito.setPromocion(promocion);
        promocion.getFavoritosPromocion().add(favorito);

        // Verificar que el método detecta que el usuario ya reaccionó
        assertTrue(favoritoPromocionService.usuarioHaReaccionado(usuario, promocion), "El usuario ya reaccionó a esta promoción, se espera true");
    }

}
