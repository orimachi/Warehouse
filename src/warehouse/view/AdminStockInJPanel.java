package warehouse.view;

import java.awt.Color;
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
import warehouse.entity.StockIn;
import warehouse.utils.ConvertDate;
import warehouse.utils.MessageBox;

public class AdminStockInJPanel extends javax.swing.JPanel {

    public AdminStockInJPanel() {
        initComponents();
        loadDataTblAdminStockIn();
        loadPagination();

    }

    StockInDAO stockInDAO = new StockInDAO();
    ProductDAO productDAO = new ProductDAO();
    SuppliersDAO suppliersDAO = new SuppliersDAO();
    WarehouseDAO warehouseDAO = new WarehouseDAO();
    int pageSize = 5;

    private void loadDataTblAdminStockIn() {
        DefaultTableModel model = (DefaultTableModel) tblAdminStockIn.getModel();
        model.setRowCount(0);

        List<StockIn> list = stockInDAO.selectALLByStatusProcessing(EStatus.PROCESSING);
        int currentPage = paginationAdminStockIn.getPage().getCurrent();

        List<StockIn> pageList = Pagination.getPage(list, currentPage, pageSize);

        for (StockIn stock : pageList) {
            String nameProduct = productDAO.getNameByUUID(stock.getIdProduct());
            String nameSupplier = suppliersDAO.getNameByUUID(stock.getIdSupplier());
            String nameWarehouse = warehouseDAO.getNameByUUID(stock.getIdWarehouse());
            model.addRow(new Object[]{
                stock.getId(),
                nameProduct,
                stock.getQuantity(),
                nameSupplier,
                nameWarehouse,
                stock.getUsername(),
                ConvertDate.toString(stock.getUpdatedDate(), "dd-MM-yyyy"),
                stock.getStatus()
            });
        }
    }

