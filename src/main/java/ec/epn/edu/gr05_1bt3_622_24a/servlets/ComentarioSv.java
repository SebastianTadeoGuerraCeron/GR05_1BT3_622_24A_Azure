package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import persistencia.ComentarioJpaController;
import persistencia.ResenaJpaController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Comentario;
import modelo.Resena;
import modelo.Usuario;
import service.ComentarioService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/ComentarioSv")
public class ComentarioSv extends HttpServlet {
    private final ComentarioJpaController comentarioController = new ComentarioJpaController();
    private final ResenaJpaController resenaController = new ResenaJpaController();
    private final ComentarioService comentarioService = new ComentarioService();

    // Maneja solicitudes GET para mostrar comentarios de una reseña
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long resenaId = Long.parseLong(request.getParameter("resenaId"));
        Resena resena = resenaController.findResena(resenaId);
        List<Comentario> comentarios = comentarioController.findComentariosByResena(resenaId);

        request.setAttribute("resena", resena);
        request.setAttribute("comentarios", comentarios);
        request.getRequestDispatcher("ListaComentarios.jsp").forward(request, response);
    }





    // Maneja solicitudes POST para añadir un nuevo comentario
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("user");

        Long resenaId = Long.parseLong(request.getParameter("resenaId"));
        String contenido = request.getParameter("contenido");

        Resena resena = resenaController.findResena(resenaId); // Recuperar la reseña

        if (comentarioService.verificarContenidoOfensivo(contenido)) {
            request.setAttribute("resena", resena); // Asegurar que la reseña esté disponible
            request.setAttribute("comentarios", comentarioController.findComentariosByResena(resenaId)); // Incluir comentarios existentes
            request.setAttribute("errorMessage", "La reseña contiene palabras ofensivas y no se ha publicado.");
            request.getRequestDispatcher("ListaComentarios.jsp").forward(request, response);
            return;
        }

        if (!comentarioService.verificarContenidoMax200(contenido)) {
            request.setAttribute("resena", resena); // Asegurar que la reseña esté disponible
            request.setAttribute("comentarios", comentarioController.findComentariosByResena(resenaId)); // Incluir comentarios existentes
            request.setAttribute("errorLengthMessage", "La reseña excede los 200 caracteres y no se ha publicado.");
            request.getRequestDispatcher("ListaComentarios.jsp").forward(request, response);
            return;
        }

        // Crear y guardar el comentario si no hay problemas
        Comentario comentario = comentarioService.crearComentario(contenido, usuario, resena);
        comentarioController.create(comentario);
        response.sendRedirect("ComentarioSv?resenaId=" + resenaId);
    }

}
