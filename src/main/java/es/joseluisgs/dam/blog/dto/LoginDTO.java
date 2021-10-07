package es.joseluisgs.dam.blog.dto;

import es.joseluisgs.dam.blog.dao.User;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;


@Data
@Builder
public class LoginDTO {
    private User user;
    private Timestamp ultimoAcceso;
    private String token;
}
