package com.svalero.proyectojunio.servlet;

import com.svalero.proyectojunio.dao.Database;
import com.svalero.proyectojunio.dao.UsuarioDao;
import com.svalero.proyectojunio.domain.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteuser")
public class DeleteUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        //Comprobar login usuario
        Usuario currentUser = (Usuario) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
        }

        String email = request.getParameter("email");
        Database database = new Database();
        UsuarioDao userDao = new UsuarioDao(database.getConnection());
        try {
            userDao.delete(email);
            response.sendRedirect("login.jsp");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}