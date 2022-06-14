package com.svalero.proyectojunio.dao;

import com.svalero.proyectojunio.domain.Marca;
import com.svalero.proyectojunio.domain.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProveedorDao {

    private Connection connection;

    public ProveedorDao(Connection connection) {
        this.connection = connection;
    }

    public Optional<Proveedor> findById(int id) throws SQLException {
        String sql = "SELECT * FROM PROVEEDORES WHERE id_proveedor = ?";
        Proveedor proveedor = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            proveedor = fromResultSet(resultSet);
        }

        return Optional.ofNullable(proveedor);
    }
    private Proveedor fromResultSet(ResultSet resultSet) throws SQLException {
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(resultSet.getInt("id_proveedor"));
        proveedor.setNombre(resultSet.getString("nombre"));
        proveedor.setDireccion(resultSet.getString("direccion"));
        proveedor.setEmail(resultSet.getString("email"));
        proveedor.setCif(resultSet.getString("cif"));
        proveedor.setPais(resultSet.getString("pais"));
        return proveedor;
    }
}
