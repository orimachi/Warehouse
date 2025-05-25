package warehouse.view;

import java.awt.event.ItemEvent;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.table.DefaultTableModel;
import warehouse.bean.EStatus;
import warehouse.component.Pagination;
import warehouse.component.pagination.EventPagination;
import warehouse.component.pagination.PaginationItemRenderStyle1;
import warehouse.dao.StockDAO;
import warehouse.dao.StockOutDAO;
import warehouse.dao.WarehouseDAO;
import warehouse.entity.Account;
import warehouse.entity.Stock;
import warehouse.entity.StockOut;
import warehouse.entity.Warehouse;
import warehouse.utils.Auth;
import warehouse.utils.MessageBox;


public class StockJPanel extends javax.swing.JPanel {

    WarehouseDAO warehouseDAO = new WarehouseDAO();
    StockDAO stockDAO = new StockDAO();
    StockOutDAO stockOutDAO = new StockOutDAO();
    int pageSize = 10;
    Account user = Auth.user;
    
    public StockJPanel() {
        initComponents();
        this.fillTableStock(null, null);
        
        loadPaginationStock();
        loadWarehouseCombobox();
        
        
        txtSearch.setSearchListener(e -> {
            String keyword = txtSearch.getSearchText();
            UUID selectedWarehouseId = ((Warehouse) cbxWarehouse.getSelectedItem()).getId();
            fillTableStock(keyword, selectedWarehouseId);
        });

        // Stock Out Display
        this.fillTableStockOut(null, null, null);
        loadPaginationStockOut();
        loadWarehouseComboboxStockOut();
        loadStatusCombobox();
        txtSearchStockOut.setSearchListener(e -> {
            String keyword = txtSearchStockOut.getSearchText();
            UUID selectedWarehouseId = ((Warehouse) cbxWarehouseStockOut.getSelectedItem()).getId();
            String status = (String) cbxStatus.getSelectedItem();
            fillTableStockOut(keyword, selectedWarehouseId, status);
        });
        
    }
    
    void fillTableStock(String keyword, UUID warehouseUUID) {
        DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
        model.setRowCount(0);
        List<Stock> list;
        boolean hasKeyword = keyword != null && !keyword.isBlank();
        boolean hasWarehouse = warehouseUUID != null;

        int currentPage = paginationStock.getPage().getCurrent();
        if (!hasKeyword && !hasWarehouse) {
            list = stockDAO.selectAll();
        } else if (hasKeyword && !hasWarehouse) {
            list = stockDAO.searchStockByProductName(keyword);
        } else if (!hasKeyword && hasWarehouse) {
            list = stockDAO.selectByWarehouse(warehouseUUID);
        } else {
            list = stockDAO.searchStockByProductNameAndWarehouse(keyword, warehouseUUID);
        }

        updatePaginationStock(list);

        List<Stock> pageList = Pagination.getPage(list, currentPage, pageSize);
        for (Stock stock : pageList) {
            String productName = stockDAO.getProductByUUID(stock.getIdProduct());
            String warehouseName = stockDAO.getWarehouseByUUID(stock.getIdWareHouse());
            model.addRow(new Object[]{
                    stock.getId(),
                    productName,
                    stock.getQuantity(),
                    warehouseName
            });
        }
        tblStock.setModel(model);
    }


    private void loadWarehouseCombobox() {
        cbxWarehouse.removeAllItems();
        cbxWarehouse.addItem(new Warehouse(null, "All warehouses", null, null));
        for (Warehouse wh : warehouseDAO.selectAll()) {
            cbxWarehouse.addItem(wh);
        }
    }
    
    private void loadPaginationStock() {
        List<Stock> list = stockDAO.selectAll();
        updatePaginationStock(list);
        
        paginationStock.addEventPagination(new EventPagination(){
            @Override
            public void pageChanged(int page) {
                String keyword = txtSearch.getSearchText();
                UUID warehouseId = ((Warehouse) cbxWarehouse.getSelectedItem()).getId();
                fillTableStock(keyword, warehouseId); 
            }
        });
    }
    
    private void updatePaginationStock(List<Stock> list) {
        int totalPage = Pagination.getTotalPage(list, pageSize);
        int current = Pagination.getCurrentPage(list, pageSize);
        paginationStock.setPagegination(current, totalPage);
        paginationStock.setPaginationItemRender(new PaginationItemRenderStyle1());
    }
    
