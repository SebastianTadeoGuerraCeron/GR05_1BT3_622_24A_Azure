package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import org.hibernate.Hibernate;
import persistencia.ResenaJpaController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Resena;
import modelo.Usuario;
import service.ResenaService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/ResenaSv")
public class ResenaSv extends HttpServlet {
    private final ResenaJpaController resenaController = new ResenaJpaController();
    private final ResenaService resenaService = new ResenaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipoComida = request.getParameter("tipoComida");
        List<Resena> resenas;

        // Obtener todas las reseñas
        if (tipoComida == null || tipoComida.isEmpty() || tipoComida.equals("Todas")) {
            resenas = resenaService.obtenerResenas();
        } else {
            resenas = resenaService.obtenerResenasPorTipo(tipoComida);
        }

        // Inicializar explícitamente la colección `reacciones` de cada `Resena`
        for (Resena resena : resenas) {
            Hibernate.initialize(resena.getReacciones());
        }

        // Configurar atributos para el JSP
        request.setAttribute("resenas", resenas);

        // Verificar si se debe mostrar el pop-up de error
        String showPopup = request.getParameter("showPopup");
        String errorMessage = request.getParameter("errorMessage");
        if ("true".equals(showPopup)) {
            request.setAttribute("showPopup", true);
            request.setAttribute("errorMessage", errorMessage);
        }

        request.getRequestDispatcher("ListaResenas.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("user");

        String restaurante = request.getParameter("restaurante");
        String tipoComida = request.getParameter("tipoComida");
        String descripcion = request.getParameter("descripcion");

        if (resenaService.verificarContenidoOfensivo(restaurante, descripcion)) {
            request.setAttribute("errorMessage", "La reseña contiene palabras ofensivas y no se ha publicado.");
            request.getRequestDispatcher("FormularioResena.jsp").forward(request, response);
            return;
        }

        if (!resenaService.verificarContenidoMax200(restaurante, descripcion)) {
            request.setAttribute("errorLengthMessage", "La reseña excede los 200 caracteres y no se ha publicado.");
            request.getRequestDispatcher("FormularioResena.jsp").forward(request, response);
            return;
        }

        Resena resena = resenaService.crearResena(restaurante, tipoComida, descripcion, usuario);

        resenaController.create(resena);

        response.sendRedirect("ResenaSv");
    }
}
