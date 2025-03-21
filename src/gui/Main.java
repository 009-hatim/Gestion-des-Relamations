/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import services.UserService;

/**
 *
 * @author hp
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        this.setTitle("Authentification");
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/image/user.png")).getImage());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtLogin = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        bnConnexion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(245, 229, 229));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Authentification"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtLogin.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtLogin.setBorder(null);
        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });
        jPanel1.add(txtLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 260, 50));

        txtPassword.setBorder(null);
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 260, 40));

        bnConnexion.setBackground(new java.awt.Color(202, 22, 59));
        bnConnexion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bnConnexion.setForeground(new java.awt.Color(255, 255, 255));
        bnConnexion.setText("Login");
        bnConnexion.setBorder(null);
        bnConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnConnexionActionPerformed(evt);
            }
        });
        jPanel1.add(bnConnexion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 400, 140, 50));

        jLabel1.setForeground(new java.awt.Color(255, 51, 102));
        jLabel1.setText("Mote de passe oublier");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 460, -1, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/login.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 670, 450));

        jLabel2.setBackground(new java.awt.Color(255, 153, 153));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Untitled design.jpg"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 980, 520));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1002, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginActionPerformed

    private void bnConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnConnexionActionPerformed
        String login = txtLogin.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        
        UserService userService = new UserService();
        
        if (userService.authenticate(login, password)) {
            MDIApplication mdi = MDIApplication.getInstance();
            mdi.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Login ou mot de passe incorrect");
        }
    }//GEN-LAST:event_bnConnexionActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        String login = JOptionPane.showInputDialog(this, "Veuillez saisir votre login :");

        if (login != null && !login.trim().isEmpty()) {
            UserService userService = new UserService();

            if (userService.userExists(login)) {

                String question = userService.getSecurityQuestion(login);

                if (question != null) {

                    String reponse = JOptionPane.showInputDialog(this, "Question de sécurité : " + question + "\nVeuillez entrer votre réponse :");

                    if (reponse != null && userService.verifySecurityQuestion(login, reponse)) {

                        String newPassword = JOptionPane.showInputDialog(this, "Entrez votre nouveau mot de passe :");

                        if (newPassword != null && !newPassword.trim().isEmpty()) {
                            userService.updatePassword(login, newPassword);
                            JOptionPane.showMessageDialog(this, "Mot de passe réinitialisé avec succès !");
                        } else {
                            JOptionPane.showMessageDialog(this, "Le mot de passe ne peut pas être vide.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Réponse incorrecte !");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Aucune question de sécurité enregistrée pour cet utilisateur.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Login introuvable.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Le login ne peut pas être vide.");
    }
    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnConnexion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
