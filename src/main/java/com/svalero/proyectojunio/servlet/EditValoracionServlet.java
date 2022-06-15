package com.svalero.proyectojunio.servlet;

import com.svalero.proyectojunio.dao.Database;
import com.svalero.proyectojunio.dao.MarcaDao;
import com.svalero.proyectojunio.dao.UsuarioDao;
import com.svalero.proyectojunio.dao.ZapatoDao;
import com.svalero.proyectojunio.domain.Marca;
import com.svalero.proyectojunio.domain.Usuario;
import com.svalero.proyectojunio.domain.Zapato;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/editvaloracion")
public class EditValoracionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Database database = new Database();
        UsuarioDao userDao = new UsuarioDao(database.getConnection());

        //Comprobar login usuario
        Usuario currentUser = (Usuario) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
        }

        // TODO: TRAER EL ZAPATO O EL ID DE ZAPATO (EN ESE CASO CAMBIAR METODO)

        try {
            userDao.modify(email, user);

            //Volvemos a asignar el currentuser con los datos de usuario modificados
            Optional<Usuario> newUser = userDao.login(username, password);
            HttpSession session = request.getSession(true);
            session.setAttribute("currentUser", newUser.get());
            out.println("<br><div class='alert alert-success' role='alert'>User data edited succesfully.</div>");
        } catch (SQLException sqle) {
            out.println("<br><div class='alert alert-danger' role='alert'>Something went wrong. Please try again in a few minutes.</div>");
        }
    }
}