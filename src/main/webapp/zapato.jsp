<%@ page import="java.util.Optional" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.svalero.proyectojunio.dao.Database" %>
<%@ page import="com.svalero.proyectojunio.domain.Zapato" %>
<%@ page import="com.svalero.proyectojunio.dao.ZapatoDao" %>
<%@ page import="com.svalero.proyectojunio.domain.Usuario" %>
<%@ page import="com.svalero.proyectojunio.domain.Valoracion" %>
<%@ page import="com.svalero.proyectojunio.dao.ValoracionDao" %>
<%@ page import="java.util.function.Supplier" %>
<html>
<head>
    <link rel="stylesheet" href="css/ada.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<script type="text/javascript">
    $(document).ready(function() {
        $("#form1").on("submit", function(event) {
            event.preventDefault();
            var formValue = $(this).serialize();
            $.post("editvaloracion", formValue, function(data) {
                $("#result1").html(data);
            });
        });
    });
</script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#form2").on("submit", function(event) {
            event.preventDefault();
            var formValue = $(this).serialize();
            $.post("addvaloracion", formValue, function(data) {
                $("#result2").html(data);
            });
        });
    });
</script>
<%
    int zapatoId = Integer.parseInt(request.getParameter("id"));
    Database db = new Database();
    ZapatoDao zapatoDao = new ZapatoDao(db.getConnection());
    Zapato zapato;
    Usuario currentUser = (Usuario) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
    }
    try {
        zapato = zapatoDao.findById(zapatoId).get();

%>
<div class="container">
    <div class="card text-center">
        <div class="card-header">
            <p><%= zapato.getModelo()%></p> - <p><%= zapato.getColor()%></p> - <p><%= zapato.getSexoZapato()%></p>
            <p><%= zapato.getMarca().getNombre()%></p>
        </div>
        <div class="card-body">
            <p class="card-text"><%= zapato.getDescripcion() %></p>
            <select class="form-select form-select-border-radius: 25%; " aria-label="select">
                <option selected>Available sizes</option>
                <option value="1"><%= zapato.getNumero()%></option>
            </select>

            <p class="card-text">Proveedor: <%= zapato.getProveedor().getNombre()%>.</p>

            <%
                ValoracionDao valDao = new ValoracionDao(db.getConnection());
                Valoracion valoracion = null;
                try {
                    Optional<Valoracion> optionalValoracion = valDao.findById(zapatoId, currentUser.getIdUsuario());
                    valoracion = optionalValoracion.orElseThrow(new Supplier<Throwable>() {
                        @Override
                        public Throwable get() {
                            return new Exception();
                        }
                    });
            %>
            <div class="container">
                <h2>Review</h2>
                <!-- Añadir aqui modify y delete -->
                <form id="form1">
                    <div class="mb-2">
                        <input name="review" type="text" class="form-control w-25" id="review" value="<% out.print(valoracion.getCantidadEstrellas()); %>">
                        <input name="description" type="text" class="form-control w-25" id="description" value="<% out.print(valoracion.getDescripcion()); %>">
                    </div>
                    <input type="hidden" name="idUsuario" value="<%=currentUser.getIdUsuario()%>">
                    <input type="hidden" name="idZapato" value="<%=zapato.getIdZapato()%>">
                    <button type="submit" class="btn btn-primary">Modify Review</button>
                </form>
                <div id="result1"></div>

                <a href="deletevaloracion?id_usuario=<%= currentUser.getIdUsuario()%>id_zapato=<%= zapato.getIdZapato()%>" class="btn btn-secondary">Delete review</a>
            </div>
            <%
                } catch (Exception e) {

            %>
            <form id="form2">
                <div class="mb-2">
                    <label for="newreview" class="form-label">Review:</label>
                    <input name="newreview" type="text" class="form-control w-25" id="newreview" value="">
                    <input name="newdescription" type="text" class="form-control w-25" id="newdescription" value="">
                </div>
                <input type="hidden" name="idUsuario" value="<%=currentUser.getIdUsuario()%>">
                <input type="hidden" name="idZapato" value="<%=zapato.getIdZapato()%>">
                <button type="submit" class="btn btn-primary">Create Review</button>
            </form>
            <div id="result2"></div>
            <%
                }
            %>
        </div>
    </div>
</div>
<%
} catch (SQLException sqle) {
%>
<div class='alert alert-danger' role='alert'>Something went wrong, please try again in a few minutes.</div>
<%
    }
%>
</body>
</html>

