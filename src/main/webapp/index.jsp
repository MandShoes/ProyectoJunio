<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"

         import="com.svalero.proyectojunio.domain.Usuario"
%>

<%
    Usuario currentUser = (Usuario) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
    }
%>

<html lang="en">
<link rel="stylesheet" href="css/ada.css">

<body>
<div class="container">
    <h2>AdmandShoes - Let your feet enjoy freedom</h2>
    <ul>
        <li><a href="zapatos.jsp">Display all available shoes</a></li>
        <%
            if ((currentUser != null)) {
        %>
        <li><a href="marcas.jsp">Display all available brands</a></li>
        <%
            }
            if (currentUser != null) {
        %>
        <li><a href="zonausuario.jsp">Access your profile</a></li>
        <%
            }
            if (currentUser != null) {
        %>
        <li><a href="logout">Close session</a></li>
        <%
            }
        %>
    </ul>
    <br/>
    <div class="alert alert-success" role="alert">
        You are logged on! Enjoy your stay, <% if (currentUser != null) out.print(currentUser.getNombre()); %><br>
    </div>
</div>
</body>
</html>
