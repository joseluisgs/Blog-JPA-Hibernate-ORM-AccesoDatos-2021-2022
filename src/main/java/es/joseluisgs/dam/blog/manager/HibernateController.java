package es.joseluisgs.dam.blog.manager;


import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Controlador de Entidades de Hibernate JPA
 */
@Getter
public class HibernateController {
    private static HibernateController controller;

    // Creamos las EntityManagerFactory para manejar las entidades y transacciones
    private EntityManagerFactory entityManagerFactory;
    private EntityManager manager;
    private EntityTransaction transaction;

    private HibernateController() {
    }

    public static HibernateController getInstance() {
        if (controller == null)
            controller = new HibernateController();
        return controller;
    }

    public void open() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        manager = entityManagerFactory.createEntityManager();
        transaction = manager.getTransaction();
    }

    public void close() {
        manager.close();
        entityManagerFactory.close();
    }
}
