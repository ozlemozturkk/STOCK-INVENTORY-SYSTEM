/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.stock_inventory_project;

import static com.mycompany.stock_inventory_project.DBManagment.connect;
import java.awt.Dimension;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hilal
 */
public class Invoıce_panel extends javax.swing.JPanel {
private JFileChooser fileChooser = new JFileChooser();
    /**
     * Creates new form Invoıce_panel
     */
 public Invoıce_panel() {
    initComponents();

    purchasesTable.setModel(new DefaultTableModel(
        new Object[][] {},
        new String[]{
            "Product Name", "Category Name", "Quantity",
            "Unit Price", "Total Price", "Location", "Purchase Date"
        }
    ));

    refreshComboBoxes(); 
}
    
   
public void refreshComboBoxes() {
    DBManagment db = new DBManagment();

    supplierCB.removeAllItems();
    for (String s : db.getAllSuppliers()) {
        supplierCB.addItem(s);
    }

    productCB.removeAllItems();
    for (String p : db.getAllProductNames()) {
        productCB.addItem(p);
    }

    categoryCB.removeAllItems();
    for (String c : db.getAllCategoryNames()) {
        categoryCB.addItem(c);
    }

    locationCB.removeAllItems();
    for (String l : db.getAllLocations()) {
        locationCB.addItem(l);
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ınvoıce_panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        showtxt = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        unitPriceField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        quantitytxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        purchaseDateField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        supplierCB = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        categoryCB = new javax.swing.JComboBox<>();
        locationCB = new javax.swing.JComboBox<>();
        productCB = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        purchasesTable = new javax.swing.JTable();

        ınvoıce_panel.setPreferredSize(new java.awt.Dimension(500, 300));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel2.setText("PURCHASES ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        showtxt.setText("Show TXT");
        showtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showtxtActionPerformed(evt);
            }
        });

        jLabel8.setText("Loaction : ");

        jLabel7.setText("Unit Price : ");

        jLabel6.setText("Quantity : ");

        jLabel5.setText("Product  Name: ");

        jLabel9.setText("Purschase Date : ");

        jLabel10.setText("Supplier : ");

        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        jLabel11.setText("Category:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(showtxt)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel5)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(productCB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(categoryCB, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(7, 7, 7)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(13, 13, 13)
                                                    .addComponent(addBtn))
                                                .addComponent(supplierCB, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(7, 7, 7)
                                            .addComponent(purchaseDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(quantitytxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(unitPriceField)
                                    .addComponent(locationCB, 0, 100, Short.MAX_VALUE)))
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(supplierCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(categoryCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(purchaseDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(productCB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(quantitytxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(unitPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(locationCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(showtxt))
                .addGap(33, 33, 33))
        );

        purchasesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Product Name", "Category Name", "Quantity", "Unit Price", "Total Price", "Location", "Purchase  Date"
            }
        ));
        jScrollPane1.setViewportView(purchasesTable);

        javax.swing.GroupLayout ınvoıce_panelLayout = new javax.swing.GroupLayout(ınvoıce_panel);
        ınvoıce_panel.setLayout(ınvoıce_panelLayout);
        ınvoıce_panelLayout.setHorizontalGroup(
            ınvoıce_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ınvoıce_panelLayout.createSequentialGroup()
                .addGroup(ınvoıce_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ınvoıce_panelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ınvoıce_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        ınvoıce_panelLayout.setVerticalGroup(
            ınvoıce_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ınvoıce_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ınvoıce_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ınvoıce_panelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ınvoıce_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ınvoıce_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void showtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showtxtActionPerformed
   DefaultTableModel model = (DefaultTableModel) purchasesTable.getModel();

    if (model.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "No items added to invoice!");
        return;
    }

    List<InvoiceItem> items = new ArrayList<>();
    String supplier = supplierCB.getSelectedItem().toString();

    for (int i = 0; i < model.getRowCount(); i++) {
        String product = model.getValueAt(i, 0).toString();
        String category = model.getValueAt(i, 1).toString();
        int qty = Integer.parseInt(model.getValueAt(i, 2).toString());
        double price = Double.parseDouble(model.getValueAt(i, 3).toString());
        String location = model.getValueAt(i, 5).toString();
        String purchaseDate = model.getValueAt(i, 6).toString();

        items.add(new InvoiceItem(product, category, qty, price, location, purchaseDate));
    }

    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int selected = fileChooser.showSaveDialog(this.getParent());

    if (selected == JFileChooser.APPROVE_OPTION) {
        File dir = fileChooser.getSelectedFile();
        File txtFile = new File(dir, "purchase_invoice.txt");

        try {
            InvoiceTxtExporter.export(supplier, items, txtFile.getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Invoice TXT created successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    }//GEN-LAST:event_showtxtActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
            String supplier = supplierCB.getSelectedItem().toString();
    String product = productCB.getSelectedItem().toString();
    int qty = Integer.parseInt(quantitytxt.getText());
    double price = Double.parseDouble(unitPriceField.getText());
    String shelf = locationCB.getSelectedItem().toString();
    String category = categoryCB.getSelectedItem().toString();
    String purchaseDate = purchaseDateField.getText(); 

    double total = qty * price;

    DBManagment db = new DBManagment();
    boolean ok = db.addPurchaseItem(supplier, product, qty, price, shelf);

    if (ok) {
        JOptionPane.showMessageDialog(this, "Purchase saved and stock updated!");

        // ⭐⭐⭐ TABLOYA EKLEME KISMI ⭐⭐⭐
        DefaultTableModel model = (DefaultTableModel) purchasesTable.getModel();
        model.addRow(new Object[]{
            product,
            category,
            qty,
            price,
            total,
            shelf,
            purchaseDate
        });

        // Form alanlarını boşalt
        quantitytxt.setText("");
        unitPriceField.setText("");

    } else {
        JOptionPane.showMessageDialog(this, "Failed to save purchase!", "Error", JOptionPane.ERROR_MESSAGE);
    }
  
    }//GEN-LAST:event_addBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JComboBox<String> categoryCB;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> locationCB;
    private javax.swing.JComboBox<String> productCB;
    private javax.swing.JTextField purchaseDateField;
    private javax.swing.JTable purchasesTable;
    private javax.swing.JTextField quantitytxt;
    private javax.swing.JButton showtxt;
    private javax.swing.JComboBox<String> supplierCB;
    private javax.swing.JTextField unitPriceField;
    private javax.swing.JPanel ınvoıce_panel;
    // End of variables declaration//GEN-END:variables
}
