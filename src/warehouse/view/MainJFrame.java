package warehouse.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import warehouse.bean.Category;
import warehouse.controller.SwitchScreen;

/**
 *
 * @author Manh Thanh
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
        setTitle("Warehouse Management System");
        setLocationRelativeTo(null);
        
        SwitchScreen controller = new  SwitchScreen(jpnView);
        controller.setView(jpnTrangChu, jbTrangChu);
        
        List<Category> listDanhMuc = new ArrayList<>();
        listDanhMuc.add(new Category("homepage", jpnTrangChu, jbTrangChu));
        listDanhMuc.add(new Category("warehouse", jpnDVQL, jbDVQL));
              
        controller.setEvent(listDanhMuc);
             
        new LoginJDialog(this, true).setVisible(true);
        new LoadingJDialog(this, true).setVisible(true);
    }
    
    public void thoat()
    {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE );
        int kq=JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát ?","",JOptionPane.YES_NO_OPTION);
        if(kq==0)
        {
            System.exit(0);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnMain = new javax.swing.JPanel();
        jbnMenu = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpnTrangChu = new javax.swing.JPanel();
        jbTrangChu = new javax.swing.JLabel();
        jpnTTNCC = new javax.swing.JPanel();
        jbTTNCC = new javax.swing.JLabel();
        jpnDVQL = new javax.swing.JPanel();
        jbDVQL = new javax.swing.JLabel();
        jpnTTKH = new javax.swing.JPanel();
        jbTTKH = new javax.swing.JLabel();
        jpnQLHH = new javax.swing.JPanel();
        jbQLHH = new javax.swing.JLabel();
        jpnTCHH = new javax.swing.JPanel();
        jbTCHH = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jpnView = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnHeThong = new javax.swing.JMenu();
        mnDoiMatKhau = new javax.swing.JMenuItem();
        mnThoat = new javax.swing.JMenuItem();
        mnTroGiup = new javax.swing.JMenu();
        mnHuongDan = new javax.swing.JMenuItem();
        mnGioiThieu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jbnMenu.setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PHẦN MỀM QUẢN LÝ KHO HÀNG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jpnTrangChu.setBackground(new java.awt.Color(0, 153, 51));
        jpnTrangChu.setPreferredSize(new java.awt.Dimension(300, 50));

        jbTrangChu.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jbTrangChu.setForeground(new java.awt.Color(255, 255, 255));
        jbTrangChu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbTrangChu.setText("TRANG CHỦ");

        javax.swing.GroupLayout jpnTrangChuLayout = new javax.swing.GroupLayout(jpnTrangChu);
        jpnTrangChu.setLayout(jpnTrangChuLayout);
        jpnTrangChuLayout.setHorizontalGroup(
            jpnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnTrangChuLayout.setVerticalGroup(
            jpnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnTTNCC.setBackground(new java.awt.Color(0, 153, 51));
        jpnTTNCC.setPreferredSize(new java.awt.Dimension(300, 50));

        jbTTNCC.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jbTTNCC.setForeground(new java.awt.Color(255, 255, 255));
        jbTTNCC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbTTNCC.setText("THÔNG TIN NHÀ CUNG CẤP");
        jbTTNCC.setMaximumSize(new java.awt.Dimension(209, 22));

        javax.swing.GroupLayout jpnTTNCCLayout = new javax.swing.GroupLayout(jpnTTNCC);
        jpnTTNCC.setLayout(jpnTTNCCLayout);
        jpnTTNCCLayout.setHorizontalGroup(
            jpnTTNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTTNCCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbTTNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnTTNCCLayout.setVerticalGroup(
            jpnTTNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTTNCCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbTTNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnDVQL.setBackground(new java.awt.Color(0, 153, 51));
        jpnDVQL.setPreferredSize(new java.awt.Dimension(300, 50));

        jbDVQL.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jbDVQL.setForeground(new java.awt.Color(255, 255, 255));
        jbDVQL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbDVQL.setText("ĐƠN VỊ QUẢN LÝ");
        jbDVQL.setMaximumSize(new java.awt.Dimension(209, 22));

        javax.swing.GroupLayout jpnDVQLLayout = new javax.swing.GroupLayout(jpnDVQL);
        jpnDVQL.setLayout(jpnDVQLLayout);
        jpnDVQLLayout.setHorizontalGroup(
            jpnDVQLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDVQLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbDVQL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnDVQLLayout.setVerticalGroup(
            jpnDVQLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDVQLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbDVQL, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnTTKH.setBackground(new java.awt.Color(0, 153, 51));
        jpnTTKH.setPreferredSize(new java.awt.Dimension(300, 50));

        jbTTKH.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jbTTKH.setForeground(new java.awt.Color(255, 255, 255));
        jbTTKH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbTTKH.setText("THÔNG TIN KHÁCH HÀNG");
        jbTTKH.setMaximumSize(new java.awt.Dimension(209, 22));

        javax.swing.GroupLayout jpnTTKHLayout = new javax.swing.GroupLayout(jpnTTKH);
        jpnTTKH.setLayout(jpnTTKHLayout);
        jpnTTKHLayout.setHorizontalGroup(
            jpnTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTTKHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbTTKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnTTKHLayout.setVerticalGroup(
            jpnTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTTKHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbTTKH, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnQLHH.setBackground(new java.awt.Color(0, 153, 51));
        jpnQLHH.setMaximumSize(new java.awt.Dimension(288, 38));

        jbQLHH.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jbQLHH.setForeground(new java.awt.Color(255, 255, 255));
        jbQLHH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbQLHH.setText("QUẢN LÝ HÀNG HÓA");

        javax.swing.GroupLayout jpnQLHHLayout = new javax.swing.GroupLayout(jpnQLHH);
        jpnQLHH.setLayout(jpnQLHHLayout);
        jpnQLHHLayout.setHorizontalGroup(
            jpnQLHHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQLHHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbQLHH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnQLHHLayout.setVerticalGroup(
            jpnQLHHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQLHHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbQLHH, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnTCHH.setBackground(new java.awt.Color(0, 153, 51));

        jbTCHH.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jbTCHH.setForeground(new java.awt.Color(255, 255, 255));
        jbTCHH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbTCHH.setText("TRA CỨU HÀNG TỒN KHO");

        javax.swing.GroupLayout jpnTCHHLayout = new javax.swing.GroupLayout(jpnTCHH);
        jpnTCHH.setLayout(jpnTCHHLayout);
        jpnTCHHLayout.setHorizontalGroup(
            jpnTCHHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTCHHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbTCHH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnTCHHLayout.setVerticalGroup(
            jpnTCHHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTCHHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbTCHH, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warehouse/icon/logo.png"))); // NOI18N
        jLabel2.setText("KC Group");

        javax.swing.GroupLayout jbnMenuLayout = new javax.swing.GroupLayout(jbnMenu);
        jbnMenu.setLayout(jbnMenuLayout);
        jbnMenuLayout.setHorizontalGroup(
            jbnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jbnMenuLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jbnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpnQLHH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnTTNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnDVQL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnTTKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnTCHH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jbnMenuLayout.setVerticalGroup(
            jbnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jbnMenuLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jpnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnTTNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnDVQL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnTTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnQLHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnTCHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jpnView.setBackground(new java.awt.Color(255, 255, 255));
        jpnView.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 928, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnMainLayout = new javax.swing.GroupLayout(jpnMain);
        jpnMain.setLayout(jpnMainLayout);
        jpnMainLayout.setHorizontalGroup(
            jpnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMainLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jbnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnMainLayout.setVerticalGroup(
            jpnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMainLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jpnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        mnHeThong.setText("Hệ thống");

        mnDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warehouse/icon/Shield.png"))); // NOI18N
        mnDoiMatKhau.setText("Đổi mật khẩu");
        mnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDoiMatKhauActionPerformed(evt);
            }
        });
        mnHeThong.add(mnDoiMatKhau);

        mnThoat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        mnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warehouse/icon/Log out.png"))); // NOI18N
        mnThoat.setText("Thoát");
        mnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnThoatActionPerformed(evt);
            }
        });
        mnHeThong.add(mnThoat);

        jMenuBar1.add(mnHeThong);

        mnTroGiup.setBackground(new java.awt.Color(0, 0, 0));
        mnTroGiup.setText("Trợ giúp");

        mnHuongDan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        mnHuongDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warehouse/icon/Globe.png"))); // NOI18N
        mnHuongDan.setText("Hướng dẫn sử dụng");
        mnHuongDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnHuongDanActionPerformed(evt);
            }
        });
        mnTroGiup.add(mnHuongDan);

        mnGioiThieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warehouse/icon/Brick house.png"))); // NOI18N
        mnGioiThieu.setText("Giới thiệu sản phẩm");
        mnGioiThieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGioiThieuActionPerformed(evt);
            }
        });
        mnTroGiup.add(mnGioiThieu);

        jMenuBar1.add(mnTroGiup);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnHuongDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnHuongDanActionPerformed
        JOptionPane.showMessageDialog(this, "File hướng dẫn đang được update!");
    }//GEN-LAST:event_mnHuongDanActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        thoat();
    }//GEN-LAST:event_formWindowClosing

    private void mnGioiThieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGioiThieuActionPerformed
        JOptionPane.showMessageDialog(this, "File giới thiệu đang được update!");
    }//GEN-LAST:event_mnGioiThieuActionPerformed

    private void mnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnThoatActionPerformed
        thoat();
    }//GEN-LAST:event_mnThoatActionPerformed

    private void mnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDoiMatKhauActionPerformed
        new ChangePassword().setVisible(true);
       
    }//GEN-LAST:event_mnDoiMatKhauActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jbDVQL;
    private javax.swing.JLabel jbQLHH;
    private javax.swing.JLabel jbTCHH;
    private javax.swing.JLabel jbTTKH;
    private javax.swing.JLabel jbTTNCC;
    private javax.swing.JLabel jbTrangChu;
    private javax.swing.JPanel jbnMenu;
    private javax.swing.JPanel jpnDVQL;
    private javax.swing.JPanel jpnMain;
    private javax.swing.JPanel jpnQLHH;
    private javax.swing.JPanel jpnTCHH;
    private javax.swing.JPanel jpnTTKH;
    private javax.swing.JPanel jpnTTNCC;
    private javax.swing.JPanel jpnTrangChu;
    private javax.swing.JPanel jpnView;
    private javax.swing.JMenuItem mnDoiMatKhau;
    private javax.swing.JMenuItem mnGioiThieu;
    private javax.swing.JMenu mnHeThong;
    private javax.swing.JMenuItem mnHuongDan;
    private javax.swing.JMenuItem mnThoat;
    private javax.swing.JMenu mnTroGiup;
    // End of variables declaration//GEN-END:variables
}
