package es.joseluisgs.dam.blog.repository;


import es.joseluisgs.dam.blog.dao.Category;
import es.joseluisgs.dam.blog.manager.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class CategoryRepository implements CrudRespository<Category, Long> {
    @Override
    public List<Category> findAll()  {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Category> query = hc.getManager().createNamedQuery("findAll", Category.class);
        List<Category> list = query.getResultList();
        hc.close();
        return list;
    }

    @Override
    public Category getById(Long ID) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        Category category = hc.getManager().find(Category.class, ID);
        hc.close();
        if(category != null)
            return category;
        throw new SQLException("Error CategoryRepository no existe categoría con ID: " + ID);
    }

    @Override
    public Category save(Category category) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().persist(category);
            hc.getTransaction().commit();
            hc.close();
            return category;
        }catch (Exception e) {
            throw new SQLException("Error CategoryRepository al insertar cantegoria en BD");
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Category update(Category category) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(category);
            hc.getTransaction().commit();
            hc.close();
            return category;
        }catch (Exception e) {
            throw new SQLException("Error CategoryRepository al actualizar categoria con id: " + category.getId());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }

    }

    @Override
    public Category delete(Category category) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
            category = hc.getManager().find(Category.class, category.getId());
            hc.getManager().remove(category);
            hc.getTransaction().commit();
            hc.close();
            return category;
        }catch (Exception e) {
            throw new SQLException("Error CategoryRepository al eliminar categoria con id: " + category.getId());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }
}
