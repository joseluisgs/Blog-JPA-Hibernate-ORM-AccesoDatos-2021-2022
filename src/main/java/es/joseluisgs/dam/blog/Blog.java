package es.joseluisgs.dam.blog;

import es.joseluisgs.dam.blog.dao.Category;
import es.joseluisgs.dam.blog.manager.HibernateController;

import javax.persistence.*;
import java.util.List;

public class Blog {
    private static Blog instance;

    private Blog() {
    }

    public static Blog getInstance() {
        if (instance == null) {
            instance = new Blog();
        }
        return instance;
    }

    public void Categories() {
        // Listamos todas las categorías
        HibernateController hibernateController = HibernateController.getInstance();

        System.out.println("Listar todos los Departamentos con consulta sobre la marcha");
        Query query= hibernateController.getManager().createQuery("SELECT c FROM Category c");
        List<Category> list = query.getResultList();
        list.forEach(System.out::println);

    }
}
