package es.joseluisgs.dam.blog.repository;


import es.joseluisgs.dam.blog.dao.Comment;
import es.joseluisgs.dam.blog.manager.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CommentRepository implements CrudRespository<Comment, Long> {
    @Override
    public List<Comment> findAll() {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Comment> query = hc.getManager().createNamedQuery("Comment.findAll", Comment.class);
        List<Comment> list = query.getResultList();
        hc.close();
        return list;
    }

    @Override
    public Comment getById(Long ID) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        Comment comment = hc.getManager().find(Comment.class, ID);
        hc.close();
        if (comment != null)
            return comment;
        throw new SQLException("Error CommentRepository no existe comentario con ID: " + ID);
    }

    @Override
    public Comment save(Comment comment) throws SQLException {
        UUID uuid = UUID.randomUUID();
        comment.setUuid(uuid.toString());
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().persist(comment);
            hc.getTransaction().commit();
            hc.close();
            return comment;
        } catch (Exception e) {
            throw new SQLException("Error CommentRepository al insertar comentario en BD");
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Comment update(Comment comment) throws SQLException {
        UUID uuid = UUID.randomUUID();
        comment.setUuid(uuid.toString());
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(comment);
            hc.getTransaction().commit();
            hc.close();
            return comment;
        } catch (Exception e) {
            throw new SQLException("Error CommentRepository al actualizar comentario con id: " + comment.getId());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }

    }

    @Override
    public Comment delete(Comment comment) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
            comment = hc.getManager().find(Comment.class, comment.getId());
            hc.getManager().remove(comment);
            hc.getTransaction().commit();
            hc.close();
            return comment;
        } catch (Exception e) {
            throw new SQLException("Error CommentRepository al eliminar comentario con id: " + comment.getId());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }
}
