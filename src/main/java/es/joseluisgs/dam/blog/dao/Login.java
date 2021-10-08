package es.joseluisgs.dam.blog.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "login") // Ojo con la minuscula que en la tabla está así
// Consulta por si queremos buscar logins por tokens
@NamedQuery(name = "Login.getByToken", query = "SELECT l FROM Login l WHERE l.token = ?1")
public class Login {
    private long userId;
    private Timestamp ultimoAcceso;
    private String token;
    private User user;

    @Id
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        // return userId;
        return user.getId();
    }

//    public void setUserId(long userId) {
//        this.userId = userId;
//    }

    @Basic
    @CreationTimestamp // Es una marca de tiempo
    @Column(name = "ultimo_acceso", nullable = false)
    public Timestamp getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Timestamp ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    @Basic
    // @ColumnTransformer(write=" UUID() ") // Le decimos que lo transforme con esta función. nos ahorramos hacerlo nosotros
    @Column(name = "token", nullable = false, length = 100)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return userId == login.userId && Objects.equals(ultimoAcceso, login.ultimoAcceso) && Objects.equals(token, login.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, ultimoAcceso, token);
    }

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.userId = user.getId();
    }
}
