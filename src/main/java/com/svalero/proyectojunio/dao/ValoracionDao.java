package com.svalero.proyectojunio.dao;

import com.svalero.proyectojunio.domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class ValoracionDao {

    private Connection connection;

    public ValoracionDao(Connection connection) {
        this.connection = connection;
    }

    public void add(Date date, int cantidadEstrellas, String descripcion, int idUsuario, int idZapato) throws SQLException {
        String sql = "INSERT INTO VALORACIONES (fecha_valoracion, cantidad_estrellas, descripcion, id_zapato, id_usuario) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, new java.sql.Date(date.getTime()));
        statement.setInt(2, cantidadEstrellas);
        statement.setString(3, descripcion);
        statement.setInt(4, idZapato);
        statement.setInt(5, idUsuario);
        statement.executeUpdate();
    }

    public boolean modify(int idUsuario, int idZapato, Valoracion valoracion) throws SQLException {

        String sql = "UPDATE VALORACIONES SET fecha_valoracion = ?, cantidad_estrellas = ?, descripcion  = ? WHERE id_usuario = ? AND id_zapato = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setDate(1, new java.sql.Date(valoracion.getFechaValoracion().getTime()));
        statement.setInt(2, valoracion.getCantidadEstrellas());
        statement.setString(3, valoracion.getDescripcion());
        statement.setInt(4, idUsuario);
        statement.setInt(5, idZapato);

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

        valoracion.setFechaValoracion(resulset.getDate("fecha_valoracion"));
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
