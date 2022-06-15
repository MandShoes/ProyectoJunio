package com.svalero.proyectojunio.dao;

import com.svalero.proyectojunio.domain.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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

        //Mirar lo de la fecha

        statement.setDate(1, convertToDate(valoracion.getFechaValoracion()));
        statement.setInt(2, valoracion.getCantidadEstrellas());
        statement.setString(3, valoracion.getDescripcion());
        statement.setInt(4, zapato.getIdZapato());
        statement.setInt(5, usuario.getIdUsuario());
        statement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
    }

    public ArrayList<Valoracion> findAll(String searchInt) throws SQLException {
        String sql = "SELECT * FROM VALORACIONES v INNER JOIN ZAPATOS z ON v.id_zapato  = z.id_zapato WHERE lower(z.id_zapato) ORDER BY id_zapato";

        ArrayList<Valoracion> valoraciones = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + searchInt + "%" );
        statement.setString(2, "%" + searchInt + "%" );

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Valoracion valoracion = fromResultSet(resultSet);
            valoraciones.add(valoracion);
        }

        return valoraciones;
    }


    private Date convertToDate (LocalDate dateToConvert){
        return java.sql.Date.valueOf(dateToConvert);
    }

    public boolean modify(int idUsuario, Zapato zapato, Valoracion valoracion) throws SQLException {

        String sql = "UPDATE VALORACIONES SET fecha_valoracion = ?, cantidad_estrellas = ?, descripcion  = ? WHERE id_usuario = ? AND id_zapato = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, (valoracion.getFechaValoracion()));
        statement.setInt(2, valoracion.getCantidadEstrellas());
        statement.setString(3, valoracion.getDescripcion());

        int rows = statement.executeUpdate();
        return rows == 1;
    }
    public Optional<Valoracion> findById(int id) throws SQLException {
        String sql = "SELECT * FROM VALORACIONES WHERE id_zapato = ?";
        Valoracion valoracion = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            valoracion = fromResultSet(resultSet);
        }

        return Optional.ofNullable(valoracion);
    }



    private Valoracion fromResultSet(ResultSet resulset) throws SQLException {
        Valoracion valoracion = new Valoracion();

        //Hay que cambiar el localdate a date o o cambiar la clase valoracion a date
        Date date = convertToDate(valoracion.getFechaValoracion());
        valoracion.setFechaValoracion(resulset.getDate(toLocalDate("fecha_valoracion")));

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
