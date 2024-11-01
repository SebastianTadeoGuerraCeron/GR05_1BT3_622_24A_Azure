package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import controlador.ComentarioJpaController;
import controlador.ResenaJpaController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Comentario;
import modelo.Resena;
import modelo.Usuario;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/ComentarioSv")
public class ComentarioSv extends HttpServlet {
    private final ComentarioJpaController comentarioController = new ComentarioJpaController();
    private final ResenaJpaController resenaController = new ResenaJpaController();

    // Maneja solicitudes GET para mostrar comentarios de una reseña
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long resenaId = Long.parseLong(request.getParameter("resenaId"));

        // Cargar la reseña y los comentarios asociados
        Resena resena = resenaController.findResena(resenaId);
        List<Comentario> comentarios = comentarioController.findComentariosByResena(resenaId);

        // Configurar los atributos para la página JSP
        request.setAttribute("resena", resena);
        request.setAttribute("comentarios", comentarios);

        // Redirigir a la página de comentarios
        request.getRequestDispatcher("ListaComentarios.jsp").forward(request, response);
    }

    // Maneja solicitudes POST para añadir un nuevo comentario
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("user");

        Long resenaId = Long.parseLong(request.getParameter("resenaId"));
        String contenido = request.getParameter("contenido");

        Resena resena = resenaController.findResena(resenaId);

        Comentario comentario = new Comentario();
        comentario.setContenido(contenido);
        comentario.setFechaCreacion(new Date());
        comentario.setUsuario(usuario);
        comentario.setResena(resena);

        comentarioController.create(comentario);

        // Redirige para evitar reenvío de formulario
        response.sendRedirect("ComentarioSv?resenaId=" + resenaId);
    }
}
