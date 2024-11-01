package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import controlador.UsuarioJpaController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;

@WebServlet("/LoginSv")
public class LoginSv extends HttpServlet {
    private UsuarioJpaController usuarioController = new UsuarioJpaController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("email: " + email);
        System.out.println("password: " + password);

        Usuario usuario = usuarioController.findByEmail(email);
        if (usuario != null && BCrypt.checkpw(password, usuario.getPasswordHash())) {
            System.out.println("Inicio de sesión exitoso para el usuario: " + usuario.getUsername());
            HttpSession session = request.getSession();
            session.setAttribute("user", usuario);
            response.sendRedirect(request.getContextPath() + "/Home.jsp");
        } else {
            System.out.println("Error: Credenciales incorrectas para el usuario con email: " + email);
            request.setAttribute("errorMessage", "Correo o contraseña incorrectos");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}
