package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import modelo.Promocion;
import modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import persistencia.PromocionJpaController;
import service.PromocionService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class PromocionSvTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private PromocionJpaController promocionController;

    @Mock
    private PromocionService promocionService;

    @InjectMocks
    private PromocionSv promocionSv;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenValidParameters_whenDoGet_thenFilteredPromocionesForwarded() throws ServletException, IOException {
        // Crear lista de promociones de prueba
        List<Promocion> promociones = new ArrayList<>();
        Promocion promocion1 = new Promocion();
        promocion1.setTipoPromocion("Porcentaje de descuento");
        promociones.add(promocion1);

        Promocion promocion2 = new Promocion();
        promocion2.setTipoPromocion("Descuento por evento especial");
        promociones.add(promocion2);

        // Configurar mocks para simular el comportamiento de la base de datos y el filtro
        when(promocionController.findPromocionesEntities()).thenReturn(promociones);
        when(request.getParameter("tipoPromocion")).thenReturn("Porcentaje de descuento");
        when(promocionService.filtrarPromocionesPorTipo(promociones, "Porcentaje de descuento")).thenReturn(List.of(promocion1));
        when(request.getRequestDispatcher("ListaPromocion.jsp")).thenReturn(dispatcher);

        // Ejecutar doGet y verificar el comportamiento esperado
        promocionSv.doGet(request, response);

        verify(request).setAttribute("promociones", List.of(promocion1));
        verify(dispatcher).forward(request, response);
    }

    @Test
    void givenValidParameters_whenDoPost_thenPromocionIsCreatedAndRedirected() throws ServletException, IOException {
        // Mockear los parámetros de la solicitud y el usuario en la sesión
        when(request.getSession(false)).thenReturn(session);

        Usuario mockUsuario = new Usuario();  // Crear usuario mock
        when(session.getAttribute("user")).thenReturn(mockUsuario);

        when(request.getParameter("titulo")).thenReturn("Promoción Especial");
        when(request.getParameter("restaurante")).thenReturn("Restaurante Ejemplo");
        when(request.getParameter("ubicacion")).thenReturn("Centro");
        when(request.getParameter("tipoPromocion")).thenReturn("Porcentaje de descuento");
        when(request.getParameter("condiciones")).thenReturn("Aplicable de lunes a viernes");

        // Configurar mocks de validación
        when(promocionService.verificarContenidoOfensivo("Promoción Especial", "Restaurante Ejemplo", "Aplicable de lunes a viernes")).thenReturn(false);
        when(promocionService.verificarContenidoMax200("Promoción Especial", "Restaurante Ejemplo", "Aplicable de lunes a viernes")).thenReturn(true);

        // Mock para crear la promoción con los valores dados
        Promocion mockPromocion = new Promocion();
        when(promocionService.crearPromocion("Promoción Especial", "Restaurante Ejemplo", "Centro", "Porcentaje de descuento", "Aplicable de lunes a viernes", mockUsuario))
                .thenReturn(mockPromocion);

        // Ejecutar el método doPost
        promocionSv.doPost(request, response);

        // Verificar que el servicio crea la promoción y que se guarda en el controlador
        verify(promocionService).crearPromocion("Promoción Especial", "Restaurante Ejemplo", "Centro", "Porcentaje de descuento", "Aplicable de lunes a viernes", mockUsuario);
        verify(promocionController).create(mockPromocion);

        // Verificar que se redirige a la URL esperada
        verify(response).sendRedirect("PromocionSv?promocionSuccess=true");
    }

}