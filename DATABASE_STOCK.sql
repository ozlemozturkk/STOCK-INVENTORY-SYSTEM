USE DATABASE_STOCK_INVENTORY
GO

--CREATE TABLE Warehouses (
--    WarehouseID INT PRIMARY KEY IDENTITY,
--    WarehouseName VARCHAR(100) NOT NULL,
--    Address VARCHAR(200)
--);

--CREATE TABLE Brands (
--    BrandID INT PRIMARY KEY IDENTITY,
--    BrandName VARCHAR(100) NOT NULL
--);

--CREATE TABLE Categories (
--    CategoryID INT PRIMARY KEY IDENTITY,
--    CategoryName VARCHAR(100) NOT NULL
--);

--CREATE TABLE Products (
--    ProductID INT PRIMARY KEY IDENTITY,
--    ProductName VARCHAR(150) NOT NULL,
--    Barcode VARCHAR(100),
--    BrandID INT,
--    CategoryID INT,

--    FOREIGN KEY (BrandID) REFERENCES Brands(BrandID),
--    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
--);

--CREATE TABLE Suppliers (
--    SupplierID INT PRIMARY KEY IDENTITY,
--    SupplierName VARCHAR(150) NOT NULL,
--    Phone VARCHAR(50),
--    Email VARCHAR(100),
--    Address VARCHAR(200)
--);

--CREATE TABLE MovementTypes (
--    MovementTypeID INT PRIMARY KEY IDENTITY,
--    MovementName VARCHAR(50) NOT NULL
--);


--CREATE TABLE Users (
--    UserID INT PRIMARY KEY IDENTITY(1,1),
--    UserName VARCHAR(100) NOT NULL,
--    Password VARCHAR(200) NOT NULL,
--    Email VARCHAR(150),
--    Phone VARCHAR(50),
--    Role VARCHAR(50) NOT NULL    -- 'Warehouse' or 'Store'
--);


--CREATE TABLE StorageLocations (
--    LocationID INT PRIMARY KEY IDENTITY,
--    WarehouseID INT NOT NULL,
--    ShelfCode VARCHAR(50),

--    FOREIGN KEY (WarehouseID) REFERENCES Warehouses(WarehouseID)
--);


--CREATE TABLE InventoryLevels (
--    InventoryID INT PRIMARY KEY IDENTITY,
--    WarehouseID INT NOT NULL,
--    StorageLocationID INT NULL,      
--    ProductID INT NOT NULL,
--    Quantity INT DEFAULT 0,

--    FOREIGN KEY (WarehouseID) REFERENCES Warehouses(WarehouseID),
--    FOREIGN KEY (StorageLocationID) REFERENCES StorageLocations(LocationID),
--    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
--);


--CREATE TABLE StockMovements (
--    MovementID INT PRIMARY KEY IDENTITY,
--    WarehouseID INT NOT NULL,
--    StorageLocationID INT NULL,
--    ProductID INT NOT NULL,
--    MovementTypeID INT NOT NULL,
--    Quantity INT NOT NULL,
--    MovementDate DATETIME DEFAULT GETDATE(),

--    FOREIGN KEY (WarehouseID) REFERENCES Warehouses(WarehouseID),
--    FOREIGN KEY (StorageLocationID) REFERENCES StorageLocations(LocationID),
--    FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
--    FOREIGN KEY (MovementTypeID) REFERENCES MovementTypes(MovementTypeID)
--);


--CREATE TABLE InventoryAudit (
--    AuditID INT PRIMARY KEY IDENTITY,
--    WarehouseID INT NOT NULL,
--    CreatedBy INT NOT NULL,
--    AuditDate DATETIME DEFAULT GETDATE(),

--    FOREIGN KEY (WarehouseID) REFERENCES Warehouses(WarehouseID),
--    FOREIGN KEY (CreatedBy) REFERENCES Users(UserID)
--);

--CREATE TABLE AuditItems (
--    AuditItemID INT PRIMARY KEY IDENTITY,
--    AuditID INT NOT NULL,
--    ProductID INT NOT NULL,
--    ExpectedQuantity INT NULL,
--    CountedQuantity INT NOT NULL,

--    FOREIGN KEY (AuditID) REFERENCES InventoryAudit(AuditID),
--    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
--);

--CREATE TABLE Purchases (
--    PurchaseID INT PRIMARY KEY IDENTITY,
--    SupplierID INT NOT NULL,
--    PurchaseDate DATETIME DEFAULT GETDATE(),
--    TotalPrice DECIMAL(10,2),

--    FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID)
--);

