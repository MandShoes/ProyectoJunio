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
    <script src="https://kit.fontawesome.com/cc976dc165.js" crossorigin="anonymous"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;700&display=swap" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">
    <h2>All available shoes of your favorite brand.</h2>

    <ul class="list-group">
        <%
            String marcaId = request.getParameter("id");

            Database database = new Database();
            ZapatoDao zapatoDao = new ZapatoDao(database.getConnection());

              try {
                List<Zapato> zapatos = zapatoDao.findAllByBrand(Integer.parseInt(marcaId));
                for (Zapato zapato: zapatos) {
        %>

        <li class="list-group-item" style="width: 50% ! important">
            <a target="_blank" href="zapato.jsp?id=<%= zapato.getIdZapato() %>"><%= zapato.getModelo() %></a>

            <p><%= zapato.getMarca().getNombre() %> - <%= zapato.getColor() %> | <%= zapato.getPrecio() %> </p>
        </li>
        <%
            }
        } catch (SQLException sqle) {
        %>
        <div class="alert alert-danger" role="alert">
            Something went wrong, please try again in a few minutes.
        </div>
        <%
        }%>

    </ul>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>