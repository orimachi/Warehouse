package com.QLK.view;

import com.QLK.dao.DonViDAO;
import com.QLK.dao.TonKhoDAO;
import com.QLK.entity.TonKho;
import com.QLK.entity.DonVi;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class TraCuuHangHoaJPanel extends javax.swing.JPanel {


    public TraCuuHangHoaJPanel() {
        initComponents();
        this.fillTable();
        this.fillComboBoxTonKho();
    }
    
    TonKhoDAO tkdao = new TonKhoDAO();
    DonViDAO dvDAO = new DonViDAO();
    private JComboBox<DonVi> cbxDonViKho;


    public void setBtnAll(JButton btnAll) {
        this.btnAll = btnAll;
    }
    
    public void setTblTonKho(JTable tblTonKho) {
        this.tblTonKho = tblTonKho;
    }

    public void setComboBoxDonViKho(JComboBox<DonVi> cbxDonViKho) {
        this.cbxDonViKho = cbxDonViKho;
    }

    
    public void fillComboBoxTonKho(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbxDonVi.getModel();
        model.removeAllElements();   
        List<DonVi> list = dvDAO.selectAll();
        for (DonVi dv : list) {
            model.addElement(dv);
        }        
    }
    
    void fillTable() {
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setGroupingUsed(true);
        DefaultTableModel model = (DefaultTableModel) tblTonKho.getModel();
        model.setRowCount(0);
        DonVi dv = (DonVi) cbxDonVi.getSelectedItem();
        if(dv!=null){
            List<TonKho> list = tkdao.selectByDonVi(dv.getTenDV());
            for(int i=0; i<list.size(); i++){
                TonKho tk = list.get(i);
                int dg = tk.getDonGia();
                int slKho = tk.getTonKho();
                int thanhtien = tk.getDonGia()*tk.getTonKho();
                model.addRow(new Object[]{
                    tk.getBarcode(),
                    tk.getMaHH(),
                    tk.getTenHH(),
                    tk.getdVT(),
                    tk.getsLNhap(),
                    tk.getsLXuat(),
                    tk.getTonKho(),
                    format.format(tk.getDonGia()),
                    format.format(thanhtien),
                    tk.getNgayCapNhat(),
                    tk.getTenDV()
                });
            }
            
        }
    }
    
    void fillall(){
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setGroupingUsed(true);
        DefaultTableModel model = (DefaultTableModel) tblTonKho.getModel();
        model.setRowCount(0);   
        try {
            List<TonKho> list = tkdao.selectAll();
                for (TonKho tk : list) {
                    int dg = tk.getDonGia();
                    int slKho = tk.getTonKho();
                    int thanhtien = tk.getDonGia()*tk.getTonKho();
                    Object[] row = {
                        tk.getBarcode(),
                        tk.getMaHH(),
                        tk.getTenHH(),
                        tk.getdVT(),
                        tk.getsLNhap(),
                        tk.getsLXuat(),
                        tk.getTonKho(),
                        format.format(tk.getDonGia()),
                        format.format(thanhtien),
                        tk.getNgayCapNhat(),
                        tk.getTenDV()
                    };
                    model.addRow(row);
                }       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    public void TimKiem(){
        DefaultTableModel model = (DefaultTableModel) tblTonKho.getModel();
        TableRowSorter<DefaultTableModel> rs = new TableRowSorter<DefaultTableModel>(model);
        tblTonKho.setRowSorter(rs);
        rs.setRowFilter(RowFilter.regexFilter(txtSearch.getText().trim()));
    }
    
    public void searchOn() {
        String searchText = txtSearch.getText().trim(); 
        DefaultTableModel model = (DefaultTableModel) tblTonKho.getModel(); 
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model); 
        tblTonKho.setRowSorter(sorter);

        if (searchText.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
    }
    
    public javax.swing.JTable getTblTonKho() {
        return tblTonKho;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTonKho = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbxDonVi = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnAll = new javax.swing.JButton();

        jPasswordField1.setText("jPasswordField1");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblTonKho.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblTonKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "BARCODE", "MÃ HH", "TÊN HÀNG HÓA", "DVT", "SL NHẬP", "SL XUẤT", "TỒN KHO", "ĐƠN GIÁ", "THÀNH TIỀN", "NGÀY CẬP NHẬT", "DVQL"
            }
        ));
        jScrollPane1.setViewportView(tblTonKho);
        if (tblTonKho.getColumnModel().getColumnCount() > 0) {
            tblTonKho.getColumnModel().getColumn(0).setPreferredWidth(90);
            tblTonKho.getColumnModel().getColumn(1).setPreferredWidth(70);
            tblTonKho.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblTonKho.getColumnModel().getColumn(3).setPreferredWidth(40);
            tblTonKho.getColumnModel().getColumn(4).setPreferredWidth(70);
            tblTonKho.getColumnModel().getColumn(5).setPreferredWidth(70);
            tblTonKho.getColumnModel().getColumn(6).setPreferredWidth(70);
            tblTonKho.getColumnModel().getColumn(8).setPreferredWidth(100);
            tblTonKho.getColumnModel().getColumn(9).setPreferredWidth(110);
            tblTonKho.getColumnModel().getColumn(10).setPreferredWidth(110);
        }

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Đơn vị:");

        cbxDonVi.setBorder(null);
        cbxDonVi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDonViActionPerformed(evt);
            }
        });

        txtSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Tìm kiếm:");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAll)
                .addGap(56, 56, 56)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cbxDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbxDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAll, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void cbxDonViActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDonViActionPerformed
        this.fillTable();
    }//GEN-LAST:event_cbxDonViActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        this.TimKiem();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllActionPerformed
        this.fillall();
    }//GEN-LAST:event_btnAllActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAll;
    public javax.swing.JComboBox<String> cbxDonVi;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tblTonKho;
    public javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