--CREATE TABLE PurchaseItems (
--    PurchaseItemID INT PRIMARY KEY IDENTITY,
--    PurchaseID INT NOT NULL,
--    ProductID INT NOT NULL,
--    Quantity INT NOT NULL,
--    UnitPrice DECIMAL(10,2),
--    TotalPrice AS (Quantity * UnitPrice),

--    FOREIGN KEY (PurchaseID) REFERENCES Purchases(PurchaseID),
--    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
--);

--CREATE TABLE Stores (
--    StoreID INT PRIMARY KEY IDENTITY,
--    StoreName VARCHAR(100),
--    Address VARCHAR(200)
--);

--CREATE TABLE StoreInventoryLevels (
--    StoreInventoryID INT PRIMARY KEY IDENTITY,
--    StoreID INT NOT NULL,
--    ProductID INT NOT NULL,
--    Quantity INT DEFAULT 0,

--    FOREIGN KEY (StoreID) REFERENCES Stores(StoreID),
--    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
--);

INSERT INTO Brands (BrandName) VALUES 

('Nike'), 

('Adidas'), 

('Zara'), 

('Samsung'), 

('Apple'), 

('Maybelline'), 

('Puma'), 

('HP'), 

('Dior'), 

('Chanel'), 

('Kiko'); 

 

INSERT INTO Categories (CategoryName) VALUES 

('Technology'), 

('Shoes'), 

('Clothing'), 

('Cosmetics'); 

 

INSERT INTO Warehouses (WarehouseName, Address) VALUES 

('Merkez Depo', 'Ýstanbul'), 

('Yedek Depo', 'Ankara'); 

 

INSERT INTO StorageLocations (WarehouseID, ShelfCode) VALUES 

(1, 'A-01'), 

(1, 'A-02'), 

(1, 'B-01'), 

(1, 'B-02'), 

(1, 'C-01'), 

(1, 'C-02'); 

 

INSERT INTO Products (ProductName, Barcode, BrandID, CategoryID) VALUES 

('Basic T-Shirt',  'B001', 3, 3), 

('Jeans',          'B002', 3, 3), 

('Sweatshirt',     'B003', 1, 3), 

('Summer Dress',   'B004', 3, 3), 

('Sweater',        'B005', 1, 3), 

('Jacket',         'B006', 3, 3); 

 

INSERT INTO Products (ProductName, Barcode, BrandID, CategoryID) VALUES 

('Air Max Sports Shoes', 'B007', 1, 2), 

('RunStar Sports Shoes', 'B008', 2, 2), 

('Classic Leather Shoes','B009', 2, 2), 

('Casual Sneakers',      'B010', 7, 2), 

('High Heels',           'B011', 3, 2), 

('Outdoor Boots',        'B012', 7, 2); 

 

INSERT INTO Products (ProductName, Barcode, BrandID, CategoryID) VALUES 

('iPhone 15',         'B013', 5, 1), 

('Samsung Galaxy S23','B014', 4, 1), 

('HP Laptop 15"',     'B015', 8, 1), 

('AirPods 3',         'B016', 5, 1), 

('Samsung 55" TV',    'B017', 4, 1), 

('Apple Watch 9',     'B018', 5, 1); 

 

INSERT INTO Products (ProductName, Barcode, BrandID, CategoryID) VALUES 

('Red Lipstick',            'B019', 6, 4), 

('Foundation 30ml',         'B020', 6, 4), 

('Volume Mascara',          'B021', 6, 4), 

('Rose Water Toner',        'B022', 3, 4), 

('Moisturizing Cream 50ml', 'B023', 6, 4), 

('Facial Cleansing Gel',    'B024', 3, 4); 

 

INSERT INTO Suppliers (SupplierName, Phone, Email, Address) VALUES 

('Tedarikçi A', '05001234567', 'a@supply.com', 'Ýstanbul'), 

('Tedarikçi B', '05007654321', 'b@supply.com', 'Ankara'); 

 

INSERT INTO MovementTypes (MovementName) VALUES 

('IN'), 

('OUT'); 

 

INSERT INTO InventoryLevels (WarehouseID, StorageLocationID, ProductID, Quantity) VALUES 

-- SHELF A-01 (1,1) 

(1,1,1,40),   -- iPhone (Tech) 

(1,1,7,30),   -- Air Max (Shoes) 

(1,1,13,12),  -- AirPods (Tech) 

(1,1,19,25),  -- Lipstick (Cosmetics) 

  

-- SHELF A-02 (1,2) 

(1,2,2,35), 

(1,2,8,28), 

(1,2,14,10), 

(1,2,20,18), 

  

-- SHELF B-01 (1,3) 

(1,3,3,25), 

(1,3,9,22), 

(1,3,15,8), 

(1,3,21,22), 

  

-- SHELF B-02 (1,4) 

