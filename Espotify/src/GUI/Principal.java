/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.awt.Container;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author ivan
 */
public class Principal extends javax.swing.JFrame {

    static void getdesktopPane() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Principal() {
        initComponents();
        
        // Configura el tamaño deseado
        setSize(800, 600); // Ajusta el tamaño según tus necesidades

        // Centra la ventana en la pantalla
        setLocationRelativeTo(null); // Esto centra la ventana en la pantalla
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu6 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem32 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        desktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        cargarDatosMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        AltaAlbum = new javax.swing.JMenuItem();
        jMenu14 = new javax.swing.JMenu();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenu15 = new javax.swing.JMenu();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenu16 = new javax.swing.JMenu();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem8.setText("jMenuItem8");

        jMenuItem9.setText("jMenuItem9");

        jMenu4.setText("File");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar2.add(jMenu5);

        jMenuItem10.setText("jMenuItem10");

        jMenu6.setText("File");
        jMenuBar3.add(jMenu6);

        jMenu7.setText("Edit");
        jMenuBar3.add(jMenu7);

        jMenuItem32.setText("jMenuItem32");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 307, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane)
        );

        jMenu1.setText("Datos de Prueba");

        cargarDatosMenu.setText("Cargar datos");
        cargarDatosMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarDatosMenuActionPerformed(evt);
            }
        });
        jMenu1.add(cargarDatosMenu);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Casos de uso");

        jMenu3.setText("AltaPerfil");

        jMenuItem3.setText("AltaCliente");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("AltaArtista");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenu2.add(jMenu3);

        jMenuItem25.setText("Alta de Genero");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem25);

        AltaAlbum.setText("AltaAlbum");
        AltaAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AltaAlbumActionPerformed(evt);
            }
        });
        jMenu2.add(AltaAlbum);

        jMenu14.setText("Crear Lista");

        jMenuItem27.setText("Crear Lista Particular");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem27);

        jMenuItem28.setText("Crear Lista Por Defecto");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem28);

        jMenu2.add(jMenu14);

        jMenu15.setText("Agregar Tema a Lista");

        jMenuItem26.setText("Agregar a Lista Particular");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem26);

        jMenuItem29.setText("Agregar a Lista Por Defecto");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem29);

        jMenu2.add(jMenu15);

        jMenu16.setText("Eliminar Tema de Lista");

        jMenuItem30.setText("Eliminar de lista particular");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem30);

        jMenuItem31.setText("Eliminar de lista por defecto");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem31);

        jMenu2.add(jMenu16);

        jMenu8.setText("Agregar Favorito");

        jMenuItem11.setText("Album");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem11);

        jMenuItem12.setText("Tema");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem12);

        jMenu9.setText("Lista de reproduccion");

        jMenuItem13.setText("Lista de Rep particular");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem13);

        jMenuItem5.setText("Lista por defecto");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem5);

        jMenu8.add(jMenu9);

        jMenu2.add(jMenu8);

        jMenuItem6.setText("Consulta Artista");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Consulta Cliente");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem14.setText("Seguir Usuario");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem14);

        jMenuItem24.setText("Dejar de seguir Usuario");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem24);

        jMenu10.setText("Eliminar Favorito");

        jMenuItem15.setText("Album");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem15);

        jMenuItem16.setText("Tema");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem16);

        jMenu11.setText("Lista de rep");

        jMenuItem18.setText("Particular");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem18);

        jMenuItem19.setText("Por Defecto");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem19);

        jMenu10.add(jMenu11);

        jMenu2.add(jMenu10);

        jMenuItem17.setText("Publicar Lista");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem17);

        jMenu12.setText("ConsultaAlbum");

        jMenuItem20.setText("Por Artista");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem20);

        jMenuItem21.setText("Por Genero");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem21);

        jMenu2.add(jMenu12);

        jMenu13.setText("ConsultaListaRep");

        jMenuItem22.setText("Por Cliente");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem22);

        jMenuItem23.setText("Por Genero");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem23);

        jMenu2.add(jMenu13);

        jMenuItem33.setText("Modificar subscripcion");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem33);

        jMenuItem34.setText("Ver Registros");
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem34);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        AltaCliente altaCliente = new AltaCliente();
    desktopPane.add(altaCliente);
    altaCliente.setVisible(true);
    altaCliente.setClosable(true);
    altaCliente.setMaximizable(true);
    altaCliente.setIconifiable(true);
    altaCliente.setResizable(true);
    altaCliente.toFront();
    altaCliente.show();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        AltaArtista altaArtista = new AltaArtista();
    desktopPane.add(altaArtista);
    altaArtista.setVisible(true);
    altaArtista.setClosable(true);
    altaArtista.setMaximizable(true);
    altaArtista.setIconifiable(true);
    altaArtista.setResizable(true);
    altaArtista.toFront();
    altaArtista.show();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void AltaAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AltaAlbumActionPerformed
        AltaAlbum altaAlbum = new AltaAlbum();
    

    desktopPane.add(altaAlbum,JLayeredPane.DEFAULT_LAYER);
    altaAlbum.setVisible(true);
    altaAlbum.setClosable(true);
    altaAlbum.setMaximizable(true);
    altaAlbum.setIconifiable(true);
    altaAlbum.setResizable(true);
    altaAlbum.toFront();
    altaAlbum.show();
    }//GEN-LAST:event_AltaAlbumActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        ConsultaPerfilArtista consulta_artista = new ConsultaPerfilArtista();
    

    desktopPane.add(consulta_artista,JLayeredPane.DEFAULT_LAYER);
    consulta_artista.setVisible(true);
    consulta_artista.setClosable(true);
    consulta_artista.setMaximizable(true);
    consulta_artista.setIconifiable(true);
    consulta_artista.setResizable(true);
    consulta_artista.toFront();
    consulta_artista.show(); 
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        ConsultaPerfilCliente consulta_cliente = new ConsultaPerfilCliente();
    

    desktopPane.add(consulta_cliente,JLayeredPane.DEFAULT_LAYER);
    consulta_cliente.setVisible(true);
    consulta_cliente.setClosable(true);
    consulta_cliente.setMaximizable(true);
    consulta_cliente.setIconifiable(true);
    consulta_cliente.setResizable(true);
    consulta_cliente.toFront();
    consulta_cliente.show(); 
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        TemaFavorito tema_favorito = new TemaFavorito();
    

    desktopPane.add(tema_favorito,JLayeredPane.DEFAULT_LAYER);
    tema_favorito.setVisible(true);
    tema_favorito.setClosable(true);
    tema_favorito.setMaximizable(true);
    tema_favorito.setIconifiable(true);
    tema_favorito.setResizable(true);
    tema_favorito.toFront();
    tema_favorito.show(); 
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        AlbumFavorito album_favorito = new AlbumFavorito();
    

    desktopPane.add(album_favorito,JLayeredPane.DEFAULT_LAYER);
    album_favorito.setVisible(true);
    album_favorito.setClosable(true);
    album_favorito.setMaximizable(true);
    album_favorito.setIconifiable(true);
    album_favorito.setResizable(true);
    album_favorito.toFront();
    album_favorito.show(); 
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        ListaDeRepFavorito lista_favorito = new ListaDeRepFavorito();
    

    desktopPane.add(lista_favorito,JLayeredPane.DEFAULT_LAYER);
    lista_favorito.setVisible(true);
    lista_favorito.setClosable(true);
    lista_favorito.setMaximizable(true);
    lista_favorito.setIconifiable(true);
    lista_favorito.setResizable(true);
    lista_favorito.toFront();
    lista_favorito.show(); 
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
       ListaDeRep_Por_Default_Favorito lista_favorito = new ListaDeRep_Por_Default_Favorito();
    

    desktopPane.add(lista_favorito,JLayeredPane.DEFAULT_LAYER);
    lista_favorito.setVisible(true);
    lista_favorito.setClosable(true);
    lista_favorito.setMaximizable(true);
    lista_favorito.setIconifiable(true);
    lista_favorito.setResizable(true);
    lista_favorito.toFront();
    lista_favorito.show(); 
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        SeguirUsuario seguir_user = new SeguirUsuario();
    

    desktopPane.add(seguir_user ,JLayeredPane.DEFAULT_LAYER);
    seguir_user .setVisible(true);
    seguir_user .setClosable(true);
    seguir_user .setMaximizable(true);
    seguir_user .setIconifiable(true);
    seguir_user .setResizable(true);
    seguir_user .toFront();
    seguir_user .show(); 
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        EliminarAlbumFavorito eliminar_album = new EliminarAlbumFavorito();
    

    desktopPane.add(eliminar_album ,JLayeredPane.DEFAULT_LAYER);
    eliminar_album .setVisible(true);
    eliminar_album .setClosable(true);
    eliminar_album .setMaximizable(true);
    eliminar_album .setIconifiable(true);
    eliminar_album .setResizable(true);
    eliminar_album .toFront();
    eliminar_album .show(); 
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        EliminarTemaFavorito eliminar_tema = new EliminarTemaFavorito();
    

    desktopPane.add(eliminar_tema ,JLayeredPane.DEFAULT_LAYER);
    eliminar_tema .setVisible(true);
    eliminar_tema .setClosable(true);
    eliminar_tema .setMaximizable(true);
    eliminar_tema .setIconifiable(true);
    eliminar_tema .setResizable(true);
    eliminar_tema .toFront();
    eliminar_tema .show(); 
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
         EliminarListaRepFavorito eliminar_listarep = new EliminarListaRepFavorito();
    

    desktopPane.add(eliminar_listarep ,JLayeredPane.DEFAULT_LAYER);
    eliminar_listarep .setVisible(true);
    eliminar_listarep .setClosable(true);
    eliminar_listarep .setMaximizable(true);
    eliminar_listarep .setIconifiable(true);
    eliminar_listarep .setResizable(true);
    eliminar_listarep .toFront();
    eliminar_listarep .show(); 
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        EliminarListaRep_Por_Defecto_Favorito eliminar_listarep_por_defecto = new EliminarListaRep_Por_Defecto_Favorito();
    

    desktopPane.add(eliminar_listarep_por_defecto ,JLayeredPane.DEFAULT_LAYER);
    eliminar_listarep_por_defecto .setVisible(true);
    eliminar_listarep_por_defecto .setClosable(true);
    eliminar_listarep_por_defecto .setMaximizable(true);
    eliminar_listarep_por_defecto .setIconifiable(true);
    eliminar_listarep_por_defecto .setResizable(true);
    eliminar_listarep_por_defecto .toFront();
    eliminar_listarep_por_defecto .show(); 
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        PublicarLista publicar_lista= new PublicarLista();
    

    desktopPane.add(publicar_lista ,JLayeredPane.DEFAULT_LAYER);
    publicar_lista .setVisible(true);
    publicar_lista .setClosable(true);
    publicar_lista .setMaximizable(true);
    publicar_lista .setIconifiable(true);
    publicar_lista .setResizable(true);
    publicar_lista .toFront();
    publicar_lista .show(); 
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
       ConsultaAlbumxArtista consulta_album= new ConsultaAlbumxArtista();
    

    desktopPane.add(consulta_album ,JLayeredPane.DEFAULT_LAYER);
    consulta_album .setVisible(true);
    consulta_album .setClosable(true);
    consulta_album .setMaximizable(true);
    consulta_album .setIconifiable(true);
    consulta_album .setResizable(true);
    consulta_album .toFront();
    consulta_album .show(); 
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        ConsultaListaRep_Genero consulta_listarepbygenero= new ConsultaListaRep_Genero();
    

    desktopPane.add(consulta_listarepbygenero ,JLayeredPane.DEFAULT_LAYER);
   consulta_listarepbygenero .setVisible(true);
    consulta_listarepbygenero .setClosable(true);
    consulta_listarepbygenero .setMaximizable(true);
    consulta_listarepbygenero .setIconifiable(true);
    consulta_listarepbygenero .setResizable(true);
    consulta_listarepbygenero.toFront();
    consulta_listarepbygenero.show(); 
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        ConsultaListaRep_Cliente consulta_listarepbycliente= new ConsultaListaRep_Cliente();
    

    desktopPane.add(consulta_listarepbycliente ,JLayeredPane.DEFAULT_LAYER);
   consulta_listarepbycliente .setVisible(true);
    consulta_listarepbycliente .setClosable(true);
    consulta_listarepbycliente .setMaximizable(true);
    consulta_listarepbycliente .setIconifiable(true);
    consulta_listarepbycliente .setResizable(true);
    consulta_listarepbycliente.toFront();
    consulta_listarepbycliente.show(); 
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        DejarDeSeguirUsuario dejar_seguir= new DejarDeSeguirUsuario();
    

    desktopPane.add(dejar_seguir,JLayeredPane.DEFAULT_LAYER);
   dejar_seguir .setVisible(true);
    dejar_seguir.setClosable(true);
    dejar_seguir.setMaximizable(true);
    dejar_seguir.setIconifiable(true);
    dejar_seguir.setResizable(true);
    dejar_seguir.toFront();
    dejar_seguir.show(); 
                                    
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
         ConsultaAlbumxGeneros consulta_album_bygenero= new ConsultaAlbumxGeneros();
    

    desktopPane.add(consulta_album_bygenero,JLayeredPane.DEFAULT_LAYER);
   consulta_album_bygenero .setVisible(true);
    consulta_album_bygenero.setClosable(true);
    consulta_album_bygenero.setMaximizable(true);
    consulta_album_bygenero.setIconifiable(true);
    consulta_album_bygenero.setResizable(true);
    consulta_album_bygenero.toFront();
    consulta_album_bygenero.show(); 
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
       AltaGenero alta_genero= new AltaGenero();
    

    desktopPane.add(alta_genero,JLayeredPane.DEFAULT_LAYER);
   alta_genero .setVisible(true);
    alta_genero.setClosable(true);
    alta_genero.setMaximizable(true);
    alta_genero.setIconifiable(true);
    alta_genero.setResizable(true);
    alta_genero.toFront();
    alta_genero.show(); 
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void cargarDatosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarDatosMenuActionPerformed
        //Cargar datos
        CargarDatosDePrueba datosPrueba= new CargarDatosDePrueba();
        desktopPane.add(datosPrueba,JLayeredPane.DEFAULT_LAYER);
        datosPrueba .setVisible(true);
        datosPrueba.setClosable(true);
        datosPrueba.setMaximizable(true);
        datosPrueba.setIconifiable(true);
        datosPrueba.setResizable(true);
        datosPrueba.toFront();
        datosPrueba.show();
    }//GEN-LAST:event_cargarDatosMenuActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
                AltaListaRep alta_listarep= new AltaListaRep();
    desktopPane.add(alta_listarep,JLayeredPane.DEFAULT_LAYER);
   alta_listarep.setVisible(true);
    alta_listarep.setClosable(true);
    alta_listarep.setMaximizable(true);
    alta_listarep.setIconifiable(true);
    alta_listarep.setResizable(true);
    alta_listarep.toFront();
    alta_listarep.show(); 
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        AltaListaRepPorDefecto alta_listarep= new AltaListaRepPorDefecto();
    desktopPane.add(alta_listarep,JLayeredPane.DEFAULT_LAYER);
   alta_listarep.setVisible(true);
    alta_listarep.setClosable(true);
    alta_listarep.setMaximizable(true);
    alta_listarep.setIconifiable(true);
    alta_listarep.setResizable(true);
    alta_listarep.toFront();
    alta_listarep.show(); 
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        AgregarTema_A_Lista_Part alta_tema_listarep= new AgregarTema_A_Lista_Part ();
    desktopPane.add(alta_tema_listarep,JLayeredPane.DEFAULT_LAYER);
   alta_tema_listarep.setVisible(true);
    alta_tema_listarep.setClosable(true);
    alta_tema_listarep.setMaximizable(true);
    alta_tema_listarep.setIconifiable(true);
    alta_tema_listarep.setResizable(true);
    alta_tema_listarep.toFront();
    alta_tema_listarep.show(); 
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        AgregarTema_A_Lista_Defecto alta_tema_listarep= new AgregarTema_A_Lista_Defecto ();
    desktopPane.add(alta_tema_listarep,JLayeredPane.DEFAULT_LAYER);
   alta_tema_listarep.setVisible(true);
    alta_tema_listarep.setClosable(true);
    alta_tema_listarep.setMaximizable(true);
    alta_tema_listarep.setIconifiable(true);
    alta_tema_listarep.setResizable(true);
    alta_tema_listarep.toFront();
    alta_tema_listarep.show(); 
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
 QuitarTemaDeLista_Part quitar_tema_listarep= new QuitarTemaDeLista_Part ();
    desktopPane.add(quitar_tema_listarep,JLayeredPane.DEFAULT_LAYER);
   quitar_tema_listarep.setVisible(true);
    quitar_tema_listarep.setClosable(true);
    quitar_tema_listarep.setMaximizable(true);
    quitar_tema_listarep.setIconifiable(true);
    quitar_tema_listarep.setResizable(true);
    quitar_tema_listarep.toFront();
    quitar_tema_listarep.show(); 
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        QuitarTemaDeLista_Def quitar_tema_listarep= new QuitarTemaDeLista_Def ();
    desktopPane.add(quitar_tema_listarep,JLayeredPane.DEFAULT_LAYER);
   quitar_tema_listarep.setVisible(true);
    quitar_tema_listarep.setClosable(true);
    quitar_tema_listarep.setMaximizable(true);
    quitar_tema_listarep.setIconifiable(true);
    quitar_tema_listarep.setResizable(true);
    quitar_tema_listarep.toFront();
    quitar_tema_listarep.show(); 
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
       ActualizarSubscripcion quitar_tema_listarep= new ActualizarSubscripcion();
    desktopPane.add(quitar_tema_listarep,JLayeredPane.DEFAULT_LAYER);
   quitar_tema_listarep.setVisible(true);
    quitar_tema_listarep.setClosable(true);
    quitar_tema_listarep.setMaximizable(true);
    quitar_tema_listarep.setIconifiable(true);
    quitar_tema_listarep.setResizable(true);
    quitar_tema_listarep.toFront();
    quitar_tema_listarep.show(); 
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        VerRegistros verregistro= new VerRegistros();
    desktopPane.add(verregistro,JLayeredPane.DEFAULT_LAYER);
   verregistro.setVisible(true);
    verregistro.setClosable(true);
    verregistro.setMaximizable(true);
    verregistro.setIconifiable(true);
    verregistro.setResizable(true);
    verregistro.toFront();
    verregistro.show(); 
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AltaAlbum;
    private javax.swing.JMenuItem cargarDatosMenu;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    // End of variables declaration//GEN-END:variables

    JDesktopPane getDesktopPane() {
        return desktopPane;
    }
}
