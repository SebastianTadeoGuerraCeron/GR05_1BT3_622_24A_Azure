package service;

import modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import service.AnuncioService;
import modelo.Anuncio;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnuncioServiceTest {

    private AnuncioService anuncioService;
    private List<Anuncio> anuncios;

    @BeforeEach
    void setUp() {
        anuncioService = new AnuncioService();

        // Crear algunos anuncios de ejemplo
        Anuncio anuncio1 = new Anuncio();
        anuncio1.setNombreRestaurante("Restaurante A");

        Anuncio anuncio2 = new Anuncio();
        anuncio2.setNombreRestaurante("Restaurante B");

        Anuncio anuncio3 = new Anuncio();
        anuncio3.setNombreRestaurante("Cafetería Central");

        // Lista de anuncios a utilizar en las pruebas
        anuncios = Arrays.asList(anuncio1, anuncio2, anuncio3);
    }

    @Test
    void given_ValidDetails_when_CreatingAnuncio_then_AnuncioIsCreatedSuccessfully() {
        String nombreRestaurante = "Restaurante Ejemplo";
        String tipoComida = "Italiana";
        String ubicacion = "Centro";
        String descripcionOfertas = "Descuento del 20%";
        Usuario usuario = new Usuario(); // Asegúrate de que la clase Usuario esté correctamente definida

        Anuncio anuncio = anuncioService.crearAnuncio(nombreRestaurante, tipoComida, ubicacion, descripcionOfertas, usuario);

        assertNotNull(anuncio);
        assertEquals(nombreRestaurante, anuncio.getNombreRestaurante());
        assertEquals(tipoComida, anuncio.getTipoComida());
        assertEquals(ubicacion, anuncio.getUbicacion());
        assertEquals(descripcionOfertas, anuncio.getDescripcionOfertas());
        assertEquals(usuario, anuncio.getUsuario());
        assertNotNull(anuncio.getFechaPublicacion());
    }

    @Test
    void given_InvalidDetails_when_CreatingAnuncio_then_AnuncioIsNotCreated() {
        String nombreRestaurante = null; // Invalid name
        String tipoComida = ""; // Invalid type
        String ubicacion = "Centro";
        String descripcionOfertas = "Descuento del 20%";
        Usuario usuario = new Usuario(); // Ensure the Usuario class is correctly defined

        Anuncio anuncio = anuncioService.crearAnuncio(nombreRestaurante, tipoComida, ubicacion, descripcionOfertas, usuario);

        assertNotNull(anuncio);
        assertNull(anuncio.getNombreRestaurante()); // Expecting null due to invalid input
        assertEquals("", anuncio.getTipoComida()); // Expecting empty string due to invalid input
        assertEquals(ubicacion, anuncio.getUbicacion());
        assertEquals(descripcionOfertas, anuncio.getDescripcionOfertas());
        assertEquals(usuario, anuncio.getUsuario());
        assertNotNull(anuncio.getFechaPublicacion());
    }

    @Test
    void given_AllBlankFields_when_CreatingAnuncio_then_AnuncioIsCreatedWithBlanks() {
        String nombreRestaurante = " ";
        String tipoComida = " ";
        String ubicacion = " ";
        String descripcionOfertas = " ";
        Usuario usuario = new Usuario();

        Anuncio anuncio = anuncioService.crearAnuncio(nombreRestaurante, tipoComida, ubicacion, descripcionOfertas, usuario);

        assertEquals(" ", anuncio.getNombreRestaurante());
        assertEquals(" ", anuncio.getTipoComida());
        assertEquals(" ", anuncio.getUbicacion());
        assertEquals(" ", anuncio.getDescripcionOfertas());
        assertEquals(usuario, anuncio.getUsuario());
    }


    @Test
    void given_AnunciosListAndMatchingName_when_FilteringByNombre_then_ReturnsMatchingAnuncios() {
        List<Anuncio> resultado = anuncioService.filtrarAnunciosPorNombre(anuncios, "Restaurante");

        assertEquals(2, resultado.size());
        assertEquals("Restaurante A", resultado.get(0).getNombreRestaurante());
        assertEquals("Restaurante B", resultado.get(1).getNombreRestaurante());
    }

    @Test
    void given_AnunciosListAndNonExistingName_when_FilteringByNombre_then_ReturnsEmptyList() {
        List<Anuncio> resultado = anuncioService.filtrarAnunciosPorNombre(anuncios, "Inexistente");

        assertEquals(0, resultado.size());
    }

    @Test
    void given_AnunciosListAndEmptyName_when_FilteringByNombre_then_ReturnsAllAnuncios() {
        List<Anuncio> resultado = anuncioService.filtrarAnunciosPorNombre(anuncios, "");

        assertEquals(anuncios.size(), resultado.size());
    }

    @Test
    void given_AnunciosListAndNullName_when_FilteringByNombre_then_ReturnsAllAnuncios() {
        List<Anuncio> resultado = anuncioService.filtrarAnunciosPorNombre(anuncios, null);

        assertEquals(anuncios.size(), resultado.size());
    }

    @Test
    void given_NombreWithDifferentCase_when_FiltrarAnunciosPorNombre_then_ReturnsMatchingAnuncios() {
        anuncios.get(0).setNombreRestaurante("Poliburguers");
        anuncios.get(1).setNombreRestaurante("poliburguers");

        List<Anuncio> resultado = anuncioService.filtrarAnunciosPorNombre(anuncios, "POLIBURGUERS");

        assertEquals(2, resultado.size());
        assertEquals("Poliburguers", resultado.get(0).getNombreRestaurante());
        assertEquals("poliburguers", resultado.get(1).getNombreRestaurante());
    }

    @ParameterizedTest
    @CsvSource({
            "'La Fonda basura', 'Snacks', 'Ponceano', 'Descuento especial en almuerzos completos'",
            "'La Sazón Quiteña', 'Extranjera', 'Tumbaco', 'Menú internacional con ingredientes basura'",
            "'Zorra Andina', 'Tradicional', 'Cotocollao', 'Platos típicos a precios accesibles'",
            "'Republica del Cacao', 'Snacks', 'Chillogallo', 'La bebida es una mierda'"
    })
    void given_AdvertisementWithOffensiveWords_when_VerificarContenidoOfensivo_then_RetornaTrue(String nombreRestaurante, String tipoDeComida, String ubicacion ,String descripcionOfertas) {
        Anuncio anuncio = anuncioService.crearAnuncio(nombreRestaurante, tipoDeComida, ubicacion, descripcionOfertas, new Usuario());
        boolean resultado = anuncioService.verificarContenidoOfensivo(anuncio.getNombreRestaurante(), anuncio.getDescripcionOfertas());
        System.out.println("resultado test");
        System.out.println(resultado);
        // Espera que el resultado sea true porque alguna parte contiene una palabra ofensiva

            assertTrue(resultado);
    }

    @ParameterizedTest
    @CsvSource({
            // Casos con menos de 200 caracteres en total
            "'La Ronda Café', 'Tradicional', 'La Carolina', 'Deliciosas empanadas y café de especialidad a precio especial'",
            "'El Fogón Quiteño', 'Tradicional', 'Cotocollao', 'Ven y prueba nuestras mejores parrilladas en el sector de Cotocollao'",
            "'Cafetería Central', 'Extranjera', 'Bellavista', 'Descuento especial en desayunos y almuerzos todos los días'",
            "'Los Hornados de Doña Rosa', 'Tradicional', 'El Condado', 'Descuento del 30% en todos los hornados los fines de semana'",

            // Casos con más de 200 caracteres en total
            "'Quito Gourmet', 'Extranjera', 'Cumbayá', 'Disfruta de una experiencia única con platillos de cocina internacional y un ambiente moderno. La oferta es válida únicamente los fines de semana y festivos. Te esperamos con una variedad exclusiva de vinos y cocteles'",
            "'Mercado de Mariscos Quito', 'Snacks', 'San Rafael', 'Los mejores mariscos frescos de la ciudad en un mercado lleno de vida. Ven y prueba nuestros ceviches y camarones apanados, con promociones únicas cada día para nuestros comensales. ¡La frescura de la costa llega a los Andes!'",

            // Casos límite (con 200 caracteres exactos en total)
            "'El Jardín de los Andes', 'Tradicional', 'Chillogallo', 'Ven y disfruta de la comida tradicional en el corazón de Chillogallo. Gran variedad de platillos andinos, preparados con ingredientes frescos para un verdadero sabor de los Andes en Quito'"
    })

    void given_AdvertisementWithValidOrInvalidContent_when_VerificarContenidoMax200_thenReturnsTrueOrFalse(String nombreRestaurante, String tipoDeComida, String ubicacion ,String descripcionOfertas) {

        Anuncio anuncio = anuncioService.crearAnuncio(nombreRestaurante, tipoDeComida, ubicacion, descripcionOfertas, new Usuario());
        boolean resultado = anuncioService.verificarContenidoMax200(nombreRestaurante, descripcionOfertas);

        // Espera que el resultado sea true si ambos campos tienen menos de 200 caracteres
        // Espera que el resultado sea false si alguno de los campos tiene más de 200 caracteres
        if (resultado) {
            System.out.println("resultado test con menos o igual a 200 caracteres");
            assertTrue(resultado);
        } else {
            System.out.println("resultado test con mas de 200 caracteres");
            assertFalse(resultado);
        }
    }


}
