package com.svalero.proyectojunio.servlet;

import com.svalero.proyectojunio.dao.Database;
import com.svalero.proyectojunio.dao.UsuarioDao;
import com.svalero.proyectojunio.domain.Usuario;
import com.svalero.proyectojunio.exception.EmailAlreadyExistException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/register")
public class AddUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("nombre");
        String password = request.getParameter("contrasena");
        String email = request.getParameter("email");
        String address = request.getParameter("direccion");
        Usuario user = new Usuario(name, password, email, address);

        Database database = new Database();
        UsuarioDao userDao = new UsuarioDao(database.getConnection());
        try {
            userDao.add(user);
            out.println("/AdmandShoes/login.jsp");
        } catch (EmailAlreadyExistException eaee) {
            out.println("<br><div class='alert alert-danger' role='alert'>Email already registered.</div>");
            eaee.printStackTrace();
        } catch (SQLException sqle) {
            out.println("<br><div class='alert alert-danger' role='alert'>Something wrong happened. Please check again in a few minutes.</div>");
            sqle.printStackTrace();
        }
    }
}