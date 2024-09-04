/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Logica.Controlador;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author topo
 */
public class SeguirUsuario extends javax.swing.JInternalFrame {

    Controlador control= new Controlador();
    public SeguirUsuario() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jLabelSeguidor = new javax.swing.JLabel();
        jLabelSeguido = new javax.swing.JLabel();
        txtSeguidor = new javax.swing.JTextField();
        txtSeguido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnDejarDeSeguir = new javax.swing.JButton();
        btnSeguir = new javax.swing.JButton();

        bg.setBackground(new java.awt.Color(0, 102, 153));

        jLabelSeguidor.setText("Nickname Seguidor");

        jLabelSeguido.setText("Nickname Seguido");

        txtSeguido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSeguidoActionPerformed(evt);
            }
        });

        jLabel3.setText("> > > > > >");

        btnDejarDeSeguir.setBackground(new java.awt.Color(255, 51, 51));
        btnDejarDeSeguir.setText("DEJAR DE SEGUIR");
        btnDejarDeSeguir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDejarDeSeguirActionPerformed(evt);
            }
        });

        btnSeguir.setBackground(new java.awt.Color(0, 153, 0));
        btnSeguir.setText("SEGUIR");
        btnSeguir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeguirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabelSeguidor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelSeguido)
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSeguidor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(txtSeguido, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnSeguir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDejarDeSeguir, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSeguido)
                    .addComponent(jLabelSeguidor))
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSeguidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSeguido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeguir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDejarDeSeguir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSeguidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSeguidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSeguidoActionPerformed

    private void btnSeguirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeguirActionPerformed
        // TODO add your handling code here:
        String nicknameSeguidor = txtSeguidor.getText();
        String nicknameSeguido = txtSeguido.getText();
        try {
             //control.seguirUsuario(nicknameSeguidor,nicknameSeguido);
         } catch (Exception ex) {
             Logger.getLogger(SeguirUsuario.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_btnSeguirActionPerformed

    private void btnDejarDeSeguirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDejarDeSeguirActionPerformed
        // TODO add your handling code here:
        String nicknameSeguidor = txtSeguidor.getText();
        String nicknameSeguido = txtSeguido.getText();
        try {
           //  control.dejarDeSeguirUsuario(nicknameSeguidor,nicknameSeguido);
         } catch (Exception ex) {
             Logger.getLogger(SeguirUsuario.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_btnDejarDeSeguirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnDejarDeSeguir;
    private javax.swing.JButton btnSeguir;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelSeguido;
    private javax.swing.JLabel jLabelSeguidor;
    private javax.swing.JTextField txtSeguido;
    private javax.swing.JTextField txtSeguidor;
    // End of variables declaration//GEN-END:variables
}
