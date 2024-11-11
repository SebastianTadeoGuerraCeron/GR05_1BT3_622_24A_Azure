
package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import modelo.FavoritoPromocion;
import modelo.Promocion;
import modelo.Usuario;
import persistencia.FavoritoPromocionJpaController;
import persistencia.PromocionJpaController;
import service.FavoritoPromocionService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/FavoritoPromocionSv")
public class FavoritoPromocionSv extends HttpServlet {
    private final FavoritoPromocionService favoritoPromocionService = new FavoritoPromocionService();
    private final PromocionJpaController promocionController = new PromocionJpaController();
    private final FavoritoPromocionJpaController favoritoPromocionController = new FavoritoPromocionJpaController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("user");
        Long promocionId = Long.parseLong(request.getParameter("promocionId"));

        Promocion promocion = promocionController.findPromocionWithFavoritos(promocionId);

        // Verificar si el usuario ya ha reaccionado a esta promoción
        boolean haReaccionado = favoritoPromocionController.hasUserReactedToPromocion(usuario.getId(), promocionId);

        if (haReaccionado) {
            // Redirigir con un mensaje de error si ya ha reaccionado
            response.sendRedirect("PromocionSv?reaccionDuplicada=true");
        } else {
            // Crear la reacción "Me Encanta" y persistirla
            FavoritoPromocion favorito = favoritoPromocionService.agregarMeEncanta(promocion, usuario);
            favoritoPromocionController.create(favorito);
            response.sendRedirect("PromocionSv?promocionId=" + promocionId + "&meEncantaSuccess=true");
        }
    }
}