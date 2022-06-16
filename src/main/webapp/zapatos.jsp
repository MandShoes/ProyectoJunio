<%@ page import="com.svalero.proyectojunio.dao.Database" %>
<%@ page import="com.svalero.proyectojunio.domain.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.svalero.proyectojunio.dao.ZapatoDao" %>
<%@ page import="com.svalero.proyectojunio.domain.Zapato" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%
    Usuario currentUser = (Usuario) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
    }
%>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h2>All available shoes currently on the store.</h2>

    <!-- Buscador de zapatos -->
    <div class="container-fluid" style="margin:0 auto; margin-bottom:30px ! important">
        <form class="d-flex" method="post" action="zapatos.jsp">
          <input class="form-control me-2" name="searchtext" id="searchtext" type="search" placeholder="Buscar zapatos por nombre, color..." aria-label="Search">
          <a class="btn btn-warning" type="submit">Search</a>
        </form>
      </div>
    <div class="row">

    <ul class="list-group">
        <%
            String searchText = request.getParameter("searchtext");
            searchText = StringUtils.lowerCase(searchText);

            Database database = new Database();
            ZapatoDao zapatoDao = new ZapatoDao(database.getConnection());

            // Si no hay nada en el buscador, acceder a la bbdd y recuperar todos los zapatos
            if (searchText == null) {
              try {
                List<Zapato> zapatos = zapatoDao.findAll();
                for (Zapato zapato: zapatos) {
        %>
        <li class="list-group-item">
            <a target="_blank" href="zapato.jsp?id=<%= zapato.getIdZapato() %>"><%= zapato.getModelo() %></a>
            <p><%= zapato.getMarca() %> - <%= zapato.getColor() %> | <%= zapato.getPrecio() %> </p>
        </li>
        <%
                }
            } catch (SQLException sqle) {
        %>
        <div class="alert alert-danger" role="alert">
            Something went wrong, please try again in a few minutes.
        </div>
        <%
            }
            } else {

         // Acceder a la bbdd y recuperar todos los zapatos filtrados por buscador
         try {
             List<Zapato> zapatos = zapatoDao.findAll(searchText);
             for (Zapato zapato: zapatos) {
        %>
        <li class="list-group-item">
            <a target="_blank" href="zapato.jsp?id=<%= zapato.getIdZapato() %>"><%= zapato.getModelo() %></a>
            <p><%= zapato.getMarca() %> - <%= zapato.getColor() %> | <%= zapato.getPrecio() %> </p>
        </li>
        <%
             }
         } catch (SQLException sqle) {
        %>
        <div class="alert alert-danger" role="alert">
            Something went wrong, please try again in a few minutes.
        </div>
        <%
         }
            }
        %>
    </ul>
</div>
</div>
</body>
</html>