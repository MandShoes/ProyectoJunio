package com.svalero.proyectojunio.dao;

import com.svalero.proyectojunio.domain.Compra;
import com.svalero.proyectojunio.domain.Usuario;
import com.svalero.proyectojunio.domain.Valoracion;
import com.svalero.proyectojunio.domain.Zapato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Optional;

public class CompraDao {

    private Connection connection;

    public CompraDao(Connection connection) {
        this.connection = connection;
    }


    public void add(Compra compra, Usuario usuario, Zapato zapato) throws SQLException {

        String sql = "INSERT INTO COMPRAS (fecha_compra, pagado, id_zapato, id_usuario) VALUES (?, ?, ?, ?)";

        connection.setAutoCommit(false);
        int paga = 0;
        if (compra.isPagado() == true){
            paga = 1;
        }
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, java.sql.Date.valueOf(compra.getFechaCompra()));
        statement.setInt(2, paga);
        statement.setInt(3, zapato.getIdZapato());
        statement.setInt(3, usuario.getIdUsuario());

        statement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
    }
    public Optional<Compra> findById(int id) throws SQLException {
        String sql = "SELECT * FROM COMPRAS WHERE id_usuario = ?";
        Compra compra = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            compra = fromResultSet(resultSet);
        }

        return Optional.ofNullable(compra);
    }
    private Compra fromResultSet(ResultSet resulset) throws SQLException {
        Compra compra = new Compra();
        compra.setFechaCompra(new java.util.Date(resulset.getDate("fecha_compra").getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        compra.setPagado(resulset.getBoolean("pagado"));

        ZapatoDao zapatoDao = new ZapatoDao(connection);
        int idZapato = resulset.getInt("id_zapato");
        Zapato zapato = zapatoDao.findById(idZapato).get();
        compra.setZapato(zapato);

        UsuarioDao usuarioDao = new UsuarioDao(connection);
        int idUsuario = resulset.getInt("id_usuario");
        Usuario usuario = usuarioDao.findById(idUsuario).get();
        compra.setUsuario(usuario);
        return compra;

    }

}
