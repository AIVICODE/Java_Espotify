/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persis;

import Logica.ListaRep;
import Persis.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ivan
 */
public class ListaRepJpaController implements Serializable {

    public ListaRepJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
      public ListaRepJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ListaRep listaRep) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(listaRep);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ListaRep listaRep) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            listaRep = em.merge(listaRep);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = listaRep.getId();
                if (findListaRep(id) == null) {
                    throw new NonexistentEntityException("The listaRep with id " + id + " no longer exists.");
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
            ListaRep listaRep;
            try {
                listaRep = em.getReference(ListaRep.class, id);
                listaRep.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The listaRep with id " + id + " no longer exists.", enfe);
            }
            em.remove(listaRep);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ListaRep> findListaRepEntities() {
        return findListaRepEntities(true, -1, -1);
    }

    public List<ListaRep> findListaRepEntities(int maxResults, int firstResult) {
        return findListaRepEntities(false, maxResults, firstResult);
    }

    private List<ListaRep> findListaRepEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ListaRep.class));
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

    public ListaRep findListaRep(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ListaRep.class, id);
        } finally {
            em.close();
        }
    }

    public int getListaRepCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ListaRep> rt = cq.from(ListaRep.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
    public ListaRep findListaRepByNombre(String nombre) throws Exception {
    EntityManager em = getEntityManager();
    try {
        // Usamos JPQL (Java Persistence Query Language) para buscar la lista de reproducción por nombre
        Query query = em.createQuery("SELECT l FROM ListaRep l WHERE l.nombre = :nombre");
        query.setParameter("nombre", nombre);
        // Utilizamos getSingleResult() si estamos seguros de que solo habrá una lista con ese nombre
        return (ListaRep) query.getSingleResult();
    } catch (NoResultException e) {
        // Si no se encuentra la lista, lanzamos una excepción específica
        throw new Exception("No se encuentra la lista de reproducción con el nombre: " + nombre, e);
    } finally {
        em.close();
    }
}
    
}
