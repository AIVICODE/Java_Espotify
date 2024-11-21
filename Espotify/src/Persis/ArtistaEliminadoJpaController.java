
package Persis;

import Logica.ArtistaEliminado;
import Persis.exceptions.NonexistentEntityException;
import Persis.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class ArtistaEliminadoJpaController implements Serializable {

    public ArtistaEliminadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ArtistaEliminadoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }
    
    public void create(ArtistaEliminado artistaEliminado) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(artistaEliminado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArtistaEliminado(artistaEliminado.getId()) != null) {
                throw new PreexistingEntityException("ArtistaEliminado " + artistaEliminado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ArtistaEliminado artistaEliminado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            artistaEliminado = em.merge(artistaEliminado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = artistaEliminado.getId();
                if (findArtistaEliminado(id) == null) {
                    throw new NonexistentEntityException("The artistaEliminado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArtistaEliminado artistaEliminado;
            try {
                artistaEliminado = em.getReference(ArtistaEliminado.class, id);
                artistaEliminado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The artistaEliminado with id " + id + " no longer exists.", enfe);
            }
            em.remove(artistaEliminado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ArtistaEliminado> findArtistaEliminadoEntities() {
        return findArtistaEliminadoEntities(true, -1, -1);
    }

    public List<ArtistaEliminado> findArtistaEliminadoEntities(int maxResults, int firstResult) {
        return findArtistaEliminadoEntities(false, maxResults, firstResult);
    }

    private List<ArtistaEliminado> findArtistaEliminadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ArtistaEliminado.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ArtistaEliminado findArtistaEliminado(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ArtistaEliminado.class, id);
        } finally {
            em.close();
        }
    }

    public int getArtistaEliminadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ArtistaEliminado> rt = cq.from(ArtistaEliminado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void modificarBiografiaArtista() {//NUEVO, le saque el em de parametro y lopuse en la primera linea de abajo
       EntityManager em = getEntityManager(); 
       EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            String query = "ALTER TABLE ARTISTA MODIFY COLUMN BIOGRAFIA VARCHAR(1000)";
            em.createNativeQuery(query).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Manejo de errores
        }
    }
    
    
}
