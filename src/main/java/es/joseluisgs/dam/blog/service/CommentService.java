package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.dao.Comment;
import es.joseluisgs.dam.blog.dto.CommentDTO;
import es.joseluisgs.dam.blog.mapper.CommentMapper;
import es.joseluisgs.dam.blog.repository.CommentRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class CommentService extends BaseService<Comment, Long, CommentRepository> {
    CommentMapper mapper = new CommentMapper();

    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public CommentService(CommentRepository repository) {
        super(repository);
    }

    // Otras operaciones o especificaciones para CRUD
    // O podíamos mapear el nombre
    // O simplemente ocultar las que no queramos usar en niveles superiores
    public List<CommentDTO> getAllComments() throws SQLException {
        // Obtenemos la lista
        List<CommentDTO> result = mapper.toDTO(this.findAll());
        return result;
    }

    public CommentDTO getCommentById(Long id) throws SQLException {
        CommentDTO commentDTO = mapper.toDTO(this.getById(id));
        return commentDTO;
    }

    public CommentDTO postComment(CommentDTO commentDTO) throws SQLException {
        Comment comment = this.save(mapper.fromDTO(commentDTO));
        CommentDTO res = mapper.toDTO(comment);
        return res;
    }

    public CommentDTO updateComment(CommentDTO commentDTO) throws SQLException {
        Comment comment = this.update(mapper.fromDTO(commentDTO));
        CommentDTO res = mapper.toDTO(comment);
        return res;
    }

    public CommentDTO deleteComment(CommentDTO commentDTO) throws SQLException {
        Comment comment = this.delete(mapper.fromDTO(commentDTO));
        CommentDTO res = mapper.toDTO(comment);
        return res;
    }

//    private User getUserById(Long id) throws SQLException {
//        UserService service = new UserService(new UserRepository());
//        return service.getById(id);
//    }
//
//    private Post getPostById(Long id) throws SQLException {
//        PostService service = new PostService(new PostRepository());
//        return service.getById(id);
//    }
//
//    public List<Comment> getCommentsByPost(Long id) throws SQLException {
//        return repository.getByPost(id);
//    }
}
