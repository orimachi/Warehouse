package com.QLK.view;

import com.QLK.dao.DonViDAO;
import com.QLK.entity.DonVi;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DonViQuanLyJPanel extends javax.swing.JPanel {

    public DonViQuanLyJPanel() {
        initComponents();
        fillTable();
    }
    
    DonViDAO dvdao = new DonViDAO();
    int current = 0;
    
    void fillTable(){
       DefaultTableModel model = (DefaultTableModel) tblDonVi.getModel();
       model.setRowCount(0);   
       try {
            List<DonVi> list = dvdao.selectAll();
            for (DonVi dv : list) {
                Object[] row = {
                    dv.getMaDV(),
                    dv.getTenDV(),
                    dv.getDienThoai(),
                    dv.getEmail(),
                    dv.getDiaChi()
                };
                model.addRow(row);
            }
       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    public boolean ValidateForm(){
        if(txtMaDV.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập mã đơn vị!");
                txtMaDV.requestFocus();
                return false;
            }else if(txtTenDV.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập tên đơn vị!");
                txtTenDV.requestFocus();
                return false;
            }else if(txtSDT.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập !");
                txtSDT.requestFocus();
                return false;
            }else if(txtEmail.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập mail!");
                txtEmail.requestFocus();
                return false;
            }else if(txtDiaChi.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập dia chi !");
                txtDiaChi.requestFocus();
                return false;
            }
        return true;
    }
    
    void setForm(DonVi dv){
        txtMaDV.setText(dv.getMaDV());
        txtTenDV.setText(dv.getTenDV());
        txtSDT.setText(dv.getDienThoai());
        txtEmail.setText(dv.getEmail());
        txtDiaChi.setText(dv.getDiaChi());
    }
    
    DonVi getForm(){
        DonVi dv = new DonVi();
        dv.setMaDV(txtMaDV.getText());
        dv.setTenDV(txtTenDV.getText());
        dv.setDienThoai(txtSDT.getText());
        dv.setEmail(txtEmail.getText());
        dv.setDiaChi(txtDiaChi.getText());
        return dv;
    }
    
    void updateForm(){
        txtMaDV.setText("");
        txtTenDV.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        this.current = -1;
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        this.fillTable();
    }
    
    void insert(){
        DonVi model = getForm();
        try {
            dvdao.insert(model);
            JOptionPane.showMessageDialog(this, "Thêm mới thành công!");
            this.fillTable();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm mới thất bại!","",JOptionPane.ERROR_MESSAGE);
        }

    }
    
    void update(){
        DonVi model = getForm();
        try {
            dvdao.update(model);
            this.fillTable();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
        }
    }
     
    void delete(){    
            String masp = txtMaDV.getText();
        try {
            dvdao.delete(masp);
            this.fillTable();
            this.updateForm();
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn mã sản phẩm cần xóa");
        }
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnThem = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnSua = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnXoa = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnLamMoi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDonVi = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtTenDV = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMaDV = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jToolBar1.setBorderPainted(false);
        jToolBar1.setEnabled(false);

        btnThem.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/QLK/icon/Add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnThem.setFocusable(false);
        btnThem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThem.setMaximumSize(new java.awt.Dimension(50, 52));
        btnThem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jToolBar1.add(btnThem);
        jToolBar1.add(jSeparator1);

        btnSua.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/QLK/icon/Edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSua.setEnabled(false);
        btnSua.setFocusable(false);
        btnSua.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSua.setMaximumSize(new java.awt.Dimension(50, 52));
        btnSua.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSua);
        jToolBar1.add(jSeparator2);

        btnXoa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/QLK/icon/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXoa.setEnabled(false);
        btnXoa.setFocusable(false);
        btnXoa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnXoa.setMaximumSize(new java.awt.Dimension(50, 52));
        btnXoa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnXoa);
        jToolBar1.add(jSeparator3);

        btnLamMoi.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/QLK/icon/Refresh.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setFocusable(false);
        btnLamMoi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLamMoi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jToolBar1.add(btnLamMoi);

        tblDonVi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblDonVi.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblDonVi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Đơn Vị", "Tên Đơn Vị", "Điện Thoại", "Email", "Địa Chỉ"
            }
        ));
        tblDonVi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDonViMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDonVi);

        jLabel6.setBackground(new java.awt.Color(204, 255, 255));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Thông Tin");

        txtTenDV.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTenDV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Điện thoại");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Địa chỉ");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tên đơn vị");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Email");

        txtDiaChi.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDiaChi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        txtSDT.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSDT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        txtEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Mã đơn vị");

        txtMaDV.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtMaDV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDiaChi)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtMaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenDV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenDV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        updateForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblDonViMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonViMouseClicked
        current = tblDonVi.getSelectedRow();
        txtMaDV.setText(tblDonVi.getValueAt(current,0).toString());
        txtTenDV.setText(tblDonVi.getValueAt(current,1).toString());
        txtSDT.setText(tblDonVi.getValueAt(current,2).toString());
        txtEmail.setText(tblDonVi.getValueAt(current,3).toString());
        txtDiaChi.setText(tblDonVi.getValueAt(current,4).toString());
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
    }//GEN-LAST:event_tblDonViMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if(ValidateForm()){
            this.insert();
        }    
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
         if(ValidateForm()){
            this.update();
        }   
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if(ValidateForm()){
            this.delete();
        }    
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblDonVi;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaDV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenDV;
    // End of variables declaration//GEN-END:variables
}
