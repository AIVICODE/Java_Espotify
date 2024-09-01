
package Persis;

import Logica.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

   


public class ControladoraPersistencia {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("EspotifyPU");

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
}