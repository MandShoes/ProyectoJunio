<%@ page import="java.util.Optional" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.svalero.proyectojunio.dao.Database" %>
<%@ page import="com.svalero.proyectojunio.domain.Zapato" %>
<%@ page import="com.svalero.proyectojunio.dao.ZapatoDao" %>
<html>
<head>
    <link rel="stylesheet" href="css/ada.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<%
    String zapatoId = request.getParameter("id");
    Database db = new Database();
    ZapatoDao zapatoDao = new ZapatoDao(db.getConnection());
    Zapato zapato;
    try {
        Optional<Zapato> optionalZapato = zapatoDao.findById(Integer.parseInt(zapatoId));
        zapato = optionalZapato.get();

%>
<div class="container">
    <div class="card text-center">
        <div class="card-header">
            <p><%= zapato.getModelo()%></p> - <p><%= zapato.getColor()%></p> - <p><%= zapato.getSexoZapato()%></p>
            <p><%= zapato.getMarca()%></p>
        </div>
        <div class="card-body">
            <p class="card-text"><%= zapato.getDescripcion() %></p>
            <select class="form-select form-select-border-radius: 25%; " aria-label="select">
                <option selected>Available sizes</option>
                <option value="1"><%= zapato.getNumero()%></option>
            </select>

            <p class="card-text">Proveedor: <%= zapato.getProveedor()%>.</p>
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

