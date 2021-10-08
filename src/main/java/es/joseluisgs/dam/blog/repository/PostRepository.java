package es.joseluisgs.dam.blog.repository;


import es.joseluisgs.dam.blog.dao.Post;
import es.joseluisgs.dam.blog.manager.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class PostRepository implements CrudRespository<Post, Long> {
    @Override
    public List<Post> findAll() {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Post> query = hc.getManager().createNamedQuery("Post.findAll", Post.class);
        List<Post> list = query.getResultList();
        hc.close();
        return list;
    }

    @Override
    public Post getById(Long ID) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        Post post = hc.getManager().find(Post.class, ID);
        hc.close();
        if (post != null)
            return post;
        throw new SQLException("Error PostRepository no existe post con ID: " + ID);
    }

    @Override
    public Post save(Post post) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().persist(post);
            hc.getTransaction().commit();
            hc.close();
            return post;
        } catch (Exception e) {
            throw new SQLException("Error PostRepository al insertar post en BD");
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Post update(Post post) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(post);
            hc.getTransaction().commit();
            hc.close();
            return post;
        } catch (Exception e) {
            throw new SQLException("Error PostRepository al actualizar post con id: " + post.getId());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }

    }

    @Override
    public Post delete(Post post) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
            post = hc.getManager().find(Post.class, post.getId());
            hc.getManager().remove(post);
            hc.getTransaction().commit();
            hc.close();
            return post;
        } catch (Exception e) {
            throw new SQLException("Error PostRepository al eliminar post con id: " + post.getId());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    public List<Post> getByUserId(Long userId) {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        List<Post> list = hc.getManager().createNamedQuery("Post.getByUserId", Post.class)
                .setParameter(1, userId).getResultList();
        hc.close();
        return list;
    }

}
