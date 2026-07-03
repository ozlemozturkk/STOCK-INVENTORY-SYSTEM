/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stock_inventory_project;

/**
 *
 * @author hilal
 */
public class ComboItem {
     private int id;
    private String name;

    public ComboItem(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }
 public String getName() {   // ← BUNU EKLE
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
