/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.sql.*;
/**
 *
 * @author KhanhCT
 */
public class DbConnect {
    static String url = "jdbc:sqlserver://;serverName=localhost;databaseName=Xstore;encrypt=true;trustServerCertificate=true";
    static String name = "sa";
    static String pass = "123";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(url, name, pass);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    public static void main(String[] args) throws SQLException {
        getConnection();
    }
}
