package br.edu.ifba.ocs.model;



import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ObraAutoraId implements Serializable {

    @Column(name = "id_obra")
    private Integer idObra;

    @Column(name = "id_autora")
    private Integer idAutora;

    public ObraAutoraId() {}

    public ObraAutoraId(Integer idObra, Integer idAutora) {
        this.idObra = idObra;
        this.idAutora = idAutora;
    }

    public Integer getIdObra() { return idObra; }

    public void setIdObra(Integer idObra) { this.idObra = idObra; }

    public Integer getIdAutora() { return idAutora; }

    public void setIdAutora(Integer idAutora) { this.idAutora = idAutora; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObraAutoraId)) return false;
        ObraAutoraId that = (ObraAutoraId) o;
        return Objects.equals(idObra, that.idObra) &&
                Objects.equals(idAutora, that.idAutora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idObra, idAutora);
    }
}
