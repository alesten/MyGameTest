package facade;

import com.google.common.hash.Hashing;
import entity.User;
import infrastructure.IUserFacade;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import rest.exception.UserAlreadyExistsException;
import utils.Password;

public class UserFacade implements Closeable, IUserFacade {

    private final EntityManager em;

    public UserFacade(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    @Override
    public User getUser(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getUsers() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public User addUser(User user) throws UserAlreadyExistsException{
        if(!em.createNamedQuery("User.findUserWithUserName").setParameter("username", user.getUserName()).getResultList().isEmpty())
            throw new UserAlreadyExistsException("Username already in use");
        
        try {
            user.setPassword(Password.getSaltedHash(user.getPassword()));
        } catch (Exception ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }

    @Override
    public void close() throws IOException {
        em.close();
    }
}
