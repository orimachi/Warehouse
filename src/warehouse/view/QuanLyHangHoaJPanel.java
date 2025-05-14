package com.QLK.view;

import com.QLK.dao.HangHoaDAO;
import com.QLK.dao.NhaCungCapDAO;
import com.QLK.entity.HangHoa;
import com.QLK.entity.NhaCungCap;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QuanLyHangHoaJPanel extends javax.swing.JPanel {

    
    public QuanLyHangHoaJPanel() {
        initComponents();
        this.fillComboBox();
        this.fillTable();
    }
    
    HangHoaDAO hhdao = new HangHoaDAO();
    NhaCungCapDAO nccdao = new NhaCungCapDAO();
    int current = 0;
    
    void fillTable(){
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setGroupingUsed(true);
        DefaultTableModel model = (DefaultTableModel) tblHangHoa.getModel();
        model.setRowCount(0);   
        NhaCungCap ncc = (NhaCungCap) cbNCC.getSelectedItem();
        try {
            if(ncc!=null){
                List<HangHoa> list = hhdao.selectByTenNCC(ncc.getTennhacc());
                for (HangHoa hh : list) {
                    Object[] row = {
                        hh.getMaHH(),
                        hh.getTenHH(),
                        hh.getDVT(),
                        hh.getDonGia(),
                        hh.getGiaNhap(),
                        hh.getMoTa(),
                        hh.getTenNCC()
                    };
                    model.addRow(row);
                }
            }
       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    void fillall(){
        DefaultTableModel model = (DefaultTableModel) tblHangHoa.getModel();
        model.setRowCount(0);   
        try {
            List<HangHoa> list = hhdao.selectAll();
                for (HangHoa hh : list) {
                    Object[] row = {
                        hh.getMaHH(),
                        hh.getTenHH(),
                        hh.getDVT(),
                        hh.getDonGia(),
                        hh.getGiaNhap(),
                        hh.getMoTa(),
                        hh.getTenNCC()
                    };
                    model.addRow(row);
                }       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    public boolean ValidateForm(){
        if(txtMaHH.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập mã hàng hóa!");
                txtMaHH.requestFocus();
                return false;
            }else if(txtTenHH.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập họ tên hàng hóa!");
                txtTenHH.requestFocus();
                return false;
            }else if(txtDVT.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập don vi tính!");
                txtDVT.requestFocus();
                return false;
            }else if(txtDonGia.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập don giá!");
                txtDonGia.requestFocus();
                return false;
            }else if(txtGiaNhap.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập giá nhâp!");
                txtGiaNhap.requestFocus();
                return false;
            }else if(txtMoTa.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Chưa nhập mô ta!");
                txtMoTa.requestFocus();
                return false;
            }
        return true;
    }
    
    
   
   void setForm(HangHoa hh){
        txtMaHH.setText(hh.getMaHH());
        txtTenHH.setText(hh.getTenHH());
        txtDVT.setText(hh.getDVT());
        txtDonGia.setText(String.valueOf(hh.getDonGia()));
        txtGiaNhap.setText(String.valueOf(hh.getGiaNhap()));
        txtMoTa.setText(hh.getMoTa());
        txtNCC.setText(hh.getTenNCC());
    }
    
    HangHoa getForm(){
        HangHoa hh = new HangHoa();
        hh.setMaHH(txtMaHH.getText());
        hh.setTenHH(txtTenHH.getText());
        hh.setDVT(txtDVT.getText());
        hh.setDonGia(Integer.valueOf(txtDonGia.getText()));
        hh.setGiaNhap(Integer.valueOf(txtGiaNhap.getText()));
        hh.setMoTa(txtMoTa.getText());
        hh.setTenNCC(txtNCC.getText());
        return hh;
    }
    
    void updateForm(){
        txtMaHH.setText("");
        txtTenHH.setText("");
        txtDVT.setText("");
        txtDonGia.setText("");
        txtGiaNhap.setText("");
        txtMoTa.setText("");
        txtNCC.setText("");
        this.current = -1;
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        this.fillTable();
    }
    
    void insert(){
        HangHoa model = getForm();
        try {
            hhdao.insert(model);
            JOptionPane.showMessageDialog(this, "Thêm mới thành công!");
            this.fillTable();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm mới thất bại!","",JOptionPane.ERROR_MESSAGE);
        }

    }
    
     void update(){
        HangHoa model = getForm();
        try {
            hhdao.update(model);
            this.fillTable();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
        }
    }
     
    void delete(){    
            String masp = txtMaHH.getText();
        try {
            hhdao.delete(masp);
            this.fillTable();
            this.updateForm();
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn mã sản phẩm cần xóa");
        }
    }
    
    void fillComboBox(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbNCC.getModel();
        model.removeAllElements();
        List<NhaCungCap> list = nccdao.selectAll();       
        for(NhaCungCap ncc : list){
            model.addElement(ncc);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHangHoa = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cbNCC = new javax.swing.JComboBox<>();
        jToolBar1 = new javax.swing.JToolBar();
        btnThem = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnSua = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnXoa = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnLamMoi = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnAll = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtDVT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenHH = new javax.swing.JTextField();
        txtMaHH = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNCC = new javax.swing.JTextField();

        jScrollPane3.setViewportView(jTextPane1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblHangHoa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblHangHoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "MÃ HH", "TÊN HÀNG HÓA", "ÐVT", "ĐƠN GIÁ", "GIÁ NHẬP", "MÔ TẢ", "TÊN NCC"
            }
        ));
        tblHangHoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHangHoaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHangHoa);
        if (tblHangHoa.getColumnModel().getColumnCount() > 0) {
            tblHangHoa.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblHangHoa.getColumnModel().getColumn(1).setPreferredWidth(250);
            tblHangHoa.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblHangHoa.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblHangHoa.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblHangHoa.getColumnModel().getColumn(5).setPreferredWidth(200);
            tblHangHoa.getColumnModel().getColumn(6).setPreferredWidth(200);
        }

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("NHÀ CUNG CẤP");

        cbNCC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbNCC.setBorder(null);
        cbNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNCCActionPerformed(evt);
            }
        });

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
        jToolBar1.add(jSeparator4);

        btnAll.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/QLK/icon/List.png"))); // NOI18N
        btnAll.setText("Xuất tất cả");
        btnAll.setFocusable(false);
        btnAll.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAll.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAll);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Tên HH:");

        txtDVT.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDVT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Đơn giá:");

        txtDonGia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDonGia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Giá nhập:");

        txtGiaNhap.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtGiaNhap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÔNG TIN HÀNG HÓA");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Mã HH:");

        txtTenHH.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTenHH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        txtMaHH.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtMaHH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("ÐVT:");

        txtMoTa.setColumns(20);
        txtMoTa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtMoTa.setRows(5);
        txtMoTa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txtMoTa.setPreferredSize(new java.awt.Dimension(150, 80));
        jScrollPane2.setViewportView(txtMoTa);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Mô tả:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Nhà cung cấp:");

        txtNCC.setEditable(false);
        txtNCC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNCC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cbNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaHH, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                            .addComponent(txtTenHH))
                                        .addGap(40, 40, 40)
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2))))
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMaHH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenHH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(txtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40))
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

    private void tblHangHoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHangHoaMouseClicked
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        current = tblHangHoa.getSelectedRow();
        txtMaHH.setText(tblHangHoa.getValueAt(current,0).toString());
        txtTenHH.setText(tblHangHoa.getValueAt(current,1).toString());
        txtDVT.setText(tblHangHoa.getValueAt(current,2).toString());
        txtDonGia.setText(tblHangHoa.getValueAt(current,3).toString());
        txtGiaNhap.setText(tblHangHoa.getValueAt(current,4).toString());
        txtMoTa.setText(tblHangHoa.getValueAt(current,5).toString());
        txtNCC.setText(tblHangHoa.getValueAt(current,6).toString());
    }//GEN-LAST:event_tblHangHoaMouseClicked

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

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        this.updateForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void cbNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNCCActionPerformed
        this.fillTable();
    }//GEN-LAST:event_cbNCCActionPerformed

    private void btnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllActionPerformed
        this.fillall();
    }//GEN-LAST:event_btnAllActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAll;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbNCC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblHangHoa;
    private javax.swing.JTextField txtDVT;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaHH;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtNCC;
    private javax.swing.JTextField txtTenHH;
    // End of variables declaration//GEN-END:variables
}
