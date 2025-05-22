package warehouse.view;

import java.awt.Color;
import java.util.List;
import java.util.UUID;
import javax.swing.table.DefaultTableModel;
import warehouse.component.Pagination;
import warehouse.component.cell.ActionCellEditor;
import warehouse.component.cell.TableActionCellRender;
import warehouse.component.cell.TableActionEvent;
import warehouse.component.pagination.EventPagination;
import warehouse.component.pagination.PaginationItemRenderStyle1;
import warehouse.dao.SuppliersDAO;
import warehouse.entity.Suppliers;
import warehouse.utils.MessageBox;

public class SupplierJPanel extends javax.swing.JPanel {

    public SupplierJPanel() {
        initComponents();
        controlButton();
        loadDataTblSuppliers();
        loadPagination();

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                selectSupplier();
            }

            @Override
            public void onDelete(int row) {
                deleteSupplier();
            }
        };

        tblSuppliers.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        tblSuppliers.getColumnModel().getColumn(4).setCellEditor(new ActionCellEditor(event));
    }

    SuppliersDAO supplierDAO = new SuppliersDAO();
    int pageSize = 3;

    private void controlButton() {
        boolean hasId = !txtIDSupplier.getText().trim().isEmpty();
        btnUpdate.setEnabled(hasId);
        btnDelete.setEnabled(hasId);
    }

    private void loadDataTblSuppliers() {
        DefaultTableModel model = (DefaultTableModel) tblSuppliers.getModel();
        model.setRowCount(0);

        List<Suppliers> list = supplierDAO.selectAll();
       int currentPage = paginationSuppliers.getPage().getCurrent();

        List<Suppliers> pageList = Pagination.getPage(list, currentPage, pageSize);

        for (Suppliers supplier : pageList) {
            model.addRow(new Object[]{
                supplier.getId(),
                supplier.getName(),
                supplier.getAddress(),
                supplier.getPhoneNumber()
            });
        }
    }

    private void loadPagination() {
        List<Suppliers> list = supplierDAO.selectAll();
        int totalPage = Pagination.getTotalPage(list, pageSize);
        int current = Pagination.getCurrentPage(list, pageSize);
        paginationSuppliers.setPagegination(current, totalPage);
        paginationSuppliers.setPaginationItemRender(new PaginationItemRenderStyle1());
        
        paginationSuppliers.addEventPagination(new EventPagination(){
            @Override
            public void pageChanged(int page) {
                  loadDataTblSuppliers(); 
            }
        });
    }

    private boolean validateInformation() {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String phone = txtPhoneNumber.getText();

        if (name.isEmpty() == true) {
            MessageBox.warning(this, "Please enter name supplier");
            return false;
        } else if (address.isEmpty() == true) {
            MessageBox.warning(this, "Please enter address supplier");
            return false;
        } else if (phone.isEmpty() == true) {
            MessageBox.warning(this, "Please enter phone number supplier");
            return false;
        }
        return true;
    }

    private Suppliers getInformationForm() {
        Suppliers supplier = new Suppliers();
        supplier.setId(UUID.fromString(txtIDSupplier.getText()));
        supplier.setName(txtName.getText());
        supplier.setAddress(txtAddress.getText());
        supplier.setPhoneNumber(txtPhoneNumber.getText());
        return supplier;
    }

    private void clearInformationForm() {
        txtIDSupplier.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtPhoneNumber.setText("");
    }

    private void setSupplierInformationForm(Suppliers supplier) {
        txtIDSupplier.setText(supplier.getId().toString());
        txtName.setText(supplier.getName());
        txtAddress.setText(supplier.getAddress());
        txtPhoneNumber.setText(supplier.getPhoneNumber());
    }

    private void insertSupplier() {
        try {
            if (validateInformation() == true) {
                Suppliers supplier = getInformationForm();
                supplierDAO.insert(supplier);
                MessageBox.success(this, "Insert supplier success");
                loadDataTblSuppliers();
                clearInformationForm();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            MessageBox.error(this, "System error check output");
        }
    }

    private void updateSupplier() {
        try {
            if (validateInformation() == true) {
                Suppliers supplier = getInformationForm();
                supplierDAO.update(supplier);
                MessageBox.success(this, "Update supplier success");
                loadDataTblSuppliers();
                clearInformationForm();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.error(this, "System error check output");
        }
    }

    private void deleteSupplier() {
        int row = tblSuppliers.getSelectedRow();
        String idProduct = tblSuppliers.getValueAt(row, 0).toString().isEmpty() ? txtIDSupplier.getText() : tblSuppliers.getValueAt(row, 0).toString();
        try {
            if (!idProduct.isEmpty()) {
                supplierDAO.delete(UUID.fromString(idProduct));
                MessageBox.success(this, "Delete Supplier success");
                loadDataTblSuppliers();
                clearInformationForm();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            MessageBox.error(this, "System error check output");
        }
    }

    private void resetButton() {
        clearInformationForm();
    }

    private void selectSupplier() {
        int row = tblSuppliers.getSelectedRow();
        String idProduct = tblSuppliers.getValueAt(row, 0).toString();
        Suppliers supplier = supplierDAO.selectById(UUID.fromString(idProduct));
        setSupplierInformationForm(supplier);
    }

    private void findByKeyType() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAdd = new warehouse.component.button.Button();
        btnUpdate = new warehouse.component.button.Button();
        btnDelete = new warehouse.component.button.Button();
        btnReset = new warehouse.component.button.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSuppliers = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtName = new warehouse.component.textfield.TextField();
        jLabel6 = new javax.swing.JLabel();
        txtIDSupplier = new warehouse.component.textfield.TextField();
        txtAddress = new warehouse.component.textfield.TextField();
        txtPhoneNumber = new warehouse.component.textfield.TextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtsearchBarProduct = new warehouse.component.SearchBar();
        jPanel2 = new javax.swing.JPanel();
        paginationSuppliers = new warehouse.component.Pagination();

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

        tblSuppliers.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblSuppliers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Address", "Phone Number", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSuppliers.setRequestFocusEnabled(false);
        tblSuppliers.setRowHeight(30);
        tblSuppliers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSuppliersMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSuppliers);
        if (tblSuppliers.getColumnModel().getColumnCount() > 0) {
            tblSuppliers.getColumnModel().getColumn(4).setResizable(false);
            tblSuppliers.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Name");

        txtName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtName.setLabelText("");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("ID");

        txtIDSupplier.setEditable(false);
        txtIDSupplier.setLabelText("");

        txtAddress.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtAddress.setLabelText("");

        txtPhoneNumber.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPhoneNumber.setLabelText("");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Address");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Phone Number");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIDSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                        .addComponent(jLabel6))
                    .addComponent(jLabel7))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                        .addComponent(txtPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtsearchBarProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsearchBarProductKeyPressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(311, 311, 311)
                .addComponent(paginationSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(paginationSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 31, Short.MAX_VALUE)
                .addComponent(txtsearchBarProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(619, 619, 619))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addComponent(txtsearchBarProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseEntered
        btnAdd.setBackground(new Color(52, 152, 219));
    }//GEN-LAST:event_btnAddMouseEntered

    private void btnAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseExited
        btnAdd.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnAddMouseExited

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        this.insertSupplier();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateMouseEntered

    private void btnUpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateMouseExited

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        this.updateSupplier();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteMouseExited

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        this.deleteSupplier();
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

    private void txtsearchBarProductKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchBarProductKeyPressed
        this.findByKeyType();
    }//GEN-LAST:event_txtsearchBarProductKeyPressed

    private void tblSuppliersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSuppliersMouseClicked
        if (evt.getClickCount() == 2) {
            selectSupplier();
        }
    }//GEN-LAST:event_tblSuppliersMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private warehouse.component.button.Button btnAdd;
    private warehouse.component.button.Button btnDelete;
    private warehouse.component.button.Button btnReset;
    private warehouse.component.button.Button btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private warehouse.component.Pagination paginationSuppliers;
    private javax.swing.JTable tblSuppliers;
    private warehouse.component.textfield.TextField txtAddress;
    private warehouse.component.textfield.TextField txtIDSupplier;
    private warehouse.component.textfield.TextField txtName;
    private warehouse.component.textfield.TextField txtPhoneNumber;
    private warehouse.component.SearchBar txtsearchBarProduct;
    // End of variables declaration//GEN-END:variables
}
