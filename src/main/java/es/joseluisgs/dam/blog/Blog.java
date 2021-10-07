package es.joseluisgs.dam.blog;

import es.joseluisgs.dam.blog.dao.Category;
import es.joseluisgs.dam.blog.database.DataBaseController;
import es.joseluisgs.dam.blog.repository.CategoryRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

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

    public void checkService() {
        DataBaseController controller = DataBaseController.getInstance();
        try {
            controller.open();
            Optional<ResultSet> rs = controller.select("SELECT 'Hello World'");
            if (rs.isPresent()) {
                rs.get().first();
                controller.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        }
    }

    public void initDataBase() {
        String sqlFile = System.getProperty("user.dir") + File.separator + "sql" + File.separator + "blog.sql";
        System.out.println(sqlFile);
        DataBaseController controller = DataBaseController.getInstance();
        try {
            controller.open();
            controller.initData(sqlFile);
            controller.close();
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el fichero de datos iniciales: " + e.getMessage());
            System.exit(1);
        }
    }

    public void Categories() {
        try {
            CategoryRepository categoryController = new CategoryRepository();

            System.out.println("GET Todas las categorías");
            categoryController.findAll().forEach(System.out::println);

            System.out.println("GET Categoría con ID = 2");
            System.out.println(categoryController.getById(2L));

            System.out.println("POST Categoría");
            Category cat = new Category();
            cat.setTexto("Insert " + LocalDateTime.now().toString());
            System.out.println(categoryController.save(cat));

            cat = new Category();
            cat.setTexto("Insert Otra" + LocalDateTime.now().toString());
            System.out.println(categoryController.save(cat));

            System.out.println("UPDATE Categoría: 4");
            cat = categoryController.getById(4L);
            cat.setTexto("Update " + LocalDateTime.now().toString());
            System.out.println(categoryController.update(cat));

            System.out.println("DELETE Categoría: 6");
            cat = categoryController.getById(6L);
            System.out.println(categoryController.delete(cat));

        } catch (SQLException e) {
            System.err.println("Error al procesar Categorias: " + e.getMessage());
        }


    }
}
