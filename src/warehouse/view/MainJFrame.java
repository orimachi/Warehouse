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
        listDanhMuc.add(new Category("warehouse", jpnWarehouse, jbWarehouse));
        listDanhMuc.add(new Category("product", jpnProduct, jbProduct));
        listDanhMuc.add(new Category("supplier", jpnSuppliers, jbSuppliers));
        listDanhMuc.add(new Category("stock", jpnStock, jbStock));
        listDanhMuc.add(new Category("stockin", jpnStockIn, jbStockIn));
        listDanhMuc.add(new Category("stockout", jpnStockOut, jbStockOut));
        if (Auth.isLogin()&& Auth.isManager() == true) {
            listDanhMuc.add(new Category("account", jpnManagerAccount, jbManagerAccount));
            listDanhMuc.add(new Category("managerStockIn", jpnManagerStockIn, jbManagerStockIn));
            listDanhMuc.add(new Category("managerStockOut", jpnManagerStockOut, jbManagerStockOut));
            listDanhMuc.add(new Category("statistics", jpnStatistics, jbStatistics));
        }

        controller.setEvent(listDanhMuc);
        
        new LoadingJDialog(this, true).setVisible(true);
    }

    private void loadUsernameAndRole() {
        jbUsername.setText(Auth.user.getUsername());
        jbRole.setText(String.valueOf(Auth.user.getRole()));
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
        jpnSuppliers = new javax.swing.JPanel();
        jbSuppliers = new javax.swing.JLabel();
        jpnWarehouse = new javax.swing.JPanel();
        jbWarehouse = new javax.swing.JLabel();
        jpnStock = new javax.swing.JPanel();
        jbStock = new javax.swing.JLabel();
        jpnStockIn = new javax.swing.JPanel();
        jbStockIn = new javax.swing.JLabel();
        jpnStockOut = new javax.swing.JPanel();
        jbStockOut = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jpnProduct = new javax.swing.JPanel();
        jbProduct = new javax.swing.JLabel();
        jpnManagerAccount = new javax.swing.JPanel();
        jbManagerAccount = new javax.swing.JLabel();
        jpnManagerStockIn = new javax.swing.JPanel();
        jbManagerStockIn = new javax.swing.JLabel();
        jpnManagerStockOut = new javax.swing.JPanel();
        jbManagerStockOut = new javax.swing.JLabel();
        jpnStatistics = new javax.swing.JPanel();
        jbStatistics = new javax.swing.JLabel();
        jbRole = new javax.swing.JLabel();
        jbUsername = new javax.swing.JLabel();
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

        jbnMenu.setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(52, 152, 219));

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
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jbTrangChu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbTrangChu.setForeground(new java.awt.Color(255, 255, 255));
        jbTrangChu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbTrangChu.setText("HOME PAGE");

        javax.swing.GroupLayout jpnTrangChuLayout = new javax.swing.GroupLayout(jpnTrangChu);
        jpnTrangChu.setLayout(jpnTrangChuLayout);
        jpnTrangChuLayout.setHorizontalGroup(
            jpnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTrangChuLayout.createSequentialGroup()
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

        jpnSuppliers.setBackground(new java.awt.Color(51, 51, 51));
        jpnSuppliers.setPreferredSize(new java.awt.Dimension(300, 50));
        jpnSuppliers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnSuppliersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnSuppliersMouseExited(evt);
            }
        });

        jbSuppliers.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbSuppliers.setForeground(new java.awt.Color(255, 255, 255));
        jbSuppliers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbSuppliers.setText("SUPPLIERS");
        jbSuppliers.setMaximumSize(new java.awt.Dimension(209, 22));
        jbSuppliers.setPreferredSize(new java.awt.Dimension(82, 17));

        javax.swing.GroupLayout jpnSuppliersLayout = new javax.swing.GroupLayout(jpnSuppliers);
        jpnSuppliers.setLayout(jpnSuppliersLayout);
        jpnSuppliersLayout.setHorizontalGroup(
            jpnSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnSuppliersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbSuppliers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnSuppliersLayout.setVerticalGroup(
            jpnSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbSuppliers, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jpnWarehouse.setBackground(new java.awt.Color(51, 51, 51));
        jpnWarehouse.setPreferredSize(new java.awt.Dimension(300, 50));
        jpnWarehouse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnWarehouseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnWarehouseMouseExited(evt);
            }
        });

        jbWarehouse.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbWarehouse.setForeground(new java.awt.Color(255, 255, 255));
        jbWarehouse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbWarehouse.setText("WAREHOUSE");
        jbWarehouse.setMaximumSize(new java.awt.Dimension(209, 22));
        jbWarehouse.setPreferredSize(new java.awt.Dimension(82, 17));

        javax.swing.GroupLayout jpnWarehouseLayout = new javax.swing.GroupLayout(jpnWarehouse);
        jpnWarehouse.setLayout(jpnWarehouseLayout);
        jpnWarehouseLayout.setHorizontalGroup(
            jpnWarehouseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnWarehouseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbWarehouse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnWarehouseLayout.setVerticalGroup(
            jpnWarehouseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnWarehouseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbWarehouse, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnStock.setBackground(new java.awt.Color(51, 51, 51));
        jpnStock.setPreferredSize(new java.awt.Dimension(300, 50));
        jpnStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnStockMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnStockMouseExited(evt);
            }
        });

        jbStock.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbStock.setForeground(new java.awt.Color(255, 255, 255));
        jbStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbStock.setText("STOCK");
        jbStock.setMaximumSize(new java.awt.Dimension(209, 22));
        jbStock.setPreferredSize(new java.awt.Dimension(82, 17));

        javax.swing.GroupLayout jpnStockLayout = new javax.swing.GroupLayout(jpnStock);
        jpnStock.setLayout(jpnStockLayout);
        jpnStockLayout.setHorizontalGroup(
            jpnStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnStockLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnStockLayout.setVerticalGroup(
            jpnStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStockLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbStock, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnStockIn.setBackground(new java.awt.Color(51, 51, 51));
        jpnStockIn.setMaximumSize(new java.awt.Dimension(288, 38));
        jpnStockIn.setPreferredSize(new java.awt.Dimension(300, 50));
        jpnStockIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnStockInMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnStockInMouseExited(evt);
            }
        });

        jbStockIn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbStockIn.setForeground(new java.awt.Color(255, 255, 255));
        jbStockIn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbStockIn.setText("STOCK IN");
        jbStockIn.setPreferredSize(new java.awt.Dimension(82, 17));

        javax.swing.GroupLayout jpnStockInLayout = new javax.swing.GroupLayout(jpnStockIn);
        jpnStockIn.setLayout(jpnStockInLayout);
        jpnStockInLayout.setHorizontalGroup(
            jpnStockInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStockInLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbStockIn, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnStockInLayout.setVerticalGroup(
            jpnStockInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStockInLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbStockIn, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnStockOut.setBackground(new java.awt.Color(51, 51, 51));
        jpnStockOut.setPreferredSize(new java.awt.Dimension(300, 50));
        jpnStockOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnStockOutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnStockOutMouseExited(evt);
            }
        });

        jbStockOut.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbStockOut.setForeground(new java.awt.Color(255, 255, 255));
        jbStockOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbStockOut.setText("STOCK OUT");
        jbStockOut.setPreferredSize(new java.awt.Dimension(82, 17));

        javax.swing.GroupLayout jpnStockOutLayout = new javax.swing.GroupLayout(jpnStockOut);
        jpnStockOut.setLayout(jpnStockOutLayout);
        jpnStockOutLayout.setHorizontalGroup(
            jpnStockOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStockOutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbStockOut, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
        );
        jpnStockOutLayout.setVerticalGroup(
            jpnStockOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStockOutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbStockOut, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warehouse/icon/logo.png"))); // NOI18N
        jLabel2.setText("KC Group");

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        jpnProduct.setBackground(new java.awt.Color(51, 51, 51));
        jpnProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnProductMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnProductMouseExited(evt);
            }
        });

        jbProduct.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbProduct.setForeground(new java.awt.Color(255, 255, 255));
        jbProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbProduct.setText("PRODUCT");
        jbProduct.setPreferredSize(new java.awt.Dimension(82, 17));

        javax.swing.GroupLayout jpnProductLayout = new javax.swing.GroupLayout(jpnProduct);
        jpnProduct.setLayout(jpnProductLayout);
        jpnProductLayout.setHorizontalGroup(
            jpnProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnProductLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnProductLayout.setVerticalGroup(
            jpnProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        jpnManagerAccount.setBackground(new java.awt.Color(51, 51, 51));
        jpnManagerAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnManagerAccountMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnManagerAccountMouseExited(evt);
            }
        });

        jbManagerAccount.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbManagerAccount.setForeground(new java.awt.Color(255, 255, 255));
        jbManagerAccount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbManagerAccount.setText("ACCOUNT");
        jbManagerAccount.setPreferredSize(new java.awt.Dimension(82, 17));

        javax.swing.GroupLayout jpnManagerAccountLayout = new javax.swing.GroupLayout(jpnManagerAccount);
        jpnManagerAccount.setLayout(jpnManagerAccountLayout);
        jpnManagerAccountLayout.setHorizontalGroup(
            jpnManagerAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnManagerAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbManagerAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnManagerAccountLayout.setVerticalGroup(
            jpnManagerAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnManagerAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbManagerAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnManagerStockIn.setBackground(new java.awt.Color(51, 51, 51));
        jpnManagerStockIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnManagerStockInMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnManagerStockInMouseExited(evt);
            }
        });

        jbManagerStockIn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbManagerStockIn.setForeground(new java.awt.Color(255, 255, 255));
        jbManagerStockIn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbManagerStockIn.setText("MANAGER STOCK IN");
        jbManagerStockIn.setPreferredSize(new java.awt.Dimension(82, 17));

        javax.swing.GroupLayout jpnManagerStockInLayout = new javax.swing.GroupLayout(jpnManagerStockIn);
        jpnManagerStockIn.setLayout(jpnManagerStockInLayout);
        jpnManagerStockInLayout.setHorizontalGroup(
            jpnManagerStockInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnManagerStockInLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbManagerStockIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnManagerStockInLayout.setVerticalGroup(
            jpnManagerStockInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnManagerStockInLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbManagerStockIn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnManagerStockOut.setBackground(new java.awt.Color(51, 51, 51));
        jpnManagerStockOut.setPreferredSize(new java.awt.Dimension(200, 52));
        jpnManagerStockOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnManagerStockOutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnManagerStockOutMouseExited(evt);
            }
        });

        jbManagerStockOut.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbManagerStockOut.setForeground(new java.awt.Color(255, 255, 255));
        jbManagerStockOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbManagerStockOut.setText("MANAGER STOCK OUT");
        jbManagerStockOut.setPreferredSize(new java.awt.Dimension(82, 17));

        javax.swing.GroupLayout jpnManagerStockOutLayout = new javax.swing.GroupLayout(jpnManagerStockOut);
        jpnManagerStockOut.setLayout(jpnManagerStockOutLayout);
        jpnManagerStockOutLayout.setHorizontalGroup(
            jpnManagerStockOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnManagerStockOutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbManagerStockOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnManagerStockOutLayout.setVerticalGroup(
            jpnManagerStockOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnManagerStockOutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbManagerStockOut, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnStatistics.setBackground(new java.awt.Color(51, 51, 51));
        jpnStatistics.setPreferredSize(new java.awt.Dimension(200, 52));
        jpnStatistics.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpnStatisticsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpnStatisticsMouseExited(evt);
            }
        });

        jbStatistics.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbStatistics.setForeground(new java.awt.Color(255, 255, 255));
        jbStatistics.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbStatistics.setText("STATISTICS");
        jbStatistics.setPreferredSize(new java.awt.Dimension(82, 17));

        javax.swing.GroupLayout jpnStatisticsLayout = new javax.swing.GroupLayout(jpnStatistics);
        jpnStatistics.setLayout(jpnStatisticsLayout);
        jpnStatisticsLayout.setHorizontalGroup(
            jpnStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStatisticsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbStatistics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnStatisticsLayout.setVerticalGroup(
            jpnStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStatisticsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbStatistics, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        jbRole.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbRole.setForeground(new java.awt.Color(255, 255, 255));
        jbRole.setText("jLabel3");

        jbUsername.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbUsername.setForeground(new java.awt.Color(255, 255, 255));
        jbUsername.setText("jLabel3");

        javax.swing.GroupLayout jbnMenuLayout = new javax.swing.GroupLayout(jbnMenu);
        jbnMenu.setLayout(jbnMenuLayout);
        jbnMenuLayout.setHorizontalGroup(
            jbnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jbnMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbUsername)
                .addGap(60, 60, 60)
                .addComponent(jbRole)
                .addGap(100, 100, 100))
            .addGroup(jbnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jbnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jbnMenuLayout.createSequentialGroup()
                        .addGroup(jbnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jbnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jpnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                                .addComponent(jpnSuppliers, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                                .addComponent(jpnWarehouse, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                                .addComponent(jpnStock, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                                .addComponent(jpnStockOut, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jbnMenuLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jbnMenuLayout.createSequentialGroup()
                        .addGroup(jbnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpnStockIn, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jpnProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnManagerAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnStatistics, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                            .addComponent(jpnManagerStockIn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnManagerStockOut, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jbnMenuLayout.setVerticalGroup(
            jbnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jbnMenuLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jbnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbRole)
                    .addComponent(jbUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jpnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnWarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnStockIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnStockOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnManagerAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnManagerStockIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnManagerStockOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jpnView.setBackground(new java.awt.Color(255, 255, 255));
        jpnView.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 926, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 903, Short.MAX_VALUE)
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
                    .addGroup(jpnMainLayout.createSequentialGroup()
                        .addComponent(jpnView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
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

    private void jpnSuppliersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnSuppliersMouseEntered
        jpnSuppliers.setBackground(new Color(0, 153, 51));
    }//GEN-LAST:event_jpnSuppliersMouseEntered

    private void jpnSuppliersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnSuppliersMouseExited
        jpnSuppliers.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_jpnSuppliersMouseExited

    private void jpnWarehouseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnWarehouseMouseEntered
        jpnWarehouse.setBackground(new Color(0, 153, 51));
    }//GEN-LAST:event_jpnWarehouseMouseEntered

    private void jpnWarehouseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnWarehouseMouseExited
        jpnWarehouse.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_jpnWarehouseMouseExited

    private void jpnStockMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnStockMouseEntered
        jpnStock.setBackground(new Color(0, 153, 51));
    }//GEN-LAST:event_jpnStockMouseEntered

    private void jpnStockMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnStockMouseExited
        jpnStock.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_jpnStockMouseExited

    private void jpnStockInMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnStockInMouseEntered
        jpnStockIn.setBackground(new Color(0, 153, 51));
    }//GEN-LAST:event_jpnStockInMouseEntered

    private void jpnStockInMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnStockInMouseExited
        jpnStockIn.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_jpnStockInMouseExited

    private void jpnStockOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnStockOutMouseEntered
        jpnStockOut.setBackground(new Color(0, 153, 51));
    }//GEN-LAST:event_jpnStockOutMouseEntered

    private void jpnStockOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnStockOutMouseExited
        jpnStockOut.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_jpnStockOutMouseExited

    private void jpnProductMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnProductMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnProductMouseEntered

    private void jpnProductMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnProductMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnProductMouseExited

    private void jpnManagerAccountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnManagerAccountMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnManagerAccountMouseEntered

    private void jpnManagerAccountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnManagerAccountMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnManagerAccountMouseExited

    private void jpnManagerStockInMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnManagerStockInMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnManagerStockInMouseEntered

    private void jpnManagerStockInMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnManagerStockInMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnManagerStockInMouseExited

    private void jpnManagerStockOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnManagerStockOutMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnManagerStockOutMouseEntered

    private void jpnManagerStockOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnManagerStockOutMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnManagerStockOutMouseExited

    private void jpnStatisticsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnStatisticsMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnStatisticsMouseEntered

    private void jpnStatisticsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnStatisticsMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnStatisticsMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel jbManagerAccount;
    private javax.swing.JLabel jbManagerStockIn;
    private javax.swing.JLabel jbManagerStockOut;
    private javax.swing.JLabel jbProduct;
    private javax.swing.JLabel jbRole;
    private javax.swing.JLabel jbStatistics;
    private javax.swing.JLabel jbStock;
    private javax.swing.JLabel jbStockIn;
    private javax.swing.JLabel jbStockOut;
    private javax.swing.JLabel jbSuppliers;
    private javax.swing.JLabel jbTrangChu;
    private javax.swing.JLabel jbUsername;
    private javax.swing.JLabel jbWarehouse;
    private javax.swing.JPanel jbnMenu;
    private javax.swing.JPanel jpnMain;
    private javax.swing.JPanel jpnManagerAccount;
    private javax.swing.JPanel jpnManagerStockIn;
    private javax.swing.JPanel jpnManagerStockOut;
    private javax.swing.JPanel jpnProduct;
    private javax.swing.JPanel jpnStatistics;
    private javax.swing.JPanel jpnStock;
    private javax.swing.JPanel jpnStockIn;
    private javax.swing.JPanel jpnStockOut;
    private javax.swing.JPanel jpnSuppliers;
    private javax.swing.JPanel jpnTrangChu;
    private javax.swing.JPanel jpnView;
    private javax.swing.JPanel jpnWarehouse;
    private javax.swing.JMenuItem mnDoiMatKhau;
    private javax.swing.JMenuItem mnGioiThieu;
    private javax.swing.JMenu mnHeThong;
    private javax.swing.JMenuItem mnHuongDan;
    private javax.swing.JMenuItem mnThoat;
    private javax.swing.JMenu mnTroGiup;
    // End of variables declaration//GEN-END:variables
}
