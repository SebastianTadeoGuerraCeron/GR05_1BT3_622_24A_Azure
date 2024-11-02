package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        anuncio3.setNombreRestaurante("Cafetería Central"); // Cambiado para evitar coincidencia

        // Lista de anuncios a utilizar en las pruebas
        anuncios = Arrays.asList(anuncio1, anuncio2, anuncio3);
    }

    @Test
    void testFiltrarAnunciosPorNombre_ConCoincidencia() {
        // Filtrar anuncios con el nombre "Restaurante"
        List<Anuncio> resultado = anuncioService.filtrarAnunciosPorNombre(anuncios, "Restaurante");

        // Verificar que solo hay dos coincidencias
        assertEquals(2, resultado.size());
        assertEquals("Restaurante A", resultado.get(0).getNombreRestaurante());
        assertEquals("Restaurante B", resultado.get(1).getNombreRestaurante());
    }

    @Test
    void testFiltrarAnunciosPorNombre_SinCoincidencia() {
        // Filtrar anuncios con un nombre que no existe
        List<Anuncio> resultado = anuncioService.filtrarAnunciosPorNombre(anuncios, "Inexistente");

        // Verificar que no hay coincidencias
        assertEquals(0, resultado.size());
    }

    @Test
    void testFiltrarAnunciosPorNombre_NombreVacio() {
        // Filtrar anuncios con un nombre vacío
        List<Anuncio> resultado = anuncioService.filtrarAnunciosPorNombre(anuncios, "");

        // Verificar que se devuelven todos los anuncios
        assertEquals(anuncios.size(), resultado.size());
    }

    @Test
    void testFiltrarAnunciosPorNombre_NombreNull() {
        // Filtrar anuncios con un nombre nulo
        List<Anuncio> resultado = anuncioService.filtrarAnunciosPorNombre(anuncios, null);

        // Verificar que se devuelven todos los anuncios
        assertEquals(anuncios.size(), resultado.size());
    }
}
