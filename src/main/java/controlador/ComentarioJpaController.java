package controlador;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import modelo.Comentario;
import modelo.Usuario;
import modelo.Resena;
import controlador.exceptions.NonexistentEntityException;

public class ComentarioJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    // Constructor con EntityManagerFactory
    public ComentarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Constructor que crea el EntityManagerFactory
    public ComentarioJpaController() {
        emf = Persistence.createEntityManagerFactory("JavaWebLasHuequitas");
    }

    // Obtener el EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear un nuevo comentario
    public void create(Comentario comentario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // Asociar comentario con usuario y reseña
            Usuario usuario = comentario.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                comentario.setUsuario(usuario);
            }

            Resena resena = comentario.getResena();
            if (resena != null) {
                resena = em.getReference(resena.getClass(), resena.getId());
                comentario.setResena(resena);
            }

            em.persist(comentario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar un comentario existente
    public void edit(Comentario comentario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // Actualizar las asociaciones de usuario y reseña
            Comentario persistentComentario = em.find(Comentario.class, comentario.getId());
            Usuario oldUsuario = persistentComentario.getUsuario();
            Usuario newUsuario = comentario.getUsuario();
            Resena oldResena = persistentComentario.getResena();
            Resena newResena = comentario.getResena();

            if (newUsuario != null && !newUsuario.equals(oldUsuario)) {
                newUsuario = em.getReference(newUsuario.getClass(), newUsuario.getId());
                comentario.setUsuario(newUsuario);
            }

            if (newResena != null && !newResena.equals(oldResena)) {
                newResena = em.getReference(newResena.getClass(), newResena.getId());
                comentario.setResena(newResena);
            }

            comentario = em.merge(comentario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (comentario.getId() == null || findComentario(comentario.getId()) == null) {
                throw new NonexistentEntityException("El comentario con id " + comentario.getId() + " ya no existe.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Eliminar un comentario por ID
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comentario comentario;
            try {
                comentario = em.getReference(Comentario.class, id);
                comentario.getId(); // Verificación para asegurar la existencia
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("El comentario con id " + id + " ya no existe.", enfe);
            }
            em.remove(comentario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Obtener todos los comentarios
    public List<Comentario> findComentarioEntities() {
        return findComentarioEntities(true, -1, -1);
    }

    // Obtener un rango de comentarios (para paginación)
    public List<Comentario> findComentarioEntities(int maxResults, int firstResult) {
        return findComentarioEntities(false, maxResults, firstResult);
    }

    // Método auxiliar para obtener todos los comentarios o una parte
    private List<Comentario> findComentarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Comentario> cq = em.getCriteriaBuilder().createQuery(Comentario.class);
            cq.select(cq.from(Comentario.class));
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

    // Buscar un comentario por ID
    public Comentario findComentario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comentario.class, id);
        } finally {
            em.close();
        }
    }

    // Contar el total de comentarios
    public int getComentarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            Root<Comentario> rt = cq.from(Comentario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Comentario> findComentariosByResena(Long resenaId) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM Comentario c WHERE c.resena.id = :resenaId", Comentario.class);
            query.setParameter("resenaId", resenaId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
