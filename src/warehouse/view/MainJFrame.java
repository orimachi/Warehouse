package warehouse.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import warehouse.bean.Category;
import warehouse.controller.SwitchScreen;
import warehouse.utils.Auth;
import warehouse.utils.MessageBox;


public class MainJFrame extends javax.swing.JFrame {

   
    public MainJFrame() {
        initComponents();
        loadUsernameAndRole();
        setTitle("Warehouse Management System");
        setLocationRelativeTo(null);

        SwitchScreen controller = new SwitchScreen(jpnView);
        controller.setView(jpnTrangChu, jbTrangChu);

        List<Category> listDanhMuc = new ArrayList<>();
        listDanhMuc.add(new Category("homepage", jpnTrangChu, jbTrangChu));
        listDanhMuc.add(new Category("warehouse", jpnDVQL, jbDVQL));
        listDanhMuc.add(new Category("product",jpnProduct, jbProduct));
        listDanhMuc.add(new Category("supplier",jpnTTNCC, jbTTNCC));
        
        controller.setEvent(listDanhMuc);

        new LoginJDialog(this, true).setVisible(true);
        new LoadingJDialog(this, true).setVisible(true);
    }

    private void loadUsernameAndRole(){
        lbUsername.setText(Auth.user.getUsername());
        lbRole.setText(String.valueOf(Auth.user.getRole()));
    }
    
