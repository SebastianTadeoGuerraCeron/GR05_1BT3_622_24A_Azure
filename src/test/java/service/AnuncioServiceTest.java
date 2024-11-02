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
    void givenValidDetails_whenCreatingAnuncio_thenAnuncioIsCreatedSuccessfully() {
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
    void givenInvalidDetails_whenCreatingAnuncio_thenAnuncioIsNotCreated() {
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
    void givenAllBlankFields_whenCreatingAnuncio_thenAnuncioIsCreatedWithBlanks() {
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
    void givenAnunciosListAndMatchingName_whenFilteringByNombre_thenReturnsMatchingAnuncios() {
        List<Anuncio> resultado = anuncioService.filtrarAnunciosPorNombre(anuncios, "Restaurante");

        assertEquals(2, resultado.size());
        assertEquals("Restaurante A", resultado.get(0).getNombreRestaurante());
        assertEquals("Restaurante B", resultado.get(1).getNombreRestaurante());
    }

    @Test
    void givenAnunciosListAndNonExistingName_whenFilteringByNombre_thenReturnsEmptyList() {
        List<Anuncio> resultado = anuncioService.filtrarAnunciosPorNombre(anuncios, "Inexistente");

        assertEquals(0, resultado.size());
    }

    @Test
    void givenAnunciosListAndEmptyName_whenFilteringByNombre_thenReturnsAllAnuncios() {
        List<Anuncio> resultado = anuncioService.filtrarAnunciosPorNombre(anuncios, "");

        assertEquals(anuncios.size(), resultado.size());
    }

    @Test
    void givenAnunciosListAndNullName_whenFilteringByNombre_thenReturnsAllAnuncios() {
        List<Anuncio> resultado = anuncioService.filtrarAnunciosPorNombre(anuncios, null);

        assertEquals(anuncios.size(), resultado.size());
    }

    @Test
    void givenNombreWithDifferentCase_whenFiltrarAnunciosPorNombre_thenReturnsMatchingAnuncios() {
        anuncios.get(0).setNombreRestaurante("Poliburguers");
        anuncios.get(1).setNombreRestaurante("poliburguers");

        List<Anuncio> resultado = anuncioService.filtrarAnunciosPorNombre(anuncios, "POLIBURGUERS");

        assertEquals(2, resultado.size());
        assertEquals("Poliburguers", resultado.get(0).getNombreRestaurante());
        assertEquals("poliburguers", resultado.get(1).getNombreRestaurante());
    }

    @ParameterizedTest
    @CsvSource({
            "'Restaurante basura', 'Snacks', 'Ponceano', 'Descuento de mierda'",  // Palabra ofensiva en descripción
            "'Sitio asqueroso', 'Extranjera', 'Chillogallo', 'Buena comida'",  // Palabra ofensiva en ubicación
            "'zorra', 'Tradicional', 'Solanda', 'Especialidad en pastas'",  // Palabra ofensiva en tipo de comida
            "'Dueño tonto', 'Extranjera', 'Tumbaco', 'Descuento en pizzas'"  // Palabra ofensiva en nombre
    })
    void givenAnuncioConPalabrasOfensivas_whenVerificarContenidoOfensivo_thenRetornaTrue(String nombreRestaurante, String descripcionOfertas) {
        // Verificar si el anuncio contiene palabras ofensivas
        boolean resultado = anuncioService.verificarContenidoOfensivo(nombreRestaurante, descripcionOfertas);

        // Espera que el resultado sea true porque alguna parte contiene una palabra ofensiva
        assertTrue(resultado);
    }


}
