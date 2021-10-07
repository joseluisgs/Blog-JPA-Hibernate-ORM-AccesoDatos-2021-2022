package es.joseluisgs.dam.blog.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="category") // Ojo con la minuscula que en la tabla está así
@NamedQuery(name = "findAll", query = "SELECT c FROM Category c")
public class Category {
    private long id;
    private String texto;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // La clave es autonumérica en MariaDB
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "texto", nullable = false, length = 255)
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != category.id) return false;
        if (texto != null ? !texto.equals(category.texto) : category.texto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (texto != null ? texto.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                '}';
    }
}
