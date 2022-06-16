<%@ page import="com.svalero.proyectojunio.dao.Database" %>
<%@ page import="com.svalero.proyectojunio.domain.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.svalero.proyectojunio.dao.UsuarioDao" %>
<%@ page import="com.svalero.proyectojunio.dao.ZapatoDao" %>
<%@ page import="com.svalero.proyectojunio.domain.Zapato" %>
<%@ page import="com.svalero.proyectojunio.dao.MarcaDao" %>
<%@ page import="com.svalero.proyectojunio.domain.Marca" %>
<%
    Usuario currentUser = (Usuario) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
    }
%>

<html>
<head>
    <link rel="stylesheet" href="css/ada.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">
    <h2>Navigate between the brands to choose the best shoe for your feet.</h2>
    <ul class="list-group">
        <%
            Database database = new Database();
            MarcaDao marcaDao = new MarcaDao(database.getConnection());
            try {
                List<Marca> marcas = marcaDao.findAll();
                for (Marca marca: marcas) {
        %>
        <li class="list-group-item">
            <a target="_blank" style="display: inline-flex; padding: 10px 20px" href="zapatospormarca.jsp?id=<%= marca.getIdMarca() %>"><%= marca.getNombre() %></a>
            <img src="logos/<%= marca.getLogo() %>" class="card-img-top" alt="imagen" style="margin-bottom:20px ! important; width:310px ! important; height:230px ! important">
            <p><%= marca.getDescripcion()  %> </p>
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
        %>
    </ul>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>