
package GUI;

import Datatypes.DTIngresoWeb;
import Logica.Fabrica;
import Logica.IControlador;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class VerRegistros extends javax.swing.JInternalFrame {
 Fabrica fabrica = Fabrica.getInstance();
        IControlador control = fabrica.getIControlador();
  
    public VerRegistros() {
        initComponents();
                cargarDatosEnTabla();

    }
private void cargarDatosEnTabla() {
        // Llamar al método que obtiene la lista de DTIngresoWeb
        List<DTIngresoWeb> registros = control.convertToDTIngresoWebList();

        // Obtener el modelo de la tabla y definir los nombres de las columnas
        DefaultTableModel model = new DefaultTableModel(new Object[]{"IP", "URL", "Navegador", "SO", "Fecha"}, 0);

        // Agregar cada registro a la tabla
        for (DTIngresoWeb registro : registros) {
            model.addRow(new Object[]{
                registro.getIp(),
                registro.getUrl(),
                registro.getBrowser(),
                registro.getSo(),
                registro.getFecha()
            });
        }

        // Establecer el modelo en la tabla
        jTable1.setModel(model);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
