
package GUI;

import Datatypes.DTArtista;
import Logica.Fabrica;
import Logica.IControlador;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;


public class ConsultaPerfilArtista extends javax.swing.JInternalFrame {
    Fabrica fabrica = Fabrica.getInstance();
    IControlador controlador = fabrica.getIControlador();
    public ConsultaPerfilArtista() {
        initComponents();
               
        for (String n:controlador.nicknamesDeTodosLosArtistas()){//por cada string en la lista de nicks
            comboNicks.addItem(n);//le agrego n q ya es un string del nickname
        }
               
        //hago que no se vean los textos
        textoMostrarNick.setVisible(false);
        textoMostrarNombre.setVisible(false);
        textoMostrarApellido.setVisible(false);
        textoMostrarCorreo.setVisible(false);
        textoMostrarNacimiento.setVisible(false);
        textoMostrarBio.removeAll();
        textoMostrarBio.setText(" ");
        textoMostrarBio.setVisible(false);
        textoMostrarWeb.setVisible(false);
        textoSeguidores.setVisible(false);
        listaSeguidores.setVisible(false);
        listaAlbumes.setVisible(false);
        
        textoMostrarWeb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                Desktop.getDesktop().browse(new URI(textoMostrarWeb.getText()));
         
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }    }
            });
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        comboNicks = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textoMostrarNick = new javax.swing.JLabel();
        textoMostrarNombre = new javax.swing.JLabel();
        textoMostrarApellido = new javax.swing.JLabel();
        textoMostrarCorreo = new javax.swing.JLabel();
        textoMostrarNacimiento = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textoMostrarBio = new javax.swing.JTextArea();
        textoSeguidores = new javax.swing.JLabel();
        textoAlbumes = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaSeguidores = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaAlbumes = new javax.swing.JList<>();
        textoMostrarWeb = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("Consulta de perfil de Artista");

        comboNicks.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboNicksItemStateChanged(evt);
            }
        });
        comboNicks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNicksActionPerformed(evt);
            }
        });

        jLabel8.setText("Seleccione el nickname del Artista a mostrar:");

        jLabel4.setText("Nombre:");

        jLabel5.setText("Apellido:");

        jLabel6.setText("Correo:");

        jLabel7.setText("Fecha de nacimiento:");

        jLabel3.setText("Nickname:");

        textoMostrarNick.setText("..");

        textoMostrarNombre.setText("..");

        textoMostrarApellido.setText("..");

        textoMostrarCorreo.setText("..");

        textoMostrarNacimiento.setText("..");

        jLabel9.setText("Biografía:");

        jLabel10.setText("Página Web:");

        textoMostrarBio.setColumns(20);
        textoMostrarBio.setRows(5);
        jScrollPane1.setViewportView(textoMostrarBio);

        textoSeguidores.setText("Seguidores:");

        textoAlbumes.setText("Albumes:");

        jScrollPane2.setViewportView(listaSeguidores);

        jScrollPane3.setViewportView(listaAlbumes);

        textoMostrarWeb.setText("jLabel2");

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textoMostrarNick)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textoSeguidores)
                                .addGap(109, 109, 109)
                                .addComponent(textoAlbumes)
                                .addGap(68, 68, 68))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoMostrarApellido)
                                    .addComponent(textoMostrarNombre)
                                    .addComponent(textoMostrarNacimiento)
                                    .addComponent(textoMostrarCorreo)
                                    .addComponent(textoMostrarWeb))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(comboNicks, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(95, 95, 95))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboNicks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textoMostrarNick)
                    .addComponent(textoSeguidores)
                    .addComponent(textoAlbumes))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
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
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(textoMostrarWeb))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jButton1)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboNicksItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboNicksItemStateChanged
        //Controlador controlador= new Controlador();      
        DTArtista artista = controlador.encontrarDTArtistaPorNickname((String) comboNicks.getSelectedItem());
        if(artista!=null){
            //muestro datos
            textoMostrarNick.setText(artista.getNickname());
            textoMostrarNombre.setText(artista.getNombre());
            textoMostrarApellido.setText(artista.getApellido());
            textoMostrarCorreo.setText(artista.getCorreo());
            String fecha = artista.getFechaNac().getDate() + "/" + (artista.getFechaNac().getMonth() +1) + "/" + (artista.getFechaNac().getYear() + 1900);
            textoMostrarNacimiento.setText(fecha); //hay q sumarle 1 a mes y 1900 al año
            textoMostrarBio.setText(artista.getBiografia());
            textoMostrarWeb.setText(artista.getSitioWeb());
            if(!(artista.getSitioWeb().startsWith("www.")) && !(artista.getSitioWeb().isEmpty())){//control para abrir el enlace
                textoMostrarWeb.setText("www." + artista.getSitioWeb());
            }
           
            //Seguidores lista
            int cantSeguidores = controlador.nicksClientesSiguenArtista((String) comboNicks.getSelectedItem()).size();
            DefaultListModel modelSeguidos = new DefaultListModel();
            for (String n:controlador.nicksClientesSiguenArtista((String) comboNicks.getSelectedItem())){
                modelSeguidos.addElement(n);
            }
            listaSeguidores.setModel(modelSeguidos);
            if(cantSeguidores == 1){
                textoSeguidores.setText(cantSeguidores + " Seguidor");
                listaSeguidores.setVisible(true);
            }
            if(cantSeguidores == 0){
                textoSeguidores.setText(cantSeguidores + " Seguidores");
                listaSeguidores.setVisible(false);
            }
            if(cantSeguidores > 1){
                textoSeguidores.setText(cantSeguidores + " Seguidores");
                listaSeguidores.setVisible(true);
            }
            
            //Albumes
            DefaultListModel modelAlbumes = new DefaultListModel();
            try {
                for(String s:controlador.listaAlbumesArtistaNick((String) comboNicks.getSelectedItem())){
                    modelAlbumes.addElement(s);//agrego album del artista a lista
                }
            } catch (Exception ex) {
                Logger.getLogger(ConsultaPerfilArtista.class.getName()).log(Level.SEVERE, null, ex);
            }
            listaAlbumes.setModel(modelAlbumes);
            
            //hago visible todo
            textoMostrarNick.setVisible(true);
            textoMostrarNombre.setVisible(true);
            textoMostrarApellido.setVisible(true);
            textoMostrarCorreo.setVisible(true);
            textoMostrarNacimiento.setVisible(true);
            textoMostrarBio.setVisible(true);
            textoMostrarWeb.setVisible(true); 
            textoSeguidores.setVisible(true);
            listaAlbumes.setVisible(true);
            
            //hyperlink
            textoMostrarWeb.setForeground(Color.BLUE.darker());//color link
            textoMostrarWeb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));        
                                   
        }        
    }//GEN-LAST:event_comboNicksItemStateChanged

    private void comboNicksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNicksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboNicksActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboNicks;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listaAlbumes;
    private javax.swing.JList<String> listaSeguidores;
    private javax.swing.JLabel textoAlbumes;
    private javax.swing.JLabel textoMostrarApellido;
    private javax.swing.JTextArea textoMostrarBio;
    private javax.swing.JLabel textoMostrarCorreo;
    private javax.swing.JLabel textoMostrarNacimiento;
    private javax.swing.JLabel textoMostrarNick;
    private javax.swing.JLabel textoMostrarNombre;
    private javax.swing.JLabel textoMostrarWeb;
    private javax.swing.JLabel textoSeguidores;
    // End of variables declaration//GEN-END:variables
}
