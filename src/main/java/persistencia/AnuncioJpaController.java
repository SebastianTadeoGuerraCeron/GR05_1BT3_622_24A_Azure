package persistencia;

import jakarta.persistence.*;
import modelo.Anuncio;
import java.util.List;

public class AnuncioJpaController {

    private final EntityManagerFactory emf;

    public AnuncioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public AnuncioJpaController() {
        emf = Persistence.createEntityManagerFactory("JavaWebLasHuequitas");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Anuncio anuncio) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(anuncio);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Anuncio> findAnuncioEntities() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Anuncio a", Anuncio.class).getResultList();
        } finally {
            em.close();
        }
    }
}
