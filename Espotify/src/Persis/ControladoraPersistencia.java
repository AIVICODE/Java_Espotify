
package Persis;

import Logica.Artista;
import Logica.Cliente;
import Logica.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

   


public class ControladoraPersistencia {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EspotifyPU");

    public void addUsuario(Usuario usuario) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al crear el usuario: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    public void updateUsuario(Usuario usuario) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al mergear el usuario: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Usuario obtenerUsuarioPorNickname(String nickname) {
        EntityManager em = emf.createEntityManager();
        try {
            //busca como Cliente
            TypedQuery<Cliente> queryCliente = em.createQuery(
                "SELECT c FROM Cliente c WHERE c.nickname = :nickname", Cliente.class);
            queryCliente.setParameter("nickname", nickname);
            List<Cliente> clientes = queryCliente.getResultList();
            if (!clientes.isEmpty()) {
                return clientes.get(0);
            }

            // Si no Cliente busca como Artista
            TypedQuery<Artista> queryArtista = em.createQuery(
                "SELECT a FROM Artista a WHERE a.nickname = :nickname", Artista.class);
            queryArtista.setParameter("nickname", nickname);
            List<Artista> artistas = queryArtista.getResultList();
            if (!artistas.isEmpty()) {
                return artistas.get(0);
            }

            // Si no se encuentra, se retorna null, tambien se podria mandar una except personalizada
            // pero todavia no se bien como transformarlas en cartelitos en la gui
            return null;
        } finally {
            em.close();
        }
    }
    
    public Usuario obtenerUsuarioPorCorreo(String mail) {
        EntityManager em = emf.createEntityManager();
        try {
            //busca como Cliente
            TypedQuery<Cliente> queryCliente = em.createQuery(
                "SELECT c FROM Cliente c WHERE c.mail = :mail", Cliente.class);
            queryCliente.setParameter("mail", mail);
            List<Cliente> clientes = queryCliente.getResultList();
            if (!clientes.isEmpty()) {
                return clientes.get(0);
            }

            // Si no Cliente busca como Artista
            TypedQuery<Artista> queryArtista = em.createQuery(
                "SELECT a FROM Artista a WHERE a.mail = :mail", Artista.class);
            queryArtista.setParameter("mail", mail);
            List<Artista> artistas = queryArtista.getResultList();
            if (!artistas.isEmpty()) {
                return artistas.get(0);
            }

            // Si no se encuentra, se retorna null, tambien se podria mandar una except personalizada
            // pero todavia no se bien como transformarlas en cartelitos en la gui
            return null;
        } finally {
            em.close();
        }
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}