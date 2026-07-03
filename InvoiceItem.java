/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stock_inventory_project;

/**
 *
 * @author elifs
 */
public class InvoiceItem {
    private String productName;
    private String category;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private String location;
    private String purchaseDate;

    // Constructor
    public InvoiceItem(String productName, String category, int quantity,
                       double unitPrice, String location, String purchaseDate) {
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = quantity * unitPrice;
        this.location = location;
        this.purchaseDate = purchaseDate;
    }

    // Getters
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public double getTotalPrice() { return totalPrice; }
    public String getLocation() { return location; }
    public String getPurchaseDate() { return purchaseDate; }
}