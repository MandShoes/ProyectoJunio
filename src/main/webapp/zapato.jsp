<%@ page import="java.util.Optional" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.svalero.proyectojunio.dao.Database" %>
<%@ page import="com.svalero.proyectojunio.domain.Zapato" %>
<%@ page import="com.svalero.proyectojunio.dao.ZapatoDao" %>
<%@ page import="com.svalero.proyectojunio.domain.Marca" %>
<%@ page import="com.svalero.proyectojunio.dao.MarcaDao" %>
<%@ page import="com.svalero.proyectojunio.domain.Usuario" %>
<%@ page import="com.svalero.proyectojunio.domain.Valoracion" %>
<%@ page import="com.svalero.proyectojunio.dao.ValoracionDao" %>
<%@ page import="java.util.function.Supplier" %>
<html>
<head>
    <link rel="stylesheet" href="css/ada.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/cc976dc165.js" crossorigin="anonymous"></script>
            <link rel="preconnect" href="https://fonts.googleapis.com">
            <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
            <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;700&display=swap" rel="stylesheet">
</head>
<body>
<script type="text/javascript">
    $(document).ready(function() {
        $("#form1").on("submit", function(event) {
            event.preventDefault();
            var formValue = $(this).serialize();
            $.post("editvaloracion", formValue, function(data) {
                if ($.trim(data) == "/AdmandShoes/index.jsp") {
                    window.location.href = data;
                } else {
                    $("#result1").html(data);
                }
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
                if ($.trim(data) == "/AdmandShoes/index.jsp") {
                    window.location.href = data;
                } else {
                    $("#result2").html(data);
                }
            });
        });
    });
</script>
<jsp:include page="header.jsp" />
<%
    int zapatoId = Integer.parseInt(request.getParameter("idzapato"));
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
<div class="container" style="margin-top: 20px ! important">

 <div class="col-sm-6" style="width:50% ! important; padding-bottom:20px ! important;">
                       <div class="card text-center">
                       <div class="card-header">
                       <p><%= zapato.getModelo()%> - <%= zapato.getSexoZapato()%></p>
                       </div>
                         <div class="card-body">
                           <h5 class="card-title"><%= zapato.getMarca().getNombre()%></h5>
                           <img src="logos/<%= zapato.getMarca().getLogo() %>" class="card-img-top"  class="card-img-top" alt="imagen" style="margin-bottom:20px ! important; width:310px ! important; height:230px ! important">
                           <h6 class="card-text">Color: <p><%= zapato.getColor()%></p></h6>
                           <h6 class="card-text"><%= zapato.getDescripcion() %></h6>
                           <select class="form-select form-select-border-radius: 25%; " aria-label="select">
                                           <option selected>Available sizes</option>
                                           <option value="1"><%= zapato.getNumero()%></option>
                                       </select>
                           <h6 class="card-text"><%= zapato.getProveedor().getNombre()%></h6>
                           <a href="#" class="btn btn-warning">Buy shoes</a>

                         </div>
                       </div>
                     </div>

            <%
                ValoracionDao valDao = new ValoracionDao(db.getConnection());
                Valoracion valoracion = null;
                try {
                    valoracion = valDao.findById(zapatoId, currentUser.getIdUsuario()).orElseThrow(new Supplier<Throwable>() {
                        @Override
                        public Throwable get() {
                            return new Exception();
                        }
                    });
            %>
            </div>
            <div class="container">
                <h2>Review</h2>
                <!-- Añadir aqui modify y delete -->
                <form id="form1">
                    <div class="mb-2">
                        <input name="review" type="text" class="form-control w-25" id="review" value="<% out.print(valoracion.getCantidadEstrellas()); %>">
                        <input name="description" type="text" class="form-control w-25" id="description" value="<% out.print(valoracion.getDescripcion()); %>">
                    </div>
                    <input type="hidden" name="idUsuario" value="<%=currentUser.getIdUsuario()%>">
                    <input type="hidden" name="idzapato" value="<%=zapatoId%>">
                    <button type="submit" class="btn btn-warning">Modify Review</button>
                </form>
                <div id="result1"></div>

                <a href="deletevaloracion?idusuario=<%= currentUser.getIdUsuario()%>&idzapato=<%= zapatoId%>" class="btn btn-danger">Delete review</a>
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
                <input type="hidden" name="idusuario" value="<%=currentUser.getIdUsuario()%>">
                <input type="hidden" name="idzapato" value="<%=zapatoId%>">
                <button type="submit" class="btn btn-warning">Create Review</button>
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
<jsp:include page="footer.jsp" />
</body>
</html>

