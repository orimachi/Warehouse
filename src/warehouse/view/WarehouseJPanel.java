package warehouse.view;

import java.awt.Color;
import java.util.List;
import java.util.UUID;
import javax.swing.table.DefaultTableModel;
import warehouse.bean.EPageSize;
import warehouse.component.Pagination;
import warehouse.component.cell.ActionCellEditor;
import warehouse.component.cell.TableActionCellRender;
import warehouse.component.cell.TableActionEvent;
import warehouse.component.pagination.EventPagination;
import warehouse.component.pagination.PaginationItemRenderStyle1;
import warehouse.dao.WarehouseDAO;
import warehouse.entity.Warehouse;
import warehouse.utils.MessageBox;

public class WarehouseJPanel extends javax.swing.JPanel {

    public WarehouseJPanel() {
        initComponents();
        loadDataTblWarehouse();
        loadPagination();

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                selectWarehouse();
            }

            @Override
            public void onDelete(int row) {
                delete();
            }
        };

        tblWarehouse.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        tblWarehouse.getColumnModel().getColumn(4).setCellEditor(new ActionCellEditor(event));

        txtsearch.setSearchListener(e -> {
            String keyword = txtsearch.getSearchText();
            searchByName(keyword);
        });
    }
    WarehouseDAO warehouseDAO = new WarehouseDAO();
    int pageSize = EPageSize.SMALL.getSize();

    private void loadDataTblWarehouse() {
        DefaultTableModel model = (DefaultTableModel) tblWarehouse.getModel();
        model.setRowCount(0);

        List<Warehouse> list = warehouseDAO.selectAll();
        int currentPage = paginationWarehouse.getPage().getCurrent();

        List<Warehouse> pageList = Pagination.getPage(list, currentPage, pageSize);

        for (Warehouse wh : pageList) {
            model.addRow(new Object[]{
                wh.getId(),
                wh.getName(),
                wh.getAddress(),
                wh.getPhoneNumber()
            });
        }
    }

    private void loadPagination() {
        List<Warehouse> list = warehouseDAO.selectAll();
        int totalPage = Pagination.getTotalPage(list, pageSize);
        int current = Pagination.getCurrentPage(list, pageSize);
        paginationWarehouse.setPagegination(current, totalPage);
        paginationWarehouse.setPaginationItemRender(new PaginationItemRenderStyle1());

        paginationWarehouse.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadDataTblWarehouse();
            }
        });
    }

    private boolean validateInformation() {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String phone = txtPhoneNumber.getText();

        if (name.isEmpty() == true) {
            MessageBox.warning(this, "Name is required!");
            return false;
        } else if (address.isEmpty() == true) {
            MessageBox.warning(this, "Address is required!");
            return false;
        } else if (phone.isEmpty() == true) {
            MessageBox.warning(this, "Phone number is required!");
            return false;
        }
        return true;
    }

     private boolean validateID() {
        String id = txtID.getText();
        String name = txtName.getText();
        List<Warehouse> list = warehouseDAO.selectAll();
        for (Warehouse warehouse : list) {
            if (id.equals(warehouse.getId().toString())) {
                MessageBox.warning(this, "IDSupplier already exist your cant insert use update");
                return false;
            } else if(name.equals(warehouse.getName())){
                MessageBox.warning(this, "Name already exist choice another name");
                return false;
            }
        }
        return true;
    }
    
    private Warehouse getInformationForm() {
        Warehouse warehouse = new Warehouse();
        String idText = txtID.getText().trim();
        if (!idText.isEmpty()) {
            try {
                warehouse.setId(UUID.fromString(idText));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                throw new RuntimeException("ID không hợp lệ: " + idText);
            }
        }
        warehouse.setName(txtName.getText());
        warehouse.setAddress(txtAddress.getText());
        warehouse.setPhoneNumber(txtPhoneNumber.getText());
        return warehouse;
    }

    private void clearInformationForm() {
        txtID.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtPhoneNumber.setText("");
    }

    private void setInformationForm(Warehouse warehouse) {
        txtID.setText(warehouse.getId().toString());
        txtName.setText(warehouse.getName());
        txtAddress.setText(warehouse.getAddress());
        txtPhoneNumber.setText(warehouse.getPhoneNumber());
    }

    private void insert() {
        try {
            if (validateInformation() == true && validateID() == true) {
                Warehouse warehouse = getInformationForm();
                warehouseDAO.insert(warehouse);
                MessageBox.success(this, "Insert supplier success");
                loadDataTblWarehouse();
                clearInformationForm();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            MessageBox.error(this, "System error check output");
        }
    }

    private void update() {
        try {
            if (validateInformation() == true) {
                Warehouse warehouse = getInformationForm();
                warehouseDAO.update(warehouse);
                MessageBox.success(this, "Update supplier success");
                loadDataTblWarehouse();
                clearInformationForm();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.error(this, "System error check output");
        }
    }

    private void delete() {
        int row = tblWarehouse.getSelectedRow();
        if (row == -1) {
            return;
        }
        String idProduct = tblWarehouse.getValueAt(row, 0).toString().isEmpty() ? txtID.getText() : tblWarehouse.getValueAt(row, 0).toString();
        try {
            if (!idProduct.isEmpty()) {
                warehouseDAO.delete(UUID.fromString(idProduct));
                MessageBox.success(this, "Delete Supplier success");
                loadDataTblWarehouse();
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

    private void selectWarehouse() {
        int row = tblWarehouse.getSelectedRow();
        if (row == -1) {
            return;
        }
        String id = tblWarehouse.getValueAt(row, 0).toString();
        Warehouse warehouse = warehouseDAO.selectById(UUID.fromString(id));
        setInformationForm(warehouse);
    }

    private void searchByName(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblWarehouse.getModel();
        model.setRowCount(0);

        List<Warehouse> list = warehouseDAO.selectByNameKeyWord(keyword);
        int currentPage = paginationWarehouse.getPage().getCurrent();

        List<Warehouse> pageList = Pagination.getPage(list, currentPage, pageSize);

        for (Warehouse wh : pageList) {
            model.addRow(new Object[]{
                wh.getId(),
                wh.getName(),
                wh.getAddress(),
                wh.getPhoneNumber()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAdd = new warehouse.component.button.Button();
        btnUpdate = new warehouse.component.button.Button();
        btnDelete = new warehouse.component.button.Button();
        btnReset = new warehouse.component.button.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblWarehouse = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtName = new warehouse.component.textfield.TextField();
        jLabel6 = new javax.swing.JLabel();
        txtID = new warehouse.component.textfield.TextField();
        txtAddress = new warehouse.component.textfield.TextField();
        txtPhoneNumber = new warehouse.component.textfield.TextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtsearch = new warehouse.component.SearchBar();
        paginationWarehouse = new warehouse.component.Pagination();

        btnAdd.setBackground(new java.awt.Color(52, 152, 219));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("ADD");
        btnAdd.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(46, 204, 113));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("UPDATE");
        btnUpdate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(231, 76, 60));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("DELETE");
        btnDelete.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(149, 165, 166));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("RESET");
        btnReset.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        tblWarehouse.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblWarehouse.setModel(new javax.swing.table.DefaultTableModel(
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
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblWarehouse.setRequestFocusEnabled(false);
        tblWarehouse.setRowHeight(30);
        tblWarehouse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblWarehouseMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblWarehouse);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Name");

        txtName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtName.setLabelText("");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("ID");

        txtID.setEditable(false);
        txtID.setLabelText("");

        txtAddress.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtAddress.setLabelText("");

        txtPhoneNumber.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPhoneNumber.setLabelText("");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Address");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Phone Number");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(jLabel7))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(334, 334, 334)
                .addComponent(paginationWarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(paginationWarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 842, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 607, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        this.insert();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        this.update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        this.delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        this.resetButton();
    }//GEN-LAST:event_btnResetActionPerformed

    private void tblWarehouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblWarehouseMouseClicked
        if (evt.getClickCount() == 2) {
            selectWarehouse();
        }
    }//GEN-LAST:event_tblWarehouseMouseClicked


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
    private warehouse.component.Pagination paginationWarehouse;
    private javax.swing.JTable tblWarehouse;
    private warehouse.component.textfield.TextField txtAddress;
    private warehouse.component.textfield.TextField txtID;
    private warehouse.component.textfield.TextField txtName;
    private warehouse.component.textfield.TextField txtPhoneNumber;
    private warehouse.component.SearchBar txtsearch;
    // End of variables declaration//GEN-END:variables
}
