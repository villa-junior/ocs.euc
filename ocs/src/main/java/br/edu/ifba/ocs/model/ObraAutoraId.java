package br.edu.ifba.ocs.model;

import java.io.Serializable;
import java.util.Objects;

public class ObraAutoraId implements Serializable {
    private Integer obra;
    private Integer autora;

    public ObraAutoraId() {
    }

    public ObraAutoraId(Integer obra, Integer autora) {
        this.obra = obra;
        this.autora = autora;
    }

    public Integer getObra() { return obra; }
    public void setObra(Integer obra) { this.obra = obra; }

    public Integer getAutora() { return autora; }
    public void setAutora(Integer autora) { this.autora = autora; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObraAutoraId)) return false;
        ObraAutoraId that = (ObraAutoraId) o;
        return Objects.equals(obra, that.obra) && Objects.equals(autora, that.autora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(obra, autora);
    }
    //testes de hoje
}
