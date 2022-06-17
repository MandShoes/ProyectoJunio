package com.svalero.proyectojunio.servlet;

import com.svalero.proyectojunio.dao.Database;
import com.svalero.proyectojunio.dao.ValoracionDao;
import com.svalero.proyectojunio.domain.Usuario;
import com.svalero.proyectojunio.domain.Zapato;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/deletevaloracion")
public class DeleteValoracionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //Comprobar login usuario
        Usuario currentUser = (Usuario) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
        }
        int idUsuario = Integer.parseInt(request.getParameter("idusuario"));
        int idZapato = Integer.parseInt(request.getParameter("idzapato"));
        Database database = new Database();
        ValoracionDao valDao = new ValoracionDao(database.getConnection());
        try {
            valDao.delete(idUsuario, idZapato);
            out.println("/AdmandShoes/index.jsp");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}