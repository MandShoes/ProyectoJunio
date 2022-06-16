package com.svalero.proyectojunio.dao;

import com.svalero.proyectojunio.domain.*;

import java.sql.*;
import java.time.ZoneId;
import java.util.Optional;

public class ValoracionDao {

    private Connection connection;

    public ValoracionDao(Connection connection) {
        this.connection = connection;
    }

    public void add(Valoracion valoracion, Usuario usuario, Zapato zapato) throws SQLException {
        String sql = "INSERT INTO VALORACIONES (fecha_valoracion, cantidad_estrellas, descripcion, id_zapato, id_usuario) VALUES (?, ?, ?, ?, ?)";

        connection.setAutoCommit(false);

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, java.sql.Date.valueOf(valoracion.getFechaValoracion()));
        statement.setInt(2, valoracion.getCantidadEstrellas());
        statement.setString(3, valoracion.getDescripcion());
        statement.setInt(4, zapato.getIdZapato());
        statement.setInt(5, usuario.getIdUsuario());
        statement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
    }

    public boolean modify(int idUsuario, Zapato zapato, Valoracion valoracion) throws SQLException {

        String sql = "UPDATE VALORACIONES SET fecha_valoracion = ?, cantidad_estrellas = ?, descripcion  = ? WHERE id_usuario = ? AND id_zapato = ?";

        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setDate(1, java.sql.Date.valueOf(valoracion.getFechaValoracion()));
        statement.setInt(2, valoracion.getCantidadEstrellas());
        statement.setString(3, valoracion.getDescripcion());

        int rows = statement.executeUpdate();
        return rows == 1;
    }

    public Optional<Valoracion> findById(int id_zapato, int id_usuario) throws SQLException {
        String sql = "SELECT * FROM VALORACIONES WHERE id_zapato = ? AND id_usuario = ?";
        Valoracion valoracion = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_zapato);
        statement.setInt(2, id_usuario);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            valoracion = fromResultSet(resultSet);
        }

        return Optional.ofNullable(valoracion);
    }

    public boolean delete(int idUsuario, int idZapato) throws SQLException {
        String sql = "DELETE FROM VALORACIONES WHERE id_usuario = ? AND id_zapato = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idUsuario);
        statement.setInt(2, idZapato);
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    private Valoracion fromResultSet(ResultSet resulset) throws SQLException {
        Valoracion valoracion = new Valoracion();

        valoracion.setFechaValoracion(new java.util.Date(resulset.getDate("fecha_valoracion").getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        valoracion.setCantidadEstrellas(resulset.getInt("cantidad_estrellas"));
        valoracion.setDescripcion(resulset.getString("descripcion"));

        ZapatoDao zapatoDao = new ZapatoDao(connection);
        int idZapato = resulset.getInt("id_zapato");
        Zapato zapato = zapatoDao.findById(idZapato).get();
        valoracion.setZapato(zapato);

        UsuarioDao usuarioDao = new UsuarioDao(connection);
        int idUsuario = resulset.getInt("id_usuario");
        Usuario usuario = usuarioDao.findById(idUsuario).get();
        valoracion.setUsuario(usuario);
        return valoracion;

    }

}
