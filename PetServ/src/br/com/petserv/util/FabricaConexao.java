package br.com.petserv.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
        String driverClassName = "com.mysql.jdbc.Driver";
        String connectionUrl = "jdbc:mysql://localhost:3306/PetServ";
        String usuario = "root";
        String password = "mysql";

        private static FabricaConexao connectionFactory = null;

        private FabricaConexao() {
                try {
                        Class.forName(driverClassName);
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                }
        }

        public Connection getConnection() throws SQLException {
                Connection conn = null;
                conn = DriverManager.getConnection(connectionUrl, usuario, password);
                return conn;
        }

        public static FabricaConexao getInstance() {
                if (connectionFactory == null) {
                        connectionFactory = new FabricaConexao();
                }
                return connectionFactory;
        }
}

