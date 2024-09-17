
package GUI;

import Datatypes.DTCliente;
import Logica.Controlador;
import java.util.List;
import javax.swing.DefaultListModel;

//NOMBRES DE LISTAS DE REP QUE CREO EL CLIENTE / TODOS SUS FAVORITOS
public class ConsultaPerfilCliente extends javax.swing.JInternalFrame {

    
    public ConsultaPerfilCliente() {
        initComponents();
        Controlador controlador= new Controlador();
        for (String n:controlador.nicksClientes()){
            comboNicknames.addItem(n);//lleno combobox con nicknames
        }
        //hago que no se vean los textos
        textoMostrarNick.setVisible(false);
        textoMostrarNombre.setVisible(false);
        textoMostrarApellido.setVisible(false);
        textoMostrarCorreo.setVisible(false);
        textoMostrarNacimiento.setVisible(false);
        
        textoSeguidores.setVisible(false);
        textoSeguidos.setVisible(false);
        textoListas.setVisible(false);
        listaSeguidores.setVisible(false);
        listaSeguidos.setVisible(false);
        listaListas.setVisible(false);

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
        textoMostrarNick = new javax.swing.JLabel();
        textoMostrarNombre = new javax.swing.JLabel();
        textoMostrarApellido = new javax.swing.JLabel();
        textoMostrarCorreo = new javax.swing.JLabel();
        textoMostrarNacimiento = new javax.swing.JLabel();
        comboNicknames = new javax.swing.JComboBox<>();
        textoSeguidores = new javax.swing.JLabel();
        textoSeguidos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaSeguidores = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaSeguidos = new javax.swing.JList<>();
        textoListas = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaListas = new javax.swing.JList<>();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("Consulta de perfil de cliente");

        jLabel2.setText("Seleccione el cliente a mostrar:");

        jLabel3.setText("Nickname:");

        jLabel4.setText("Nombre:");

        jLabel5.setText("Apellido:");

        jLabel6.setText("Correo:");

        jLabel7.setText("Fecha de nacimiento:");

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

        textoSeguidores.setText("Seguidores");

        textoSeguidos.setText("Seguidos");

        jScrollPane1.setViewportView(listaSeguidores);

        jScrollPane2.setViewportView(listaSeguidos);

        textoListas.setText("Listas de Reproducción creadas:");

        jScrollPane3.setViewportView(listaListas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoMostrarCorreo)
                                    .addComponent(textoMostrarApellido)
                                    .addComponent(textoMostrarNombre)
                                    .addComponent(textoMostrarNick))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(textoSeguidores)
                                        .addGap(61, 61, 61)
                                        .addComponent(textoSeguidos))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textoMostrarNacimiento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoListas)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(83, 83, 83))
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
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoSeguidores)
                    .addComponent(textoSeguidos)
                    .addComponent(textoListas))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
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
                                    .addComponent(textoMostrarCorreo)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(textoMostrarNacimiento)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//ItemStateChanged
    private void comboNicknamesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboNicknamesItemStateChanged
        Controlador controlador= new Controlador();
        DTCliente cliente = controlador.encontrarClientePorNickname((String) comboNicknames.getSelectedItem());
        DefaultListModel modelSeguidores = new DefaultListModel();
        DefaultListModel modelSeguidos = new DefaultListModel();
        DefaultListModel modelListas = new DefaultListModel();
        modelSeguidores.removeAllElements();
        modelSeguidos.removeAllElements();
        modelListas.removeAllElements();
        
        //cargo datos
        textoMostrarNick.setText(cliente.getNickname());
        textoMostrarNombre.setText(cliente.getNombre());
        textoMostrarApellido.setText(cliente.getApellido());
        textoMostrarCorreo.setText(cliente.getCorreo());
        String fecha = cliente.getFechaNac().getDate() + "/" + (cliente.getFechaNac().getMonth() +1) + "/" + (cliente.getFechaNac().getYear() + 1900);
        textoMostrarNacimiento.setText(fecha); //hay q sumarle 1 a mes y 1900 al año
        //hago visible
        textoMostrarNick.setVisible(true);
        textoMostrarNombre.setVisible(true);
        textoMostrarApellido.setVisible(true);
        textoMostrarCorreo.setVisible(true);
        textoMostrarNacimiento.setVisible(true);
        
        //Seguidos y seguidores       
        for(String s:controlador.seguidoresDelCliente((String) comboNicknames.getSelectedItem())){
            modelSeguidores.addElement(s);
        }
        listaSeguidores.setModel(modelSeguidores);
        //hacer int
        int cantidadSeguidores = controlador.seguidoresDelCliente((String) comboNicknames.getSelectedItem()).size();
        textoSeguidores.setText(cantidadSeguidores + " Seguidores");
        
        modelSeguidos.addElement("  Clientes:  ");
        for(String s:controlador.clientesSeguidosDelCliente((String) comboNicknames.getSelectedItem())){
            modelSeguidos.addElement(s);
        }
        modelSeguidos.addElement("   ");
        modelSeguidos.addElement("  Artistas:  "); 
        listaSeguidos.setModel(modelSeguidos);
        for(String s:controlador.artistasSeguidosDelCliente((String) comboNicknames.getSelectedItem())){
            modelSeguidos.addElement(s);
        }
        //Listas de rep creadas
        for(String s:controlador.nombresListaRepDeCliente((String) comboNicknames.getSelectedItem())){
            modelListas.addElement(s);
        }
        listaListas.setModel(modelListas);
        
        textoSeguidores.setVisible(true);
        textoSeguidos.setVisible(true);
        textoListas.setVisible(true);
        listaSeguidores.setVisible(true);
        listaSeguidos.setVisible(true);
        listaListas.setVisible(true);

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listaListas;
    private javax.swing.JList<String> listaSeguidores;
    private javax.swing.JList<String> listaSeguidos;
    private javax.swing.JLabel textoListas;
    private javax.swing.JLabel textoMostrarApellido;
    private javax.swing.JLabel textoMostrarCorreo;
    private javax.swing.JLabel textoMostrarNacimiento;
    private javax.swing.JLabel textoMostrarNick;
    private javax.swing.JLabel textoMostrarNombre;
    private javax.swing.JLabel textoSeguidores;
    private javax.swing.JLabel textoSeguidos;
    // End of variables declaration//GEN-END:variables
}
