package es.joseluisgs.dam.blog;

import es.joseluisgs.dam.blog.controller.*;
import es.joseluisgs.dam.blog.dao.Comment;
import es.joseluisgs.dam.blog.database.DataBaseController;
import es.joseluisgs.dam.blog.dto.CategoryDTO;
import es.joseluisgs.dam.blog.dto.PostDTO;
import es.joseluisgs.dam.blog.dto.UserDTO;
import es.joseluisgs.dam.blog.mapper.CategoryMapper;
import es.joseluisgs.dam.blog.mapper.UserMapper;
import es.joseluisgs.dam.blog.repository.CommentRepository;
import es.joseluisgs.dam.blog.service.CommentService;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
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
        System.out.println(categoryController.getAllCategories());

        System.out.println("GET Categoría con ID = 2");
        System.out.println(categoryController.getCategoryById(2L));

        System.out.println("POST Categoría");
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .texto("Insert " + LocalDateTime.now())
                .build();
        System.out.println(categoryController.postCategory(categoryDTO));

        categoryDTO = CategoryDTO.builder()
                .texto("Insert Otra " + LocalDateTime.now())
                .build();
        System.out.println(categoryController.postCategory(categoryDTO));

        System.out.println("UPDATE Categoría con ID 4");
        Optional<CategoryDTO> optionalCategoryDTO = categoryController.getCategoryByIdOptional(4L);
        if (optionalCategoryDTO.isPresent()) {
            optionalCategoryDTO.get().setTexto("Update " + LocalDateTime.now());
            System.out.println(categoryController.updateCategory(optionalCategoryDTO.get()));
        }

        System.out.println("DELETE Categoría con ID 6");
        optionalCategoryDTO = categoryController.getCategoryByIdOptional(6L);
        if (optionalCategoryDTO.isPresent()) {
            System.out.println(optionalCategoryDTO.get());
            System.out.println(categoryController.deleteCategory(optionalCategoryDTO.get()));
        }

        System.out.println("FIN CATEGORIAS");
    }

    public void Users() {
        System.out.println("INICIO USUARIOS");

        UserController userController = UserController.getInstance();

        System.out.println("GET Todos los usuarios");
        System.out.println(userController.getAllUsers());

        System.out.println("GET Usuario con ID = 2");
        System.out.println(userController.getUserById(2L));

        System.out.println("POST nuevo Usuario 1");
        UserDTO userDTO = UserDTO.builder()
                .nombre("Insert " + LocalDateTime.now())
                .email("email" + LocalDateTime.now() + "@mail.com")
                .password("1234")
                .build();
        System.out.println(userController.postUser(userDTO));

        System.out.println("POST nuevo Usuario 2");
        userDTO = UserDTO.builder()
                .nombre("Insert Otro" + LocalDateTime.now())
                .email("emailOtro" + LocalDateTime.now() + "@mail.com")
                .password("1234")
                .build();
        System.out.println(userController.postUser(userDTO));

        System.out.println("UPDATE Usuario con ID 4");
        Optional<UserDTO> optionalUserDTO = userController.getUserByIdOptional(4L);
        if (optionalUserDTO.isPresent()) {
            optionalUserDTO.get().setNombre("Update " + LocalDateTime.now());
            optionalUserDTO.get().setEmail("emailUpdate" + LocalDateTime.now() + "@mail.com");
            System.out.println(userController.updateUser(optionalUserDTO.get()));
        }

        System.out.println("DELETE Usuario con ID 6");
        optionalUserDTO = userController.getUserByIdOptional(6L);
        if (optionalUserDTO.isPresent()) {
            System.out.println(userController.deleteUser(optionalUserDTO.get()));
        }

        System.out.println("FIN USUARIOS");
    }

    public void Login() {
        System.out.println("INICIO LOGIN");

        LoginController loginController = LoginController.getInstance();

        System.out.println("Login con un usario que SI existe");
        System.out.println(loginController.login("pepe@pepe.es", "1234"));

        System.out.println("Login con un usario que SI existe Y mal Password datos correctos");
        System.out.println(loginController.login("pepe@pepe.es", "1255"));

        System.out.println("Login con un usario que NO existe o mal Password datos correctos");
        System.out.println(loginController.login("pepe@pepe.com", "1255"));

        System.out.println("Logout de usuario que está logueado");
        System.out.println(loginController.logout(1L));

        System.out.println("Logout de usuario que no está logueado");
        System.out.println(loginController.logout(33L));

        System.out.println("FIN LOGIN");
    }

    public void Posts() {
        System.out.println("INICIO POSTS");

        PostController postController = PostController.getInstance();

        System.out.println("GET Todos los Post");
        System.out.println(postController.getAllPost());

        System.out.println("GET Post con ID = 2");
        System.out.println(postController.getPostById(2L));

        System.out.println("POST Insertando Post 1");
        // Lo primero que necesito es un usuario...
        UserController userController = UserController.getInstance();
        UserDTO user = userController.getUserByIdOptional(1L).get(); // Sé que el id existe ...
        // Y una categoría
        CategoryController categoryController = CategoryController.getInstance();
        CategoryDTO category = categoryController.getCategoryByIdOptional(1L).get();

        // Neceistamos mapearlos a objetos y no DTO, no debería ser así y trabajar con DTO completos, pero no es tan crucial para el CRUD
        UserMapper userMapper = new UserMapper();
        CategoryMapper categoryMapper = new CategoryMapper();

        System.out.println("POST Insertando Post 2");
        PostDTO postDTO = PostDTO.builder()
                .titulo("Insert " + LocalDateTime.now())
                .contenido("Contenido " + Instant.now().toString())
                .url("http://" + Math.random() + ".dominio.com")
                .user(userMapper.fromDTO(user))
                .category(categoryMapper.fromDTO(category))
                .build();

        System.out.println(postController.postPost(postDTO));
        user = userController.getUserByIdOptional(1L).get();
        category = categoryController.getCategoryByIdOptional(1L).get();
        postDTO = PostDTO.builder()
                .titulo("Insert Otro" + LocalDateTime.now())
                .contenido("Contenido Otro" + Instant.now().toString())
                .url("http://" + Math.random() + ".dominio.com")
                .user(userMapper.fromDTO(user))
                .category(categoryMapper.fromDTO(category))
                .build();
        System.out.println(postController.postPost(postDTO));

        System.out.println("UPDATE Post con ID 5");
        Optional<PostDTO> optionalPostDTO = postController.getPostByIdOptional(5L);
        if (optionalPostDTO.isPresent()) {
            optionalPostDTO.get().setTitulo("Update " + LocalDateTime.now());
            optionalPostDTO.get().setContenido("emailUpdate" + LocalDateTime.now() + "@mail.com");
            System.out.println(postController.updatePost(optionalPostDTO.get()));
        }

        System.out.println("DELETE Post con ID 6");
        optionalPostDTO = postController.getPostByIdOptional(6L);
        if (optionalPostDTO.isPresent()) {
            System.out.println(postController.deletePost(optionalPostDTO.get()));
        }

        System.out.println("GET By Post con User ID 1 usando la Relación Post --> Usuario");
        postController.getPostByUserId(1L).forEach(System.out::println);

        System.out.println("GET By Post con User ID 1 usando la Relación Usuario --> Post");
        user = userController.getUserByIdOptional(1L).get();
        // Por cierto, prueba quitando el FetchType.EAGER de getPost de User y mira que pasa. ¿Lo entiendes?
        user.getPosts().forEach(System.out::println);

        System.out.println("FIN POSTS");
    }

    public void Comments() {
        System.out.println("INICIO COMENTARIOS");

        CommentController commentController = CommentController.getInstance();

        System.out.println("GET Todos los Comentarios");
        System.out.println(commentController.getAllComments());

        System.out.println("FIN COMENTARIOS");
    }
}
