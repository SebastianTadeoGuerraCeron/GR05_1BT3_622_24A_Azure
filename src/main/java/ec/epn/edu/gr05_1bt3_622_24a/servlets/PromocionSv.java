package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import modelo.Promocion;
import modelo.Usuario;
import persistencia.PromocionJpaController;
import service.AnuncioService;
import persistencia.AnuncioJpaController;

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

    public PromocionSv(PromocionJpaController promocionController) {
        this.promocionController = promocionController;
    }
    public PromocionSv() {
        this.promocionController = new PromocionJpaController();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener todos los anuncios directamente desde el controlador
        List<Promocion> promociones = promocionController.findPromocionesEntities();

        request.setAttribute("promociones", promociones);
        request.getRequestDispatcher("ListaPromocion.jsp").forward(request, response);
    }
}