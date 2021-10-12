package es.joseluisgs.dam.blog.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category") // Ojo con la minuscula que en la tabla está así
// Creamos una named Query, debe tener nombre único para obtener todas las categorias
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category {
    private long id;
    private String texto;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La clave es autonumérica en MariaDB
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
        return texto != null ? texto.equals(category.texto) : category.texto == null;
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
