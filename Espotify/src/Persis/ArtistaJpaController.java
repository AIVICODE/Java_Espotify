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
import Logica.Artista;
import Persis.exceptions.NonexistentEntityException;
import Persis.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author ivan
 */
public class ArtistaJpaController implements Serializable {

    public ArtistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ArtistaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }
    
    public void create(Artista artista) throws PreexistingEntityException, Exception {
        if (artista.getAlbumes() == null) {
            artista.setAlbumes(new ArrayList<Album>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Album> attachedAlbumes = new ArrayList<Album>();
            for (Album albumesAlbumToAttach : artista.getAlbumes()) {
                albumesAlbumToAttach = em.getReference(albumesAlbumToAttach.getClass(), albumesAlbumToAttach.getId());
                attachedAlbumes.add(albumesAlbumToAttach);
            }
            artista.setAlbumes(attachedAlbumes);
            em.persist(artista);
            for (Album albumesAlbum : artista.getAlbumes()) {
                Artista oldArtistaOfAlbumesAlbum = albumesAlbum.getArtista();
                albumesAlbum.setArtista(artista);
                albumesAlbum = em.merge(albumesAlbum);
                if (oldArtistaOfAlbumesAlbum != null) {
                    oldArtistaOfAlbumesAlbum.getAlbumes().remove(albumesAlbum);
                    oldArtistaOfAlbumesAlbum = em.merge(oldArtistaOfAlbumesAlbum);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArtista(artista.getMail()) != null) {
                throw new PreexistingEntityException("Artista " + artista + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Artista artista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Artista persistentArtista = em.find(Artista.class, artista.getMail());
            List<Album> albumesOld = persistentArtista.getAlbumes();
            List<Album> albumesNew = artista.getAlbumes();
            List<Album> attachedAlbumesNew = new ArrayList<Album>();
            for (Album albumesNewAlbumToAttach : albumesNew) {
                albumesNewAlbumToAttach = em.getReference(albumesNewAlbumToAttach.getClass(), albumesNewAlbumToAttach.getId());
                attachedAlbumesNew.add(albumesNewAlbumToAttach);
            }
            albumesNew = attachedAlbumesNew;
            artista.setAlbumes(albumesNew);
            artista = em.merge(artista);
            for (Album albumesOldAlbum : albumesOld) {
                if (!albumesNew.contains(albumesOldAlbum)) {
                    albumesOldAlbum.setArtista(null);
                    albumesOldAlbum = em.merge(albumesOldAlbum);
                }
            }
            for (Album albumesNewAlbum : albumesNew) {
                if (!albumesOld.contains(albumesNewAlbum)) {
                    Artista oldArtistaOfAlbumesNewAlbum = albumesNewAlbum.getArtista();
                    albumesNewAlbum.setArtista(artista);
                    albumesNewAlbum = em.merge(albumesNewAlbum);
                    if (oldArtistaOfAlbumesNewAlbum != null && !oldArtistaOfAlbumesNewAlbum.equals(artista)) {
                        oldArtistaOfAlbumesNewAlbum.getAlbumes().remove(albumesNewAlbum);
                        oldArtistaOfAlbumesNewAlbum = em.merge(oldArtistaOfAlbumesNewAlbum);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = artista.getMail();
                if (findArtista(id) == null) {
                    throw new NonexistentEntityException("The artista with id " + id + " no longer exists.");
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
            Artista artista;
            try {
                artista = em.getReference(Artista.class, id);
                artista.getMail();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The artista with id " + id + " no longer exists.", enfe);
            }
            List<Album> albumes = artista.getAlbumes();
            for (Album albumesAlbum : albumes) {
                albumesAlbum.setArtista(null);
                albumesAlbum = em.merge(albumesAlbum);
            }
            em.remove(artista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Artista> findArtistaEntities() {
        return findArtistaEntities(true, -1, -1);
    }

    public List<Artista> findArtistaEntities(int maxResults, int firstResult) {
        return findArtistaEntities(false, maxResults, firstResult);
    }

    private List<Artista> findArtistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Artista.class));
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

    public Artista findArtista(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Artista.class, id);
        } finally {
            em.close();
        }
    }

    public int getArtistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Artista> rt = cq.from(Artista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

   public Artista findArtistaByNickname(String nickname) {
    // Aquí asumimos que tienes una consulta JPQL o SQL preparada para buscar por nickname
    EntityManager em = getEntityManager();
    try {
        return em.createQuery("SELECT a FROM Artista a WHERE a.nickname = :nickname", Artista.class)
                 .setParameter("nickname", nickname)
                 .getSingleResult();
    } catch (NoResultException e) {
        return null; // O maneja el caso en que no se encuentre el artista
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
