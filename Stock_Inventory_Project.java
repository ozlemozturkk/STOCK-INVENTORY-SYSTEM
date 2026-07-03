/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.stock_inventory_project;


import java.sql.Connection;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;
/**
 *
 * @author hilal
 */
public class Stock_Inventory_Project {

    public static void main(String[] args) {
        // Tema ayarı
       

        // Veritabanı bağlantısını test et
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("✅ SQL Server bağlantısı BAŞARILI!");
            try {
                conn.close();
                System.out.println("Bağlantı kapatıldı.");
            } catch (Exception e) {
                System.out.println("Bağlantı kapatma hatası: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Bağlantı kurulamadı!");
        }

        // GUI başlat
       
    }
}
