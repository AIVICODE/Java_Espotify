/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persis;

import Logica.Album;
import Logica.Favoritos;
import Logica.Genero;
import Logica.ListaRep;
import Logica.Tema;
import Persis.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 *
 * @author ivan
 */
public class FavoritosJpaController implements Serializable {

    public FavoritosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public FavoritosJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Favoritos favoritos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(favoritos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Favoritos favoritos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            favoritos = em.merge(favoritos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = favoritos.getId();
                if (findFavoritos(id) == null) {
                    throw new NonexistentEntityException("The favoritos with id " + id + " no longer exists.");
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
            Favoritos favoritos;
            try {
                favoritos = em.getReference(Favoritos.class, id);
                favoritos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The favoritos with id " + id + " no longer exists.", enfe);
            }
            em.remove(favoritos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Favoritos> findFavoritosEntities() {
        return findFavoritosEntities(true, -1, -1);
    }

    public List<Favoritos> findFavoritosEntities(int maxResults, int firstResult) {
        return findFavoritosEntities(false, maxResults, firstResult);
    }

    private List<Favoritos> findFavoritosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Favoritos.class));
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

    public Favoritos findFavoritos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Favoritos.class, id);
        } finally {
            em.close();
        }
    }

    public int getFavoritosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Favoritos> rt = cq.from(Favoritos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
public List<Favoritos> findFavoritosByFiltroCriteria(String filtro, String sortBy) {
    EntityManager em = getEntityManager();
    try {
        List<Favoritos> resultados = new ArrayList<>();

        // Buscar en Tema
        resultados.addAll(findTemasByFiltro(filtro, sortBy, em));

        // Buscar en Album
        resultados.addAll(findAlbumsByFiltro(filtro, sortBy, em));

        // Buscar en ListaRep
        resultados.addAll(findListasRepByFiltro(filtro, sortBy, em));

        return resultados;
    } finally {
        em.close();
    }
}

private List<Tema> findTemasByFiltro(String filtro, String sortBy, EntityManager em) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Tema> cq = cb.createQuery(Tema.class);
    Root<Tema> temaRoot = cq.from(Tema.class);
    
    // Realiza un JOIN con Album
    Join<Tema, Album> albumJoin = temaRoot.join("album");
    
    // Realiza un JOIN con Genero a través de Album
    Join<Album, Genero> generoJoin = albumJoin.join("listaGeneros");
    
    // Filtros
    Predicate nombreLike = cb.like(temaRoot.get("nombre"), "%" + filtro + "%");
    Predicate generoLike = cb.like(generoJoin.get("nombre"), "%" + filtro + "%");
    
    // Combinar condiciones
    cq.where(cb.or(nombreLike, generoLike));
    cq.distinct(true);
    // Ordenar
    if (sortBy.equals("nombre")) {
        cq.orderBy(cb.asc(temaRoot.get("nombre")));
    } else if (sortBy.equals("fecha")) {
        // Asumiendo que "fechaCreacion" es el atributo en Album
        cq.orderBy(cb.asc(albumJoin.get("anioCreacion")));
    }
    
    Query query = em.createQuery(cq);
    return query.getResultList();
}

private List<Album> findAlbumsByFiltro(String filtro, String sortBy, EntityManager em) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Album> cq = cb.createQuery(Album.class);
    Root<Album> albumRoot = cq.from(Album.class);
    
    // JOIN con Genero
    Join<Album, Genero> generoJoin = albumRoot.join("listaGeneros");
    
    // Filtros
    Predicate nombreLike = cb.like(albumRoot.get("nombre"), "%" + filtro + "%");
    Predicate generoLike = cb.like(generoJoin.get("nombre"), "%" + filtro + "%");
    cq.where(cb.or(nombreLike, generoLike));
cq.distinct(true);
    // Ordenar
    if (sortBy.equals("nombre")) {
        cq.orderBy(cb.asc(albumRoot.get("nombre")));
    } else if (sortBy.equals("fecha")) {
        cq.orderBy(cb.asc(albumRoot.get("anioCreacion"))); // Asegúrate de que este atributo existe
    }
    
    Query query = em.createQuery(cq);
    return query.getResultList();
}

private List<ListaRep> findListasRepByFiltro(String filtro, String sortBy, EntityManager em) {
    if ("fecha".equals(sortBy)) {
        return new ArrayList<>(); // Retorna una lista vacía si se solicita ordenar por fecha
    }
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<ListaRep> cq = cb.createQuery(ListaRep.class);
    Root<ListaRep> listaRepRoot = cq.from(ListaRep.class);
    
    // Filtros
    Predicate nombreLike = cb.like(listaRepRoot.get("nombre"), "%" + filtro + "%");
    cq.where(nombreLike);
cq.distinct(true);
    // Ordenar
    if (sortBy.equals("nombre")) {
        cq.orderBy(cb.asc(listaRepRoot.get("nombre")));
    } 
    
    Query query = em.createQuery(cq);
    return query.getResultList();
}



    
}
