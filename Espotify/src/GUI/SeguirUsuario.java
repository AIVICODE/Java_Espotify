/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;


import Logica.Controlador;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author topo
 */
public class SeguirUsuario extends javax.swing.JInternalFrame {

    Controlador control= new Controlador();
    public SeguirUsuario() {
initComponents();
        setTitle("Seguir usuario");
        cargaComboSeguidor();
        cargaComboSeguido();

        // Listeners para actualizar el nickname y correo
        comboSeguidor.addActionListener(evt -> actualizarNicknameSeguidor());
//        comboSeguido.addActionListener(evt -> actualizarNicknameSeguido());

        txtSeguidor.setVisible(false);
        txtSeguido.setVisible(false);
    }
    private void cargaComboSeguidor() {
        comboSeguidor.removeAllItems();
        List<String> correosClientes = control.MostrarNombreClientes();
        for (String correo : correosClientes) {
            comboSeguidor.addItem(correo);
        }
    }

    private void cargaComboSeguido() {
        comboSeguido.removeAllItems();
        List<String> correosClientes = control.MostrarNombreClientes();
        List<String> correosArtistas = control.MostrarNombreArtistas();

        for (String correo : correosClientes) {
            comboSeguido.addItem(correo);
        }
        for (String correo : correosArtistas) {
            comboSeguido.addItem(correo);
        }
    }
  private void actualizarNicknameSeguidor() {
        String correoSeguidor = (String) comboSeguidor.getSelectedItem();
        if (correoSeguidor != null) {
            String nicknameSeguidor = control.encontrarNicknameCliente(correoSeguidor);
            txtSeguidor.setText(nicknameSeguidor != null ? nicknameSeguidor : "Desconocido");
            txtSeguidor.setVisible(true);
        }
    }

//    private void actualizarNicknameSeguido() {
//        String correoSeguido = (String) comboSeguido.getSelectedItem();
//        if (correoSeguido != null) {
//            String nicknameSeguido = control.encontrarNicknameCliente(correoSeguido);
//            if (nicknameSeguido == null) {
//                nicknameSeguido = control.encontrarNicknameArtista(correoSeguido);
//            }
//            txtSeguido.setText(nicknameSeguido != null ? nicknameSeguido : "Desconocido");
//            txtSeguido.setVisible(true);
//        }
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        label_Seguidor = new javax.swing.JLabel();
        label_Seguido = new javax.swing.JLabel();
        comboSeguidor = new javax.swing.JComboBox<>();
        comboSeguido = new javax.swing.JComboBox<>();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtSeguidor = new javax.swing.JLabel();
        txtSeguido = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        label_Seguidor.setText("Seguidor");

        label_Seguido.setText("Seguido");

        comboSeguidor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboSeguidorItemStateChanged(evt);
            }
        });
        comboSeguidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSeguidorActionPerformed(evt);
            }
        });

        comboSeguido.setModel(new javax.swing.DefaultComboBoxModel<>());
        comboSeguido.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboSeguidoItemStateChanged(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nickname");

        txtSeguidor.setText("...");

        txtSeguido.setText("...");

        jLabel3.setText("Nickname");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setText("Seguir usuario");

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtSeguidor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSeguido)
                    .addComponent(jLabel3))
                .addGap(134, 134, 134))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(label_Seguidor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_Seguido)
                .addGap(85, 85, 85))
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addComponent(comboSeguidor, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboSeguido, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(btnCancelar)
                        .addGap(44, 44, 44)
                        .addComponent(btnAceptar)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Seguido)
                    .addComponent(label_Seguidor))
                .addGap(18, 18, 18)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSeguidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSeguido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSeguidor)
                    .addComponent(txtSeguido))
                .addGap(31, 31, 31)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboSeguidorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboSeguidorItemStateChanged

    }//GEN-LAST:event_comboSeguidorItemStateChanged

    private void comboSeguidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSeguidorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSeguidorActionPerformed

    private void comboSeguidoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboSeguidoItemStateChanged
        
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            actualizarNicknameSeguidor();
        }
    }//GEN-LAST:event_comboSeguidoItemStateChanged

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        String mailSeguidor = (String) comboSeguidor.getSelectedItem();
        String mailSeguido = (String) comboSeguido.getSelectedItem();
        try {
            control.seguirUsuario(mailSeguidor, mailSeguido);
            JOptionPane.showMessageDialog(this, "Ahora " + txtSeguidor.getText() + " sigue al usuario " + txtSeguido.getText() + " ", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> comboSeguido;
    private javax.swing.JComboBox<String> comboSeguidor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel label_Seguido;
    private javax.swing.JLabel label_Seguidor;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JLabel txtSeguido;
    private javax.swing.JLabel txtSeguidor;
    // End of variables declaration//GEN-END:variables
}
