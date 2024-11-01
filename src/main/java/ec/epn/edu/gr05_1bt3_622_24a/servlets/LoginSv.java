package ec.epn.edu.gr05_1bt3_622_24a.servlets;

import persistencia.UsuarioJpaController;
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
            HttpSession session = request.getSession();
            session.setAttribute("user", usuario);
            response.sendRedirect(request.getContextPath() + "/Home.jsp");
        } else {
            request.setAttribute("errorMessage", "Correo o contraseña incorrectos");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}
