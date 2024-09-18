/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

//import Logica.Controlador;
import Logica.Fabrica;
import Logica.IControlador;
import javax.swing.JOptionPane;

/**
 *
 * @author topo
 */
public class SeguirUsuario extends javax.swing.JInternalFrame {
    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();
    //Controlador control= new Controlador();
    public SeguirUsuario() {
        initComponents();
        setTitle("Gestion de seguidores");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel_titulo = new javax.swing.JLabel();
        seguidor_Label = new javax.swing.JLabel();
        seguido_Label = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSeguidor = new javax.swing.JTextField();
        txtSeguido = new javax.swing.JTextField();
        btnDejarDeSeguir = new javax.swing.JButton();
        btnSeguir = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        panel_titulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        panel_titulo.setForeground(new java.awt.Color(0, 0, 0));
        panel_titulo.setText("SEGUIR / DEJAR DE SEGUIR USUARIOS");

        seguidor_Label.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        seguidor_Label.setForeground(new java.awt.Color(0, 0, 0));
        seguidor_Label.setText("Seguidor");

        seguido_Label.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        seguido_Label.setForeground(new java.awt.Color(0, 0, 0));
        seguido_Label.setText("Cliente / Artista");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText(">>>>");

        btnDejarDeSeguir.setBackground(new java.awt.Color(255, 102, 0));
        btnDejarDeSeguir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDejarDeSeguir.setForeground(new java.awt.Color(0, 0, 0));
        btnDejarDeSeguir.setText("DEJAR DE SEGUIR");
        btnDejarDeSeguir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDejarDeSeguirActionPerformed(evt);
            }
        });

        btnSeguir.setBackground(new java.awt.Color(0, 153, 0));
        btnSeguir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSeguir.setForeground(new java.awt.Color(0, 0, 0));
        btnSeguir.setText("SEGUIR");
        btnSeguir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeguirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_titulo)
                .addGap(73, 73, 73))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(seguidor_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(seguido_Label)
                .addGap(52, 52, 52))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSeguidor, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnSeguir, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSeguido)
                    .addComponent(btnDejarDeSeguir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panel_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seguidor_Label)
                    .addComponent(seguido_Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSeguidor, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSeguido, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeguir, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDejarDeSeguir, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDejarDeSeguirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDejarDeSeguirActionPerformed
        String mailSeguidor = txtSeguidor.getText();
        String mailSeguido = txtSeguido.getText();
        try {
            control.dejarSeguirUsuario(mailSeguidor, mailSeguido);
            JOptionPane.showMessageDialog(this, "Ahora "+txtSeguidor.getText()+" ya no sigue al usuario "+txtSeguido.getText()+" ", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SeguirUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDejarDeSeguirActionPerformed

    private void btnSeguirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeguirActionPerformed
        String mailSeguidor = txtSeguidor.getText();
        String mailSeguido = txtSeguido.getText();
        try {
            control.seguirUsuario(mailSeguidor, mailSeguido);
            JOptionPane.showMessageDialog(this, "Ahora "+txtSeguidor.getText()+" sigue al usuario "+txtSeguido.getText()+" ", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            java.util.logging.Logger.getLogger(SeguirUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSeguirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDejarDeSeguir;
    private javax.swing.JButton btnSeguir;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel panel_titulo;
    private javax.swing.JLabel seguido_Label;
    private javax.swing.JLabel seguidor_Label;
    private javax.swing.JTextField txtSeguido;
    private javax.swing.JTextField txtSeguidor;
    // End of variables declaration//GEN-END:variables
}
