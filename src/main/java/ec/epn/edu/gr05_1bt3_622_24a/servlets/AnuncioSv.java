package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import modelo.Anuncio;
import modelo.Usuario;
//import service.AnuncioService;
//import persistencia.AnuncioJpaController;

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
    private final AnuncioJpaController anuncioController;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ubicacion = request.getParameter("ubicacion");
        String nombreRestaurante = request.getParameter("nombreRestaurante");

        // Obtener todos los anuncios directamente desde el controlador
        List<Anuncio> anuncios = anuncioController.findAnuncioEntities();

        /*
        // Aplicar filtros usando AnuncioService si es necesario
        if (ubicacion != null && !ubicacion.isEmpty() && !ubicacion.equals("Todas")) {
            anuncios = anuncioService.filtrarAnunciosPorUbicacion(anuncios, ubicacion);
        }
         */

        /*
        if (nombreRestaurante != null && !nombreRestaurante.isEmpty()) {
            // Verificar caracteres especiales
            if (anuncioService.verificarContenidoCaracteresEspeciales(nombreRestaurante)) {
                request.setAttribute("errorSpecialCharacterMessage", "El nombre del restaurante contiene caracteres especiales y no se ha publicado.");
            } else {
                // Si no hay caracteres especiales, filtrar anuncios por nombre
                anuncios = anuncioService.filtrarAnunciosPorNombre(anuncios, nombreRestaurante);
            }
        }
         */

        request.setAttribute("anuncios", anuncios);
        request.getRequestDispatcher("ListaAnuncio.jsp").forward(request, response);
    }
}
