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
<!--"estilos bootstrap"--><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="css/ada.css">

<body>
<jsp:include page="header.jsp" />
<div class="container">
    <h2>AdmandShoes - Let your feet enjoy freedom</h2>

    <div class="card">
      <div class="card-header">
        Shoes
      </div>
      <div class="card-body">
        <h5 class="card-title">Display all available shoes</h5>
        <p class="card-text">All your favorite shoes from your favorite brands are available here.</p>
        <a href="zapatos.jsp" class="btn btn-warning">See shoes</a>
      </div>
    </div>

    <div class="card">
          <div class="card-header">
            Brands
          </div>
          <div class="card-body">
            <h5 class="card-title">Display all available brands</h5>
            <p class="card-text">All your favorite shoe brands all around the world are available for you.</p>
            <a href="marcas.jsp" class="btn btn-warning">See brands</a>
          </div>
        </div>
<jsp:include page="footer.jsp" />
</body>
</html>
