package es.joseluisgs.dam.blog.dto;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import es.joseluisgs.dam.blog.dao.Post;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Builder
@Getter
@Setter
// Exposé expone solo los campso que queramos en el JSON
public class UserDTO {
    // @Expose
    private Long id;
    // @Expose
    private String nombre;
    // @Expose
    private String email;
    // @Expose
    private Date fechaRegistro;

    // TODO Bidireccionalidad
    // Lista de Comentarios
    //private Set<Comment> comentarios = new HashSet<>();
    // Lista de Posts
    private Set<Post> posts = new HashSet<>();
    // Su login activo si lo tiene
    //private Login login;

    // Eliminar campos de las serialización
    // https://www.baeldung.com/gson-exclude-fields-serialization
    private String password;

    // From/To JSON
    public static UserDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, UserDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation() // Quitamos los campos que no están expuestos @expose y evitamos lo anterior
                .setPrettyPrinting()
                .create();
        // Otra manera de quitar un campo determinado para imprimir
        // prettyGson.toJsonTree(this).getAsJsonObject().remove("password");
        return prettyGson.toJson(this);
    }

//    @Override
//    public String toString() {
//        return this.toJSON();
//    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", posts=" + posts +
                ", password='" + password + '\'' +
                '}';
    }
}