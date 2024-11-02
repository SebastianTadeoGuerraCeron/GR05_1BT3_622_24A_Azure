package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import modelo.Anuncio;
import modelo.Usuario;
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

@WebServlet("/AnuncioSv")
public class AnuncioSv extends HttpServlet {
    private final AnuncioJpaController anuncioController = new AnuncioJpaController();
    private final AnuncioService anuncioService = new AnuncioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ubicacion = request.getParameter("ubicacion");
        String nombreRestaurante = request.getParameter("nombreRestaurante");
        List<Anuncio> anuncios;

        // Obtener todos los anuncios
        anuncios = anuncioService.obtenerAnuncios();

        if (ubicacion != null && !ubicacion.isEmpty() && !ubicacion.equals("Todas")) {
            anuncios = anuncioService.filtrarAnunciosPorUbicacion(anuncios, ubicacion);
        }

        if (nombreRestaurante != null && !nombreRestaurante.isEmpty()) {
            anuncios = anuncioService.filtrarAnunciosPorNombre(anuncios, nombreRestaurante);
        }

        request.setAttribute("anuncios", anuncios);
        request.getRequestDispatcher("ListaAnuncio.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("user");

        String nombreRestaurante = request.getParameter("nombreRestaurante");
        String tipoComida = request.getParameter("tipoComida");
        String ubicacion = request.getParameter("ubicacion");
        String descripcionOfertas = request.getParameter("descripcionOfertas");

        // Crear el anuncio usando el servicio, pasando el usuario directamente como en ResenaSv
        Anuncio anuncio = anuncioService.crearAnuncio(nombreRestaurante, tipoComida, ubicacion, descripcionOfertas, usuario);

        // Persistir el anuncio en la base de datos
        anuncioController.create(anuncio);

        response.sendRedirect("AnuncioSv");
    }
}
