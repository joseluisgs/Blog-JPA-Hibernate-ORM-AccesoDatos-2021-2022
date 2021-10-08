package es.joseluisgs.dam.blog.dto;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.joseluisgs.dam.blog.dao.Post;
import es.joseluisgs.dam.blog.dao.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Builder
@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String texto;
    private Timestamp fechaPublicacion;
    // Autor que la realiza
    private User user;
    // Post al que pertenece
    private Post post;

    // From/To JSON
    public static CategoryDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, CategoryDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder()
                // .addSerializationExclusionStrategy(strategy)
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(this);
    }

    @Override
    // DE comentario nos interesa saber su autor, porque el foro sabemos cual si accedemos por foro
    // Asi que en vez d eimprimirlo todo, solo haremos cosas que nos interese
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", user=" + user +
                ", post= Post{id:" + post.getId() + ", titulo=" + post.getTitulo() + ", " + "url=" + post.getUrl() +
                "}}";
    }
}
