package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private final String hostname;
    private final int porta;
    private final String database;
    private final String username;
    private final String password;

    private Connection connection;

    public DataSource() {
        hostname = System.getProperty("balanca_mas_nao_cai.db.host", "192.168.20.5");
        porta = Integer.getInteger("balanca_mas_nao_cai.db.port", 3306);
        database = System.getProperty("balanca_mas_nao_cai.db.name", "balanca_mas_nao_cai");
        username = System.getProperty("balanca_mas_nao_cai.db.user", "root");
        password = System.getProperty("balanca_mas_nao_cai.db.password", "12345");
    }

    private void conectar() throws SQLException {
        String url = "jdbc:mysql://" + hostname + ":" + porta + "/" + database
                + "?useTimezone=true&serverTimezone=UTC&connectTimeout=5000";
        connection = DriverManager.getConnection(url, username, password);
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            conectar();
        }
        return connection;
    }

    public void closeDataSource() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao fechar conexão: " + ex.getMessage());
        }
    }
}
