package persistencia;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import modelo.Resena;
import modelo.Usuario;
import persistencia.exceptions.NonexistentEntityException;

public class ResenaJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    // Constructor con EntityManagerFactory
    public ResenaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Constructor que crea el EntityManagerFactory
    public ResenaJpaController() {
        emf = Persistence.createEntityManagerFactory("JavaWebLasHuequitas");
    }

    // Obtener el EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear una nueva reseña
    public void create(Resena resena) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // Asociar la reseña con un usuario si no está desvinculado
            Usuario usuario = resena.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                resena.setUsuario(usuario);
            }

            em.persist(resena);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar una reseña existente
    public void edit(Resena resena) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // Actualizar la asociación de usuario
            Resena persistentResena = em.find(Resena.class, resena.getId());
            Usuario oldUsuario = persistentResena.getUsuario();
            Usuario newUsuario = resena.getUsuario();

            if (newUsuario != null && !newUsuario.equals(oldUsuario)) {
                newUsuario = em.getReference(newUsuario.getClass(), newUsuario.getId());
                resena.setUsuario(newUsuario);
            }

            resena = em.merge(resena);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (resena.getId() == null || findResena(resena.getId()) == null) {
                throw new NonexistentEntityException("La reseña con id " + resena.getId() + " ya no existe.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Eliminar una reseña por ID
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resena resena;
            try {
                resena = em.getReference(Resena.class, id);
                resena.getId(); // Verificación para asegurar la existencia
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("La reseña con id " + id + " ya no existe.", enfe);
            }
            em.remove(resena);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Obtener todas las reseñas
    public List<Resena> findResenaEntities() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT r FROM Resena r LEFT JOIN FETCH r.reacciones", Resena.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }


    // Obtener un rango de reseñas (para paginación)
    public List<Resena> findResenaEntities(int maxResults, int firstResult) {
        return findResenaEntities(false, maxResults, firstResult);
    }

    // Método auxiliar para obtener todas las reseñas o una parte
    private List<Resena> findResenaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Resena> cq = em.getCriteriaBuilder().createQuery(Resena.class);
            cq.select(cq.from(Resena.class));
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

    // Buscar una reseña por ID
    public Resena findResena(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Resena.class, id);
        } finally {
            em.close();
        }
    }

    // Contar el total de reseñas
    public int getResenaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            Root<Resena> rt = cq.from(Resena.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Resena> findResenasByTipoComida(String tipoComida) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT r FROM Resena r WHERE r.tipoComida = :tipoComida", Resena.class);
            query.setParameter("tipoComida", tipoComida);
            return query.getResultList();
        } finally {
            em.close();
        }
    }


    public List<Resena> findResenasByTipoComidaWithReactions(String tipoComida) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT r FROM Resena r LEFT JOIN FETCH r.reacciones WHERE r.tipoComida = :tipoComida", Resena.class)
                    .setParameter("tipoComida", tipoComida)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Resena findResenaConComentarios(Long id) {
        EntityManager em = getEntityManager();
        try {
            // Consulta que carga la reseña con los comentarios para inicializar la colección
            Query query = em.createQuery("SELECT r FROM Resena r LEFT JOIN FETCH r.comentarios WHERE r.id = :id");
            query.setParameter("id", id);
            return (Resena) query.getSingleResult();
        } finally {
            em.close();
        }
    }
}
