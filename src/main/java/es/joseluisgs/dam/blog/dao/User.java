package es.joseluisgs.dam.blog.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

// @Data Ojo con el data que entra en un bucle infinito por la definición de la relación muchos a uno, debes hacer el string a mano
// y Quitar los posts
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user") // Ojo con la minuscula que en la tabla está así
// Todos los usuarios
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
// Todos los usuarios con emial indicados
@NamedQuery(name = "User.getByMail", query = "SELECT u FROM User u WHERE u.email = ?1")
// Todos los post de un usuario
@NamedQuery(name = "User.getMyPosts", query = "SELECT u.posts FROM User u WHERE u.id = ?1")
public class User {
    private long id;
    private String nombre;
    private String email;
    private String password;
    private Date fechaRegistro;
    private Login login;
    private Collection<Post> posts;

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
    @Column(name = "nombre", nullable = false, length = 100)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @ColumnTransformer(write = " SHA(?) ")
    // Le decimos que lo transforme con esta función. Nos ahorramos cifrarlo nosotros
    @Column(name = "password", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @CreationTimestamp // Es una marca de tiempo
    @Column(name = "fecha_registro", nullable = false)
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(nombre, user.nombre) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(fechaRegistro, user.fechaRegistro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, email, password, fechaRegistro);
    }

    @OneToOne(mappedBy = "user")
    public Login getLogin() {
        return login;
    }

    public void setLogin(Login loginById) {
        this.login = loginById;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", login=" + login +
                '}';
    }

    // @OneToMany(fetch = FetchType.EAGER, mappedBy = "topic", cascade = CascadeType.ALL)
    // Si lo ponemos a lazy perdemos el contecto de la sesión.. a veces y te puedes saltarte una excepción
    /* En @OneToMany el fetch type default es Lazy, esto hace que el atributo posts no sea instanciado hasta que se haga getPosts().
       El problema es que en ese momento ya no cuentas con la Session de JPA, es decir, que la llamada a getPosts()
       debería haber ocurrido antes cuando estabas buscando los datos en el userRepository.
        cambia el comportamiento default con @OneToMany(fetch=FetchType.EAGER).
        Esto hace que friends se instancie junto con el resto de los atributos.
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    public Collection<Post> getPosts() {
        return posts;
    }

    // No es necesario si no queremos cambiar los post desde usuario
    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }
}