    public void exit() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        boolean choice = MessageBox.confirm(this, "Do you want to exit", null);
        if (choice == true) {
            Auth.clear();
            System.exit(0);
        }
    }

    
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
        jpnProduct = new javax.swing.JPanel();
        jbProduct = new javax.swing.JLabel();
        jpnTCHH = new javax.swing.JPanel();
        jbTCHH = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        lbUsername = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        lbRole = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
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

        jpnTrangChu.setBackground(new java.awt.Color(51, 51, 51));
        jpnTrangChu.setPreferredSize(new java.awt.Dimension(300, 50));
        jpnTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnTrangChuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnTrangChuMouseExited(evt);
            }
        });

        jbTrangChu.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jbTrangChu.setForeground(new java.awt.Color(255, 255, 255));
        jbTrangChu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbTrangChu.setText("TRANG CHỦ");

        javax.swing.GroupLayout jpnTrangChuLayout = new javax.swing.GroupLayout(jpnTrangChu);
        jpnTrangChu.setLayout(jpnTrangChuLayout);
        jpnTrangChuLayout.setHorizontalGroup(
            jpnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTrangChuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );
        jpnTrangChuLayout.setVerticalGroup(
            jpnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnTTNCC.setBackground(new java.awt.Color(51, 51, 51));
        jpnTTNCC.setPreferredSize(new java.awt.Dimension(300, 50));
        jpnTTNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnTTNCCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnTTNCCMouseExited(evt);
            }
        });

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
                .addGap(49, 49, 49)
                .addComponent(jbTTNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnTTNCCLayout.setVerticalGroup(
            jpnTTNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbTTNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jpnDVQL.setBackground(new java.awt.Color(51, 51, 51));
        jpnDVQL.setPreferredSize(new java.awt.Dimension(300, 50));
        jpnDVQL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnDVQLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnDVQLMouseExited(evt);
            }
        });

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
                .addGap(68, 68, 68)
                .addComponent(jbDVQL, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnDVQLLayout.setVerticalGroup(
            jpnDVQLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDVQLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbDVQL, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnTTKH.setBackground(new java.awt.Color(51, 51, 51));
        jpnTTKH.setPreferredSize(new java.awt.Dimension(300, 50));
        jpnTTKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnTTKHMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnTTKHMouseExited(evt);
            }
        });

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
                .addGap(63, 63, 63)
                .addComponent(jbTTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnTTKHLayout.setVerticalGroup(
            jpnTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTTKHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbTTKH, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnProduct.setBackground(new java.awt.Color(51, 51, 51));
        jpnProduct.setMaximumSize(new java.awt.Dimension(288, 38));
        jpnProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnProductMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnProductMouseExited(evt);
            }
        });

        jbProduct.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jbProduct.setForeground(new java.awt.Color(255, 255, 255));
        jbProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbProduct.setText("QUẢN LÝ HÀNG HÓA");

        javax.swing.GroupLayout jpnProductLayout = new javax.swing.GroupLayout(jpnProduct);
        jpnProduct.setLayout(jpnProductLayout);
        jpnProductLayout.setHorizontalGroup(
            jpnProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnProductLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnProductLayout.setVerticalGroup(
            jpnProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnProductLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnTCHH.setBackground(new java.awt.Color(51, 51, 51));
        jpnTCHH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnTCHHMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnTCHHMouseExited(evt);
            }
        });

        jbTCHH.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jbTCHH.setForeground(new java.awt.Color(255, 255, 255));
        jbTCHH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbTCHH.setText("TRA CỨU HÀNG TỒN KHO");

        javax.swing.GroupLayout jpnTCHHLayout = new javax.swing.GroupLayout(jpnTCHH);
        jpnTCHH.setLayout(jpnTCHHLayout);
        jpnTCHHLayout.setHorizontalGroup(
            jpnTCHHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTCHHLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jbTCHH, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
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

        jToolBar1.setBackground(new java.awt.Color(51, 51, 51));
        jToolBar1.setRollover(true);

        lbUsername.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lbUsername.setForeground(new java.awt.Color(255, 255, 255));
        lbUsername.setText("Username");
        jToolBar1.add(lbUsername);
        jToolBar1.add(jSeparator1);

        lbRole.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lbRole.setForeground(new java.awt.Color(255, 255, 255));
        lbRole.setText("Role");
        jToolBar1.add(lbRole);

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jbnMenuLayout = new javax.swing.GroupLayout(jbnMenu);
        jbnMenu.setLayout(jbnMenuLayout);
        jbnMenuLayout.setHorizontalGroup(
            jbnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jbnMenuLayout.createSequentialGroup()
                .addGroup(jbnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jbnMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jbnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jpnProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnTTNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnDVQL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnTTKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnTCHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jbnMenuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(jbnMenuLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jbnMenuLayout.setVerticalGroup(
            jbnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jbnMenuLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnTTNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnDVQL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnTTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnTCHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
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
            .addGap(0, 693, Short.MAX_VALUE)
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
        exit();
    }//GEN-LAST:event_formWindowClosing

    private void mnGioiThieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGioiThieuActionPerformed
        JOptionPane.showMessageDialog(this, "File giới thiệu đang được update!");
    }//GEN-LAST:event_mnGioiThieuActionPerformed

    private void mnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnThoatActionPerformed
        exit();
    }//GEN-LAST:event_mnThoatActionPerformed

    private void mnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDoiMatKhauActionPerformed
        new ChangePassword().setVisible(true);

    }//GEN-LAST:event_mnDoiMatKhauActionPerformed

    private void jpnTrangChuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnTrangChuMouseEntered
        jpnTrangChu.setBackground(new Color(0, 153, 51));
    }//GEN-LAST:event_jpnTrangChuMouseEntered

    private void jpnTrangChuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnTrangChuMouseExited
        jpnTrangChu.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_jpnTrangChuMouseExited

    private void jpnTTNCCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnTTNCCMouseEntered
        jpnTTNCC.setBackground(new Color(0, 153, 51));
    }//GEN-LAST:event_jpnTTNCCMouseEntered

    private void jpnTTNCCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnTTNCCMouseExited
        jpnTTNCC.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_jpnTTNCCMouseExited

    private void jpnDVQLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnDVQLMouseEntered
        jpnDVQL.setBackground(new Color(0, 153, 51));
    }//GEN-LAST:event_jpnDVQLMouseEntered

    private void jpnDVQLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnDVQLMouseExited
        jpnDVQL.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_jpnDVQLMouseExited

    private void jpnTTKHMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnTTKHMouseEntered
        jpnTTKH.setBackground(new Color(0, 153, 51));
    }//GEN-LAST:event_jpnTTKHMouseEntered

    private void jpnTTKHMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnTTKHMouseExited
        jpnTTKH.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_jpnTTKHMouseExited

    private void jpnProductMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnProductMouseEntered
        jpnProduct.setBackground(new Color(0, 153, 51));
    }//GEN-LAST:event_jpnProductMouseEntered

    private void jpnProductMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnProductMouseExited
        jpnProduct.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_jpnProductMouseExited

    private void jpnTCHHMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnTCHHMouseEntered
        jpnTCHH.setBackground(new Color(0, 153, 51));
    }//GEN-LAST:event_jpnTCHHMouseEntered

    private void jpnTCHHMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnTCHHMouseExited
        jpnTCHH.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_jpnTCHHMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel jbDVQL;
    private javax.swing.JLabel jbProduct;
    private javax.swing.JLabel jbTCHH;
    private javax.swing.JLabel jbTTKH;
    private javax.swing.JLabel jbTTNCC;
    private javax.swing.JLabel jbTrangChu;
    private javax.swing.JPanel jbnMenu;
    private javax.swing.JPanel jpnDVQL;
    private javax.swing.JPanel jpnMain;
    private javax.swing.JPanel jpnProduct;
    private javax.swing.JPanel jpnTCHH;
    private javax.swing.JPanel jpnTTKH;
    private javax.swing.JPanel jpnTTNCC;
    private javax.swing.JPanel jpnTrangChu;
    private javax.swing.JPanel jpnView;
    private javax.swing.JLabel lbRole;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JMenuItem mnDoiMatKhau;
    private javax.swing.JMenuItem mnGioiThieu;
    private javax.swing.JMenu mnHeThong;
    private javax.swing.JMenuItem mnHuongDan;
    private javax.swing.JMenuItem mnThoat;
    private javax.swing.JMenu mnTroGiup;
    // End of variables declaration//GEN-END:variables
}
