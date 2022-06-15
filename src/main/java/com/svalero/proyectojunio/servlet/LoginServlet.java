package com.svalero.proyectojunio.servlet;

import com.svalero.proyectojunio.dao.Database;
import com.svalero.proyectojunio.dao.UsuarioDao;
import com.svalero.proyectojunio.domain.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static java.lang.System.out;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("nombre");
        String password = request.getParameter("contrasena");

        Database database = new Database();
        UsuarioDao userDao = new UsuarioDao(database.getConnection());
        try {
            Optional<Usuario> user = userDao.login(username, password);
            if (user.isPresent()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentUser", user.get());
                out.println("Succesful login.");
                response.sendRedirect("index.jsp");
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Incorrect credentials.');");
                out.println("location='index.jsp';");
                out.println("</script>");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
