package com.svalero.proyectojunio.dao;

import com.svalero.proyectojunio.domain.Marca;
import com.svalero.proyectojunio.domain.Proveedor;
import com.svalero.proyectojunio.domain.Usuario;
import com.svalero.proyectojunio.domain.Zapato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ZapatoDao {
    private Connection connection;

    public ZapatoDao(Connection connection) {
        this.connection = connection;
    }

    public void add(Zapato zapato, Marca marca, Proveedor proveedor) throws SQLException {
        String sql = "INSERT INTO ZAPATOS (modelo, numero, color, sexo, descripcion, precio, id_marca, id_proveedor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        connection.setAutoCommit(false);

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, zapato.getModelo());
        statement.setDouble(2, zapato.getNumero());
        statement.setString(3, zapato.getColor());
        statement.setString(4, zapato.getSexoZapato());
        statement.setString(5, zapato.getDescripcion());
        statement.setDouble(6, zapato.getPrecio());
        statement.setInt(7, marca.getIdMarca());
        statement.setInt(8, proveedor.getIdProveedor());

        statement.executeUpdate();

        connection.commit();
        connection.setAutoCommit(true);
    }
    public List<Zapato> findAll() throws SQLException {
        String sql = "SELECT * FROM ZAPATOS ORDER BY nombre";
        List<Zapato> zapatos = new List<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
           Zapato zapato = fromResultSet(resultSet);
           zapatos.add(zapato);
        }

        return zapatos;
    }

    public ArrayList<Zapato> findAll(String searchText) throws SQLException {
        String sql = "SELECT * FROM zapatos z INNER JOIN marcas m ON z.id_marca = m.id_marca WHERE lower(m.nombre) LIKE ? OR lower(z.modelo) LIKE ? ORDER BY nombre";
        ArrayList<Zapato> zapatos = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + searchText + "%" );
        statement.setString(2, "%" + searchText + "%" );
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Zapato zapato = fromResultSet(resultSet);
            zapatos.add(zapato);
        }

        return zapatos;
    }

    public ArrayList<Zapato> findAllByBrand(int idMarca) throws SQLException {
        String sql = "SELECT * FROM zapatos WHERE id_marca = ?";
        ArrayList<Zapato> zapatos = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idMarca);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Zapato zapato = fromResultSet(resultSet);
            zapatos.add(zapato);
        }

        return zapatos;
    }

    private Zapato fromResultSet(ResultSet resulset) throws SQLException {
        Zapato zapato = new Zapato();
        zapato.setIdZapato(resulset.getInt("id_zapato"));
        zapato.setModelo(resulset.getString("modelo"));
        zapato.setNumero(resulset.getDouble("numero"));
        zapato.setColor(resulset.getString("color"));
        zapato.setSexoZapato(resulset.getString("sexo"));
        zapato.setDescripcion(resulset.getString("descripcion"));
        zapato.setPrecio(resulset.getDouble("precio"));
        MarcaDao marcaDao = new MarcaDao(connection);
        int idMarca = resulset.getInt("id_marca");
        Marca marca = marcaDao.findById(idMarca).get();
        zapato.setMarca(marca);
        ProveedorDao proveedorDao = new ProveedorDao(connection);
        int idProveedor = resulset.getInt("id_proveedor");
        Proveedor proveedor = proveedorDao.findById(idProveedor).get();
        zapato.setProveedor(proveedor);
        return zapato;

    }
    public Optional<Zapato> findById(int id) throws SQLException {
        String sql = "SELECT * FROM ZAPATOS WHERE id_zapato = ?";
        Zapato zapato = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            zapato = fromResultSet(resultSet);
        }

        return Optional.ofNullable(zapato);
    }
}
