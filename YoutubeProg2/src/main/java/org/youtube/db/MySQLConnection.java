package org.youtube.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
    String url="jdbc:mysql://162.241.2.243:3306/ipbuta68_mackenzie";
    String usuario = "ipbuta68_mack";
    String senha = "mackenzie";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, usuario, senha);

        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}