package es.joseluisgs.dam.blog.mapper;

import es.joseluisgs.dam.blog.dao.Comment;
import es.joseluisgs.dam.blog.dao.Post;
import es.joseluisgs.dam.blog.dto.PostDTO;

import java.util.List;

public class PostMapper extends BaseMapper<Post, PostDTO> {
    @Override
    public Post fromDTO(PostDTO item) {
        Post post = new Post();
        if (item.getId() != null) {
            post.setId(item.getId());
        }
        post.setTitulo(item.getTitulo());
        post.setUrl(item.getUrl());
        post.setContenido(item.getContenido());
        post.setFechaPublicacion(item.getFechaPublicacion());
        post.setUser(item.getUser());
        post.setCategory(item.getCategory());
        return post;

    }

    @Override
    public PostDTO toDTO(Post item) {
        return PostDTO.builder()
                .id(item.getId())
                .titulo(item.getTitulo())
                .url(item.getUrl())
                .contenido(item.getContenido())
                .fechaPublicacion(item.getFechaPublicacion())
                .user(item.getUser())
                .category(item.getCategory())
                .comments((List<Comment>) item.getComments())
                .build();
    }
}
