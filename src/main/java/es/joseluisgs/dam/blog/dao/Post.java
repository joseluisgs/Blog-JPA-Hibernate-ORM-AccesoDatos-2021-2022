package es.joseluisgs.dam.blog.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
// Consulta para obtener todos
@NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p")
// Consulta para obtener todos los post dado el id de un usuario
@NamedQuery(name = "Post.getByUserId", query = "SELECT p FROM Post p WHERE p.user.id = ?1")
@Table(name = "post") // Ojo con la minuscula que en la tabla está así
public class Post {
    private long id;
    private String titulo;
    private String url;
    private String contenido;
    private Timestamp fechaPublicacion;
    private User user;
    private Category category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "titulo", nullable = false, length = 250)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 250)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "contenido", nullable = false, length = -1)
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Basic
    @CreationTimestamp // Es una marca de tiempo
    @Column(name = "fecha_publicacion", nullable = false)
    public Timestamp getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Timestamp fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && Objects.equals(titulo, post.titulo) && Objects.equals(url, post.url) && Objects.equals(contenido, post.contenido) && Objects.equals(fechaPublicacion, post.fechaPublicacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, url, contenido, fechaPublicacion);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
