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
import Logica.Album;
import Logica.Tema;
import Persis.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ivan
 */
public class TemaJpaController implements Serializable {

    public TemaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public TemaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }

    public void create(Tema tema) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Album album = tema.getAlbum();
            if (album != null) {
                album = em.getReference(album.getClass(), album.getNombre());
                tema.setAlbum(album);
            }
            em.persist(tema);
            if (album != null) {
                album.getListaTemas().add(tema);
                album = em.merge(album);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tema tema) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tema persistentTema = em.find(Tema.class, tema.getId());
            Album albumOld = persistentTema.getAlbum();
            Album albumNew = tema.getAlbum();
            if (albumNew != null) {
                albumNew = em.getReference(albumNew.getClass(), albumNew.getNombre());
                tema.setAlbum(albumNew);
            }
            tema = em.merge(tema);
            if (albumOld != null && !albumOld.equals(albumNew)) {
                albumOld.getListaTemas().remove(tema);
                albumOld = em.merge(albumOld);
            }
            if (albumNew != null && !albumNew.equals(albumOld)) {
                albumNew.getListaTemas().add(tema);
                albumNew = em.merge(albumNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tema.getId();
                if (findTema(id) == null) {
                    throw new NonexistentEntityException("The tema with id " + id + " no longer exists.");
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
            Tema tema;
            try {
                tema = em.getReference(Tema.class, id);
                tema.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tema with id " + id + " no longer exists.", enfe);
            }
            Album album = tema.getAlbum();
            if (album != null) {
                album.getListaTemas().remove(tema);
                album = em.merge(album);
            }
            em.remove(tema);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tema> findTemaEntities() {
        return findTemaEntities(true, -1, -1);
    }

    public List<Tema> findTemaEntities(int maxResults, int firstResult) {
        return findTemaEntities(false, maxResults, firstResult);
    }

    private List<Tema> findTemaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tema.class));
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

    public Tema findTema(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tema.class, id);
        } finally {
            em.close();
        }
    }

    public int getTemaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tema> rt = cq.from(Tema.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
