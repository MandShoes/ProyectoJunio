<%@ page import="com.svalero.proyectojunio.domain.Usuario" %>
<%@ page import="com.svalero.proyectojunio.dao.Database" %>
<%@ page import="com.svalero.proyectojunio.dao.UsuarioDao" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.sql.SQLException" %>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
%>

<%
  String textButton = "";
  String userId = request.getParameter("id");
  Usuario user = null;
  if (userId != null) {
    textButton = "Modificar";
    Database db = new Database();
    UsuarioDao usuarioDao = new UsuarioDao(db.getConnection());
    try {
      Optional<Usuario> optionalUsuario = usuarioDao.findById(Integer.parseInt(userId));
      user = optionalUsuario.get();
    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
  } else {
    textButton = "Registrar";
  }
%>

<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <title>Creating a New User</title>

</head>
<body>
<script type="text/javascript">
  $(document).ready(function() {
    $("form").on("submit", function(event) {
      event.preventDefault();
      var formValue = $(this).serialize();
      $.post("UserRegisterServlet", formValue, function(data) {
        $("#result").html(data);
      });
    });
  });
</script>

<div class="container">
  <h2>New Account</h2>
  <form>
    <div class="mb-2">
      <label for="usuario" class="form-label">Name:</label>
      <input name="usuario" type="text" class="form-control w-25" id="usuario" value="<% if (user != null) out.print(user.getNombre()); %>">
    </div>
    <div class="mb-2">
      <label for="contrasena" class="form-label">Password:</label>
      <input name="contrasena" type="text" class="form-control w-25" id="contrasena" value="<% if (user != null) out.print(user.getContrasena()); %>">
    </div>
    <div class="mb-3">
      <label for="email" class="form-label">Email:</label>
      <input name="email" type="text" class="form-control w-25" id="email" value="<% if (user != null) out.print(user.getEmail()); %>">
    </div>
    <div class="mb-2">
      <label for="direccion" class="form-label">Address:</label>
      <input name="direccion" type="text" class="form-control w-25" id="direccion" value="<% if (user != null) out.print(user.getDireccion()); %>">
    </div>

    <input type="hidden" name="action" value="<% if (user != null) out.print("modify"); else out.print("register"); %>">
    <input type="hidden" name="idUsuario" value="<% if (user != null) out.print(user.getIdUsuario()); %>">
    <button type="submit" class="btn btn-primary"><%= textButton %></button>
  </form>
  <div id="result"></div>
</div>
</body>
</html>

