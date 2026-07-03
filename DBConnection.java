/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stock_inventory_project;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hilal
 */
public class DBConnection {
    private static final String URL =
        "jdbc:sqlserver://localhost:1433;databaseName=DATABASE_STOCK_INVENTORY;encrypt=false;trustServerCertificate=true;";
    
    private static final String USER = "database";
    private static final String PASSWORD = "Java123";

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("❌ Bağlantı hatası: " + e.getMessage());
            return null;
        }
    }
}
