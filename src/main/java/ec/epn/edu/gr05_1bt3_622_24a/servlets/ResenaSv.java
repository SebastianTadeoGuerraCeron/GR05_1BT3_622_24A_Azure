package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import controlador.ResenaJpaController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Resena;
import modelo.Usuario;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/ResenaSv")
public class ResenaSv extends HttpServlet {
    private final ResenaJpaController resenaController = new ResenaJpaController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipoComida = request.getParameter("tipoComida");
        List<Resena> resenas;

        // Obtener todas las reseñas
        if (tipoComida == null || tipoComida.isEmpty() || tipoComida.equals("Todas")) {
            resenas = resenaController.findResenaEntities();
        } else {
            resenas = resenaController.findResenasByTipoComidaWithReactions(tipoComida);
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

        Resena resena = new Resena();
        resena.setRestaurante(restaurante);
        resena.setTipoComida(tipoComida);
        resena.setDescripcion(descripcion);
        resena.setFechaCreacion(new Date());
        resena.setUsuario(usuario);

        resenaController.create(resena);

        response.sendRedirect("ResenaSv");
    }
}
