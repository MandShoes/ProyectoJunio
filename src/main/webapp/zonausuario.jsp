<%@ page import="com.svalero.proyectojunio.domain.Usuario" %>
<%@ page import="com.svalero.proyectojunio.dao.Database" %>
<%@ page import="com.svalero.proyectojunio.dao.CompraDao" %>
<%@ page import="com.svalero.proyectojunio.domain.Compra" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
%>
<%
    Usuario currentUser = (Usuario) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
    }
%>

<html lang="es">
<head>
    <title>Your Profile</title>
    <meta charset="UTF-8">
    <!--"estilos bootstrap"--><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/cc976dc165.js" crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;700&display=swap" rel="stylesheet">
</head>

</html>

<!-- Datos del usuario logueado -->
<main class="contenedor">
    <h3>Your Profile</h3>
    <form>
        <fieldset disabled>
            <legend>UserData</legend>
            <div class="mb-3">
                <label for="nombre" class="form-label">Name</label>
                <input type="text" id="nombre" class="form-control" value="<%= currentUser.getNombre() %>">
            </div>
            <div class="mb-3">
                <label for="contrasena" class="form-label">Password</label>
                <input type="password" id="contrasena" class="form-control" value="<%= currentUser.getContrasena() %>">
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="text" id="email" class="form-control" value="<%= currentUser.getEmail() %>">
            </div>
            <div class="mb-3">
                <label for="address" class="form-label">Address</label>
                <input type="text" id="address" class="form-control" value="<%= currentUser.getDireccion() %>">
            </div>

        </fieldset>
        <!-- Acceso a editar usuario -->
        <a href="edituser.jsp" class="btn btn-secondary">Editar datos de usuario</a>

        <!-- Borra el usuario y sus reservas asociadas de la bbdd -->
        <a href="deleteuser?username=<%= currentUser.getEmail() %>" class="btn btn-danger">Delete your account</a>
        <div class="alert alert-danger d-flex align-items-center" role="alert" style="margin-top:20px ! important; width:550px ! important">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img" aria-label="Warning:">
                <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </svg>
            <div>If you delete your account you won't be able to recover any shopping data you had stored, and your current orders will be canceled.</div>
        </div>
    </form>
    </section>

    <div class="container">
        <h3 style="text-align:left">My Orders</h3>
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

            <%
                Database database = new Database();
                CompraDao orderDao = new CompraDao(database.getConnection());
                try {
                    List<Compra> compras = orderDao.findById(currentUser.getIdUsuario());
                    for (Compra compra: compras) {
            %>

            <div class="col">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h5 class="card-text"><%=compra.getZapato().getModelo() %></h5>
                        <h6 class="card-text"><%=compra.getZapato().getColor() %></h6>
                        <p class="text-muted" style:font-size:12px ! important><%=compra.getZapato().getPrecio() %></p>
                        </div>
                    </div>
                </div>
            </div>
            <%
                }
            } catch (SQLException sqle) {
            %>
            <div class="alert alert-danger" role="alert">
                Failed to connect to database. Try again in a few minutes.
            </div>
            <%
                }
            %>
        </div>
    </div>
    </div>
</main>
