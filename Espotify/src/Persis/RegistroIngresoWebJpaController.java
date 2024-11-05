/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persis;

import Logica.RegistroIngresoWeb;
import Persis.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ivan
 */
public class RegistroIngresoWebJpaController implements Serializable {

    public RegistroIngresoWebJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public RegistroIngresoWebJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }

    public void create(RegistroIngresoWeb registroIngresoWeb) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(registroIngresoWeb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RegistroIngresoWeb registroIngresoWeb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            registroIngresoWeb = em.merge(registroIngresoWeb);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = registroIngresoWeb.getId();
                if (findRegistroIngresoWeb(id) == null) {
                    throw new NonexistentEntityException("The registroIngresoWeb with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RegistroIngresoWeb registroIngresoWeb;
            try {
                registroIngresoWeb = em.getReference(RegistroIngresoWeb.class, id);
                registroIngresoWeb.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroIngresoWeb with id " + id + " no longer exists.", enfe);
            }
            em.remove(registroIngresoWeb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RegistroIngresoWeb> findRegistroIngresoWebEntities() {
        return findRegistroIngresoWebEntities(true, -1, -1);
    }

    public List<RegistroIngresoWeb> findRegistroIngresoWebEntities(int maxResults, int firstResult) {
        return findRegistroIngresoWebEntities(false, maxResults, firstResult);
    }

    private List<RegistroIngresoWeb> findRegistroIngresoWebEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RegistroIngresoWeb.class));
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

    public RegistroIngresoWeb findRegistroIngresoWeb(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RegistroIngresoWeb.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroIngresoWebCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RegistroIngresoWeb> rt = cq.from(RegistroIngresoWeb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
