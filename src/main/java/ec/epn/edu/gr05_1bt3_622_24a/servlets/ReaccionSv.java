package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import controlador.ReaccionJpaController;
import controlador.ResenaJpaController;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Reaccion;
import modelo.Resena;
import modelo.Usuario;

import java.io.IOException;

@WebServlet("/ReaccionSv")
public class ReaccionSv extends HttpServlet {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaWebLasHuequitas");
    private final ReaccionJpaController reaccionController = new ReaccionJpaController(emf);
    private final ResenaJpaController resenaController = new ResenaJpaController(emf);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("user");

        Long resenaId = Long.parseLong(request.getParameter("resenaId"));
        String tipoReaccion = request.getParameter("tipo"); // Puede ser "like" o "dislike"

        Resena resena = resenaController.findResena(resenaId);

        // Verificar si el usuario ya ha reaccionado a esta reseña
        if (reaccionController.hasUserReactedToResena(usuario.getId(), resenaId)) {
            request.setAttribute("errorMessage", "Ya has reaccionado a esta reseña.");
            request.getRequestDispatcher("ListaResenas.jsp").forward(request, response);
            return;
        }

        // Crear y guardar una nueva reacción
        Reaccion reaccion = new Reaccion();
        reaccion.setUsuario(usuario);
        reaccion.setResena(resena);
        reaccion.setTipo(tipoReaccion);

        reaccionController.create(reaccion);

        response.sendRedirect("ResenaSv?resenaId=" + resenaId);
    }
}
