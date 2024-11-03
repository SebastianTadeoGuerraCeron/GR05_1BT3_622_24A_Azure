package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import persistencia.UsuarioJpaController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import service.UsuarioService;

import java.io.IOException;

@WebServlet("/RegistroSv")
public class RegistroSv extends HttpServlet {
    private final UsuarioJpaController usuarioController = new UsuarioJpaController();
    private final UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario usuario = usuarioService.registrarUsuario(username, email, password);

        usuarioController.create(usuario);

        // Redirige al login con el parámetro success=true
        response.sendRedirect(request.getContextPath() + "/Login.jsp?success=true");
    }
}