    private void displayStockDetails(int row) {
        DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
        UUID stockId = UUID.fromString(model.getValueAt(row, 0).toString());
        String productName = model.getValueAt(row, 1).toString();
        int quantity = Integer.parseInt(model.getValueAt(row, 2).toString());

        txtProduct.setText(productName);
        txtQuantity.setText(String.valueOf(quantity));
    }
    
    
    // Stock out UIs
    void fillTableStockOut(String keyword, UUID warehouseUUID, String status) {
        DefaultTableModel model = (DefaultTableModel) tblStockOut.getModel();
        model.setRowCount(0);
        List<StockOut> list;
        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();
        boolean hasWarehouse = warehouseUUID != null;
        boolean hasStatus = status != null && !status.equals("All statuses");

        int currentPage = paginationStockOut.getPage().getCurrent();
        if (!hasKeyword && !hasWarehouse && !hasStatus) {
            list = stockOutDAO.selectAll();
        } else if (hasKeyword && !hasWarehouse && !hasStatus) {
            list = stockOutDAO.searchStockOutByProductName(keyword);
        } else if (!hasKeyword && hasWarehouse && !hasStatus) {
            list = stockOutDAO.selectByWarehouse(warehouseUUID);
        } else if (!hasKeyword && !hasWarehouse && hasStatus) {
            list = stockOutDAO.selectByStatus(status);
        } else {
            list = stockOutDAO.searchStockOutByProductNameAndWarehouseAndStatus(keyword, warehouseUUID, status);
        }

        updatePaginationStockOut(list);

        List<StockOut> pageList = Pagination.getPage(list, currentPage, pageSize);
        for (StockOut stockOut : pageList) {
            String productName = stockOutDAO.getProductByUUID(stockOut.getIdProduct());
            String warehouseName = stockOutDAO.getWarehouseByUUID(stockOut.getIdWarehouse());
            model.addRow(new Object[]{
                    productName,
                    stockOut.getQuantity(),
                    warehouseName,
                    stockOut.getUsername(),
                    stockOut.getUpdatedDate(),
                    stockOut.getStatus()
            });
        }
        tblStockOut.setModel(model);
    }

    private void loadWarehouseComboboxStockOut() {
        cbxWarehouseStockOut.removeAllItems();
        cbxWarehouseStockOut.addItem(new Warehouse(null, "All warehouses", null, null));
        for (Warehouse wh : warehouseDAO.selectAll()) {
            cbxWarehouseStockOut.addItem(wh);
        }
    }

    private void loadStatusCombobox() {
        cbxStatus.removeAllItems();
        cbxStatus.addItem("All statuses");
        cbxStatus.addItem("ACCEPT");
        cbxStatus.addItem("CANCEL");
        cbxStatus.addItem("PROCESSING");
    }

