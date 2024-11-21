
package GUI;

import Datatypes.DTArtista;
import Logica.Fabrica;
import Logica.IControlador;


public class VerArtistasEliminados extends javax.swing.JInternalFrame {
    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();
 
    public VerArtistasEliminados() {
        initComponents();
        comboArtistasE.addItem("...");
        for (String art : control.listaArtistasEliminados()){
            comboArtistasE.addItem(art);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboArtistasE = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        textoFechaE = new javax.swing.JLabel();
        textoNick = new javax.swing.JLabel();
        textoMail = new javax.swing.JLabel();
        textoNombre = new javax.swing.JLabel();
        textoApe = new javax.swing.JLabel();
        textoFechaNac = new javax.swing.JLabel();
        textoWeb = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textoBio = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textoAlbumes = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        textoTemas = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("Ver Artistas Eliminados");

        jLabel2.setText("Seleccione el artista eliminado a visualizar: ");

        comboArtistasE.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboArtistasEItemStateChanged(evt);
            }
        });

        jLabel3.setText("Nickname:");

        jLabel4.setText("Mail: ");

        jLabel5.setText("Nombre:");

        jLabel6.setText("Apellido:");

        jLabel7.setText("Fecha de nacimiento:");

        jLabel8.setText("Sitio Web:");

        jLabel9.setText("Biografía:");

        jLabel10.setText("Fecha de eliminación:");

        textoFechaE.setText("----");

        textoNick.setText("----");

        textoMail.setText("----");

        textoNombre.setText("----");

        textoApe.setText("----");

        textoFechaNac.setText("----");

        textoWeb.setText("----");

        textoBio.setColumns(20);
        textoBio.setRows(5);
        jScrollPane1.setViewportView(textoBio);

        jLabel18.setText("Albumes:");

        jLabel19.setText("Temas:");

        textoAlbumes.setColumns(20);
        textoAlbumes.setRows(5);
        jScrollPane2.setViewportView(textoAlbumes);

        textoTemas.setColumns(20);
        textoTemas.setRows(5);
        jScrollPane3.setViewportView(textoTemas);

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
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(comboArtistasE, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(textoWeb)
                            .addComponent(textoFechaNac)
                            .addComponent(textoApe)
                            .addComponent(textoNombre)
                            .addComponent(textoMail)
                            .addComponent(textoNick)
                            .addComponent(textoFechaE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(56, 56, 56)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel19))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(46, 46, 46))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboArtistasE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(textoNombre))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(textoApe))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(textoFechaNac))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(textoWeb)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(textoFechaE)
                                    .addComponent(jLabel18))
                                .addGap(18, 18, 18)
                                .addComponent(textoNick)
                                .addGap(18, 18, 18)
                                .addComponent(textoMail))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(73, 73, 73)
                                .addComponent(jLabel9)))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboArtistasEItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboArtistasEItemStateChanged
        if (!(comboArtistasE.getSelectedItem().equals("..."))){
            textosVisibles(true);
            textoBio.removeAll();
            textoBio.setText(" ");
            textoAlbumes.removeAll();
            textoAlbumes.setText(" ");
            textoTemas.removeAll();
            textoTemas.setText(" ");
            //cargo datos
            DTArtista a = control.artistaEliminadoSeleccionado((String)comboArtistasE.getSelectedItem());
            textoNick.setText(a.getNickname());
            textoNombre.setText(a.getNombre());
            textoApe.setText(a.getApellido());
            textoMail.setText(a.getCorreo());
            textoBio.setText(a.getBiografia());
            textoWeb.setText(a.getSitioWeb());
            String fecha = a.getFechaNac().getDate() + "/" + (a.getFechaNac().getMonth() +1) + "/" + (a.getFechaNac().getYear() + 1900);
            textoFechaNac.setText(fecha);
            textoFechaE.setText(control.fechaEArtistaESel((String) comboArtistasE.getSelectedItem()));
            textoAlbumes.setText(control.albumesArtistaElimSel((String)comboArtistasE.getSelectedItem()));
            textoTemas.setText(control.temasArtistaElimSel((String)comboArtistasE.getSelectedItem()));
        }else{
            textosVisibles(false);
        }
        
        
    }//GEN-LAST:event_comboArtistasEItemStateChanged

    public void textosVisibles(Boolean b){
        textoBio.removeAll();
        textoBio.setText(" ");
        textoAlbumes.removeAll();
        textoAlbumes.setText(" ");
        textoTemas.removeAll();
        textoTemas.setText(" ");
        //limpio datos
        textoNick.setVisible(b);
        textoNombre.setVisible(b);
        textoApe.setVisible(b);
        textoMail.setVisible(b);
        textoBio.setVisible(b);
        textoWeb.setVisible(b);
        textoFechaNac.setVisible(b);
        textoFechaE.setVisible(b);
        textoAlbumes.setVisible(b);
        textoTemas.setVisible(b);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboArtistasE;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JTextArea textoAlbumes;
    private javax.swing.JLabel textoApe;
    private javax.swing.JTextArea textoBio;
    private javax.swing.JLabel textoFechaE;
    private javax.swing.JLabel textoFechaNac;
    private javax.swing.JLabel textoMail;
    private javax.swing.JLabel textoNick;
    private javax.swing.JLabel textoNombre;
    private javax.swing.JTextArea textoTemas;
    private javax.swing.JLabel textoWeb;
    // End of variables declaration//GEN-END:variables
}
