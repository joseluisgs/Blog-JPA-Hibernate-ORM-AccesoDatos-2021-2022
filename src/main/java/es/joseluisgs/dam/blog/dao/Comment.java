package es.joseluisgs.dam.blog.dao;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "comment") // Ojo con la minuscula que en la tabla está así
// Todos los comentarios
@NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
public class Comment {
    private long id;
    private String texto;
    private Timestamp fechaPublicacion;
    private String uuid;
    private User user;
    private Post post;

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
    @Column(name = "texto", nullable = false, length = -1)
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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

    @Basic
    @Column(name = "uuid", nullable = false, length = 100)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && Objects.equals(texto, comment.texto) && Objects.equals(fechaPublicacion, comment.fechaPublicacion) && Objects.equals(uuid, comment.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, texto, fechaPublicacion, uuid);
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
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    // No es obligatorio, pero al hacerlo podemos tener problemas con la recursividad de las llamadas
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", uuid='" + uuid + '\'' +
                // Cuidado con esto que si no los post que tengan comentarios entra en recursividad
                ", user=" + user +
                ", post= post=Post{id:" + post.getId() + ", titulo=" + post.getTitulo() +
                "}}";
    }
}
