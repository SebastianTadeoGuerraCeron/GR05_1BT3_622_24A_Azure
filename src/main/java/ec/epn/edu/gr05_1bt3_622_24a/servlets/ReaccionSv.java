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

// Verificar si el usuario ya ha reaccionado
        if (reaccionController.hasUserReactedToResena(usuario.getId(), resenaId, "like")) {
            // El usuario ya reaccionó con un like
            if ("like".equals(tipoReaccion)) {
                // Si el usuario intenta dar otro like
                response.sendRedirect("ResenaSv?showPopup=true&errorMessage=No%20puedes%20reaccionar%20mas%20de%20una%20vez%20positivamente.");
                return;
            } else {
                // Si el usuario intenta cambiar a dislike
                response.sendRedirect("ResenaSv?showPopup=true&errorMessage=Usted%20ya%20ha%20reaccionado%20positivamente%20a%20esta%20resena.");
                return;
            }
        } else if (reaccionController.hasUserReactedToResena(usuario.getId(), resenaId, "dislike")) {
            // El usuario ya reaccionó con un dislike
            if ("dislike".equals(tipoReaccion)) {
                // Si el usuario intenta dar otro dislike
                response.sendRedirect("ResenaSv?showPopup=true&errorMessage=No%20puedes%20reaccionar%20mas%20de%20una%20vez%20negativamente.");
                return;
            } else {
                // Si el usuario intenta cambiar a like
                response.sendRedirect("ResenaSv?showPopup=true&errorMessage=Usted%20ya%20ha%20reaccionado%20negativamente%20a%20esta%20resena.");
                return;
            }
        }

        Reaccion reaccion = reaccionService.crearReaccion(usuario, resena, tipoReaccion);

        reaccionController.create(reaccion);
        response.sendRedirect("ResenaSv?resenaId=" + resenaId);
    }

}
