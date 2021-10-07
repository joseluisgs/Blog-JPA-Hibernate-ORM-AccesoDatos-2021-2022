package es.joseluisgs.dam.blog.repository;


import es.joseluisgs.dam.blog.dao.User;
import es.joseluisgs.dam.blog.manager.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class UserRepository implements CrudRespository<User, Long> {
    @Override
    public List<User> findAll()  {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<User> query = hc.getManager().createNamedQuery("User.findAll", User.class);
        List<User> list = query.getResultList();
        hc.close();
        return list;
    }

    @Override
    public User getById(Long ID) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        User user = hc.getManager().find(User.class, ID);
        hc.close();
        if(user != null)
            return user;
        throw new SQLException("Error UserRepository no existe usuario con ID: " + ID);
    }

    @Override
    public User save(User user) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().persist(user);
            hc.getTransaction().commit();
            hc.close();
            return user;
        }catch (Exception e) {
            throw new SQLException("Error UserRepository al insertar usuario en BD");
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public User update(User user) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(user);
            hc.getTransaction().commit();
            hc.close();
            return user;
        }catch (Exception e) {
            throw new SQLException("Error UserRepository al actualizar usuario con id: " + user.getId());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }

    }

    @Override
    public User delete(User user) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
            user = hc.getManager().find(User.class, user.getId());
            hc.getManager().remove(user);
            hc.getTransaction().commit();
            hc.close();
            return user;
        }catch (Exception e) {
            throw new SQLException("Error UserRepository al eliminar usuario con id: " + user.getId());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    public User getByEmail(String userMail) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        User user =  hc.getManager().createNamedQuery("User.getByMail", User.class)
                .setParameter(1, userMail)
                        .getSingleResult();
        hc.close();
        if(user != null)
            return user;
        throw new SQLException("Error UserRepository no existe usuario con Email: " + userMail);
    }
}
