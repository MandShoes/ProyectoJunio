package com.svalero.proyectojunio.util;

import com.svalero.proyectojunio.dao.Database;

import java.sql.Connection;

public class ShowMenu {

    private Database database;
    private Connection connection;

    public void connect(){
        database = new Database();
        connection = database.getConnection();
    }

    public void showMenu() { connect();}
}
