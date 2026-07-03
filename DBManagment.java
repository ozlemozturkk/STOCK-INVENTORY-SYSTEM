package com.mycompany.stock_inventory_project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class DBManagment {
    Connection conn = connect();
   
    //--------------------------------------------------------DATABASE CONNECTION ------------------------------------------------------------------------------------
 
      public static Connection connect() {
        Connection con = null;

        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DATABASE_STOCK_INVENTORY;encrypt=false;trustServerCertificate=true;";
            String user = "database";
            String pass = "Java123";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Database Connected!");

        } catch (Exception e) {
            System.out.println("Connection Error: " + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    

   //---------------------------------------------------------REGISTER (SIGN-IN)---------------------------------------------------------------------------------------
    public boolean signInUser(String name, String email, String phone, String password, String role) {

        String sql = "INSERT INTO Users (UserName, Email, Phone, Password, Role) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, phone);
            pst.setString(4, password);
            pst.setString(5, role);

            pst.executeUpdate();
            return true;

        } catch (Exception ex) {
            System.out.println("SIGN IN ERROR: " + ex.getMessage());
            return false;
        }
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    
    //-------------------------------------------------------------------LOG_IN----------------------------------------------------------------------- 
    public String loginUser(String username, String password) {

        String sql = "SELECT Password, Role FROM Users WHERE UserName = ?";

        try (Connection con = connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return "NOT_FOUND";
            }

            String dbPass = rs.getString("Password");
            String dbRole = rs.getString("Role");

            if (!dbPass.equals(password)) {
                return "WRONG_PASSWORD";
            }

            return dbRole;

        } catch (Exception e) {
            System.out.println("Login Error: " + e.getMessage());
            return "ERROR";
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    // ----------------------------------------------------PRODUCT_STOCK_PANEL--------------------------------------------------------------------------
    // ADD PRODUCT 
public int addProduct(String name, int categoryID, int brandID, String barcode) {
    String sql = "INSERT INTO Products (ProductName, Barcode, BrandID, CategoryID) "
               + "VALUES (?, ?, ?, ?)";
Connection con = connect();
    try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setString(1, name);
        ps.setString(2, barcode);
        ps.setInt(3, brandID);
        ps.setInt(4, categoryID);

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);   
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return -1;
}

// UPDATE PRODUCT 

public boolean updateProduct(int id, String productName, int categoryID, int brandID, String barcode) {

    String sql = "UPDATE Products SET ProductName = ?, CategoryID = ?, BrandID = ?, Barcode = ? WHERE ProductID = ?";

    try (Connection con = connect();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, productName);
        ps.setInt(2, categoryID);
        ps.setInt(3, brandID);
        ps.setString(4, barcode);
        ps.setInt(5, id);

        int affected = ps.executeUpdate();
        return affected > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    
 // DELETE PRODUCT 
public void deleteProduct(int productId) {
    String deleteStockMovements = "DELETE FROM StockMovements WHERE ProductID = ?";
    String deleteInventory = "DELETE FROM InventoryLevels WHERE ProductID = ?";
    String deleteStoreInventory = "DELETE FROM StoreInventoryLevels WHERE ProductID = ?";
    String deleteAuditItems = "DELETE FROM AuditItems WHERE ProductID = ?";
    String deletePurchaseItems = "DELETE FROM PurchaseItems WHERE ProductID = ?";
    String deleteProduct = "DELETE FROM Products WHERE ProductID = ?";

    try (Connection con = connect()) {

        // 1) StockMovements
        try (PreparedStatement ps = con.prepareStatement(deleteStockMovements)) {
            ps.setInt(1, productId);
            ps.executeUpdate();
        }

        // 2) InventoryLevels
        try (PreparedStatement ps = con.prepareStatement(deleteInventory)) {
            ps.setInt(1, productId);
            ps.executeUpdate();
        }

        // 3) StoreInventoryLevels
        try (PreparedStatement ps = con.prepareStatement(deleteStoreInventory)) {
            ps.setInt(1, productId);
            ps.executeUpdate();
        }

        // 4) AuditItems
        try (PreparedStatement ps = con.prepareStatement(deleteAuditItems)) {
            ps.setInt(1, productId);
            ps.executeUpdate();
        }

        // 5) PurchaseItems
        try (PreparedStatement ps = con.prepareStatement(deletePurchaseItems)) {
            ps.setInt(1, productId);
            ps.executeUpdate();
        }

        // 6) En son ürün silinir
        try (PreparedStatement ps = con.prepareStatement(deleteProduct)) {
            ps.setInt(1, productId);
            ps.executeUpdate();
        }

        System.out.println("Product and related records deleted.");

    } catch (Exception e) {
        System.out.println("deleteProduct error: " + e.getMessage());
    }
}

     // LIST PRODUCT ( PRODUCT_STOCK_PANEL , WAREHOUSE , ADDWAREHOUSEPRODUCT,DEPO STOCK GÖR  ) 
    public List<Object[]> getProducts() {
        List<Object[]> list = new ArrayList<>();

        String sql =
                "SELECT p.ProductID, p.ProductName, c.CategoryName, b.BrandName, p.Barcode " +
                "FROM Products p " +
                "LEFT JOIN Categories c ON p.CategoryID = c.CategoryID " +
                "LEFT JOIN Brands b ON p.BrandID = b.BrandID";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Object[]{
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("CategoryName"),
                        rs.getString("BrandName"),
                        rs.getString("Barcode")
                });
            }

        } catch (Exception e) {
            System.out.println("getProducts error: " + e.getMessage());
        }

        return list;
    }
    
  // INITIAL STOCK CREATION - product_stock_panel
public void createInitialStock(int productID) {

    String inv = "INSERT INTO InventoryLevels (WarehouseID, StorageLocationID, ProductID, Quantity) "
               + "VALUES (1, 1, ?, 0)";
 Connection con = connect();
    try (PreparedStatement ps = con.prepareStatement(inv)) {
        ps.setInt(1, productID);
        ps.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    String storeInv = "INSERT INTO StoreInventoryLevels (StoreID, ProductID, Quantity) "
                    + "VALUES (1, ?, 0)";  // store A için örnek

    try (PreparedStatement ps = con.prepareStatement(storeInv)) {
        ps.setInt(1, productID);
        ps.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

  //----------------------------------------------------------------------------------------------------------------------------------------------------
 
    
   

    //------------------------------------------------------------------------SUPPLIER PANEL ----------------------------------------------------------------------

//LIST SUPPLIER  -supplier_panel
public List<Object[]> getSuppliers() {
    List<Object[]> list = new ArrayList<>();

    String sql = "SELECT SupplierID, SupplierName, Phone, Email, Address FROM Suppliers";

    try (Connection con = connect();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(new Object[]{
                rs.getInt("SupplierID"),
                rs.getString("SupplierName"),
                rs.getString("Phone"),
                rs.getString("Email"),
                rs.getString("Address")
            });
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}



//ADD SUPPLIER -  -supplier_panel
public boolean addSupplier(String name, String phone, String email, String address) {
    String sql = "INSERT INTO Suppliers (SupplierName, Phone, Email, Address) VALUES (?, ?, ?, ?)";

    try (Connection con = connect();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, name);
        ps.setString(2, phone);
        ps.setString(3, email);
        ps.setString(4, address);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

//UPDATE SUPLIER -supplier_panel
public boolean updateSupplier(int id, String name, String phone, String email, String address) {
    String sql = "UPDATE Suppliers SET SupplierName=?, Phone=?, Email=?, Address=? WHERE SupplierID=?";

    try (Connection con = connect();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, name);
        ps.setString(2, phone);
        ps.setString(3, email);
        ps.setString(4, address);
        ps.setInt(5, id);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}

//DELETE SUPPLIER  -supplier_panel
public boolean deleteSupplier(int supplierId) {
    String deletePurchaseItems =
        "DELETE FROM PurchaseItems WHERE PurchaseID IN " +
        "(SELECT PurchaseID FROM Purchases WHERE SupplierID = ?)";

    String deletePurchases =
        "DELETE FROM Purchases WHERE SupplierID = ?";

    String deleteSupplier =
        "DELETE FROM Suppliers WHERE SupplierID = ?";

    try (Connection con = connect()) {

        con.setAutoCommit(false);

        // 1) Bu tedarikçiye ait purchase item'ları sil
        PreparedStatement ps1 = con.prepareStatement(deletePurchaseItems);
        ps1.setInt(1, supplierId);
        ps1.executeUpdate();

        // 2) Bu tedarikçiye ait purchase kayıtlarını sil
        PreparedStatement ps2 = con.prepareStatement(deletePurchases);
        ps2.setInt(1, supplierId);
        ps2.executeUpdate();

        // 3) Artık tedarikçi silinebilir
        PreparedStatement ps3 = con.prepareStatement(deleteSupplier);
        ps3.setInt(1, supplierId);
        int rows = ps3.executeUpdate();

        con.commit();
        return rows > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
   

//--------------------------------------------------------------------WAREHOUSE-----------------------------------------------------------------------------

//LIST IN-OUT- WareHouse
public List<Object[]> getWarehouseMovements() {

    String sql =
        "SELECT sm.MovementID, mt.MovementName, sm.Quantity, p.ProductName, " +
        "sl.ShelfCode AS Location, sm.MovementDate " +
        "FROM StockMovements sm " +
        "JOIN MovementTypes mt ON sm.MovementTypeID = mt.MovementTypeID " +
        "JOIN Products p ON sm.ProductID = p.ProductID " +
        "LEFT JOIN StorageLocations sl ON sm.StorageLocationID = sl.LocationID " +
        "WHERE sm.WarehouseID = 1 " +
        "ORDER BY sm.MovementDate DESC";

    List<Object[]> list = new ArrayList<>();

    try (Connection conn = connect();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(new Object[]{
                rs.getInt("MovementID"),
                rs.getString("MovementName"),   // IN / OUT
                rs.getInt("Quantity"),
                rs.getString("ProductName"),
                rs.getString("Location"),
                rs.getTimestamp("MovementDate")
            });
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

//-----------------------------------------------------------------------------------------------------------------------------------------

//---------------------------------------------------------------------TRANSFER-------------------------------------------------------------------------
public List<Object[]> getWarehouseInventory(int warehouseId) {

    String sql =
        "SELECT p.ProductName, c.CategoryName, b.BrandName, il.Quantity " +
        "FROM InventoryLevels il " +
        "JOIN Products p ON il.ProductID = p.ProductID " +
        "JOIN Categories c ON p.CategoryID = c.CategoryID " +
        "JOIN Brands b ON p.BrandID = b.BrandID " +
        "WHERE il.WarehouseID = ?";

    List<Object[]> list = new ArrayList<>();

    try (Connection conn = connect();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, warehouseId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new Object[]{
                rs.getString("ProductName"),
                rs.getString("CategoryName"),
                rs.getString("BrandName"),
                rs.getInt("Quantity")
            });
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

// COMBOBOXA URUNLERIN GELMESI (ADD PRODUCT TO STORE ,TRANSFER )
public List<ComboItem> getAllProductsAsCombo() {
    List<ComboItem> list = new ArrayList<>();

    String sql = "SELECT ProductID, ProductName FROM Products ORDER BY ProductName";

    try (Connection con = connect();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("ProductID");
            String name = rs.getString("ProductName");

            list.add(new ComboItem(name, id));  // name + id
        }

    } catch (SQLException e) {
        System.out.println("getAllProductsAsCombo error: " + e.getMessage());
    }

    return list;
}
public boolean transferToStore(int productId, int warehouseId, int quantity) {

    String decreaseWarehouseStock =
        "UPDATE InventoryLevels " +
        "SET Quantity = Quantity - ? " +
        "WHERE ProductID = ? AND WarehouseID = ? AND Quantity >= ?";

    // OUT hareketi (MovementTypeID = 2)
    String insertMovement =
        "INSERT INTO StockMovements " +
        "(WarehouseID, StorageLocationID, ProductID, MovementTypeID, Quantity) " +
        "VALUES (?, NULL, ?, 2, ?)";

    try (Connection conn = connect()) {

        conn.setAutoCommit(false);

        // 1) Depo stok azalt
        try (PreparedStatement ps1 = conn.prepareStatement(decreaseWarehouseStock)) {
            ps1.setInt(1, quantity);
            ps1.setInt(2, productId);
            ps1.setInt(3, warehouseId);
            ps1.setInt(4, quantity);

            int rows = ps1.executeUpdate();
            if (rows == 0) {
                conn.rollback();
                return false; // yeterli ürün yok
            }
        }

        // 2) OUT hareketini StockMovements'a kaydet
        try (PreparedStatement ps4 = conn.prepareStatement(insertMovement)) {
            ps4.setInt(1, warehouseId);
            ps4.setInt(2, productId);
            ps4.setInt(3, quantity);
            ps4.executeUpdate();
        }

        conn.commit();
        return true;

    } catch (Exception ex) {
        ex.printStackTrace();
        return false;
    }
}


//-----------------------------------------------------------------------------------------------------------------------------------------------------

// ---------------------------------------------------------------------------ADD STORE PRODUCT-----------------------------------------------------------

public int getStoreIDByName(String name) {
    String sql = "SELECT StoreID FROM Stores WHERE StoreName = ?";
    try (Connection conn = connect();
         PreparedStatement pst = conn.prepareStatement(sql)) {

        pst.setString(1, name);
        ResultSet rs = pst.executeQuery();
        if (rs.next())
            return rs.getInt("StoreID");

    } catch (Exception e) {
        System.out.println("getStoreIDByName error: " + e.getMessage());
    }
    return -1;
}


public int getProductIDByName(String name) {
    String sql = "SELECT ProductID FROM Products WHERE ProductName = ?";
    try (Connection conn = connect();
         PreparedStatement pst = conn.prepareStatement(sql)) {

        pst.setString(1, name);
        ResultSet rs = pst.executeQuery();
        if (rs.next())
            return rs.getInt("ProductID");

    } catch (Exception e) {
        System.out.println("getProductIDByName error: " + e.getMessage());
    }
    return -1;
}
public List<Object[]> getStoreProducts() {
    List<Object[]> list = new ArrayList<>();

    String sql = """
        SELECT s.StoreName, p.ProductName, si.Quantity
        FROM StoreInventoryLevels si
        JOIN Stores s ON si.StoreID = s.StoreID
        JOIN Products p ON si.ProductID = p.ProductID
    """;

    try (Connection conn = connect();
         PreparedStatement pst = conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            list.add(new Object[]{
                rs.getString("StoreName"),
                rs.getString("ProductName"),
                rs.getInt("Quantity")
            });
        }

    } catch (Exception e) {
        System.out.println("getStoreProducts error: " + e.getMessage());
    }
    return list;
}

public boolean addProductToStore(int storeID, int productID, int quantity) {

    String checkSql =
        "SELECT Quantity FROM StoreInventoryLevels WHERE StoreID = ? AND ProductID = ?";

    String insertSql =
        "INSERT INTO StoreInventoryLevels (StoreID, ProductID, Quantity) VALUES (?, ?, ?)";

    String updateSql =
        "UPDATE StoreInventoryLevels SET Quantity = Quantity + ? WHERE StoreID = ? AND ProductID = ?";

    String insertMovementSql =
        "INSERT INTO StockMovements (WarehouseID, StorageLocationID, ProductID, MovementTypeID, Quantity) " +
        "VALUES (?, NULL, ?, ?, ?)";

    try (Connection conn = connect()) {

        conn.setAutoCommit(false);

        // 1) ÜRÜN MAĞAZADA VAR MI?
        PreparedStatement pstCheck = conn.prepareStatement(checkSql);
        pstCheck.setInt(1, storeID);
        pstCheck.setInt(2, productID);
        ResultSet rs = pstCheck.executeQuery();

        boolean exists = false;
        int oldQty = 0;

        if (rs.next()) {
            exists = true;
            oldQty = rs.getInt("Quantity");
        }

        // 2) Varsa → miktarı artır
        if (exists) {
            PreparedStatement pstUpdate = conn.prepareStatement(updateSql);
            pstUpdate.setInt(1, quantity);
            pstUpdate.setInt(2, storeID);
            pstUpdate.setInt(3, productID);
            pstUpdate.executeUpdate();
        }
        // 3) Yoksa → yeni kayıt ekle
        else {
            PreparedStatement pstInsert = conn.prepareStatement(insertSql);
            pstInsert.setInt(1, storeID);
            pstInsert.setInt(2, productID);
            pstInsert.setInt(3, quantity);
            pstInsert.executeUpdate();
        }

        // 4) Stok hareketi (IN) ekle
        int warehouseID = 1;       // Foreign Key zorunluluğu
        int movementTypeIN = 1;    // IN

        PreparedStatement pstMove = conn.prepareStatement(insertMovementSql);
        pstMove.setInt(1, warehouseID);
        pstMove.setInt(2, productID);
        pstMove.setInt(3, movementTypeIN);
        pstMove.setInt(4, quantity);
        pstMove.executeUpdate();

        conn.commit();
        return true;

    } catch (Exception e) {
        System.out.println("addProductToStore error: " + e.getMessage());
        return false;
    }
}



//--------------------------------------------------------------------------------------------------------------------------------------------------------------------


//-------------------------------------------------------------------------ADD WAREHOUSE PRODUCT-------------------------------------------------------------------------

//DEPO STOCK GÖR , ADD WAREHOOUSE PRODUCT 
public List<Object[]> getInventoryList() {
    List<Object[]> list = new ArrayList<>();

    String sql = "SELECT p.ProductName, c.CategoryName, b.BrandName, " +
                 "p.Barcode, i.Quantity, s.ShelfCode " +
                 "FROM InventoryLevels i " +
                 "JOIN Products p ON i.ProductID = p.ProductID " +
                 "JOIN Categories c ON p.CategoryID = c.CategoryID " +
                 "JOIN Brands b ON p.BrandID = b.BrandID " +
                 "LEFT JOIN StorageLocations s ON i.StorageLocationID = s.LocationID";

    try (Connection con = connect();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(new Object[]{
                rs.getString("ProductName"),
                rs.getString("CategoryName"),
                rs.getString("BrandName"),
                rs.getString("Barcode"),
                rs.getInt("Quantity"),
                rs.getString("ShelfCode")
            });
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
public boolean addProductWareHouse(String name, String barcode,
                                   int brandID, int categoryID,
                                   int shelfLocationID, int quantity) {

    String insertProductSql =
        "INSERT INTO Products (ProductName, Barcode, BrandID, CategoryID) VALUES (?, ?, ?, ?)";

    String insertInventorySql =
        "INSERT INTO InventoryLevels (WarehouseID, StorageLocationID, ProductID, Quantity) VALUES (?, ?, ?, ?)";

    Connection con = connect();

    try {
        con.setAutoCommit(false);

        PreparedStatement ps = con.prepareStatement(insertProductSql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, name);
        ps.setString(2, barcode);
        ps.setInt(3, brandID);
        ps.setInt(4, categoryID);

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        int newProductID = -1;

        if (rs.next()) {
            newProductID = rs.getInt(1);
        }

        if (newProductID == -1) {
            con.rollback();
            return false;
        }

        PreparedStatement ps2 = con.prepareStatement(insertInventorySql);

        int warehouseID = 1;

        ps2.setInt(1, warehouseID);
        ps2.setInt(2, shelfLocationID);
        ps2.setInt(3, newProductID);
        ps2.setInt(4, quantity);
        ps2.executeUpdate();

        con.commit();
        return true;

    } catch (SQLException ex) {
        ex.printStackTrace();
        try { con.rollback(); } catch (SQLException ignore) {}
        return false;
    }
}

//--------------------------------------------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------INVOICE PANEL--------------------------------------------------------------------------


public boolean addPurchaseItem(String supplierName, String productName,
                               int quantity, double unitPrice, String shelfCode) {

    try {
       
 Connection conn = connect();
        // 1) SupplierID bul
        PreparedStatement ps1 = conn.prepareStatement(
            "SELECT SupplierID FROM Suppliers WHERE SupplierName=?");
        ps1.setString(1, supplierName);
        ResultSet rs1 = ps1.executeQuery();
        if (!rs1.next()) return false;
        int supplierID = rs1.getInt("SupplierID");

        // 2) ProductID bul
        PreparedStatement ps2 = conn.prepareStatement(
            "SELECT ProductID FROM Products WHERE ProductName=?");
        ps2.setString(1, productName);
        ResultSet rs2 = ps2.executeQuery();
        if (!rs2.next()) return false;
        int productID = rs2.getInt("ProductID");

        // 3) ShelfCode → LocationID bul
        PreparedStatement ps3 = conn.prepareStatement(
            "SELECT LocationID FROM StorageLocations WHERE ShelfCode=?");
        ps3.setString(1, shelfCode);
        ResultSet rs3 = ps3.executeQuery();
        if (!rs3.next()) return false;
        int locationID = rs3.getInt("LocationID");

        double totalPrice = quantity * unitPrice;

        // 4) Purchases tablosuna ekle
        PreparedStatement ps4 = conn.prepareStatement(
            "INSERT INTO Purchases (SupplierID, TotalPrice) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS);
        ps4.setInt(1, supplierID);
        ps4.setDouble(2, totalPrice);
        ps4.executeUpdate();

        ResultSet keys = ps4.getGeneratedKeys();
        keys.next();
        int purchaseID = keys.getInt(1);

        // 5) PurchaseItems ekle
        PreparedStatement ps5 = conn.prepareStatement(
            "INSERT INTO PurchaseItems (PurchaseID, ProductID, Quantity, UnitPrice) VALUES (?, ?, ?, ?)");
        ps5.setInt(1, purchaseID);
        ps5.setInt(2, productID);
        ps5.setInt(3, quantity);
        ps5.setDouble(4, unitPrice);
        ps5.executeUpdate();

        // 6) InventoryLevels stok artır
        PreparedStatement ps6 = conn.prepareStatement(
            "UPDATE InventoryLevels SET Quantity = Quantity + ? " +
            "WHERE ProductID = ? AND StorageLocationID = ?");
        ps6.setInt(1, quantity);
        ps6.setInt(2, productID);
        ps6.setInt(3, locationID);
        ps6.executeUpdate();

        return true;

    } catch (Exception e) {
        System.out.println("addPurchaseItem ERROR: " + e.getMessage());
        return false;
    }
}



    public List<String> getAllCategoryNames() {
        List<String> list = new ArrayList<>();

        String sql = "SELECT CategoryName FROM Categories";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getString("CategoryName"));
            }

        } catch (Exception e) {
            System.out.println("getAllCategoryNames error: " + e.getMessage());
        }
        return list;
    }

public List<String> getAllSuppliers() { //purshaces için
    List<String> list = new ArrayList<>();
    String sql = "SELECT SupplierName FROM Suppliers";
Connection conn = connect();
    try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(rs.getString("SupplierName"));
        }

    } catch (Exception e) {
        System.out.println("getAllSuppliers Error: " + e.getMessage());
    }
    return list;
}




public List<String> getAllLocations() {   // depodaki ürünleri filtreleyen combobox içini doldurmak için ve purshace için
    List<String> list = new ArrayList<>();
    String sql = "SELECT ShelfCode FROM StorageLocations";

    
    Connection conn = connect();
    try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(rs.getString("ShelfCode"));
        }

    } catch (Exception e) {
        System.out.println("getAllLocations error: " + e.getMessage());
    }

    return list;
}

    public List<String> getAllProductNames() {
        List<String> list = new ArrayList<>();

        String sql = "SELECT ProductName FROM Products";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getString("ProductName"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

//----------------------------------------------------------------------------------------------------------------------------------------------------



//-----------------------------------------------------------------------------STOCK--------------------------------------------------

public List<Object[]> getSingleProductInStore(int storeId, int productId) {
    List<Object[]> list = new ArrayList<>();

    String sql =
        "SELECT P.ProductName, C.CategoryName, B.BrandName, SI.Quantity " +
        "FROM StoreInventoryLevels SI " +
        "JOIN Products P ON SI.ProductID = P.ProductID " +
        "JOIN Categories C ON P.CategoryID = C.CategoryID " +
        "JOIN Brands B ON P.BrandID = B.BrandID " +
        "WHERE SI.StoreID = ? AND SI.ProductID = ?";

    try (Connection con = connect();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, storeId);
        ps.setInt(2, productId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new Object[]{
                rs.getString("ProductName"),
                rs.getString("CategoryName"),
                rs.getString("BrandName"),
                rs.getInt("Quantity")
            });
        }

    } catch (SQLException e) {
        System.out.println("getSingleProductInStore ERROR → " + e.getMessage());
    }

    return list;
}



public List<Object[]> getAllProductsInStore(int storeId) {
    List<Object[]> list = new ArrayList<>();

    String sql = 
        "SELECT P.ProductName, C.CategoryName, B.BrandName, SI.Quantity " +
        "FROM StoreInventoryLevels SI " +
        "JOIN Products P ON SI.ProductID = P.ProductID " +
        "JOIN Categories C ON P.CategoryID = C.CategoryID " +
        "JOIN Brands B ON P.BrandID = B.BrandID " +
        "WHERE SI.StoreID = ?";

    try (Connection con = connect();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, storeId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new Object[]{
                rs.getString("ProductName"),
                rs.getString("CategoryName"),
                rs.getString("BrandName"),
                rs.getInt("Quantity")
            });
        }

    } catch (SQLException e) {
        System.out.println("getAllProductsInStore ERROR → " + e.getMessage());
    }

    return list;
}
//------------------------------------------------------------------------------------------------------------------------------------------------------------


//-----------------------------------------------------------------------------TOTAL COUNT -----------------------------------------------------------------------------


public int getSystemQuantity(int productID, String shelfCode) {
    int qty = 0;

    String sql = "SELECT Quantity FROM InventoryLevels " +
                 "WHERE ProductID=? AND StorageLocationID = " +
                 "(SELECT LocationID FROM StorageLocations WHERE ShelfCode=?)";

    try (Connection conn = connect();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, productID);
        ps.setString(2, shelfCode);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            qty = rs.getInt("Quantity");
        }

    } catch (Exception e) {
        System.out.println("getSystemQuantity error: " + e.getMessage());
    }

    return qty;
}

public int createAudit(int warehouseID, int userID) {

    String sql = "INSERT INTO InventoryAudit (WarehouseID, CreatedBy) VALUES (?, ?)";
    int auditID = -1;

    try (Connection conn = connect();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setInt(1, warehouseID);
        ps.setInt(2, userID);

        // Gün/ay/yıl şeklindeyse SQL hata verir
        // Format: "2025-12-09"
       
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            auditID = rs.getInt(1);
        }

    } catch (Exception e) {
        System.out.println("createAudit error: " + e.getMessage());
    }

    return auditID;
}

