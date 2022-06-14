package com.svalero.proyectojunio.dao;

import com.svalero.proyectojunio.domain.Marca;
import com.svalero.proyectojunio.domain.Usuario;
import com.svalero.proyectojunio.exception.EmailAlreadyExistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarcaDao {

    private Connection connection;

    public MarcaDao(Connection connection) {
        this.connection = connection;
    }

    public void add(Marca marca) throws SQLException {

        String sql = "INSERT INTO MARCAS (nombre, descripcion, logo, direccion) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, marca.getNombre());
        statement.setString(2, marca.getDescripcion());
        statement.setString(3, marca.getLogo());
        statement.setString(4, marca.getDireccionSede());
        statement.executeUpdate();
    }

    public ArrayList<Marca> findAll() throws SQLException {
        String sql = "SELECT * FROM MARCAS ORDER BY nombre";
        ArrayList<Marca> marcas = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Marca marca = fromResultSet(resultSet);
            marcas.add(marca);
        }

        return marcas;
    }
    public ArrayList<Marca> findAll(String searchText) throws SQLException {
        String sql = "SELECT * FROM MARCAS WHERE INSTR(nombre, ?) ORDER BY nombre";
        ArrayList<Marca> marcas = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, searchText);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Marca marca = fromResultSet(resultSet);
            marcas.add(marca);
        }

        return marcas;
    }


    private Marca fromResultSet(ResultSet resultSet) throws SQLException {
        Marca marca = new Marca();
        marca.setIdMarca(resultSet.getInt("id_marca"));
        marca.setNombre(resultSet.getString("nombre"));
        marca.setDescripcion(resultSet.getString("descripcion"));
        marca.setLogo(resultSet.getString("logo"));
        marca.setDireccionSede(resultSet.getString("direccion"));
        return marca;
    }
}
