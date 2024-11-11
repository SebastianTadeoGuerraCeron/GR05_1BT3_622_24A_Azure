package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import modelo.Promocion;
import modelo.Usuario;
import persistencia.PromocionJpaController;
import service.PromocionService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/PromocionSv")
public class PromocionSv extends HttpServlet {
    private final PromocionJpaController promocionController;
    private final PromocionService promocionService;

    public PromocionSv() {
        this.promocionController = new PromocionJpaController();
        this.promocionService = new PromocionService();
    }


    public PromocionSv(PromocionJpaController promocionController, PromocionService promocionService) {
        this.promocionController = promocionController;
        this.promocionService = promocionService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el tipo de promoción del parámetro de solicitud
        String tipoPromocion = request.getParameter("tipoPromocion");

        // Obtener todas las promociones directamente desde el controlador
        List<Promocion> promociones = promocionController.findPromocionesEntities();

        // Aplicar filtro de tipo de promoción usando PromocionService si es necesario
        if (tipoPromocion != null && !tipoPromocion.isEmpty() && !tipoPromocion.equalsIgnoreCase("Todos")) {
            promociones = promocionService.filtrarPromocionesPorTipo(promociones, tipoPromocion);
        }

        // Verificar si la promoción fue agregada con éxito y mostrar el mensaje
        String promocionSuccess = request.getParameter("promocionSuccess");
        if ("true".equals(promocionSuccess)) {
            request.setAttribute("successMessage", "¡Promoción agregada exitosamente!");
        }

        // Pasar la lista de promociones (filtrada o completa) a la vista
        request.setAttribute("promociones", promociones);
        request.getRequestDispatcher("ListaPromocion.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("user");

        // Obtener datos del formulario
        String titulo = request.getParameter("titulo");
        String nombreRestaurante = request.getParameter("restaurante");
        String ubicacion = request.getParameter("ubicacion");
        String tipoPromocion = request.getParameter("tipoPromocion");
        String condiciones = request.getParameter("condiciones");


        if (promocionService.verificarContenidoOfensivo(titulo,nombreRestaurante, condiciones)) {
            request.setAttribute("errorMessage", "El anuncio contiene palabras ofensivas y no se ha publicado.");
            request.getRequestDispatcher("FormularioPromocion.jsp").forward(request, response);
            return;
        }

        if (!promocionService.verificarContenidoMax200(titulo,nombreRestaurante,condiciones)) {
            request.setAttribute("errorLengthMessage", "El anuncio excede los 200 caracteres y no se ha publicado.");
            request.getRequestDispatcher("FormularioPromocion.jsp").forward(request, response);
            return;
        }


        // Crear la promoción utilizando PromocionService
        Promocion promocion = promocionService.crearPromocion(titulo, nombreRestaurante, ubicacion, tipoPromocion, condiciones, usuario);

        // Guardar la promoción en la base de datos
        promocionController.create(promocion);

        // Redirigir para mostrar la lista actualizada de promociones
        response.sendRedirect("PromocionSv?promocionSuccess=true");
    }
}
