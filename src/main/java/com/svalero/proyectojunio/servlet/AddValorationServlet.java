package com.svalero.proyectojunio.servlet;

import com.svalero.proyectojunio.dao.Database;
import com.svalero.proyectojunio.dao.UsuarioDao;
import com.svalero.proyectojunio.dao.ValoracionDao;
import com.svalero.proyectojunio.domain.Usuario;
import com.svalero.proyectojunio.domain.Valoracion;
import com.svalero.proyectojunio.exception.EmailAlreadyExistException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/addvaloracion")
public class AddValorationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int cantidadEstrellas = Integer.parseInt(request.getParameter("newreview"));
        String descripcion = request.getParameter("newdescription");
        Date date = new Date(System.currentTimeMillis());
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        int idZapato = Integer.parseInt(request.getParameter("idZapato"));

        Database database = new Database();
        ValoracionDao valDao = new ValoracionDao(database.getConnection());
        try {
            valDao.add(date, cantidadEstrellas, descripcion, idUsuario, idZapato);
            out.println("<br><div class='alert alert-success' role='alert'>User was correctly created.</div>");
            response.sendRedirect("login.jsp");
        } catch (SQLException sqle) {
            out.println("<br><div class='alert alert-danger' role='alert'>Something wrong happened. Please check again in a few minutes.</div>");
            sqle.printStackTrace();
        }
    }
}