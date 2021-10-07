package es.joseluisgs.dam.blog;

import es.joseluisgs.dam.blog.controller.CategoryController;
import es.joseluisgs.dam.blog.dao.Category;
import es.joseluisgs.dam.blog.database.DataBaseController;
import es.joseluisgs.dam.blog.dto.CategoryDTO;
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
        System.out.println("INICIO CATEGORIAS");

        CategoryController categoryController = CategoryController.getInstance();

        System.out.println("GET Todas las categorías");
        System.out.println(categoryController.getAllCategoriesJSON());

        System.out.println("GET Categoría con ID = 2");
        System.out.println(categoryController.getCategoryByIdJSON(2L));

        System.out.println("POST Categoría");
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .texto("Insert " + LocalDateTime.now().toString())
                .build();
        System.out.println(categoryController.postCategoryJSON(categoryDTO));

        categoryDTO = CategoryDTO.builder()
                .texto("Insert Otra " + LocalDateTime.now().toString())
                .build();
        System.out.println(categoryController.postCategoryJSON(categoryDTO));

        System.out.println("UPDATE Categoría: 4");
        Optional<CategoryDTO> optionalCategoryDTO = categoryController.getCategoryById(4L);
        if(optionalCategoryDTO.isPresent()) {
            System.out.println(optionalCategoryDTO.get());
            optionalCategoryDTO.get().setTexto("Update " + LocalDateTime.now().toString());
            System.out.println(categoryController.updateCategoryJSON(optionalCategoryDTO.get()));
        }

        System.out.println("DELETE Categoría: 6");
        optionalCategoryDTO = categoryController.getCategoryById(6L);
        if(optionalCategoryDTO.isPresent()) {
            System.out.println(optionalCategoryDTO.get());
            System.out.println(categoryController.deleteCategoryJSON(optionalCategoryDTO.get()));
        }

        System.out.println("FIN CATEGORIAS");


    }
}
