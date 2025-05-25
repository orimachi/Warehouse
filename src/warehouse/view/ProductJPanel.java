package warehouse.view;

import java.awt.Color;
import java.util.List;
import java.util.UUID;
import javax.swing.table.DefaultTableModel;
import warehouse.bean.ECalcUnit;
import warehouse.bean.ECategory;
import warehouse.bean.EPageSize;
import warehouse.component.Pagination;
import warehouse.component.cell.ActionCellEditor;
import warehouse.component.cell.TableActionCellRender;
import warehouse.component.cell.TableActionEvent;
import warehouse.component.pagination.EventPagination;
import warehouse.component.pagination.PaginationItemRenderStyle1;
import warehouse.dao.ProductDAO;
import warehouse.dao.SuppliersDAO;
import warehouse.entity.Product;
import warehouse.entity.Suppliers;
import warehouse.utils.MessageBox;

public class ProductJPanel extends javax.swing.JPanel {

    public ProductJPanel() {
        initComponents();
        loadDataCBBCalcUnit();
        loadDataCBBSupplier();
        loadDataCBBCategory();
        loadDataTblProducts();
        loadPagination();

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                selectProduct();
            }

            @Override
            public void onDelete(int row) {
                deleteProduct();
            }
        };

        tblProducts.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        tblProducts.getColumnModel().getColumn(6).setCellEditor(new ActionCellEditor(event));

        txtsearchBarProduct.setSearchListener(e -> {
            String keyword = txtsearchBarProduct.getSearchText();
            loadDataTblSearchByNameProduct(keyword);
        });
    }

    SuppliersDAO suppliersDAO = new SuppliersDAO();
    ProductDAO productDAO = new ProductDAO();
    int pageSize = EPageSize.MEDIUM.getSize();

    private void loadDataTblSearchByNameProduct(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
        model.setRowCount(0);

        List<Product> list = productDAO.selectByNameKeyWord(keyword);
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

    private void loadDataTblProducts() {
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
        model.setRowCount(0);

        List<Product> list = productDAO.selectAll();
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

    private void loadPagination() {
        List<Product> list = productDAO.selectAll();
        int totalPage = Pagination.getTotalPage(list, pageSize);
        int current = Pagination.getCurrentPage(list, pageSize);
        paginationProduct.setPagegination(current, totalPage);
        paginationProduct.setPaginationItemRender(new PaginationItemRenderStyle1());

        paginationProduct.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadDataTblProducts();
            }
        });
    }

    private void loadDataCBBCalcUnit() {
        cbbCalcUnit.removeAllItems();
        cbbCalcUnit.addItem("-- Please choice --");
        for (ECalcUnit calcUnit : ECalcUnit.values()) {
            cbbCalcUnit.addItem(calcUnit.toString());
        }
        cbbCalcUnit.setSelectedIndex(0);
    }

    private void loadDataCBBCategory() {
        cbbCategory.removeAllItems();
        cbbCategory.addItem("-- Please choice --");
        for (ECategory category : ECategory.values()) {
            cbbCategory.addItem(category.toString());
        }
        cbbCategory.setSelectedIndex(0);
    }

    private void loadDataCBBSupplier() {
        List<Suppliers> list = suppliersDAO.selectAll();
        cbbSuppliers.removeAllItems();
        cbbSuppliers.addItem("-- Please choice --");
        for (Suppliers dataCbb : list) {
            cbbSuppliers.addItem(dataCbb.getName());
        }
        cbbSuppliers.setSelectedIndex(0);
    }

    private boolean validateInformation() {
        String name = txtName.getText();
        String description = txtDescription.getText();
        String calcUnit = cbbCalcUnit.getSelectedItem().toString();
        String category = cbbCategory.getSelectedItem().toString();
        String idSupplier = cbbSuppliers.getSelectedItem().toString();

        if (name.isEmpty() == true) {
            MessageBox.warning(this, "Name product is required!");
            return false;
        } else if (description.isEmpty() == true) {
            MessageBox.warning(this, "Description product is required!");
            return false;
        } else if (calcUnit == null || calcUnit.startsWith("--")) {
            MessageBox.warning(this, "Caculation unit product is required!");
            return false;
        } else if (category == null || category.startsWith("--")) {
            MessageBox.warning(this, "Category product is required!");
            return false;
        } else if (idSupplier == null || idSupplier.startsWith("--")) {
            MessageBox.warning(this, "Suppliers product is required!");
            return false;
        }
        return true;
    }

    private boolean validateID() {
        String idSupplier = txtIDProduct.getText();
        String name = txtName.getText();
        List<Product> list = productDAO.selectAll();
        for (Product product : list) {
            if (idSupplier.equals(product.getId().toString())) {
                MessageBox.warning(this, "IDSupplier already exist your cant insert use update");
                return false;
            } else if (name.equals(product.getName())) {
                MessageBox.warning(this, "Name already exist choice another name");
                return false;
            }
        }
        return true;
    }

    private Product getInformationForm() {
        Product product = new Product();
        String idText = txtIDProduct.getText().trim();
        if (!idText.isEmpty()) {
            try {
                product.setId(UUID.fromString(idText));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                throw new RuntimeException("ID không hợp lệ: " + idText);
            }
        }
        product.setName(txtName.getText());
        product.setDescription(txtDescription.getText());
        product.setCalcUnit(ECalcUnit.valueOf(String.valueOf(cbbCalcUnit.getSelectedItem())));
        product.setIdSupplier(suppliersDAO.getUUIDByName(String.valueOf(cbbSuppliers.getSelectedItem())));
        product.setCategory(ECategory.valueOf(String.valueOf(cbbCategory.getSelectedItem())));
        return product;
    }

    private void clearProductInformationForm() {
        txtIDProduct.setText("");
        txtName.setText("");
        txtDescription.setText("");
        cbbCalcUnit.setSelectedIndex(0);
        cbbCategory.setSelectedIndex(0);
        cbbSuppliers.setSelectedIndex(0);
    }

    private void setProductInformationForm(Product product) {
        txtIDProduct.setText(product.getId().toString());
        txtName.setText(product.getName());
        txtDescription.setText(product.getDescription());
        cbbCalcUnit.setSelectedItem(product.getCalcUnit().name());
        cbbCategory.setSelectedItem(product.getCategory().name());
        cbbSuppliers.setSelectedItem(suppliersDAO.getNameByUUID(product.getIdSupplier()));
    }

    private void insertProduct() {
        try {
            if (validateInformation() == true && validateID() == true) {
                Product product = getInformationForm();
                productDAO.insert(product);
                MessageBox.success(this, "Insert product success");
                loadDataTblProducts();
                clearProductInformationForm();
            }
        } catch (RuntimeException e) {
            MessageBox.error(this, e.getMessage());
        }
    }

    private void updateProduct() {
        try {
            if (validateInformation() == true) {
                Product product = getInformationForm();
                productDAO.update(product);
                MessageBox.success(this, "Update product success");
                loadDataTblProducts();
                clearProductInformationForm();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.error(this, e.getMessage());
        }
    }

    private void deleteProduct() {
        int row = tblProducts.getSelectedRow();
        if (row == -1) {
            return;
        }
        String idProduct = tblProducts.getValueAt(row, 0).toString().isEmpty() ? txtIDProduct.getText() : tblProducts.getValueAt(row, 0).toString();
        try {
            if (!idProduct.isEmpty()) {
                productDAO.delete(UUID.fromString(idProduct));
                MessageBox.success(this, "Delete product success");
                loadDataTblProducts();
                clearProductInformationForm();
            }
        } catch (RuntimeException e) {
            MessageBox.error(this, "System error check output");
        }
    }

    private void resetButton() {
        clearProductInformationForm();
    }

    private void selectProduct() {
        int row = tblProducts.getSelectedRow();
        if (row == -1) {
            return;
        }
        String idProduct = tblProducts.getValueAt(row, 0).toString();
        Product product = productDAO.selectById(UUID.fromString(idProduct));
        setProductInformationForm(product);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbbCategory = new warehouse.component.Combobox();
        cbbCalcUnit = new warehouse.component.Combobox();
        cbbSuppliers = new warehouse.component.Combobox();
        txtName = new warehouse.component.textfield.TextField();
        jLabel6 = new javax.swing.JLabel();
        txtIDProduct = new warehouse.component.textfield.TextField();
        txtsearchBarProduct = new warehouse.component.SearchBar();
        btnAdd = new warehouse.component.button.Button();
        btnUpdate = new warehouse.component.button.Button();
        btnDelete = new warehouse.component.button.Button();
        btnReset = new warehouse.component.button.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        paginationProduct = new warehouse.component.Pagination();

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Name");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Caculation Unit");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Description");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Supplier");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Category");

        cbbCategory.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbbCategory.setLabeText("");

        cbbCalcUnit.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbbCalcUnit.setLabeText("");

        cbbSuppliers.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbbSuppliers.setLabeText("");

        txtName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtName.setLabelText("");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("ID");

        txtIDProduct.setEditable(false);
        txtIDProduct.setLabelText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addComponent(txtIDProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cbbSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel2))
                            .addComponent(jLabel4)
                            .addComponent(cbbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cbbCalcUnit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbCalcUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        btnAdd.setBackground(new java.awt.Color(0, 0, 0));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("ADD");
        btnAdd.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddMouseExited(evt);
            }
        });
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(46, 204, 113));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("UPDATE");
        btnUpdate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUpdateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUpdateMouseExited(evt);
            }
        });
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(231, 76, 60));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("DELETE");
        btnDelete.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteMouseExited(evt);
            }
        });
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(149, 165, 166));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("RESET");
        btnReset.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnResetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnResetMouseExited(evt);
            }
        });
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        tblProducts.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name ", "Caculation Unit", "Description", "Supplier", "Category", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
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

        paginationProduct.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(txtsearchBarProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(paginationProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(txtsearchBarProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(paginationProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseEntered
        btnAdd.setBackground(new Color(52, 152, 219));
    }//GEN-LAST:event_btnAddMouseEntered

    private void btnAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseExited
        btnAdd.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnAddMouseExited

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        this.insertProduct();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateMouseEntered

    private void btnUpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateMouseExited

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        this.updateProduct();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteMouseExited

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        this.deleteProduct();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetMouseEntered

    private void btnResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetMouseExited

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        this.resetButton();
    }//GEN-LAST:event_btnResetActionPerformed

    private void tblProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductsMouseClicked
        if (evt.getClickCount() == 2) {
            this.selectProduct();
        }
    }//GEN-LAST:event_tblProductsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private warehouse.component.button.Button btnAdd;
    private warehouse.component.button.Button btnDelete;
    private warehouse.component.button.Button btnReset;
    private warehouse.component.button.Button btnUpdate;
    private warehouse.component.Combobox cbbCalcUnit;
    private warehouse.component.Combobox cbbCategory;
    private warehouse.component.Combobox cbbSuppliers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private warehouse.component.Pagination paginationProduct;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextArea txtDescription;
    private warehouse.component.textfield.TextField txtIDProduct;
    private warehouse.component.textfield.TextField txtName;
    private warehouse.component.SearchBar txtsearchBarProduct;
    // End of variables declaration//GEN-END:variables
}
