package es.joseluisgs.dam.blog.mapper;

import es.joseluisgs.dam.blog.dao.Comment;
import es.joseluisgs.dam.blog.dto.CommentDTO;

public class CommentMapper extends BaseMapper<Comment, CommentDTO> {
    @Override
    public Comment fromDTO(CommentDTO item) {
        Comment comment = new Comment();
        if (item.getId() != null) {
            comment.setId(item.getId());
        }
        comment.setTexto(item.getTexto());
        comment.setFechaPublicacion(item.getFechaPublicacion());
        comment.setUser(item.getUser());
        comment.setPost(item.getPost());
        return comment;
    }

    @Override
    public CommentDTO toDTO(Comment item) {
        return CommentDTO.builder()
                .id(item.getId())
                .texto(item.getTexto())
                .fechaPublicacion(item.getFechaPublicacion())
                .user(item.getUser())
                .post(item.getPost())
                .build();
    }
}
