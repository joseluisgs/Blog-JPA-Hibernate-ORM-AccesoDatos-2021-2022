package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.dao.Login;
import es.joseluisgs.dam.blog.dao.User;
import es.joseluisgs.dam.blog.manager.HibernateController;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class LoginRepository implements CrudRespository<Login, Long> {
    @Override
    public List<Login> findAll() throws SQLException {
        throw new SQLException("Error: Método findAll no implementado");
    }

    @Override
    public Login getById(Long aLong) throws SQLException {
        throw new SQLException("Error: Método getById  no implementado");
    }

    @Override
    public Login save(Login login) throws SQLException {
        UUID uuid = UUID.randomUUID();
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            login.setToken(uuid.toString());
            hc.getManager().persist(login);
            hc.getTransaction().commit();
            hc.close();
            return login;
        }catch (Exception e) {
            throw new SQLException("Error LoginRepository al insertar login en BD");
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Login update(Login login) throws SQLException {
        throw new SQLException("Error: Método update no implementado");
    }

    @Override
    public Login delete(Login login) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
            login = hc.getManager().find(Login.class, login.getUserId());
            hc.getManager().remove(login);
            hc.getTransaction().commit();
            hc.close();
            return login;
        }catch (Exception e) {
            throw new SQLException("Error LoginRepository al eliminar login con id: " + login.getUserId());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }
}
