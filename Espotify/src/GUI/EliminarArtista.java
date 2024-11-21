
package GUI;

import Logica.Fabrica;
import Logica.IControlador;
import javax.swing.JOptionPane;


public class EliminarArtista extends javax.swing.JInternalFrame {
    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();
    public EliminarArtista() {
        initComponents();
        for (String n:control.nicknamesDeTodosLosArtistas()){//por cada string en la lista de nicks
            comboArtistas.addItem(n);//le agrego n q ya es un string del nickname
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboArtistas = new javax.swing.JComboBox<>();
        botonEliminar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("Eliminar Artista");

        jLabel2.setText("Seleccione el artista a eliminar");

        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboArtistas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(157, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(botonEliminar)
                .addGap(84, 84, 84))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(comboArtistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(botonEliminar)
                .addGap(86, 86, 86))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        control.eliminarArtista((String)comboArtistas.getSelectedItem());
        JOptionPane.showMessageDialog(null, "Artista eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        //Actualizo combo
        comboArtistas.removeAllItems();
        for (String n:control.nicknamesDeTodosLosArtistas()){//por cada string en la lista de nicks
            comboArtistas.addItem(n);//le agrego n q ya es un string del nickname
        }
    }//GEN-LAST:event_botonEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminar;
    private javax.swing.JComboBox<String> comboArtistas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
