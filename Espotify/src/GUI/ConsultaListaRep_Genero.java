/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Datatypes.DTListaRep;
import Datatypes.DTTema;
import Logica.Controlador;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author ivan
 */
public class ConsultaListaRep_Genero extends javax.swing.JInternalFrame {

    Controlador control= new Controlador();
    
    public ConsultaListaRep_Genero() {
        initComponents();
        configurarEnlace();
        Enlace.setText("Abrir enlace");
        Enlace.setForeground(Color.BLUE); // Establecer color del texto
        Enlace.setFont(new Font("SansSerif", Font.PLAIN, 12)); // Establecer fuente del texto
        Enlace.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        Enlace.setVisible(false);

    
    // Código existente para agregar Enlace al layout

    Enlace.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Lógica del clic
        }
    });
        
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
                // Obtiene el género seleccionado
                String selectedGenero = selectedNode.getUserObject().toString();
                
                // Llama a la operación del controlador para obtener la lista basada en el género
                List<DTListaRep> lista = control.obtenerDTListaPorGenero(selectedGenero);
                
                // Convertir la lista de DTListaRep en un array de Strings para el JList
                String[] listaArray = lista.stream()
                                           .map(DTListaRep::getNombreListaRep) // Obtener solo el nombre de la lista
                                           .toArray(String[]::new);

                // Actualiza el JList con la nueva información
                jList1.setListData(listaArray);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        MuestraLista = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        DatosDeLista = new javax.swing.JList<>();
        Enlace = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jTree1);

        MuestraLista.setText("Mostrar lista");
        MuestraLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MuestraListaActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jList1);

        jScrollPane3.setViewportView(DatosDeLista);

        Enlace.setText("Enlace al tema");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(MuestraLista)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Enlace)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(131, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(MuestraLista))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(Enlace)))))
                .addContainerGap(134, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MuestraListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MuestraListaActionPerformed
        String nombreSeleccionado = jList1.getSelectedValue();
        
        if (nombreSeleccionado != null) {
            try {
                DTListaRep dtListaRep = control.obtenerDatosDeLista(nombreSeleccionado);
                
                if (dtListaRep != null) {
                    List<String> datosTemas = new ArrayList<>();
                    
                    for (DTTema tema : dtListaRep.getTemas()) {
                        datosTemas.add(String.format("%s - %d:%d",
                            tema.getNombre(), 
                            tema.getMinutos(), 
                            tema.getSegundos()));
                    }
                    
                    DatosDeLista.setListData(datosTemas.toArray(new String[0]));

                    String enlace = dtListaRep.getTemas().get(0).getDirectorio();
                    if (!enlace.startsWith("http://") && !enlace.startsWith("https://")) {
                        enlace = "http://" + enlace; // Asegúrate de que el enlace tenga un esquema
                    }
                    
                    // Almacenar el enlace para usarlo en el MouseListener
                    Enlace.putClientProperty("directorio", enlace);
                    
                    // Mostrar el Enlace
                    Enlace.setVisible(true);
                } else {
                    System.out.println("No se encontraron datos para la lista seleccionada.");
                    
                    // Ocultar el Enlace
                    Enlace.setVisible(false);
                }
            } catch (Exception ex) {
                Logger.getLogger(ConsultaListaRep_Genero.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("No se ha seleccionado ninguna lista.");
            
            // Ocultar el Enlace
            Enlace.setVisible(false);
        }
    }//GEN-LAST:event_MuestraListaActionPerformed

private void configurarEnlace() {
    Enlace.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            String enlace = (String) Enlace.getClientProperty("directorio");
            if (enlace != null) {
                try {
                    java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                    if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                        desktop.browse(new java.net.URI(enlace));
                    } else {
                        System.out.println("Acción de navegación no soportada.");
                    }
                } catch (java.net.URISyntaxException ex) {
                    Logger.getLogger(ConsultaListaRep_Genero.class.getName()).log(Level.SEVERE, "URL mal formada: " + enlace, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ConsultaListaRep_Genero.class.getName()).log(Level.SEVERE, "Error al abrir el enlace: " + enlace, ex);
                }
            } else {
                System.out.println("No hay enlace asignado.");
            }
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> DatosDeLista;
    private javax.swing.JLabel Enlace;
    private javax.swing.JButton MuestraLista;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
