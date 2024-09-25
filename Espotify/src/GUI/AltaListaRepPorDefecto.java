/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Datatypes.DTListaRep;
import Logica.Fabrica;
import Logica.IControlador;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author ivan
 */
public class AltaListaRepPorDefecto extends javax.swing.JInternalFrame {

    /**
     * Creates new form AltaListaRepPorDefecto
     */
    Fabrica fabrica = Fabrica.getInstance();
        IControlador control = fabrica.getIControlador();
        private String selectedGenero = null;
    private String imagenSeleccionada;
    public AltaListaRepPorDefecto() {
        initComponents();
 jTree1.setModel(control.buildGeneroTree());

        // Establece el modo de selección única
        jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        // Añade un TreeSelectionListener para manejar la selección de un nodo
        jTree1.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                // Obtiene el nodo seleccionado
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();

                if (selectedNode != null) {
                    // Asigna el género seleccionado a la variable selectedGenero
                    selectedGenero = selectedNode.getUserObject().toString();
                    System.out.println("Género seleccionado: " + selectedGenero);
                } else {
                    System.out.println("No se ha seleccionado ningún género.");
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombreLista = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ButtonChosser = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jLabel4 = new javax.swing.JLabel();

        jLabel1.setText("Nombre Lista");

        jLabel2.setText("Seleccione imagen");

        ButtonChosser.setText("Seleccionar");
        ButtonChosser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonChosserActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Agregar Lista");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Lista por defecto");

        jScrollPane1.setViewportView(jTree1);

        jLabel4.setText("Seleccione un genero");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(62, 62, 62))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ButtonChosser, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(92, 92, 92)
                                                .addComponent(txtNombreLista, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(jLabel3)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ButtonChosser))
                .addGap(28, 28, 28)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonChosserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonChosserActionPerformed
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            imagenSeleccionada = file.getAbsolutePath(); // Guardar la ruta de la imagen seleccionada
        }
    }//GEN-LAST:event_ButtonChosserActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         String nombreLista = txtNombreLista.getText();
        
        // Verificar si se ha seleccionado un género
        if (selectedGenero == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un género.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Verificar si el nombre de la lista está vacío
        if (nombreLista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un nombre para la lista.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Aquí puedes utilizar el controlador para agregar la lista con el género seleccionado
        try {
            control.CrearListaRepGeneral(nombreLista, imagenSeleccionada,selectedGenero);
            JOptionPane.showMessageDialog(this, "Lista por defecto agregada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar la lista: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonChosser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JTextField txtNombreLista;
    // End of variables declaration//GEN-END:variables
}
