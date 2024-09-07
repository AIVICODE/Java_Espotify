/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Logica.Album;
import Logica.Artista;
import Logica.Controlador;
import Logica.Genero;
import java.util.List;

/**
 *
 * @author camil
 */
public class ConsultaAlbum extends javax.swing.JInternalFrame {

    /**
     * Creates new form ConsultaAlbum
     */
    public ConsultaAlbum() {
        initComponents();
        txtG.setVisible(false);  //No aparece en la ventana principal hasta que el usuario seleccione genero o artista
        comboOpciones.setVisible(false); // Lo mismo con este
        txtAlbum.setVisible(false);
        comboOpcionesA.setVisible(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        txtG = new javax.swing.JLabel();
        comboOpciones = new javax.swing.JComboBox<>();
        txtAlbum = new javax.swing.JLabel();
        comboOpcionesA = new javax.swing.JComboBox<>();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("Consulta Álbum");

        jLabel2.setText("Seleccione la opción a consultar:");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Genero", "Artista" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        txtG.setText("texto");

        txtAlbum.setText("Albumes:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboOpcionesA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlbum)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtG)
                            .addComponent(comboOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(257, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(txtG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(txtAlbum)
                .addGap(18, 18, 18)
                .addComponent(comboOpcionesA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        if ("Genero".equals(jList1.getSelectedValue()) ){ //Si se selecciona Genero de la lista
            txtG.setText("Generos:"); //Se setea el texto con la palabra genero
            txtG.setVisible(true);   //Y se hace visible ya que antes no lo era   
            comboOpciones.removeAllItems(); 
            Controlador controlador= new Controlador();
             List<Genero> listaGeneros = controlador.listaGeneros(); //pido los Artistas
            for (Genero auxA:listaGeneros){//lleno el combobox con los mails
                 comboOpciones.addItem(auxA.getNombre());
            }
            comboOpciones.setVisible(true);
            txtAlbum.setVisible(true);
            comboOpcionesA.removeAllItems();
            List<Album> listaAlbumes = controlador.listaAlbumes(); //Obtengo la lista de todos los albumes del controlador
            for (Album auxA:listaAlbumes){//Recorro esa lista de albumbes
                 List<Genero> listaGenero = auxA.getListaGeneros();//A cada album de la lista anterior, le pido su lista de generos
                 Genero gen = controlador.findGenerobynombre((String)comboOpciones.getSelectedItem());//Guardo en una variable el genero que me selecciona el usuario en comboBox1
                 //Toca comparar si el genero seleccionado por el usuario, existe en la lista de generos del album actual
                 if (listaGenero.contains(gen)){
                    comboOpcionesA.addItem(auxA.getNombre());    
                 }
                 
            }
            comboOpcionesA.setVisible(true);
            
        }
        if ("Artista".equals(jList1.getSelectedValue()) ){
            txtG.setText("Artistas:"); //Lo mismo pero con Artista
            txtG.setVisible(true);
            comboOpciones.removeAllItems();
            Controlador controlador= new Controlador();
             List<Artista> listaArtistas = controlador.listaArtistas(); //pido los Artistas
            for (Artista auxA:listaArtistas){//lleno el combobox con los mails
                 comboOpciones.addItem(auxA.getNombre());
            }
            comboOpciones.setVisible(true);
            txtAlbum.setVisible(true);
            comboOpcionesA.removeAllItems();
            comboOpcionesA.setVisible(true);
            
        }
        
       
        
    }//GEN-LAST:event_jList1ValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboOpciones;
    private javax.swing.JComboBox<String> comboOpcionesA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txtAlbum;
    private javax.swing.JLabel txtG;
    // End of variables declaration//GEN-END:variables
}
