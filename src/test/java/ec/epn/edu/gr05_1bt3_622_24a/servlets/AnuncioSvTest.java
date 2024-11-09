package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import modelo.Anuncio;
import modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.AnuncioService;
import persistencia.AnuncioJpaController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class AnuncioSvTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private AnuncioJpaController anuncioController;

    @Mock
    private AnuncioService anuncioService;

    @InjectMocks
    private AnuncioSv anuncioSv;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenValidParameters_whenDoGet_thenFilteredAnunciosForwarded() throws ServletException, IOException {
        // Crear lista de anuncios de prueba
        List<Anuncio> anuncios = new ArrayList<>();
        Anuncio anuncio1 = new Anuncio();
        anuncio1.setNombreRestaurante("Restaurante A");
        anuncio1.setUbicacion("Centro");
        anuncios.add(anuncio1);

        Anuncio anuncio2 = new Anuncio();
        anuncio2.setNombreRestaurante("Restaurante B");
        anuncio2.setUbicacion("Sur");
        anuncios.add(anuncio2);

        // Configurar mocks para simular el comportamiento de la base de datos
        when(anuncioController.findAnuncioEntities()).thenReturn(anuncios);
        when(request.getParameter("ubicacion")).thenReturn("Centro");
        when(request.getParameter("nombreRestaurante")).thenReturn("Restaurante A");
        when(anuncioService.filtrarAnunciosPorUbicacion(anuncios, "Centro")).thenReturn(List.of(anuncio1));
        when(anuncioService.filtrarAnunciosPorNombre(List.of(anuncio1), "Restaurante A")).thenReturn(List.of(anuncio1));
        when(request.getRequestDispatcher("ListaAnuncio.jsp")).thenReturn(dispatcher);

        // Ejecutar doGet y verificar el comportamiento esperado
        anuncioSv.doGet(request, response);

        verify(request).setAttribute("anuncios", List.of(anuncio1));
        verify(dispatcher).forward(request, response);
    }

    @Test
    void givenValidParameters_whenDoPost_thenAnuncioIsCreatedAndRedirected() throws ServletException, IOException {
        // Mockear los parámetros de la solicitud y el usuario en la sesión
        when(request.getSession(false)).thenReturn(session);

        Usuario mockUsuario = new Usuario();  // Crear usuario mock
        when(session.getAttribute("user")).thenReturn(mockUsuario);

        when(request.getParameter("nombreRestaurante")).thenReturn("Restaurante Ejemplo");
        when(request.getParameter("tipoComida")).thenReturn("Italiana");
        when(request.getParameter("ubicacion")).thenReturn("Centro");
        when(request.getParameter("descripcionOfertas")).thenReturn("Descuento del 20%");

        // Mockear validaciones para que devuelvan false, permitiendo la creación del anuncio
        when(anuncioService.verificarContenidoOfensivo("Restaurante Ejemplo", "Descuento del 20%")).thenReturn(false);
        when(anuncioService.verificarContenidoMax200("Restaurante Ejemplo", "Descuento del 20%")).thenReturn(true);

        // Mockear el anuncio creado por el servicio
        Anuncio mockAnuncio = new Anuncio();
        when(anuncioService.crearAnuncio("Restaurante Ejemplo", "Italiana", "Centro", "Descuento del 20%", mockUsuario))
                .thenReturn(mockAnuncio);

        // Mock del RequestDispatcher para forward a "FormularioAnuncio.jsp"
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("FormularioAnuncio.jsp")).thenReturn(dispatcher);

        // Ejecutar el método doPost
        anuncioSv.doPost(request, response);

        // Verificar que se creó y persistió el anuncio
        verify(anuncioService).crearAnuncio("Restaurante Ejemplo", "Italiana", "Centro", "Descuento del 20%", mockUsuario);
        verify(anuncioController).create(mockAnuncio);

        // Verificar que la respuesta redirige a la URL esperada
        verify(response).sendRedirect("AnuncioSv?anuncioSuccess=true");
    }
}
