package es.joseluisgs.dam.blog.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user") // Ojo con la minuscula que en la tabla está así
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User {
    private long id;
    private String nombre;
    private String email;
    private String password;
    private Date fechaRegistro;
    private Login login;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    @ColumnTransformer(write=" SHA(?) ") // Le decimos que lo transforme con esta función. Nos ahorramos cifrarlo nosotros
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
}
