
package GUI;

import Logica.Cliente;
import Logica.Controlador;
import java.util.List;


public class ConsultaPerfilCliente extends javax.swing.JInternalFrame {

    
    public ConsultaPerfilCliente() {
        initComponents();
        Controlador controlador= new Controlador();
        List<Cliente> listaClientes = controlador.listaClientes(); //pido los clientes
        for (Cliente auxC:listaClientes){//lleno el combobox con los mails
            comboNicknames.addItem(auxC.getMail());
        }
        //hago que no se vean los textos
        textoMostrarNick.setVisible(false);
        textoMostrarNombre.setVisible(false);
        textoMostrarApellido.setVisible(false);
        textoMostrarCorreo.setVisible(false);
        textoMostrarNacimiento.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        textoMostrarNick = new javax.swing.JLabel();
        textoMostrarNombre = new javax.swing.JLabel();
        textoMostrarApellido = new javax.swing.JLabel();
        textoMostrarCorreo = new javax.swing.JLabel();
        textoMostrarNacimiento = new javax.swing.JLabel();
        comboNicknames = new javax.swing.JComboBox<>();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("Consulta de perfil de cliente");

        jLabel2.setText("Seleccione el nickname del cliente a mostrar:");

        jLabel3.setText("Nickname:");

        jLabel4.setText("Nombre:");

        jLabel5.setText("Apellido:");

        jLabel6.setText("Correo:");

        jLabel7.setText("Fecha de nacimiento:");

        jLabel8.setText("imagen / seguidos / seguidores / listas / favoritos");

        textoMostrarNick.setText("...");

        textoMostrarNombre.setText("...");

        textoMostrarApellido.setText("...");

        textoMostrarCorreo.setText("...");

        textoMostrarNacimiento.setText("...");

        comboNicknames.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboNicknamesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(comboNicknames, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoMostrarNacimiento)
                                    .addComponent(textoMostrarCorreo)
                                    .addComponent(textoMostrarApellido)
                                    .addComponent(textoMostrarNombre)
                                    .addComponent(textoMostrarNick)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboNicknames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textoMostrarNick))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textoMostrarNombre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textoMostrarApellido))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textoMostrarCorreo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textoMostrarNacimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//ItemStateChanged
    private void comboNicknamesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboNicknamesItemStateChanged
        Controlador controlador= new Controlador();//busco el cliente
        Cliente cliente = controlador.encontrarCliente((String) comboNicknames.getSelectedItem());
        //muestro datos
        textoMostrarNick.setText(cliente.getNickname());
        textoMostrarNombre.setText(cliente.getNombre());
        textoMostrarApellido.setText(cliente.getApellido());
        textoMostrarCorreo.setText(cliente.getMail());
        String fecha = cliente.getFechaNac().getDate() + "/" + (cliente.getFechaNac().getMonth() +1) + "/" + (cliente.getFechaNac().getYear() + 1900);
        textoMostrarNacimiento.setText(fecha); //hay q sumarle 1 a mes y 1900 al año
        //hago visible
        textoMostrarNick.setVisible(true);
        textoMostrarNombre.setVisible(true);
        textoMostrarApellido.setVisible(true);
        textoMostrarCorreo.setVisible(true);
        textoMostrarNacimiento.setVisible(true);

    }//GEN-LAST:event_comboNicknamesItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboNicknames;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel textoMostrarApellido;
    private javax.swing.JLabel textoMostrarCorreo;
    private javax.swing.JLabel textoMostrarNacimiento;
    private javax.swing.JLabel textoMostrarNick;
    private javax.swing.JLabel textoMostrarNombre;
    // End of variables declaration//GEN-END:variables
}
