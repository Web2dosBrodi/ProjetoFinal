package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author luiz-
 */
@Entity
public class Evento implements Serializable {
    @Id @GeneratedValue
    public Long id;
    public String nome;
    public Date data;
    public int userId;

    public Evento() {
    }

    public Evento(String nome, Date data, int userId) {
        this.nome = nome;
        this.data = data;
        this.userId = userId;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
