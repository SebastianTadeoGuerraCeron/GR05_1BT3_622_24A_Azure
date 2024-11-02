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
        // Verificar si hay un mensaje de error
        if ("true".equals(request.getParameter("error"))) {
            request.setAttribute("errorMessage", "El comentario contiene palabras ofensivas y no se ha publicado.");
        }

        Long resenaId = Long.parseLong(request.getParameter("resenaId"));
        Resena resena = resenaController.findResenaConComentarios(resenaId);
        List<Comentario> comentarios = resena.getComentarios();

        // Obtener el mensaje de error si existe en los parámetros de la URL
        String errorMessage = request.getParameter("errorMessage");
        if (errorMessage != null && !errorMessage.isEmpty()) {
            request.setAttribute("errorMessage", errorMessage);
        }

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

        // Verificar si el contenido es ofensivo
        if (comentarioService.verificarContenidoOfensivo(contenido)) {
            request.setAttribute("errorMessage", "El comentario contiene palabras ofensivas y no se ha publicado.");
            response.sendRedirect("ComentarioSv?resenaId=" + resenaId + "&error=true");
            return;
        }


        // Crear y guardar el comentario solo si es válido
        Resena resena = resenaController.findResenaConComentarios(resenaId);
        Comentario comentario = comentarioService.crearComentario(contenido, usuario, resena);
        comentarioController.create(comentario);

        // Redirige para evitar reenvío de formulario
        response.sendRedirect("ComentarioSv?resenaId=" + resenaId);
    }
}