    private void loadPaginationStockOut() {
        List<StockOut> list = stockOutDAO.selectAll();
        updatePaginationStockOut(list);

        paginationStockOut.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                String keyword = txtSearchStockOut.getSearchText();
                UUID warehouseId = ((Warehouse) cbxWarehouseStockOut.getSelectedItem()).getId();
                String status = (String) cbxStatus.getSelectedItem();
                fillTableStockOut(keyword, warehouseId, status);
            }
        });
    }

    private void updatePaginationStockOut(List<StockOut> list) {
        int totalPage = Pagination.getTotalPage(list, pageSize);
        int current = Pagination.getCurrentPage(list, pageSize);
        paginationStockOut.setPagegination(current, totalPage);
        paginationStockOut.setPaginationItemRender(new PaginationItemRenderStyle1());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cbxWarehouse = new warehouse.component.Combobox();
        txtSearch = new warehouse.component.SearchBar();
        btnExport = new warehouse.component.button.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStock = new javax.swing.JTable();
        paginationStock = new warehouse.component.Pagination();
        txtQuantity = new warehouse.component.textfield.TextField();
        txtProduct = new warehouse.component.textfield.TextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        cbxWarehouseStockOut = new warehouse.component.Combobox();
        txtSearchStockOut = new warehouse.component.SearchBar();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblStockOut = new javax.swing.JTable();
        paginationStockOut = new warehouse.component.Pagination();
        cbxStatus = new warehouse.component.Combobox();

        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        cbxWarehouse.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbxWarehouse.setLabeText("Warehouse");
        cbxWarehouse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxWarehouseItemStateChanged(evt);
            }
        });
        cbxWarehouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxWarehouseActionPerformed(evt);
            }
        });

        txtSearch.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        btnExport.setBackground(new java.awt.Color(52, 152, 219));
        btnExport.setForeground(new java.awt.Color(255, 255, 255));
        btnExport.setText("EXPORT");
        btnExport.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        tblStock.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Quantity", "Warehouse"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStockMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStock);

        paginationStock.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtQuantity.setLabelText("Quantity");
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQuantityKeyTyped(evt);
            }
        });

        txtProduct.setLabelText("Product");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(paginationStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(379, 379, 379))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(txtProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(cbxWarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxWarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(paginationStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Stock", jPanel3);

        cbxWarehouseStockOut.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbxWarehouseStockOut.setLabeText("Warehouse");
        cbxWarehouseStockOut.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxWarehouseStockOutItemStateChanged(evt);
            }
        });
        cbxWarehouseStockOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxWarehouseStockOutActionPerformed(evt);
            }
        });

        tblStockOut.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblStockOut.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Quantity", "Warehouse", "Username", "Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblStockOut);

        cbxStatus.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbxStatus.setLabeText("Status");
        cbxStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxStatusItemStateChanged(evt);
            }
        });
        cbxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtSearchStockOut, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(cbxWarehouseStockOut, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(319, 319, 319)
                        .addComponent(paginationStockOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSearchStockOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxWarehouseStockOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addComponent(paginationStockOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Stock Out", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbxWarehouseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxWarehouseItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Warehouse selectedWarehouse = (Warehouse) cbxWarehouse.getSelectedItem();
            UUID warehouseId = selectedWarehouse != null ? selectedWarehouse.getId() : null;
            String keyword = txtSearch.getSearchText();
            fillTableStock(keyword, warehouseId);
        }
    }//GEN-LAST:event_cbxWarehouseItemStateChanged

    private void cbxWarehouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxWarehouseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxWarehouseActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblStock.getSelectedRow();
        if (selectedRow < 0) {
            MessageBox.warning(this, "Please select a stock record to export");
            return;
        }

        try {
            DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
            UUID stockId = UUID.fromString(model.getValueAt(selectedRow, 0).toString());
            String productName = model.getValueAt(selectedRow, 1).toString();
            int stockQuantity = Integer.parseInt(model.getValueAt(selectedRow, 2).toString());
            String warehouseName = model.getValueAt(selectedRow, 3).toString();

            String quantityText = txtQuantity.getText().trim();
            if (quantityText.isEmpty()) {
                MessageBox.warning(this, "Please enter a quantity");
                return;
            }

            int exportQuantity;
            try {
                exportQuantity = Integer.parseInt(quantityText);
            } catch (NumberFormatException e) {
                MessageBox.warning(this, "Invalid quantity format");
                return;
            }

            if (exportQuantity > stockQuantity) {
                MessageBox.warning(this, "Input quantity exceeds current stock: " + stockQuantity);
                return;
            }

            UUID warehouseId = ((Warehouse) cbxWarehouse.getSelectedItem()).getId();
            if (warehouseId == null) {
                warehouseId = stockDAO.selectById(stockId).getIdWareHouse();
            }

            StockOut stockOut = new StockOut();
            stockOut.setId(UUID.randomUUID());
            stockOut.setQuantity(exportQuantity);
            stockOut.setIdProduct(stockDAO.getProductUUIDByName(productName));
            stockOut.setIdWarehouse(warehouseId);
            //            stockOut.setUsername(user.getUsername());
            stockOut.setUsername("user1");
            stockOut.setUpdatedDate(new Date());
            stockOut.setStatus(EStatus.PROCESSING);

            stockOutDAO.insert(stockOut);
            MessageBox.success(this, "Stock exported to StockOut successfully");

            String keyword = txtSearchStockOut.getSearchText();
            UUID selectedWarehouseId = ((Warehouse) cbxWarehouseStockOut.getSelectedItem()).getId();
            String status = (String) cbxStatus.getSelectedItem();
            fillTableStockOut(keyword, selectedWarehouseId, status);

            String keywordStock = txtSearch.getSearchText();
            UUID selectedWarehouseIdStock = ((Warehouse) cbxWarehouse.getSelectedItem()).getId();
            fillTableStock(keywordStock, selectedWarehouseIdStock);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnExportActionPerformed

    private void tblStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStockMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblStock.getSelectedRow();
        if(evt.getClickCount()==2){
            displayStockDetails(selectedRow);
        }
    }//GEN-LAST:event_tblStockMouseClicked

    private void txtQuantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtQuantityKeyTyped

    private void cbxWarehouseStockOutItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxWarehouseStockOutItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Warehouse selectedWarehouse = (Warehouse) cbxWarehouseStockOut.getSelectedItem();
            UUID warehouseId = selectedWarehouse != null ? selectedWarehouse.getId() : null;
            String keyword = txtSearchStockOut.getSearchText();
            String status = (String) cbxStatus.getSelectedItem();
            fillTableStockOut(keyword, warehouseId, status);
        }
    }//GEN-LAST:event_cbxWarehouseStockOutItemStateChanged

    private void cbxWarehouseStockOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxWarehouseStockOutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxWarehouseStockOutActionPerformed

    private void cbxStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxStatusItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String keyword = txtSearchStockOut.getSearchText();
            UUID warehouseId = ((Warehouse) cbxWarehouseStockOut.getSelectedItem()).getId();
            String status = (String) cbxStatus.getSelectedItem();
            fillTableStockOut(keyword, warehouseId, status);
        }
    }//GEN-LAST:event_cbxStatusItemStateChanged

    private void cbxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxStatusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private warehouse.component.button.Button btnExport;
    private warehouse.component.Combobox cbxStatus;
    private warehouse.component.Combobox cbxWarehouse;
    private warehouse.component.Combobox cbxWarehouseStockOut;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private warehouse.component.Pagination paginationStock;
    private warehouse.component.Pagination paginationStockOut;
    private javax.swing.JTable tblStock;
    private javax.swing.JTable tblStockOut;
    private warehouse.component.textfield.TextField txtProduct;
    private warehouse.component.textfield.TextField txtQuantity;
    private warehouse.component.SearchBar txtSearch;
    private warehouse.component.SearchBar txtSearchStockOut;
    // End of variables declaration//GEN-END:variables
}
