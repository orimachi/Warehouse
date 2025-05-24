package warehouse.view;

import java.awt.Color;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import warehouse.bean.ERole;
import warehouse.component.Pagination;
import warehouse.component.cell.ActionCellEditor;
import warehouse.component.cell.TableActionCellRender;
import warehouse.component.cell.TableActionEvent;
import warehouse.component.pagination.EventPagination;
import warehouse.component.pagination.PaginationItemRenderStyle1;
import warehouse.dao.AccountDAO;
import warehouse.entity.Account;
import warehouse.utils.MessageBox;

public class AccountJPanel extends javax.swing.JPanel {

    public AccountJPanel() {
        initComponents();
        loadDataTblAccounts();
        loadPagination();
        loadDataCBBRole();
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                selectAccount();
            }

            @Override
            public void onDelete(int row) {
                deleteAccount();
            }
        };

        tblAccounts.getColumnModel().getColumn(3).setCellRenderer(new TableActionCellRender());
        tblAccounts.getColumnModel().getColumn(3).setCellEditor(new ActionCellEditor(event));

        txtsearchBarAccount.setSearchListener(e -> {
            String keyword = txtsearchBarAccount.getSearchText();
            loadDataTblSearchByNameAccount(keyword);
        });

    }
    AccountDAO accountDAO = new AccountDAO();
    int pageSize = 1;

    private void loadDataTblAccounts() {
        DefaultTableModel model = (DefaultTableModel) tblAccounts.getModel();
        model.setRowCount(0);

        List<Account> list = accountDAO.selectAll();
        int currentPage = paginationAccount.getPage().getCurrent();

        List<Account> pageList = Pagination.getPage(list, currentPage, pageSize);

        for (Account product : pageList) {
            model.addRow(new Object[]{
                product.getUsername(),
                product.getPassword(),
                product.getRole().name(),});
        }
    }

    private void loadDataTblSearchByNameAccount(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblAccounts.getModel();
        model.setRowCount(0);

        List<Account> list = accountDAO.selectALLByName(keyword);
        int currentPage = paginationAccount.getPage().getCurrent();

        List<Account> pageList = Pagination.getPage(list, currentPage, pageSize);

        for (Account product : pageList) {
            model.addRow(new Object[]{
                product.getUsername(),
                product.getPassword(),
                product.getRole().name(),});
        }
    }

    private void loadDataCBBRole() {
        cbbRole.removeAllItems();
        cbbRole.addItem("-- Please choice --");
        for (ERole calcUnit : ERole.values()) {
            cbbRole.addItem(calcUnit.toString());
        }
        cbbRole.setSelectedIndex(0);
    }

    private void loadPagination() {
        List<Account> list = accountDAO.selectAll();
        int totalPage = Pagination.getTotalPage(list, pageSize);
        int current = Pagination.getCurrentPage(list, pageSize);
        paginationAccount.setPagegination(current, totalPage);
        paginationAccount.setPaginationItemRender(new PaginationItemRenderStyle1());

        paginationAccount.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadDataTblAccounts();
            }
        });
    }

    private boolean validateInformation() {
        String name = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        String role = cbbRole.getSelectedItem().toString();

        if (name.isEmpty() == true) {
            MessageBox.warning(this, "Please enter name account");
            return false;
        } else if (password.isEmpty() == true) {
            MessageBox.warning(this, "Please enter password account");
            return false;
        } else if (role == null || role.startsWith("--")) {
            MessageBox.warning(this, "Please choice role  account");
            return false;
        }
        return true;
    }

    private Account getInformationForm() {
        Account account = new Account();
        String password = new String(txtPassword.getPassword());
        account.setUsername(txtUsername.getText());
        account.setPassword(String.valueOf(password.hashCode()));
        account.setRole(ERole.valueOf(String.valueOf(cbbRole.getSelectedItem())));
        return account;
    }

    private void clearInformationForm() {
        txtUsername.setText("");
        txtPassword.setText("");
        cbbRole.setSelectedIndex(0);
    }

    private void setInformationForm(Account account) {
        txtUsername.setText(account.getUsername());
        txtPassword.setText(account.getPassword());
        cbbRole.setSelectedItem(account.getRole().name());
    }

    private void insertAccount() {
        try {
            if (validateInformation() == true) {
                Account account = getInformationForm();
                accountDAO.insert(account);
                MessageBox.success(this, "Insert account success");
                loadDataTblAccounts();
                clearInformationForm();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            MessageBox.error(this, "System error check output");
        }
    }

    private void updateAccount() {
        try {
            if (validateInformation() == true) {
                Account account = getInformationForm();
                accountDAO.update(account);
                MessageBox.success(this, "Update account success");
                loadDataTblAccounts();
                clearInformationForm();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.error(this, "System error check output");
        }
    }

    private void deleteAccount() {
        int row = tblAccounts.getSelectedRow();
        String idAccount = tblAccounts.getValueAt(row, 0).toString().isEmpty() ? txtUsername.getText() : tblAccounts.getValueAt(row, 0).toString();
        try {
            if (!idAccount.isEmpty()) {
                accountDAO.delete(idAccount);
                MessageBox.success(this, "Delete account success");
                loadDataTblAccounts();
                clearInformationForm();
            }
        } catch (RuntimeException e) {
            MessageBox.error(this, "System error check output");
        }
    }

    private void resetButton() {
        clearInformationForm();
    }

    private void selectAccount() {
        int row = tblAccounts.getSelectedRow();
        String idProduct = tblAccounts.getValueAt(row, 0).toString();
        Account account = accountDAO.selectById(idProduct);
        setInformationForm(account);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDelete = new warehouse.component.button.Button();
        btnReset = new warehouse.component.button.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAccounts = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbbRole = new warehouse.component.Combobox();
        jLabel6 = new javax.swing.JLabel();
        txtUsername = new warehouse.component.textfield.TextField();
        txtPassword = new warehouse.component.textfield.PasswordField();
        paginationAccount = new warehouse.component.Pagination();
        txtsearchBarAccount = new warehouse.component.SearchBar();
        btnAdd = new warehouse.component.button.Button();
        btnUpdate = new warehouse.component.button.Button();

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

        tblAccounts.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblAccounts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Username", "Password", "Role", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAccounts.setRequestFocusEnabled(false);
        tblAccounts.setRowHeight(30);
        tblAccounts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAccountsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblAccounts);
        if (tblAccounts.getColumnModel().getColumnCount() > 0) {
            tblAccounts.getColumnModel().getColumn(3).setResizable(false);
            tblAccounts.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Password");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Role");

        cbbRole.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbbRole.setLabeText("");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Username");

        txtUsername.setLabelText("");

        txtPassword.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPassword.setLabelText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cbbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(310, 310, 310)
                            .addComponent(paginationAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtsearchBarAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtsearchBarAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(paginationAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteMouseExited

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        this.deleteAccount();
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

    private void btnAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseEntered
        btnAdd.setBackground(new Color(52, 152, 219));
    }//GEN-LAST:event_btnAddMouseEntered

    private void btnAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseExited
        btnAdd.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnAddMouseExited

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        this.insertAccount();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateMouseEntered

    private void btnUpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateMouseExited

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        this.updateAccount();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblAccountsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAccountsMouseClicked
        if (evt.getClickCount() == 2) {
            selectAccount();
        }
    }//GEN-LAST:event_tblAccountsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private warehouse.component.button.Button btnAdd;
    private warehouse.component.button.Button btnDelete;
    private warehouse.component.button.Button btnReset;
    private warehouse.component.button.Button btnUpdate;
    private warehouse.component.Combobox cbbRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private warehouse.component.Pagination paginationAccount;
    private javax.swing.JTable tblAccounts;
    private warehouse.component.textfield.PasswordField txtPassword;
    private warehouse.component.textfield.TextField txtUsername;
    private warehouse.component.SearchBar txtsearchBarAccount;
    // End of variables declaration//GEN-END:variables
}
