
package com.QLK.view;

import com.QLK.dao.NhaCungCapDAO;
import com.QLK.entity.NhaCungCap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ThongTinNhaCungCapJPanel extends javax.swing.JPanel {


    public ThongTinNhaCungCapJPanel() {
        initComponents();
        fillTable();
    }
    
    
    
    NhaCungCapDAO dao = new NhaCungCapDAO();
    public int current = 0;
    
    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblNCC.getModel();
        model.setRowCount(0);
        try {
            List<NhaCungCap> list = dao.selectAll();
            for (NhaCungCap ncc : list) {
                Object[] row = {
                    ncc.getMaNCC(),
                    ncc.getTennhacc(),
                    ncc.getDienThoai(),
                    ncc.getEmail(),                  
                    ncc.getDiaChi()
                };
                model.addRow(row);
            }
        } 
        catch (Exception e) {
           JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    public boolean ValidateForm(){
        if(txtMaNCC.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập mã nhà cung cấp!");
                txtMaNCC.requestFocus();
                return false;
        }else if(txtTenNCC.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập tên nhà cung cấp!");
                txtTenNCC.requestFocus();
                return false;
        }else if(txtDthoai.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập số điện thoại của nhà cung cấp!");
                txtDthoai.requestFocus();
                return false;
        }else if(txtEmail.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập mail nhà cung cấp!");
                txtEmail.requestFocus();
                return false;
        }else if(txtDiaChi.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập địa chỉ nhà cung cấp!");
                txtDiaChi.requestFocus();
                return false;
        
        }       
        return true;
    }
    
    public void clearForm(){ 
    	txtMaNCC.setText("");
    	txtTenNCC.setText("");
        txtDthoai.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        this.current = -1;
        this.fillTable();
        txtMaNCC.requestFocus();
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    } 
   
  void setForm(NhaCungCap ncc){
        txtMaNCC.setText(ncc.getMaNCC());
        txtTenNCC.setText(ncc.getTennhacc());
        txtDiaChi.setText(ncc.getDiaChi());
        txtEmail.setText(ncc.getEmail());
       txtDthoai.setText(ncc.getDienThoai());
    }
  
    NhaCungCap getForm(){
        NhaCungCap ncc = new NhaCungCap();
        ncc.setMaNCC(txtMaNCC.getText());
        ncc.setDienThoai(txtDthoai.getText());
        ncc.setEmail(txtEmail.getText());
        ncc.setDiaChi(txtDiaChi.getText());
        ncc.setTennhacc(txtTenNCC.getText());
        return ncc;
    }

    void insert(){
        NhaCungCap ncc = this.getForm();
            try {
                dao.insert(ncc); // thêm mới
                this.fillTable(); // đỗ lại bảng
                this.clearForm(); // xóa trắng form
                JOptionPane.showMessageDialog(this, "Thêm mới thành công!");
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Thêm mới thất bại!");
            
        }
    }
    
    void update(){
        NhaCungCap model = getForm();
        try {
                dao.update(model);
                this.fillTable();
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
        }
    }
    
    void delete(){
        int res = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa nhà cung cấp này không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        String makh = txtMaNCC.getText();
        if(res == JOptionPane.YES_OPTION){
                try {
                    dao.delete(makh);
                    this.fillTable();
                    this.clearForm();
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                } 
                catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                
            }
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNCC = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        btnThem = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnSua = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnXoa = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnLamMoi = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtTenNCC = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtDthoai = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtMaNCC = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblNCC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblNCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
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
                "Mã NCC", "Tên Nhà Cung Cấp", "Điện Thoại", "Email", "Địa Chỉ"
            }
        ));
        tblNCC.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNCCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNCC);

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

        jLabel6.setBackground(new java.awt.Color(204, 255, 255));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Thông Tin");

        txtTenNCC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Mã NCC");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Điện Thoại");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Địa chỉ");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Tên NCC:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Email");

        txtDiaChi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        txtDthoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        txtMaNCC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(txtDthoai))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(txtEmail)))
                    .addComponent(txtDiaChi))
                .addGap(82, 82, 82))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(txtDthoai, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(21, Short.MAX_VALUE))
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

    private void tblNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNCCMouseClicked
        current = tblNCC.getSelectedRow();
        txtMaNCC.setText(tblNCC.getValueAt(current,0).toString());
        txtTenNCC.setText(tblNCC.getValueAt(current,1).toString());
        txtDthoai.setText(tblNCC.getValueAt(current,2).toString());
        txtEmail.setText(tblNCC.getValueAt(current,3).toString());
        txtDiaChi.setText(tblNCC.getValueAt(current,4).toString());
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
    }//GEN-LAST:event_tblNCCMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if(ValidateForm()){
            this.insert();         
        }    
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        this.clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

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
    public javax.swing.JButton btnLamMoi;
    public javax.swing.JButton btnSua;
    public javax.swing.JButton btnThem;
    public javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblNCC;
    public javax.swing.JTextField txtDiaChi;
    public javax.swing.JTextField txtDthoai;
    public javax.swing.JTextField txtEmail;
    public javax.swing.JTextField txtMaNCC;
    public javax.swing.JTextField txtTenNCC;
    // End of variables declaration//GEN-END:variables
}
