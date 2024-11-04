/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Datatypes.DTAlbum;
import Datatypes.DTTema;
import Logica.Fabrica;
import Logica.IControlador;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;


public class AltaAlbum_FlujoArtista extends javax.swing.JInternalFrame {
private List<String> selectedNodesList = new ArrayList<>();

    private String correo;
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    Fabrica fabrica = Fabrica.getInstance();
    IControlador control = fabrica.getIControlador();
    
    public AltaAlbum_FlujoArtista() {
        initComponents();
        setTitle("Crear Album");
        anioAlbum.setValue(2000);
        jLabel4.setVisible(true);//no visible imagen 
        txtImagen.setVisible(true);//hace invisible la opcion de poner imagen
        
       
        jTree1.setModel(control.buildGeneroTree());
        
        

// Agrega un TreeSelectionListener al JTree
    jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);

    // Agrega un TreeSelectionListener al JTree
    jTree1.addTreeSelectionListener(new TreeSelectionListener() {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            // Obtiene los nodos seleccionados
            TreePath[] paths = jTree1.getSelectionPaths();
            if (paths != null) {
                // Limpia la lista de nodos seleccionados
                selectedNodesList.clear();
                for (TreePath path : paths) {
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                    String selectedNodeContent = selectedNode.getUserObject().toString();
                    selectedNodesList.add(selectedNodeContent);
                }
            }
        }
    });
        }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreAlbum = new javax.swing.JTextField();
        txtImagen = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        DuracionTema = new javax.swing.JSpinner();
        jButton2 = new javax.swing.JButton();
        txtNombreTema = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDIR = new javax.swing.JTextField();
        anioAlbum = new javax.swing.JSpinner();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnImgAlbum = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        jLabel1.setText("Ingrese nombre del album");

        jLabel3.setText("Ingrese Fecha de creacion");

        jLabel4.setText("Ingrese imagen");

        txtNombreAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreAlbumActionPerformed(evt);
            }
        });

        jLabel6.setText("Seleccione generos");

        jScrollPane1.setViewportView(jTree1);

        jLabel2.setText("Nombre del tema ");

        DuracionTema.setModel(new SpinnerDateModel(new Date(0), null, null, Calendar.SECOND));

        jButton2.setText("Confirmar Album");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setText("Duracion");

        jLabel9.setText("URL del tema o seleccione archivo");

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel10.setText("Alta de Album");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabel5.setText("Temas del Album");

        jButton4.setText("Agregar archivo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("Agregar Tema");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnImgAlbum.setText("Seleccionar");
        btnImgAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImgAlbumActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnImgAlbum))
                                            .addComponent(anioAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombreAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(189, 189, 189)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(199, 199, 199)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(139, 139, 139)
                                .addComponent(jButton3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNombreTema, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(txtDIR, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addComponent(jLabel8)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(DuracionTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombreAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(anioAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnImgAlbum))))
                        .addGap(30, 30, 30)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DuracionTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDIR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton4)))
                        .addGap(64, 64, 64))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(DuracionTema, "mm:ss");
        DuracionTema.setEditor(timeEditor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 242, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreAlbumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreAlbumActionPerformed
private List<DTTema> listaTemas = new ArrayList<>();
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        String nombreTema = txtNombreTema.getText();
        //si hay repetido es true y no se puede, si no hay repetido es false y si se puede
        boolean hayRepetido = false; //para comprobar si hay algun tema que sea igual al q quiero poner
        for(DTTema t:listaTemas){
            if(t.getNombre().equals(nombreTema)){
                hayRepetido = true;// si hay un tema que se llama como el nuevo tema cambia a true
            }
        }
        
        if ((nombreTema.equals("")) || hayRepetido == true){//si ingresan un nombre vacio o repetido
            javax.swing.JOptionPane.showMessageDialog(this, "El nombre del tema no puede ser vacio o igual a otro en el album", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }else{
            Date duracion = (Date) DuracionTema.getValue();
            String directorio = txtDIR.getText();
            // Convertir la duración a un formato más manejable (por ejemplo, minutos y segundos)
            Calendar cal = Calendar.getInstance();
            cal.setTime(duracion);
            int minutos = cal.get(Calendar.MINUTE);
            int segundos = cal.get(Calendar.SECOND);

            // Crear un nuevo tema y agregarlo a la lista
            DTTema nuevoTema = new DTTema(nombreTema, minutos, segundos,directorio);
            listaTemas.add(nuevoTema);

            // Limpiar los campos de texto para el siguiente tema
            txtNombreTema.setText("");
            txtDIR.setText("");
            DuracionTema.setValue(new Date(0));

            JOptionPane.showMessageDialog(null, "Tema agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Tema agregado: " + nombreTema + " (" + minutos + ":" + segundos + ")");
        }
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
try {
        String nombreAlbum = txtNombreAlbum.getText();
        int fechaCreacion = (int) anioAlbum.getValue();
        String imagen = txtImagen.getText();

        if (selectedNodesList.isEmpty()) {
            throw new IllegalArgumentException("La lista de géneros seleccionados no puede estar vacía.");
        }
        List<String> generosSeleccionados = new ArrayList<>(selectedNodesList);

        // Crear un objeto Album con todos los datos
        if (!imagen.isEmpty()) {
        byte[] archivoImagen = Files.readAllBytes(Paths.get(imagen));
        imagen = control.guardarImagenesAlbum(archivoImagen, nombreAlbum, correo);
        }
        DTAlbum nuevoAlbum = new DTAlbum(nombreAlbum, fechaCreacion, imagen, generosSeleccionados);

    
            // Llamar al controlador para guardar el álbum
            List<DTTema> listaTemasAlbum = new ArrayList<>();
            for(DTTema t : listaTemas){
                if (Files.exists(Paths.get(t.getDirectorio()))) {
                    byte[] archivoTema = Files.readAllBytes(Paths.get(t.getDirectorio()));
                    String ruta= control.guardarTemaEnCarpeta(archivoTema, nuevoAlbum.getNombre()+" - "+t.getNombre());
                    DTTema nuevoTema = new DTTema(t.getNombre(), t.getMinutos(), t.getSegundos(),ruta);
                    listaTemasAlbum.add(nuevoTema);
                }else{
                    listaTemasAlbum.add(t);
                }
            }
            control.CrearAlbum(correo, nuevoAlbum, listaTemasAlbum);
            JOptionPane.showMessageDialog(null, "Álbum creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
    } finally {
        limpiarFormulario();
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
           javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();

    // Establecer el modo de selección de archivos
    fileChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);

    // Mostrar el cuadro de diálogo para la selección de archivos
    int returnValue = fileChooser.showOpenDialog(this);

    // Si el usuario selecciona un archivo
    if (returnValue == javax.swing.JFileChooser.APPROVE_OPTION) {
        // Obtener la ruta del archivo seleccionado
        java.io.File selectedFile = fileChooser.getSelectedFile();
        String filePath = selectedFile.getAbsolutePath();

        // Mostrar la ruta en el campo de texto o guardarla
        txtDIR.setText(filePath);
    }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnImgAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImgAlbumActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Imagen de Album");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg"));
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();

            String extension = control.obtenerExtensionArchivo(archivoSeleccionado.getName());
            if (extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg")) {
                ImageIcon icono = new ImageIcon(archivoSeleccionado.getAbsolutePath());
                Image imagen = icono.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); //escalar la imagen para mostrarla en JLabel8
                jLabel7.setIcon(new ImageIcon(imagen));

                txtImagen.setText(archivoSeleccionado.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un archivo de imagen válido (PNG, JPG, JPEG).", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnImgAlbumActionPerformed
private void limpiarFormulario() {
        // Limpiar campos de texto
    txtNombreAlbum.setText("");
    txtImagen.setText("");
    txtNombreTema.setText("");
    txtDIR.setText("");
    
    // Restablecer JSpinners a sus valores por defecto
    anioAlbum.setValue(2000); // O el valor por defecto que desees
    DuracionTema.setValue(new Date(0)); // Valor por defecto para duración (00:00)
    
    // Limpiar lista de temas
    listaTemas.clear();
    
    // Limpiar selección en el JTree
    jTree1.clearSelection();
    
    // Limpiar la lista de géneros seleccionados
    selectedNodesList.clear();
   
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner DuracionTema;
    private javax.swing.JSpinner anioAlbum;
    private javax.swing.JButton btnImgAlbum;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTree jTree1;
    private javax.swing.JTextField txtDIR;
    private javax.swing.JTextField txtImagen;
    private javax.swing.JTextField txtNombreAlbum;
    private javax.swing.JTextField txtNombreTema;
    // End of variables declaration//GEN-END:variables
}
