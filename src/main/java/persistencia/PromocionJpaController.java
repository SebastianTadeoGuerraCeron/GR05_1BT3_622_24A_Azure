package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import modelo.Promocion;

import java.util.List;

public class PromocionJpaController {
    private EntityManagerFactory emf;

    public PromocionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Promocion promocion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(promocion);
            tx.commit();
        } catch (PersistenceException e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Promocion promocion) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(promocion);
            tx.commit();
        } catch (PersistenceException e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Promocion promocion = em.getReference(Promocion.class, id);
            em.remove(promocion);
            tx.commit();
        } catch (PersistenceException e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Promocion findPromocion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Promocion.class, id);
        } finally {
            em.close();
        }
    }

    public List<Promocion> findPromociones() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Promocion p", Promocion.class).getResultList();
        } finally {
            em.close();
        }
    }
}
