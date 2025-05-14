package com.QLK.view;

import com.QLK.dao.KhachHangDAO;
import com.QLK.entity.KhachHang;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ThongTinKhachHangJPanel extends javax.swing.JPanel {

    public ThongTinKhachHangJPanel() {
        initComponents();
        this.fillTable();
    }
    
    KhachHangDAO khdao = new KhachHangDAO();
    int current = 0;
    
    public boolean ValidateForm(){
            if(txtMaKH.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập mã khách hàng!");
                txtMaKH.requestFocus();
                return false;
            }else if(txtHoTen.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập họ tên khách hàng!");
                txtHoTen.requestFocus();
                return false;
            }else if(txtSdt.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập số điện thoại");
                txtSdt.requestFocus();
                return false;
            }else if(txtmst.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập mã số thuế");
                txtmst.requestFocus();
                return false;
            }else if(txtEmail.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập email khách hàng!");
                txtEmail.requestFocus();
                return false;
            }else if(txtDiaChi.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập địa chỉ khách hàng!");
                txtDiaChi.requestFocus();
                return false;
            }
        return true;
    }
    
    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        try {
            List<KhachHang> list = khdao.selectAll();
            for (KhachHang kh : list) {
                Object[] row = {
                    kh.getMaKH(),
                    kh.getTenKH(),
                    kh.getsDT(),
                    kh.getmST(),
                    kh.getEmail(),
                    kh.getDiaChi()
                };
                model.addRow(row);
            }
       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    void insert(){
            KhachHang model = getForm();
        try {       
            khdao.insert(model);
            JOptionPane.showMessageDialog(this, "Thêm mới thành công!");
            this.fillTable();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng đã tồn tại!");       
        }

    }
    
    void delete(){
        int res = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa khách hàng này không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        String makh = txtMaKH.getText();
        if(res == JOptionPane.YES_OPTION){
                try {
                    khdao.delete(makh);
                    this.fillTable();
                    this.clearForm();
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                } 
                catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                
            }
        }
        
    }
    
    void update(){
        KhachHang model = getForm();
        try {
                khdao.update(model);
                this.fillTable();
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
        }
    }
    
    void clearForm(){
        txtMaKH.setText("");
        txtHoTen.setText("");
        txtSdt.setText("");
        txtmst.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        this.current = -1;
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        this.fillTable();
        txtMaKH.requestFocus();
    }
    
    
    void setForm(KhachHang kh){
        txtMaKH.setText(kh.getMaKH());
        txtHoTen.setText(kh.getTenKH());
        txtSdt.setText(kh.getsDT());
        txtmst.setText(kh.getmST());
        txtEmail.setText(kh.getEmail());
        txtDiaChi.setText(kh.getDiaChi());
    }
    
    KhachHang getForm(){
        KhachHang kh = new KhachHang();
        kh.setMaKH(txtMaKH.getText());
        kh.setTenKH(txtHoTen.getText());
        kh.setsDT(txtSdt.getText());
        kh.setmST(txtmst.getText());
        kh.setEmail(txtEmail.getText());
        kh.setDiaChi(txtDiaChi.getText());
        return kh;
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
        tblKhachHang = new javax.swing.JTable();
        txtHoTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtmst = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar1.setRollover(true);
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

        tblKhachHang.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MÃ KHÁCH HÀNG", "TÊN KHÁCH HÀNG", "SỐ ĐIỆN THOẠI", "MST", "EMAIL", "ĐỊA CHỈ"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        txtHoTen.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtHoTen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Điện thoại:");

        txtSdt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSdt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("MST:");

        txtmst.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtmst.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÔNG TIN");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Địa chỉ:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Mã KH:");

        txtDiaChi.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDiaChi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        txtMaKH.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtMaKH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Họ tên:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtmst, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtHoTen)))
                    .addComponent(txtDiaChi))
                .addGap(49, 49, 49))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtmst, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if(ValidateForm()){
            this.delete();
        }     
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        current = tblKhachHang.getSelectedRow();
        txtMaKH.setText(tblKhachHang.getValueAt(current,0).toString());
        txtHoTen.setText(tblKhachHang.getValueAt(current,1).toString());
        txtSdt.setText(tblKhachHang.getValueAt(current,2).toString());
        txtmst.setText(tblKhachHang.getValueAt(current,3).toString());
        txtEmail.setText(tblKhachHang.getValueAt(current,4).toString());
        txtDiaChi.setText(tblKhachHang.getValueAt(current,5).toString());
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
    }//GEN-LAST:event_tblKhachHangMouseClicked

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

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        this.clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtmst;
    // End of variables declaration//GEN-END:variables
}
