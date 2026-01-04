package br.edu.ifba.ocs.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class ObraAutoraId implements Serializable {

    @Column(name = "id_obra", columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID idObra;

    @Column(name = "id_autora", columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID idAutora;

    public ObraAutoraId() {}

    public ObraAutoraId(UUID idObra, UUID idAutora) {
        this.idObra = idObra;
        this.idAutora = idAutora;
    }

    public UUID getIdObra() {
        return idObra;
    }

    public void setIdObra(UUID idObra) {this.idObra = idObra;}

    public UUID getIdAutora() {return idAutora;}

    public void setIdAutora(UUID idAutora) {this.idAutora = idAutora;}

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
