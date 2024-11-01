package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import persistencia.ReaccionJpaController;
import persistencia.ResenaJpaController;
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
import service.ReaccionService;

import java.io.IOException;

@WebServlet("/ReaccionSv")
public class ReaccionSv extends HttpServlet {
    private final ReaccionService reaccionService = new ReaccionService();
    private final ReaccionJpaController reaccionController = new ReaccionJpaController();
    private final ResenaJpaController resenaController = new ResenaJpaController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("user");
        Long resenaId = Long.parseLong(request.getParameter("resenaId"));
        String tipoReaccion = request.getParameter("tipo"); // "like" o "dislike"

        Resena resena = resenaController.findResena(resenaId);
        if (reaccionController.hasUserReactedToResena(usuario.getId(), resenaId)) {
            response.sendRedirect("ResenaSv?showPopup=true&errorMessage=Ya%20has%20reaccionado%20a%20esta%20resena.");
            return;
        }


        Reaccion reaccion = reaccionService.crearReaccion(usuario, resena, tipoReaccion);

        reaccionController.create(reaccion);
        response.sendRedirect("ResenaSv?resenaId=" + resenaId);
    }

}
