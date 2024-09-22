/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persis;

import Logica.Album;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Artista;
import Logica.Tema;
import Persis.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author ivan
 */
public class AlbumJpaController implements Serializable {

    public AlbumJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public AlbumJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Album album) {
        if (album.getListaTemas() == null) {
            album.setListaTemas(new ArrayList<Tema>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Artista artista = album.getArtista();
            if (artista != null) {
                artista = em.getReference(artista.getClass(), artista.getMail());
                album.setArtista(artista);
            }
            List<Tema> attachedListaTemas = new ArrayList<Tema>();
            for (Tema listaTemasTemaToAttach : album.getListaTemas()) {
                listaTemasTemaToAttach = em.getReference(listaTemasTemaToAttach.getClass(), listaTemasTemaToAttach.getId());
                attachedListaTemas.add(listaTemasTemaToAttach);
            }
            album.setListaTemas(attachedListaTemas);
            em.persist(album);
            if (artista != null) {
                artista.getAlbumes().add(album);
                artista = em.merge(artista);
            }
            for (Tema listaTemasTema : album.getListaTemas()) {
                Album oldAlbumOfListaTemasTema = listaTemasTema.getAlbum();
                listaTemasTema.setAlbum(album);
                listaTemasTema = em.merge(listaTemasTema);
                if (oldAlbumOfListaTemasTema != null) {
                    oldAlbumOfListaTemasTema.getListaTemas().remove(listaTemasTema);
                    oldAlbumOfListaTemasTema = em.merge(oldAlbumOfListaTemasTema);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Album album) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Album persistentAlbum = em.find(Album.class, album.getId());
            Artista artistaOld = persistentAlbum.getArtista();
            Artista artistaNew = album.getArtista();
            List<Tema> listaTemasOld = persistentAlbum.getListaTemas();
            List<Tema> listaTemasNew = album.getListaTemas();
            if (artistaNew != null) {
                artistaNew = em.getReference(artistaNew.getClass(), artistaNew.getMail());
                album.setArtista(artistaNew);
            }
            List<Tema> attachedListaTemasNew = new ArrayList<Tema>();
            for (Tema listaTemasNewTemaToAttach : listaTemasNew) {
                listaTemasNewTemaToAttach = em.getReference(listaTemasNewTemaToAttach.getClass(), listaTemasNewTemaToAttach.getId());
                attachedListaTemasNew.add(listaTemasNewTemaToAttach);
            }
            listaTemasNew = attachedListaTemasNew;
            album.setListaTemas(listaTemasNew);
            album = em.merge(album);
            if (artistaOld != null && !artistaOld.equals(artistaNew)) {
                artistaOld.getAlbumes().remove(album);
                artistaOld = em.merge(artistaOld);
            }
            if (artistaNew != null && !artistaNew.equals(artistaOld)) {
                artistaNew.getAlbumes().add(album);
                artistaNew = em.merge(artistaNew);
            }
            for (Tema listaTemasOldTema : listaTemasOld) {
                if (!listaTemasNew.contains(listaTemasOldTema)) {
                    listaTemasOldTema.setAlbum(null);
                    listaTemasOldTema = em.merge(listaTemasOldTema);
                }
            }
            for (Tema listaTemasNewTema : listaTemasNew) {
                if (!listaTemasOld.contains(listaTemasNewTema)) {
                    Album oldAlbumOfListaTemasNewTema = listaTemasNewTema.getAlbum();
                    listaTemasNewTema.setAlbum(album);
                    listaTemasNewTema = em.merge(listaTemasNewTema);
                    if (oldAlbumOfListaTemasNewTema != null && !oldAlbumOfListaTemasNewTema.equals(album)) {
                        oldAlbumOfListaTemasNewTema.getListaTemas().remove(listaTemasNewTema);
                        oldAlbumOfListaTemasNewTema = em.merge(oldAlbumOfListaTemasNewTema);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = album.getId();
                if (findAlbum(id) == null) {
                    throw new NonexistentEntityException("The album with id " + id + " no longer exists.");
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
            Album album;
            try {
                album = em.getReference(Album.class, id);
                album.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The album with id " + id + " no longer exists.", enfe);
            }
            Artista artista = album.getArtista();
            if (artista != null) {
                artista.getAlbumes().remove(album);
                artista = em.merge(artista);
            }
            List<Tema> listaTemas = album.getListaTemas();
            for (Tema listaTemasTema : listaTemas) {
                listaTemasTema.setAlbum(null);
                listaTemasTema = em.merge(listaTemasTema);
            }
            em.remove(album);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Album> findAlbumEntities() {
        return findAlbumEntities(true, -1, -1);
    }

    public List<Album> findAlbumEntities(int maxResults, int firstResult) {
        return findAlbumEntities(false, maxResults, firstResult);
    }

    private List<Album> findAlbumEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Album.class));
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

    public Album findAlbum(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Album.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlbumCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Album> rt = cq.from(Album.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

public List<Album> findAlbumByName(String nombre) {
    EntityManager em = getEntityManager();
    try {
        // Crear la consulta usando el nombre del álbum
        TypedQuery<Album> query = em.createQuery("SELECT a FROM Album a WHERE a.nombre = :nombre", Album.class);
        query.setParameter("nombre", nombre);

        // Devolver la lista de resultados
        return query.getResultList();
    } finally {
        em.close();
    }
}

       public Album findOneAlbumByName(String nombre) {
        EntityManager em = getEntityManager();
        try {
            // Crear la consulta usando el nombre del álbum
            TypedQuery<Album> query = em.createQuery("SELECT a FROM Album a WHERE a.nombre = :nombre", Album.class);
            query.setParameter("nombre", nombre);

            // Intentar obtener un resultado único
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Si no se encuentra un álbum con ese nombre, devolver null
            return null;
        } finally {
            em.close();
        }
    }
    
}
