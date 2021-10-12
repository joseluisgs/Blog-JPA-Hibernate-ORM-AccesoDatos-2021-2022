package es.joseluisgs.dam.blog.controller;

import es.joseluisgs.dam.blog.dto.PostDTO;
import es.joseluisgs.dam.blog.repository.PostRepository;
import es.joseluisgs.dam.blog.service.PostService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PostController {
    private static PostController controller = null;

    // Mi Servicio unido al repositorio
    private final PostService postService;

    // Implementamos nuestro Singleton para el controlador
    private PostController(PostService postService) {
        this.postService = postService;
    }

    public static PostController getInstance() {
        if (controller == null) {
            controller = new PostController(new PostService(new PostRepository()));
        }
        return controller;
    }

    // Ejemplo de operaciones
    public List<PostDTO> getAllPost() {
        try {
            return postService.getAllPosts();
        } catch (SQLException e) {
            System.err.println("Error PostController en getAllPots: " + e.getMessage());
            return null;
        }
    }

    public PostDTO getPostById(Long id) {
        try {
           return postService.getPostById(id);
        } catch (SQLException e) {
            System.err.println("Error PostController en getPostById " + e.getMessage());
            return null;
        }
    }

    public PostDTO postPost(PostDTO postDTO) {
        try {
            return postService.postPost(postDTO);
        } catch (SQLException e) {
            System.err.println("Error PostController en postPost: " + e.getMessage());
            return null;
        }
    }

    public PostDTO updatePost(PostDTO postDTO) {
        try {
            return postService.updatePost(postDTO);
        } catch (SQLException e) {
            System.err.println("Error PostController en updatePost: " + e.getMessage());
            return null;
        }
    }

    public PostDTO deletePost(PostDTO postDTO) {
        try {
            return postService.deletePost(postDTO);
        } catch (SQLException e) {
            System.err.println("Error PostController en deletePost: " + e.getMessage());
            return null;
        }
    }

    // Lo hago como Optional para que veas como deberías hacerlo sin devolver siempre null
    public Optional<PostDTO> getPostByIdOptional(Long id) {
        try {
            return Optional.of(postService.getPostById(id));
        } catch (SQLException e) {
            System.err.println("Error PostController en getPostById: " + e.getMessage());
            return Optional.empty();
        }
    }

    public List<PostDTO> getPostByUserId(Long userId) {
        // Vamos a devolver el JSON de las categorías
        return postService.getPostsByUserId(userId);
    }
}