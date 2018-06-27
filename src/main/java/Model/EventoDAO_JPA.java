package Model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author White
 */
public class EventoDAO_JPA {

    private static EntityManagerFactory factory = null;

    public EntityManagerFactory getFactory() {
        if (EventoDAO_JPA.factory == null) {
            EventoDAO_JPA.factory = Persistence.createEntityManagerFactory("AgendaFiltros");
            System.out.println("Factory: " + factory);
        }
        return EventoDAO_JPA.factory;
    }

    public void insertEvent(Evento evento) {
        EntityManager em = getFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(evento);
        em.getTransaction().commit();
        em.close();
    }
    
    public List<Evento> getListaEventos() {
        EntityManager em = getFactory().createEntityManager();
        List<Evento> list = em.createQuery("SELECT e FROM Evento e")
                .getResultList();
        em.close();
        return list;
    }

    public List<Evento> getBuscaEvento(String nomeEvento) {
        EntityManager em = getFactory().createEntityManager();
        List<Evento> list = em.createQuery("SELECT e FROM Evento e WHERE e.nome like :busca")
                .setParameter("busca", nomeEvento)
                .getResultList();
        em.close();
        return list;
    }
    
    /*
    public void deletar (long id) {
        EntityManager em = this.getFactory().createEntityManager();
        Evento e = em.find(Evento.class, new Long(id));
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
        em.close();
    }*/
}
