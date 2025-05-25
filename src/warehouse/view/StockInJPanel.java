package warehouse.view;

import java.util.List;
import java.util.UUID;
import javax.swing.table.DefaultTableModel;
import warehouse.bean.EStatus;
import warehouse.component.Pagination;
import warehouse.component.pagination.EventPagination;
import warehouse.component.pagination.PaginationItemRenderStyle1;
import warehouse.dao.ProductDAO;
import warehouse.dao.StockInDAO;
import warehouse.dao.SuppliersDAO;
import warehouse.dao.WarehouseDAO;
import warehouse.entity.Account;
import warehouse.entity.Product;
import warehouse.entity.StockIn;
import warehouse.entity.Suppliers;
import warehouse.entity.Warehouse;
import warehouse.utils.Auth;
import warehouse.utils.MessageBox;


public class StockInJPanel extends javax.swing.JPanel {

    SuppliersDAO suppliersDAO = new SuppliersDAO();
    ProductDAO productDAO = new ProductDAO();
    WarehouseDAO warehouseDAO = new WarehouseDAO();
    StockInDAO stockInDAO = new StockInDAO();
    int pageSize = 10;
    Account currentUser = Auth.user;
    
    public StockInJPanel() {
        initComponents();
        loadDataTblProducts(null);
        loadPagination();
        loadDataCbxProduct();
        loadDataCbxWarehouse();
        loadDataCbxSupplier();
        txtSearch.setSearchListener(e -> {
            String keyword = txtSearch.getSearchText();
            loadDataTblProducts(keyword);
        });
        
        cbxProduct.addActionListener(e -> updateSupplierComboBox());
        
    }
    
    private void loadDataTblProducts(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
        model.setRowCount(0);
        
        boolean hasKeyword = keyword != null && !keyword.isBlank();

        List<Product> list;
        if(!hasKeyword){
            list = productDAO.selectAll();
        } else {
            list = productDAO.getProductByKey(keyword);
        }
                
        int currentPage = paginationProduct.getPage().getCurrent();

        List<Product> pageList = Pagination.getPage(list, currentPage, pageSize);

        for (Product product : pageList) {
            String nameSupplier = suppliersDAO.getNameByUUID(product.getIdSupplier());
            model.addRow(new Object[]{
                product.getId(),
                product.getName(),
                product.getCalcUnit().name(),
                product.getDescription(),
                nameSupplier,
                product.getCategory().name()
            });
        }
    }
    
    private void loadDataCbxProduct(){
        cbxProduct.removeAllItems();
        cbxProduct.addItem(null);
        for (Product pr : productDAO.selectAll()) {
            cbxProduct.addItem(pr);
        }
    }
    
    private void loadDataCbxWarehouse(){
        cbxWarehouse.removeAllItems();
        cbxWarehouse.addItem(null);
        for (Warehouse wh : warehouseDAO.selectAll()) {
            cbxWarehouse.addItem(wh);
        }
    }
    
    private void loadDataCbxSupplier(){
        cbxSupplier.removeAllItems();
        cbxSupplier.addItem(null);
        for (Suppliers wh : suppliersDAO.selectAll()) {
            cbxSupplier.addItem(wh);
        }
    }
    
    private void updateSupplierComboBox() {
        cbxSupplier.removeAllItems();
        cbxSupplier.addItem(null);

        Product selectedProduct = (Product) cbxProduct.getSelectedItem();
        if (selectedProduct != null && selectedProduct.getIdSupplier() != null) {
            Suppliers supplier = suppliersDAO.selectById(selectedProduct.getIdSupplier());
            if (supplier != null) {
                cbxSupplier.addItem(supplier);
            }
        } else {
            loadDataCbxSupplier();
        }
    }
    
    private void loadPagination() {
        List<Product> list = productDAO.selectAll();
        int totalPage = Pagination.getTotalPage(list, pageSize);
        int current = Pagination.getCurrentPage(list, pageSize);
        paginationProduct.setPagegination(current, totalPage);
        paginationProduct.setPaginationItemRender(new PaginationItemRenderStyle1());
        
        paginationProduct.addEventPagination(new EventPagination(){
            @Override
            public void pageChanged(int page) {
                  loadDataTblProducts(null); 
            }
        });
    }
    
