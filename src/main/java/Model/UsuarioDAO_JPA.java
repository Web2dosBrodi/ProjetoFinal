package Model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author luiz-
 */
public class UsuarioDAO_JPA {
    private static EntityManagerFactory factory = null;

    public EntityManagerFactory getFactory() {
        if (UsuarioDAO_JPA.factory == null) {
            UsuarioDAO_JPA.factory = Persistence.createEntityManagerFactory("agendacomunitaria");
        }
        return UsuarioDAO_JPA.factory;
    }
    
    public Usuario logarUsuario(Usuario user){
        EntityManager em = getFactory().createEntityManager();
        Usuario usuario = (Usuario) em.createQuery("SELECT * FROM usuario WHERE "
                                        + "name_user=:nome AND password=:senha")
                                .setParameter("nome", user.getUserName())
                                .setParameter("senha", user.getPassword())
                                .getSingleResult();
        user.setLogado(true);
        em.close();
        return usuario;
    }
    
    public void adicionaUsuario(Usuario usuario) {
        EntityManager em = getFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }
    
    public List<Usuario> getListaUsuarios() {
        EntityManager em = getFactory().createEntityManager();
        List <Usuario> list = em.createQuery("SELECT * FROM usuario")
                              .getResultList();
        em.close();
        return list;
    }
    
}
