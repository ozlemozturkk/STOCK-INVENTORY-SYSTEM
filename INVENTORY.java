/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.stock_inventory_project;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author hilal
 */
public class INVENTORY extends javax.swing.JFrame {

  
    private CardLayout card;
    private Map<String, JPanel> panelMap = new HashMap<>();

    public Transfer transferPanel;
    public AddWareHouseProduct addProductPanel;
    public supplier_panel supplierPanel;
    public Invoıce_panel invoicePanel;
    public WareHouse warehousePanel;
    public TotalCount totalCountPanel;
    public depoStockGör auditPanel;

    public INVENTORY() {
 

        initComponents();
        pack();
        setLocationRelativeTo(null);

        // --- CARDLAYOUT BAŞLAT ---
        card = new CardLayout();
        mainPanel.setLayout(card);
        mainPanel.add(new JPanel(), "empty");

        // --- PANELLERİ OLUŞTUR ---
        createPanels();

        // --- CARDLAYOUT’A EKLE ---
        addPanelsToCard();

        // İlk görünen panel
        switchTo("empty");


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
        
 

  private void createPanels() {
        supplierPanel = new supplier_panel();
        invoicePanel = new Invoıce_panel();
        warehousePanel = new WareHouse();
       
        auditPanel = new depoStockGör();

         totalCountPanel = new TotalCount();
        addProductPanel = new AddWareHouseProduct(this);
        transferPanel = new Transfer(this);
        

        panelMap.put("supplier", supplierPanel);
        panelMap.put("invoice", invoicePanel);
        panelMap.put("warehouse", warehousePanel);
        panelMap.put("totalcount", totalCountPanel);
        panelMap.put("audit", auditPanel);
        panelMap.put("addProduct", addProductPanel);
        panelMap.put("transfer", transferPanel);
    }
    // ----------------------------
    //   PANELLERİ CARDLAYOUT’A EKLE
    // ----------------------------
    private void addPanelsToCard() {
        for (Map.Entry<String, JPanel> entry : panelMap.entrySet()) {
            mainPanel.add(entry.getValue(), entry.getKey());
        }
    }

//    private void showPage(String name) {
//        card.show(mainPanel, name);
//    }

    public void switchTo(String panelName) {
        if (panelMap.containsKey(panelName)) {
            card.show(mainPanel, panelName);
        } else {
            System.out.println("Panel not found: " + panelName);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnPanel = new javax.swing.JPanel();
        supplierBttn = new javax.swing.JToggleButton();
        productBttn = new javax.swing.JToggleButton();
        InvoiceBttn = new javax.swing.JToggleButton();
        logoutBttn = new javax.swing.JToggleButton();
        warehouuseBttn = new javax.swing.JToggleButton();
        totalCountBttn = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        addProduct = new javax.swing.JToggleButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        baslıkPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(supplierBttn);
        supplierBttn.setText("SUPPLIER");
        supplierBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierBttnActionPerformed(evt);
            }
        });

        buttonGroup1.add(productBttn);
        productBttn.setText("PRODUCT");
        productBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productBttnActionPerformed(evt);
            }
        });

        buttonGroup1.add(InvoiceBttn);
        InvoiceBttn.setText("INVOICE");
        InvoiceBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InvoiceBttnActionPerformed(evt);
            }
        });

        buttonGroup1.add(logoutBttn);
        logoutBttn.setText("LOG OUT");
        logoutBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBttnActionPerformed(evt);
            }
        });

        buttonGroup1.add(warehouuseBttn);
        warehouuseBttn.setText("WAREHOUSE");
        warehouuseBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                warehouuseBttnActionPerformed(evt);
            }
        });

        buttonGroup1.add(totalCountBttn);
        totalCountBttn.setText("TOTAL  COUNT");
        totalCountBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalCountBttnActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel2.setText("OPTIONS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(19, 19, 19))
        );

        addProduct.setText("ADD PRODUCT");
        addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });

        jToggleButton1.setText("TRANSFER");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout btnPanelLayout = new javax.swing.GroupLayout(btnPanel);
        btnPanel.setLayout(btnPanelLayout);
        btnPanelLayout.setHorizontalGroup(
            btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPanelLayout.createSequentialGroup()
                .addGroup(btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(btnPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(warehouuseBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(productBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(logoutBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(InvoiceBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(supplierBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(totalCountBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(addProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        btnPanelLayout.setVerticalGroup(
            btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(supplierBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(productBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(InvoiceBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(warehouuseBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(totalCountBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(logoutBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );

        baslıkPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("INVENTORY MAIN MENU");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout baslıkPanelLayout = new javax.swing.GroupLayout(baslıkPanel);
        baslıkPanel.setLayout(baslıkPanelLayout);
        baslıkPanelLayout.setHorizontalGroup(
            baslıkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baslıkPanelLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(880, Short.MAX_VALUE))
        );
        baslıkPanelLayout.setVerticalGroup(
            baslıkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, baslıkPanelLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        mainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mainPanel.setLayout(new java.awt.CardLayout());
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(baslıkPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(baslıkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void supplierBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierBttnActionPerformed
 switchTo("supplier");
    }//GEN-LAST:event_supplierBttnActionPerformed

    private void logoutBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBttnActionPerformed
      
          this.dispose();
        new Choose().setVisible(true);  
    }//GEN-LAST:event_logoutBttnActionPerformed

    private void warehouuseBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_warehouuseBttnActionPerformed
 switchTo("warehouse");
    }//GEN-LAST:event_warehouuseBttnActionPerformed

    private void totalCountBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalCountBttnActionPerformed
      
  totalCountPanel.refreshComboData();  
    switchTo("totalcount");

    }//GEN-LAST:event_totalCountBttnActionPerformed

    private void productBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productBttnActionPerformed
switchTo("audit");
    }//GEN-LAST:event_productBttnActionPerformed

    private void InvoiceBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InvoiceBttnActionPerformed

     invoicePanel.refreshComboBoxes(); 
        switchTo("invoice");

    }//GEN-LAST:event_InvoiceBttnActionPerformed

    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductActionPerformed
        switchTo("addProduct");


    }//GEN-LAST:event_addProductActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        switchTo("transfer");
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Dark tema yüklenemedi: " + ex.getMessage());
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new INVENTORY().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton InvoiceBttn;
    private javax.swing.JToggleButton addProduct;
    private javax.swing.JPanel baslıkPanel;
    private javax.swing.JPanel btnPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton logoutBttn;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JToggleButton productBttn;
    private javax.swing.JToggleButton supplierBttn;
    private javax.swing.JToggleButton totalCountBttn;
    private javax.swing.JToggleButton warehouuseBttn;
    // End of variables declaration//GEN-END:variables
}
