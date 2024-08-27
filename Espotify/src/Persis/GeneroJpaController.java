/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persis;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Genero;
import Persis.exceptions.NonexistentEntityException;
import Persis.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ivan
 */
public class GeneroJpaController implements Serializable {

    public GeneroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Genero genero) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genero padre = genero.getPadre();
            if (padre != null) {
                padre = em.getReference(padre.getClass(), padre.getNombre());
                genero.setPadre(padre);
            }
            em.persist(genero);
            if (padre != null) {
                Genero oldPadreOfPadre = padre.getPadre();
                if (oldPadreOfPadre != null) {
                    oldPadreOfPadre.setPadre(null);
                    oldPadreOfPadre = em.merge(oldPadreOfPadre);
                }
                padre.setPadre(genero);
                padre = em.merge(padre);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGenero(genero.getNombre()) != null) {
                throw new PreexistingEntityException("Genero " + genero + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Genero genero) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genero persistentGenero = em.find(Genero.class, genero.getNombre());
            Genero padreOld = persistentGenero.getPadre();
            Genero padreNew = genero.getPadre();
            if (padreNew != null) {
                padreNew = em.getReference(padreNew.getClass(), padreNew.getNombre());
                genero.setPadre(padreNew);
            }
            genero = em.merge(genero);
            if (padreOld != null && !padreOld.equals(padreNew)) {
                padreOld.setPadre(null);
                padreOld = em.merge(padreOld);
            }
            if (padreNew != null && !padreNew.equals(padreOld)) {
                Genero oldPadreOfPadre = padreNew.getPadre();
                if (oldPadreOfPadre != null) {
                    oldPadreOfPadre.setPadre(null);
                    oldPadreOfPadre = em.merge(oldPadreOfPadre);
                }
                padreNew.setPadre(genero);
                padreNew = em.merge(padreNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = genero.getNombre();
                if (findGenero(id) == null) {
                    throw new NonexistentEntityException("The genero with id " + id + " no longer exists.");
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
            Genero genero;
            try {
                genero = em.getReference(Genero.class, id);
                genero.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The genero with id " + id + " no longer exists.", enfe);
            }
            Genero padre = genero.getPadre();
            if (padre != null) {
                padre.setPadre(null);
                padre = em.merge(padre);
            }
            em.remove(genero);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Genero> findGeneroEntities() {
        return findGeneroEntities(true, -1, -1);
    }

    public List<Genero> findGeneroEntities(int maxResults, int firstResult) {
        return findGeneroEntities(false, maxResults, firstResult);
    }

    private List<Genero> findGeneroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Genero.class));
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

    public Genero findGenero(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Genero.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Genero> rt = cq.from(Genero.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
