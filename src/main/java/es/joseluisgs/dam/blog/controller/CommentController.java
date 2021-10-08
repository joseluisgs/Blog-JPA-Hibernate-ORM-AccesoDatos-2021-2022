package es.joseluisgs.dam.blog.controller;

import es.joseluisgs.dam.blog.dto.CommentDTO;
import es.joseluisgs.dam.blog.dto.PostDTO;
import es.joseluisgs.dam.blog.repository.CommentRepository;
import es.joseluisgs.dam.blog.service.CommentService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CommentController {
    private static CommentController controller = null;

    // Mi Servicio unido al repositorio
    private final CommentService commentService;

    // Implementamos nuestro Singleton para el controlador
    private CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    public static CommentController getInstance() {
        if (controller == null) {
            controller = new CommentController(new CommentService(new CommentRepository()));
        }
        return controller;
    }

    // Ejemplo de operaciones
    public List<CommentDTO> getAllComments() {
        // Vamos a devolver el JSON de las categorías
        try {
            return commentService.getAllComments();
        } catch (SQLException e) {
            System.err.println("Error CommentController en getAllComments: " + e.getMessage());
            return null;
        }
    }

    public CommentDTO getCommentById(Long id) {
        try {
            return commentService.getCommentById(id);
        } catch (SQLException e) {
            System.err.println("Error CommentController en getCommentById: " + e.getMessage());
            return null;
        }
    }

    public CommentDTO postComment(CommentDTO commentDTO) {
        try {
           return commentService.postComment(commentDTO);
        } catch (SQLException e) {
            System.err.println("Error CommentController en postComment: " + e.getMessage());
            return null;
        }
    }

    public CommentDTO updateComment(CommentDTO commentDTO) {
        try {
            return commentService.updateComment(commentDTO);
        } catch (SQLException e) {
            System.err.println("Error CommentController en updateCommment: " + e.getMessage());
            return null;
        }
    }

    public CommentDTO deleteComment(CommentDTO commentDTO) {
        try {
           return commentService.deleteComment(commentDTO);
        } catch (SQLException e) {
            System.err.println("Error CommentController en deleteComment: " + e.getMessage());
            return null;
        }
    }

    public Optional<CommentDTO> getCommentByIdOptional(Long id) {
        try {
            return Optional.of(commentService.getCommentById(id));
        } catch (SQLException e) {
            System.err.println("Error CommentController en getCommentById: " + e.getMessage());
            return Optional.empty();
        }
    }

}
