
package GUI;

import Logica.Artista;
import Logica.Controlador;
import java.util.List;


public class ConsultaPerfilArtista extends javax.swing.JInternalFrame {

    public ConsultaPerfilArtista() {
        initComponents();
        Controlador controlador= new Controlador();
        List<Artista> listaArtistas = controlador.listaArtistas(); //pido los Artistas
        for (Artista auxA:listaArtistas){//lleno el combobox con los mails
            comboMails.addItem(auxA.getMail());
        }
        //hago que no se vean los textos
        textoMostrarNick.setVisible(false);
        textoMostrarNombre.setVisible(false);
        textoMostrarApellido.setVisible(false);
        textoMostrarCorreo.setVisible(false);
        textoMostrarNacimiento.setVisible(false);
        textoMostrarBio.setVisible(false);
        textoMostrarWeb.setVisible(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        comboMails = new javax.swing.JComboBox<>();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        textoMostrarBio = new javax.swing.JLabel();
        textoMostrarWeb = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("Consulta de perfil de Artista");

        comboMails.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboMailsItemStateChanged(evt);
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

        jLabel2.setText("Imagen / seguidores nicknames / albumes");

        jLabel9.setText("Biografía:");

        jLabel10.setText("Página Web:");

        textoMostrarBio.setText("...");

        textoMostrarWeb.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(comboMails, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoMostrarNacimiento)
                                    .addComponent(textoMostrarCorreo)
                                    .addComponent(textoMostrarApellido)
                                    .addComponent(textoMostrarNombre)
                                    .addComponent(textoMostrarNick)
                                    .addComponent(textoMostrarBio)
                                    .addComponent(textoMostrarWeb)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboMails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(textoMostrarBio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(textoMostrarWeb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboMailsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboMailsItemStateChanged
        Controlador controlador= new Controlador();//busco el cliente
        Artista artista = controlador.encontrarArtista((String) comboMails.getSelectedItem());
        //muestro datos
        textoMostrarNick.setText(artista.getNickname());
        textoMostrarNombre.setText(artista.getNombre());
        textoMostrarApellido.setText(artista.getApellido());
        textoMostrarCorreo.setText(artista.getMail());
        String fecha = artista.getFechaNac().getDate() + "/" + (artista.getFechaNac().getMonth() +1) + "/" + (artista.getFechaNac().getYear() + 1900);
        textoMostrarNacimiento.setText(fecha); //hay q sumarle 1 a mes y 1900 al año
        textoMostrarBio.setText(artista.getBiografia());
        textoMostrarWeb.setText(artista.getSitioWeb());
        
        //hago visible
        textoMostrarNick.setVisible(true);
        textoMostrarNombre.setVisible(true);
        textoMostrarApellido.setVisible(true);
        textoMostrarCorreo.setVisible(true);
        textoMostrarNacimiento.setVisible(true);
        textoMostrarBio.setVisible(true);
        textoMostrarWeb.setVisible(true);
    }//GEN-LAST:event_comboMailsItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboMails;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel textoMostrarApellido;
    private javax.swing.JLabel textoMostrarBio;
    private javax.swing.JLabel textoMostrarCorreo;
    private javax.swing.JLabel textoMostrarNacimiento;
    private javax.swing.JLabel textoMostrarNick;
    private javax.swing.JLabel textoMostrarNombre;
    private javax.swing.JLabel textoMostrarWeb;
    // End of variables declaration//GEN-END:variables
}
