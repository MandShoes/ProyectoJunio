package com.svalero.proyectojunio.dao;

import com.svalero.proyectojunio.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UsuarioDao {
    private Connection connection;

    public UsuarioDao(Connection connection) {
        this.connection = connection;
    }

    public Optional<Usuario> login(String email, String contrasena) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE email = ? AND contrasena = ?";
        Usuario user = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, contrasena);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            user = fromResultSet(resultSet);
        }

        return Optional.ofNullable(user);
    }

    public void add(Usuario user) throws SQLException, EmailAlreadyExistException {
        if (existsUser(user.getEmail()))
            throw new EmailAlreadyExistException();

        String sql = "INSERT INTO USUARIOS (id_usuario, nombre, contrasena, email, direccion) VALUES (?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, user.getIdUsuario());
        statement.setString(2, user.getContrasena());
        statement.setString(3, user.getNombre());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getDireccion());
        statement.executeUpdate();
    }

    public boolean delete(String email, String contrasena) throws SQLException {
        String sql = "DELETE FROM USUARIOS WHERE email = ? AND contrasena = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, contrasena);
        int rows = statement.executeUpdate();
        return rows == 1;

        // TODO: falta borrar de verdad el usuario
    }

    public boolean modify(String email, Usuario user) throws SQLException {
        String sql = "UPDATE USUARIOS SET id_usuario = ?, nombre = ?, contrasena = ?, email = ?, direccion = ? WHERE usuario = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, user.getIdUsuario());
        statement.setString(2, user.getContrasena());
        statement.setString(3, user.getNombre());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getDireccion());
        int rows = statement.executeUpdate();
        return rows == 1;

        // TODO: falta modificar de verdad el usuario
    }

    public boolean modify(int id, Usuario user) throws SQLException {
        String sql = "UPDATE usuarios SET id = ?, usuario = ?, contrasena = ?, nombre = ?, email = ? WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, user.getId());
        statement.setString(2, user.getUsuario());
        statement.setString(3, user.getContrasena());
        statement.setString(4, user.getNombre());
        statement.setString(5, user.getEmail());
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    public ArrayList<Usuario> findAll() throws SQLException {
        String sql = "SELECT * FROM usuarios ORDER BY usuario";
        ArrayList<Usuario> users = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Usuario user = fromResultSet(resultSet);
            users.add(user);
        }

        return users;
    }

    public ArrayList<Usuario> findAll(String searchText) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE INSTR(usuario, ?) != 0 OR INSTR(nombre, ?) != 0 ORDER BY usuario";
        ArrayList<Usuario> users = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, searchText);
        statement.setString(2, searchText);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Usuario user = fromResultSet(resultSet);
            users.add(user);
        }

        return users;
    }

    public Optional<Usuario> findByUsuario(String usuario) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE usuario = ?";
        Usuario user = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            user = fromResultSet(resultSet);
        }

        return Optional.ofNullable(user);
    }

    public Optional<Usuario> findById(int id) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario user = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            user = fromResultSet(resultSet);
        }

        return Optional.ofNullable(user);
    }

    private Usuario fromResultSet(ResultSet resultSet) throws SQLException {
        Usuario user = new Usuario();
        user.setId(resultSet.getInt("id"));
        user.setUsuario(resultSet.getString("usuario"));
        user.setContrasena(resultSet.getString("contrasena"));
        user.setNombre(resultSet.getString("nombre"));
        user.setEmail(resultSet.getString("email"));
        return user;
    }

    private boolean existsUser(String usuario) throws SQLException {
        Optional<Usuario> user = findByUsuario(usuario);
        return user.isPresent();
    }
}