    private void loadPagination() {
        List<StockIn> list = stockInDAO.selectALLByStatusProcessing(EStatus.PROCESSING);
        int totalPage = Pagination.getTotalPage(list, pageSize);
        int current = Pagination.getCurrentPage(list, pageSize);
        paginationAdminStockIn.setPagegination(current, totalPage);
        paginationAdminStockIn.setPaginationItemRender(new PaginationItemRenderStyle1());

        paginationAdminStockIn.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadDataTblAdminStockIn();
            }
        });
    }

    private StockIn getInformationForm() {
        StockIn stock = new StockIn();
        stock.setId(UUID.fromString(txtIDStockIn.getText()));
        stock.setIdProduct(productDAO.getUUIDByName(txtIDProduct.getText()));
        stock.setQuantity(Integer.parseInt(txtQuantity.getText()));
        stock.setIdSupplier(suppliersDAO.getUUIDByName(String.valueOf(txtIDSupplier.getText())));
        stock.setIdWarehouse(warehouseDAO.getUUIDByName(txtIDWarehouse.getText()));
        stock.setUsername(txtUsername.getText());
        stock.setUpdatedDate(ConvertDate.toDate(txtUpdateDate.getText(), "dd-MM-yyyy"));
        stock.setStatus(EStatus.valueOf(txtStatus.getText()));
        return stock;
    }

    private void clearInformationForm() {
        txtIDStockIn.setText("");
        txtIDProduct.setText("");
        txtQuantity.setText("");
        txtUsername.setText("");
        txtIDSupplier.setText("");
        txtUpdateDate.setText("");
        txtStatus.setText("");
        txtIDWarehouse.setText("");
    }

    private void setInformationForm(StockIn stock) {
        txtIDStockIn.setText(stock.getId().toString());
        txtIDProduct.setText(productDAO.getNameByUUID(stock.getIdProduct()));
        txtQuantity.setText(String.valueOf(stock.getQuantity()));
        txtUsername.setText(stock.getUsername());
        txtIDSupplier.setText(suppliersDAO.getNameByUUID(stock.getIdSupplier()));
        txtUpdateDate.setText(ConvertDate.toString(stock.getUpdatedDate(), "dd-MM-yyyy"));
        txtStatus.setText(stock.getStatus().name());
        txtIDWarehouse.setText(warehouseDAO.getNameByUUID(stock.getIdWarehouse()));
    }

    private void confirmStockIn() {
        try {
            if (validateForm() == true) {
                StockIn stock = getInformationForm();
                stock.setStatus(EStatus.ACCECPT);
                stockInDAO.update(stock);
                MessageBox.success(this, "Confirm success");
                loadDataTblAdminStockIn();
                clearInformationForm();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.error(this, "System error check output");
        }
    }

    private void cancelStockIn() {
        try {
            if (validateForm() == true) {
                StockIn stock = getInformationForm();
                stock.setStatus(EStatus.CANCEL);
                stockInDAO.update(stock);
                MessageBox.success(this, "Cancel success");
                loadDataTblAdminStockIn();
                clearInformationForm();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.error(this, "System error check output");
        }
    }

    private boolean validateForm() {
        if (txtIDProduct.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private void selectStockIn() {
        int row = tblAdminStockIn.getSelectedRow();
        String idStockIn = tblAdminStockIn.getValueAt(row, 0).toString();
        StockIn stock = stockInDAO.selectById(UUID.fromString(idStockIn));
        setInformationForm(stock);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        tblAdminStockIn = new javax.swing.JTable();
        paginationAdminStockIn = new warehouse.component.Pagination();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtQuantity = new warehouse.component.textfield.TextField();
        jLabel6 = new javax.swing.JLabel();
        txtIDStockIn = new warehouse.component.textfield.TextField();
        txtIDWarehouse = new warehouse.component.textfield.TextField();
        txtIDProduct = new warehouse.component.textfield.TextField();
        txtUpdateDate = new warehouse.component.textfield.TextField();
        txtIDSupplier = new warehouse.component.textfield.TextField();
        txtStatus = new warehouse.component.textfield.TextField();
        jLabel7 = new javax.swing.JLabel();
        txtUsername = new warehouse.component.textfield.TextField();
        jLabel8 = new javax.swing.JLabel();
        btnConfirm = new warehouse.component.button.Button();
        btnCancel = new warehouse.component.button.Button();

        tblAdminStockIn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblAdminStockIn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Product", "Quantity", "Supplier", "Warehouse", "Username", "Updated Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAdminStockIn.setRequestFocusEnabled(false);
        tblAdminStockIn.setRowHeight(30);
        tblAdminStockIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAdminStockInMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblAdminStockIn);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Quantity");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Name");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Warehouse");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Supplier");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Username");

        txtQuantity.setEditable(false);
        txtQuantity.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtQuantity.setLabelText("");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("ID");

        txtIDStockIn.setEditable(false);
        txtIDStockIn.setLabelText("");

        txtIDWarehouse.setEditable(false);
        txtIDWarehouse.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtIDWarehouse.setLabelText("");

        txtIDProduct.setEditable(false);
        txtIDProduct.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtIDProduct.setLabelText("");

        txtUpdateDate.setEditable(false);
        txtUpdateDate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtUpdateDate.setLabelText("");

        txtIDSupplier.setEditable(false);
        txtIDSupplier.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtIDSupplier.setLabelText("");

        txtStatus.setEditable(false);
        txtStatus.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtStatus.setLabelText("");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Updated Date");

        txtUsername.setEditable(false);
        txtUsername.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtUsername.setLabelText("");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Status");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3)
                        .addComponent(jLabel6)
                        .addComponent(txtIDStockIn, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIDWarehouse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtUpdateDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel7))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtIDSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                            .addComponent(txtStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIDProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
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
                    .addComponent(txtIDStockIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIDWarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUpdateDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        btnConfirm.setBackground(new java.awt.Color(0, 0, 0));
        btnConfirm.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirm.setText("CONFIRM");
        btnConfirm.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfirmMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfirmMouseExited(evt);
            }
        });
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(0, 0, 0));
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("CANCEL");
        btnCancel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelMouseExited(evt);
            }
        });
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(349, 349, 349)
                        .addComponent(paginationAdminStockIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(paginationAdminStockIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmMouseEntered
        btnConfirm.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnConfirmMouseEntered

    private void btnConfirmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmMouseExited
        btnConfirm.setBackground(new Color(52, 152, 219));
    }//GEN-LAST:event_btnConfirmMouseExited

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        this.confirmStockIn();
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseEntered
        btnCancel.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnCancelMouseEntered

    private void btnCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseExited
        btnCancel.setBackground(new Color(231, 76, 60));
    }//GEN-LAST:event_btnCancelMouseExited

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.cancelStockIn();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tblAdminStockInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAdminStockInMouseClicked
        if (evt.getClickCount() == 2) {
            this.selectStockIn();
        }
    }//GEN-LAST:event_tblAdminStockInMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private warehouse.component.button.Button btnCancel;
    private warehouse.component.button.Button btnConfirm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private warehouse.component.Pagination paginationAdminStockIn;
    private javax.swing.JTable tblAdminStockIn;
    private warehouse.component.textfield.TextField txtIDProduct;
    private warehouse.component.textfield.TextField txtIDStockIn;
    private warehouse.component.textfield.TextField txtIDSupplier;
    private warehouse.component.textfield.TextField txtIDWarehouse;
    private warehouse.component.textfield.TextField txtQuantity;
    private warehouse.component.textfield.TextField txtStatus;
    private warehouse.component.textfield.TextField txtUpdateDate;
    private warehouse.component.textfield.TextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
