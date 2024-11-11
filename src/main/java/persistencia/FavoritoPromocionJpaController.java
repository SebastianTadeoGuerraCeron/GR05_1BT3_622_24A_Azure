package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import modelo.FavoritoPromocion;

public class FavoritoPromocionJpaController {

    private final EntityManagerFactory emf;

    public FavoritoPromocionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public FavoritoPromocionJpaController() {
        emf = Persistence.createEntityManagerFactory("JavaWebLasHuequitas");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FavoritoPromocion favoritoPromocion) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(favoritoPromocion);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public boolean hasUserReactedToPromocion(Long userId, Long promocionId) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT COUNT(f) FROM FavoritoPromocion f WHERE f.usuario.id = :userId AND f.promocion.id = :promocionId");
            query.setParameter("userId", userId);
            query.setParameter("promocionId", promocionId);
            return ((Long) query.getSingleResult()) > 0;
        } finally {
            em.close();
        }
    }

}