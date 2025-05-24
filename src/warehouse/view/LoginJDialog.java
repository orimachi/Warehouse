package warehouse.view;

import warehouse.dao.AccountDAO;
import warehouse.entity.Account;
import warehouse.utils.Auth;
import warehouse.utils.MessageBox;

public class LoginJDialog extends javax.swing.JDialog {

    public LoginJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setTitle("LOGIN");
    }

    AccountDAO accountDAO = new AccountDAO();

    void signin() {
        String username = txtUsername.getText();
         // when add new user add hashCode()
        String password = new String(txtPassword.getPassword());
        Account account;
        
        if (username.isEmpty() || password.isEmpty()) {
            MessageBox.warning(this, "Username and password are required");
            return;
        }

        try {
            account = accountDAO.selectById(username);
            if (account == null || !password.equals(account.getPassword())) {
                MessageBox.warning(this, "Incorrect username or password");
                return;
            }
        } catch (RuntimeException e) {
            MessageBox.warning(this, "Incorrect username or password");
            return;
        }
        if (!password.equals(account.getPassword())) {
            MessageBox.warning(this, "Incorrect username or password");
            return;
        }
        
        Auth.user = account;
        MessageBox.success(this, "Đăng nhập thành công!");
        
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginJDialog dialog = new LoginJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtPassword = new warehouse.component.textfield.PasswordField();
        txtUsername = new warehouse.component.textfield.TextField();
        jRadioButton1 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnLogin.setBackground(new java.awt.Color(52, 152, 219));
        btnLogin.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("ĐĂNG NHẬP");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warehouse/icon/AnhLogin.png"))); // NOI18N

        txtPassword.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPassword.setLabelText("Password");

        txtUsername.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtUsername.setLabelText("Username");

        jRadioButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jRadioButton1.setText("Remember me");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jRadioButton1))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        this.signin();
    }//GEN-LAST:event_btnLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private warehouse.component.textfield.PasswordField txtPassword;
    private warehouse.component.textfield.TextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
