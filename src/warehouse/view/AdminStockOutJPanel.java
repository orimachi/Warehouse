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
import warehouse.dao.StockOutDAO;
import warehouse.entity.StockOut;
import warehouse.utils.ConvertDate;
import warehouse.utils.MessageBox;

public class AdminStockOutJPanel extends javax.swing.JPanel {

    public AdminStockOutJPanel() {
        initComponents();
        initComponents();
        loadDataTblAdminStockOut();
        loadPagination();
    }
    StockOutDAO stockOutDAO = new StockOutDAO();
    ProductDAO productDAO = new ProductDAO();
    int pageSize = 5;

    private void loadDataTblAdminStockOut() {
        DefaultTableModel model = (DefaultTableModel) tblAdminStockOut.getModel();
        model.setRowCount(0);

        List<StockOut> list = stockOutDAO.selectALLByStatusProcessing(EStatus.PROCESSING);
        int currentPage = paginationAdminStockOut.getPage().getCurrent();

        List<StockOut> pageList = Pagination.getPage(list, currentPage, pageSize);

        for (StockOut stock : pageList) {
            String nameProduct = productDAO.getNameByUUID(stock.getIdProduct());
            model.addRow(new Object[]{
                stock.getId(),
                nameProduct,
                stock.getQuantity(),
                stock.getUsername(),
                ConvertDate.toString(stock.getUpdatedDate(), "dd-MM-yyyy"),
                stock.getStatus()
            });
        }
    }

    private void loadPagination() {
        List<StockOut> list = stockOutDAO.selectALLByStatusProcessing(EStatus.PROCESSING);
        int totalPage = Pagination.getTotalPage(list, pageSize);
        int current = Pagination.getCurrentPage(list, pageSize);
        paginationAdminStockOut.setPagegination(current, totalPage);
        paginationAdminStockOut.setPaginationItemRender(new PaginationItemRenderStyle1());

        paginationAdminStockOut.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadDataTblAdminStockOut();
            }
        });
    }

    private StockOut getInformationForm() {
        StockOut stock = new StockOut();
        stock.setId(UUID.fromString(txtIDStockOut.getText()));
        stock.setIdProduct(productDAO.getUUIDByName(txtIDProduct.getText()));
        stock.setQuantity(Integer.parseInt(txtQuantity.getText()));
        stock.setUsername(txtUsername.getText());
        stock.setUpdatedDate(ConvertDate.toDate(txtUpdateDate.getText(), "dd-MM-yyyy"));
        stock.setStatus(EStatus.valueOf(txtStatus.getText()));
        return stock;
    }

    private void clearInformationForm() {
        txtIDStockOut.setText("");
        txtIDProduct.setText("");
        txtQuantity.setText("");
        txtUsername.setText("");
        txtUpdateDate.setText("");
        txtStatus.setText("");
    }

    private void setInformationForm(StockOut stock) {
        txtIDStockOut.setText(stock.getId().toString());
        txtIDProduct.setText(productDAO.getNameByUUID(stock.getIdProduct()));
        txtQuantity.setText(String.valueOf(stock.getQuantity()));
        txtUsername.setText(stock.getUsername());
        txtUpdateDate.setText(ConvertDate.toString(stock.getUpdatedDate(), "dd-MM-yyyy"));
        txtStatus.setText(stock.getStatus().name());
    }

    private void confirmStockOut() {
        try {
            if (validateForm() == true) {
                StockOut stock = getInformationForm();
                stock.setStatus(EStatus.ACCECPT);
                stockOutDAO.update(stock);
                MessageBox.success(this, "Confirm success");
                loadDataTblAdminStockOut();
                clearInformationForm();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.error(this, "System error check output");
        }
    }

    private void cancelStockOut() {
        try {
            if (validateForm() == true) {
                StockOut stock = getInformationForm();
                stock.setStatus(EStatus.CANCEL);
                stockOutDAO.update(stock);
                MessageBox.success(this, "Cancel success");
                loadDataTblAdminStockOut();
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

    private void selectStockOut() {
        int row = tblAdminStockOut.getSelectedRow();
        String idStockOut = tblAdminStockOut.getValueAt(row, 0).toString();
        StockOut stock = stockOutDAO.selectById(UUID.fromString(idStockOut));
        setInformationForm(stock);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnConfirm = new warehouse.component.button.Button();
        btnCancel = new warehouse.component.button.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAdminStockOut = new javax.swing.JTable();
        paginationAdminStockOut = new warehouse.component.Pagination();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtQuantity = new warehouse.component.textfield.TextField();
        jLabel6 = new javax.swing.JLabel();
        txtIDStockOut = new warehouse.component.textfield.TextField();
        txtIDProduct = new warehouse.component.textfield.TextField();
        txtUpdateDate = new warehouse.component.textfield.TextField();
        txtStatus = new warehouse.component.textfield.TextField();
        jLabel7 = new javax.swing.JLabel();
        txtUsername = new warehouse.component.textfield.TextField();
        jLabel8 = new javax.swing.JLabel();

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

        tblAdminStockOut.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblAdminStockOut.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Product", "Quantity", "Username", "Updated Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAdminStockOut.setRequestFocusEnabled(false);
        tblAdminStockOut.setRowHeight(30);
        tblAdminStockOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAdminStockOutMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblAdminStockOut);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Quantity");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Name");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Username");

        txtQuantity.setEditable(false);
        txtQuantity.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtQuantity.setLabelText("");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("ID");

        txtIDStockOut.setEditable(false);
        txtIDStockOut.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtIDStockOut.setLabelText("");

        txtIDProduct.setEditable(false);
        txtIDProduct.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtIDProduct.setLabelText("");

        txtUpdateDate.setEditable(false);
        txtUpdateDate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtUpdateDate.setLabelText("");

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
                        .addComponent(jLabel6)
                        .addComponent(txtIDStockOut, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtUpdateDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel7))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                            .addComponent(txtIDProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5))
                                .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(txtIDStockOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUpdateDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(349, 349, 349)
                                .addComponent(paginationAdminStockOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(paginationAdminStockOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        this.confirmStockOut();
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseEntered
        btnCancel.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnCancelMouseEntered

    private void btnCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseExited
        btnCancel.setBackground(new Color(231, 76, 60));
    }//GEN-LAST:event_btnCancelMouseExited

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.cancelStockOut();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tblAdminStockOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAdminStockOutMouseClicked
        if (evt.getClickCount() == 2) {
            this.selectStockOut();
        }
    }//GEN-LAST:event_tblAdminStockOutMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private warehouse.component.button.Button btnCancel;
    private warehouse.component.button.Button btnConfirm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private warehouse.component.Pagination paginationAdminStockOut;
    private javax.swing.JTable tblAdminStockOut;
    private warehouse.component.textfield.TextField txtIDProduct;
    private warehouse.component.textfield.TextField txtIDStockOut;
    private warehouse.component.textfield.TextField txtQuantity;
    private warehouse.component.textfield.TextField txtStatus;
    private warehouse.component.textfield.TextField txtUpdateDate;
    private warehouse.component.textfield.TextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
