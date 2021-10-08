package es.joseluisgs.dam.blog.mapper;

import es.joseluisgs.dam.blog.dao.Post;
import es.joseluisgs.dam.blog.dao.User;
import es.joseluisgs.dam.blog.dto.UserDTO;

import java.util.List;

public class UserMapper extends BaseMapper<User, UserDTO> {
    @Override
    public User fromDTO(UserDTO item) {
        User user = new User();
        if (item.getId() != null) {
            user.setId(item.getId());
        }
        user.setNombre(item.getNombre());
        user.setEmail(item.getEmail());
        user.setPassword(item.getPassword());
        user.setFechaRegistro(item.getFechaRegistro());
        user.setPosts(item.getPosts());
        user.setComments(item.getComentarios());
        return user;
    }

    @Override
    public UserDTO toDTO(User item) {
        return UserDTO.builder()
                .id(item.getId())
                .nombre(item.getNombre())
                .email(item.getEmail())
                .password(item.getPassword())
                .fechaRegistro(item.getFechaRegistro())
                .posts(item.getPosts())
                .comentarios(item.getComments())
                .build();
    }
}
