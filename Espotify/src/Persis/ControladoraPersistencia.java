package Persis;

import Logica.Artista;
import Logica.Cliente;
import Logica.Genero;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class ControladoraPersistencia {
    ClienteJpaController clijpa = new ClienteJpaController();
    ArtistaJpaController artjpa = new ArtistaJpaController();
    GeneroJpaController genjpa = new GeneroJpaController();
    
    public void AddCliente(Cliente cli) throws Exception {
        clijpa.create(cli);
    }
    
    public void AddArtista(Artista art) throws Exception {
        artjpa.create(art);
    }
    
    public void AddGenero(Genero gen) throws Exception {
        genjpa.create(gen);
    }
    
    public Genero findGenerobynombre(String nombre) {
        
        return genjpa.findGenero(nombre);
    }
    
    public boolean findArtista(String correo) throws Exception {
        return artjpa.findArtista(correo) != null; // Si encuentra al artista, devuelve true, de lo contrario, false
    }
    
    // Método para construir el modelo de árbol (JTree)
   public DefaultTreeModel buildGeneroTree() {
    // Crear un nodo raíz
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Géneros");

    // Obtener todos los géneros desde la base de datos
    List<Genero> generos = genjpa.findGeneroEntities();

    // Crear un mapa para organizar los géneros
    Map<String, DefaultMutableTreeNode> nodes = new HashMap<>();

    // Primero, crear todos los nodos y añadirlos al mapa
    for (Genero genero : generos) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(genero.getNombre());
        nodes.put(genero.getNombre(), node);
    }

    // Ahora, construir las relaciones padre-hijo
    for (Genero genero : generos) {
        DefaultMutableTreeNode node = nodes.get(genero.getNombre());

        if (genero.getPadre() != null) {
            DefaultMutableTreeNode parentNode = nodes.get(genero.getPadre().getNombre());

            if (parentNode != null) {
                // Verificar si el nodo a agregar no es un ancestro del nodo actual
                if (!isAncestor(parentNode, node)) {
                    parentNode.add(node);
                } else {
                    System.out.println("No se puede agregar " + genero.getNombre() + " como hijo de " + genero.getPadre().getNombre() + " porque es un ancestro.");
                }
            } else {
                System.out.println("Padre no encontrado para: " + genero.getNombre());
            }
        } else {
            // Si no tiene padre, agregar el nodo al nodo raíz
            root.add(node);
        }
    }

    return new DefaultTreeModel(root);
}

// Método para verificar si un nodo es ancestro de otro
private boolean isAncestor(DefaultMutableTreeNode ancestor, DefaultMutableTreeNode node) {
    DefaultMutableTreeNode current = node;
    while (current != null) {
        if (current.equals(ancestor)) {
            return true;
        }
        current = (DefaultMutableTreeNode) current.getParent();
    }
    return false;
}


}
