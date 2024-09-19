
package GUI;

import Datatypes.DTCliente;
import Logica.Fabrica;
import Logica.IControlador;
//import Logica.Controlador;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PublicarLista extends javax.swing.JInternalFrame {
    Fabrica fabrica = Fabrica.getInstance();
    IControlador controlador = fabrica.getIControlador();
    public PublicarLista() {
        initComponents();
        //Controlador controlador= new Controlador();  
        List<DTCliente> listaClientesDT = controlador.listaClientesDT(); //pido los clientes
        for (DTCliente auxCd:listaClientesDT){//lleno el combobox con los nicks
            comboDT.addItem(auxCd.getNickname());
        }
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboListas = new javax.swing.JComboBox<>();
        comboDT = new javax.swing.JComboBox<>();
        cancelar = new javax.swing.JButton();
        publicarBoton = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("Publicar Lista");

        jLabel2.setText("Seleccione un Cliente:");

        jLabel3.setText("Seleccione una lista:");

        comboDT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboDTItemStateChanged(evt);
            }
        });

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        publicarBoton.setText("Hacer Publica");
        publicarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                publicarBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(comboListas, 0, 202, Short.MAX_VALUE)
                                .addComponent(comboDT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cancelar)
                                .addGap(18, 18, 18)
                                .addComponent(publicarBoton)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboListas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelar)
                    .addComponent(publicarBoton))
                .addContainerGap(109, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboDTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboDTItemStateChanged
        //Controlador controlador= new Controlador();        
        comboListas.removeAllItems();//para que no se sumen en el combobox si se seleccionan varios clientes        
        DTCliente cliente = controlador.encontrarClientePorNickname((String) comboDT.getSelectedItem());//busco cliente seleccionado      
        //Guardo en una lista string los nombres de las listas privadas del cliente
        List<String> listasPrivCliente = controlador.nombreDeListasPrivadasDeCliente(cliente.getCorreo());
        for(String nombre:listasPrivCliente){//por cada nombre en la lista de nombres de listas del cliente
            comboListas.addItem(nombre);
        }
    }//GEN-LAST:event_comboDTItemStateChanged

    private void publicarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_publicarBotonActionPerformed
        //Controlador controlador= new Controlador();
        try {
            controlador.publicarListaPrivada(((String)comboDT.getSelectedItem()), ((String)comboListas.getSelectedItem()));
                                                  JOptionPane.showMessageDialog(null, "Lista publicada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            Logger.getLogger(PublicarLista.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Lista publicada con exito!"); 
        comboListas.removeAllItems();//limpio el combo para no publicar dos veces la misma lista
    }//GEN-LAST:event_publicarBotonActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        this.dispose();//cierra la ventana 
    }//GEN-LAST:event_cancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JComboBox<String> comboDT;
    private javax.swing.JComboBox<String> comboListas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton publicarBoton;
    // End of variables declaration//GEN-END:variables
}