(1,4,4,20), 

(1,4,10,18), 

(1,4,16,20), 

(1,4,22,30), 

  

-- SHELF C-01 (1,5) 

(1,5,5,15), 

(1,5,11,12), 

(1,5,17,6), 

(1,5,23,16), 

  

-- SHELF C-02 (1,6) 

(1,6,6,10), 

(1,6,12,15), 

(1,6,18,14), 

(1,6,24,20); 

 

INSERT INTO StockMovements (WarehouseID, StorageLocationID, ProductID, MovementTypeID, Quantity) VALUES 

(1,1,1,1,40), 

(1,1,7,1,30), 

(1,1,13,1,12), 

(1,1,19,1,25), 

  

(1,2,2,1,35), 

(1,2,8,1,28), 

(1,2,14,1,10), 

(1,2,20,1,18), 

  

(1,3,3,1,25), 

(1,3,9,1,22), 

(1,3,15,1,8), 

(1,3,21,1,22), 

  

(1,4,4,1,20), 

(1,4,10,1,18), 

(1,4,16,1,20), 

(1,4,22,1,30), 

  

(1,5,5,1,15), 

(1,5,11,1,12), 

(1,5,17,1,6), 

(1,5,23,1,16), 

  

(1,6,6,1,10), 

(1,6,12,1,15), 

(1,6,18,1,14), 

(1,6,24,1,20); 

 

INSERT INTO Stores (StoreName, Address) VALUES 

('Store A', 'Kadikoy'), 

('Store B', 'Besiktas'); 

 

INSERT INTO StoreInventoryLevels (StoreID, ProductID, Quantity) VALUES 

(1, 1, 5), 

(1, 7, 3), 

(1, 13, 2), 

(1, 19, 4), 

  

(2, 3, 6), 

(2, 10, 4), 

(2, 18, 1), 

(2, 22, 3);


--EXEC sp_MSforeachtable "ALTER TABLE ? NOCHECK CONSTRAINT ALL";

---- ALT TABLOLAR ÖNCE SÝLÝNÝR
--DELETE FROM AuditItems;
--DELETE FROM PurchaseItems;
--DELETE FROM StoreInventoryLevels;
--DELETE FROM StockMovements;
--DELETE FROM InventoryLevels;
--DELETE FROM StorageLocations;

---- ORTA TABLOLAR
--DELETE FROM InventoryAudit;
--DELETE FROM Purchases;

---- ANA TABLOLAR
--DELETE FROM Products;
--DELETE FROM Categories;
--DELETE FROM Brands;
--DELETE FROM Suppliers;
--DELETE FROM MovementTypes;
--DELETE FROM Stores;
--DELETE FROM Warehouses;
--DELETE FROM Users;

--DBCC CHECKIDENT ('AuditItems', RESEED, 0);
--DBCC CHECKIDENT ('PurchaseItems', RESEED, 0);
--DBCC CHECKIDENT ('StoreInventoryLevels', RESEED, 0);
--DBCC CHECKIDENT ('StockMovements', RESEED, 0);
--DBCC CHECKIDENT ('InventoryLevels', RESEED, 0);
--DBCC CHECKIDENT ('StorageLocations', RESEED, 0);

--DBCC CHECKIDENT ('InventoryAudit', RESEED, 0);
--DBCC CHECKIDENT ('Purchases', RESEED, 0);

--DBCC CHECKIDENT ('Products', RESEED, 0);
--DBCC CHECKIDENT ('Categories', RESEED, 0);
--DBCC CHECKIDENT ('Brands', RESEED, 0);
--DBCC CHECKIDENT ('Suppliers', RESEED, 0);
--DBCC CHECKIDENT ('MovementTypes', RESEED, 0);
--DBCC CHECKIDENT ('Stores', RESEED, 0);
--DBCC CHECKIDENT ('Warehouses', RESEED, 0);
--DBCC CHECKIDENT ('Users', RESEED, 0);

--EXEC sp_MSforeachtable "ALTER TABLE ? WITH CHECK CHECK CONSTRAINT ALL";



SELECT * FROM Brands;
SELECT * FROM Categories;
SELECT * FROM Products;

SELECT * FROM Suppliers;
SELECT * FROM MovementTypes;

SELECT * FROM Warehouses;
SELECT * FROM StorageLocations;

SELECT * FROM Users;

SELECT * FROM Stores;

SELECT * FROM InventoryLevels;
SELECT * FROM StoreInventoryLevels;

SELECT * FROM StockMovements;

SELECT * FROM Purchases;
SELECT * FROM PurchaseItems;

SELECT * FROM InventoryAudit;
SELECT * FROM AuditItems;

SELECT * FROM USERS
