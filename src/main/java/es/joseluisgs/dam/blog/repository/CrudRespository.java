package es.joseluisgs.dam.blog.repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudRespository<T, ID> {

    // Operaciones CRUD

    // Obtiene todos
    List<T> findAll();

    // Obtiene por ID
    T getById(ID id);

    // Salva
    T save(T t) throws SQLException;

    // Actualiza
    T update(T t) throws SQLException;

    // Elimina
    T delete(T t) throws SQLException;


}
