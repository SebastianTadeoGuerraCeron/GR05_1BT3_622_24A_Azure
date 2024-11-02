package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.AnuncioService;
import modelo.Anuncio;

import java.util.Arrays;
import java.util.List;

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
}
