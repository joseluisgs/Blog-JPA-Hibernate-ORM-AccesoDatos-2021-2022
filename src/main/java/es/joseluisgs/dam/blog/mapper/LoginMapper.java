package es.joseluisgs.dam.blog.mapper;

import es.joseluisgs.dam.blog.dao.Login;
import es.joseluisgs.dam.blog.dto.LoginDTO;

public class LoginMapper extends BaseMapper<Login, LoginDTO> {
    @Override
    public Login fromDTO(LoginDTO item) {
        Login login = new Login();
        login.setUserId(item.getUser().getId());
        login.setUltimoAcceso(item.getUltimoAcceso());
        login.setToken(item.getToken());
        return login;
    }

    @Override
    public LoginDTO toDTO(Login item) {
        return LoginDTO.builder()
                .user(item.getUser())
                .ultimoAcceso(item.getUltimoAcceso())
                .token(item.getToken())
                .build();
    }
}
