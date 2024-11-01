package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import controlador.UsuarioJpaController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;

@WebServlet("/RegistroSv")
public class RegistroSv extends HttpServlet {
    private final UsuarioJpaController usuarioController = new UsuarioJpaController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setPasswordHash(passwordHash);

        usuarioController.create(usuario);
        System.out.println("Usuario registrado: " + usuario.getUsername());
        response.sendRedirect(request.getContextPath() + "/Login.jsp");
    }
}
