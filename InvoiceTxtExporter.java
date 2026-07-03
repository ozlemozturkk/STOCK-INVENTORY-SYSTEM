/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stock_inventory_project;

import com.mycompany.stock_inventory_project.InvoiceItem;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 
 *
 * @author elifs
 */
public class InvoiceTxtExporter {
     public static void export(String supplier, List<InvoiceItem> items, String path) throws Exception {
    BufferedWriter writer = new BufferedWriter(new FileWriter(path));

    double subtotal = 0;
    for (InvoiceItem item : items) {
        subtotal += item.getTotalPrice();
    }
    double tax = subtotal * 0.18;
    double total = subtotal + tax;

    LocalDateTime now = LocalDateTime.now();
    String invoiceNumber = "INV-" + now.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

    writer.write("==============================================\n");
    writer.write("               PURCHASE INVOICE\n");
    writer.write("==============================================\n\n");
    writer.write("Invoice No : " + invoiceNumber + "\n");
    writer.write("Invoice Date : " + now.toLocalDate().toString() + "\n\n");
    writer.write("Supplier : " + supplier + "\n\n");

    writer.write("----------------------------------------------\n");
    writer.write("Items:\n");
    writer.write("----------------------------------------------\n");
    for (InvoiceItem item : items) {
        writer.write(
            item.getProductName() + " | " +
            item.getCategory() + " | " +
            "Qty: " + item.getQuantity() + " | " +
            "Unit: " + String.format("%.2f ₺", item.getUnitPrice()) + " | " +
            "Total: " + String.format("%.2f ₺", item.getTotalPrice()) + " | " +
            "Loc: " + item.getLocation() + " | " +
            "Date: " + item.getPurchaseDate() + "\n"
        );
    }

    writer.write("\n----------------------------------------------\n");
    writer.write("Subtotal: " + String.format("%.2f ₺", subtotal) + "\n");
    writer.write("KDV (%18): " + String.format("%.2f ₺", tax) + "\n");
    writer.write("GRAND TOTAL: " + String.format("%.2f ₺", total) + "\n");
    writer.write("==============================================\n");

    writer.close();
}
}