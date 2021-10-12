package es.joseluisgs.dam.blog.controller;

import es.joseluisgs.dam.blog.dto.UserDTO;
import es.joseluisgs.dam.blog.repository.UserRepository;
import es.joseluisgs.dam.blog.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserController {
    private static UserController controller = null;

    // Mi Servicio unido al repositorio
    private final UserService userService;


    // Implementamos nuestro Singleton para el controlador
    private UserController(UserService userService) {
        this.userService = userService;
    }

    public static UserController getInstance() {
        if (controller == null) {
            controller = new UserController(new UserService(new UserRepository()));
        }
        return controller;
    }

    public List<UserDTO> getAllUsers() {
        // Vamos a devolver el JSON de las categorías
        try {
           return userService.getAllUsers();
        } catch (SQLException e) {
            System.err.println("Error UserController en getAllUser: " + e.getMessage());
            return null;
        }
    }

    public UserDTO getUserById(Long id) {
        // Vamos a devolver el JSON de las categorías
        try {
            return userService.getUserById(id);
        } catch (SQLException e) {
            System.err.println("Error UserController en getUserById " + e.getMessage());
            return null;
        }
    }

    public UserDTO postUser(UserDTO userDTO) {
        try {
            return userService.postUser(userDTO);
        } catch (SQLException e) {
            System.err.println("Error UserController en postUser " + e.getMessage());
            return null;
        }
    }

    public UserDTO updateUser(UserDTO userDTO) {
        try {
            return userService.updateUser(userDTO);
        } catch (SQLException e) {
            System.err.println("Error UserController en updateUser " + e.getMessage());
            return null;
        }
    }

    public UserDTO deleteUser(UserDTO userDTO) {
        try {
           return userService.deleteUser(userDTO);
        } catch (SQLException e) {
            System.err.println("Error UserController en deleteUser " + e.getMessage());
            return null;
        }
    }

    // Lo hago así para que veas otra forma de no devolver null y evitar errores.
    public Optional<UserDTO> getUserByIdOptional(Long id) {
        try {
            return Optional.of(userService.getUserById(id));
        } catch (SQLException e) {
            System.err.println("Error UserController en getUserById: " + e.getMessage());
            return Optional.empty();
        }
    }
}