    private boolean validateInformation() {
        Object product = cbxProduct.getSelectedItem();
        Object supplier = cbxSupplier.getSelectedItem();
        String quantity = txtQuantity.getText().trim();
        Object warehouse = cbxWarehouse.getSelectedItem();

        if (quantity.isEmpty()) {
            MessageBox.warning(this, "Quantity is required!");
            return false;
        }  
        
        if (product == null) {
            MessageBox.warning(this, "Product is required!");
            return false;
        }  
        
        if (supplier == null) {
            MessageBox.warning(this, "Supplier is required!");
            return false;
        }  
        
        if (warehouse == null) {
            MessageBox.warning(this, "Warehouse is required!");
            return false;
        }
        return true;
    }

    private StockIn getInformationForm() {
     
        StockIn stock = new StockIn();
        stock.setIdProduct(productDAO.getUUIDByName(String.valueOf(cbxProduct.getSelectedItem())));
        stock.setIdSupplier(suppliersDAO.getUUIDByName(String.valueOf(cbxSupplier.getSelectedItem())));
        stock.setQuantity(Integer.parseInt(txtQuantity.getText()));
        stock.setIdWarehouse(warehouseDAO.getUUIDByName(String.valueOf(cbxWarehouse.getSelectedItem())));
        stock.setUsername(currentUser.getUsername());
        stock.setStatus(EStatus.PROCESSING);
        return stock;
    }
    
    private void clearForm() {
        cbxProduct.setSelectedItem(null);
        cbxSupplier.setSelectedItem(null);
        cbxWarehouse.setSelectedItem(null);
        txtQuantity.setText("");
    }
    
    private void insert() {
        try {
            if (validateInformation()) {
                StockIn stock = getInformationForm();
                System.out.println(productDAO.getUUIDByName(String.valueOf(cbxProduct.getSelectedItem())));
                stockInDAO.insert(stock);
                MessageBox.success(this, "Create a new stock successfully");
                clearForm();
            }
        } catch (RuntimeException e) {
            MessageBox.error(this, e.getMessage());
            System.out.println(e.getMessage());
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSearch = new warehouse.component.SearchBar();
        paginationProduct = new warehouse.component.Pagination();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtQuantity = new warehouse.component.textfield.TextField();
        btnCreate = new warehouse.component.button.Button();
        btnRefresh = new warehouse.component.button.Button();
        cbxProduct = new warehouse.component.ComboBoxSuggestion();
        cbxSupplier = new warehouse.component.ComboBoxSuggestion();
        cbxWarehouse = new warehouse.component.ComboBoxSuggestion();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        paginationProduct.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        tblProducts.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name ", "Caculation Unit", "Description", "Supplier", "Category"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProducts.setRequestFocusEnabled(false);
        tblProducts.setRowHeight(30);
        tblProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblProducts);

        jPanel2.setBackground(new java.awt.Color(52, 152, 219));
        jPanel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Stock In");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(332, 332, 332)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        txtQuantity.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtQuantity.setLabelText("");
        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQuantityKeyTyped(evt);
            }
        });

        btnCreate.setBackground(new java.awt.Color(52, 152, 219));
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        btnCreate.setText("CREATE");
        btnCreate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnRefresh.setBackground(new java.awt.Color(149, 165, 166));
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("REFRESH");
        btnRefresh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        cbxWarehouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxWarehouseActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Product");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Supplier");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Quantity");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Warehouse");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(cbxProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxWarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbxProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(cbxWarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(paginationProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(paginationProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductsMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2) {
            int selectedRow = tblProducts.getSelectedRow();
            if(selectedRow!=-1){
                String id = tblProducts.getValueAt(selectedRow, 0).toString();
                Product selectedProduct = productDAO.selectById(UUID.fromString(id));
                cbxProduct.setSelectedItem(selectedProduct);
                Suppliers sp = suppliersDAO.selectById(selectedProduct.getIdSupplier());
                cbxSupplier.setSelectedItem(sp);
            }
        }
    }//GEN-LAST:event_tblProductsMouseClicked

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void txtQuantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_txtQuantityKeyTyped

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void cbxWarehouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxWarehouseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxWarehouseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private warehouse.component.button.Button btnCreate;
    private warehouse.component.button.Button btnRefresh;
    private warehouse.component.ComboBoxSuggestion cbxProduct;
    private warehouse.component.ComboBoxSuggestion cbxSupplier;
    private warehouse.component.ComboBoxSuggestion cbxWarehouse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private warehouse.component.Pagination paginationProduct;
    private javax.swing.JTable tblProducts;
    private warehouse.component.textfield.TextField txtQuantity;
    private warehouse.component.SearchBar txtSearch;
    // End of variables declaration//GEN-END:variables
}
