package com.svalero.proyectojunio.servlet;

import com.svalero.proyectojunio.dao.*;
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

        //Comprobar login usuario
        Usuario currentUser = (Usuario) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
        }
        String codeEmail = request.getParameter("codeemail");
        String codeUser = request.getParameter("codeuser");
        Database database = new Database();
        ValoracionDao valDao = new ValoracionDao(database.getConnection());

        try {
            valDao.modify(codeEmail, codeUser);
            out.println("<br><div class='alert alert-success' role='alert'>Rating data edited succesfully.</div>");
        } catch (SQLException sqle) {
            out.println("<br><div class='alert alert-danger' role='alert'>Something went wrong. Please try again in a few minutes.</div>");
        }
    }
}