public ArrayList<String> getAllUsers() {
    ArrayList<String> list = new ArrayList<>();

    String sql = "SELECT UserName FROM Users";

    try (Connection conn = connect();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(rs.getString("UserName"));
        }

    } catch (Exception e) {
        System.out.println("getAllUsers error : " + e.getMessage());
    }
    return list;
}


 
public ArrayList<String> getAllProducts() {
    ArrayList<String> list = new ArrayList<>();

    String sql = "SELECT ProductName FROM Products";

    try (Connection conn = connect();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(rs.getString("ProductName"));
        }

    } catch (Exception e) {
        System.out.println("getAllProducts error: " + e.getMessage());
    }
    return list;
}


public ArrayList<String> getAllShelves() {
    ArrayList<String> list = new ArrayList<>();

    String sql = "SELECT ShelfCode FROM StorageLocations";

    try (Connection conn = connect();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(rs.getString("ShelfCode"));
        }

    } catch (Exception e) {
        System.out.println("getAllShelves error : " + e.getMessage());
    }
    return list;
}



public boolean addAuditItem(int auditID, int productID, int expectedQty, int countedQty) {
    String sql = "INSERT INTO AuditItems (AuditID, ProductID, ExpectedQuantity, CountedQuantity) VALUES (?, ?, ?, ?)";

    try (Connection conn = connect();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, auditID);
        ps.setInt(2, productID);
        ps.setInt(3, expectedQty);
        ps.setInt(4, countedQty);

        ps.executeUpdate();
        return true;

    } catch (SQLException e) {
        System.out.println("addAuditItem error: " + e.getMessage());
        return false;
    }
}

