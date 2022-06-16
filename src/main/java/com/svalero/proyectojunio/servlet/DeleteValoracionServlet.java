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
import java.sql.SQLException;
/*
@WebServlet("/deletevaloracion")
public class DeleteValoracionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        //Comprobar login usuario
        Usuario currentUser = (Usuario) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
        }
        Database database = new Database();
        ValoracionDao valDao= new ValoracionDao(database.getConnection());
        // TODO: TRAER EL ZAPATO O EL ID DE ZAPATO (EN ESE CASO CAMBIAR METODO)
        try {
            valDao.delete(currentUser.getIdUsuario(), zapato);
            response.sendRedirect("login.jsp");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}

 */