public int getUserID(String userName) {
    int id = -1;

    String sql = "SELECT UserID FROM Users WHERE UserName=?";

    try (Connection conn = connect();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            id = rs.getInt("UserID");
        }

    } catch (Exception e) {
        System.out.println("getUserID error: " + e.getMessage());
    }
    return id;
}



 public int getProductID(String productName) {
    int id = -1;

    String sql = "SELECT ProductID FROM Products WHERE ProductName=?";

    try (Connection conn = connect();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, productName);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            id = rs.getInt("ProductID");
        }

    } catch (Exception e) {
        System.out.println("getProductID error: " + e.getMessage());
    }
    return id;
}


//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

 
 
//----------------------------------------------------------------------------DEPO STOCK GÖR --------------------------------------------------------------------------

 
public ResultSet getFilteredInventory(int categoryID, int locationID) {
    String sql =
        "SELECT p.ProductName, c.CategoryName, b.BrandName, p.Barcode, il.Quantity, sl.ShelfCode " +
        "FROM InventoryLevels il " +
        "JOIN Products p ON il.ProductID = p.ProductID " +
        "JOIN Categories c ON p.CategoryID = c.CategoryID " +
        "JOIN Brands b ON p.BrandID = b.BrandID " +
        "JOIN StorageLocations sl ON il.StorageLocationID = sl.LocationID " +
        "WHERE c.CategoryID = ? AND sl.LocationID = ?";

    try {
        Connection con = connect();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, categoryID);
        ps.setInt(2, locationID);
        return ps.executeQuery();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------





}