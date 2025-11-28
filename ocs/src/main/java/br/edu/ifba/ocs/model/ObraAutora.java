package br.edu.ifba.ocs.model;




import jakarta.persistence.*;

@Entity
@Table(name = "obra_autora")
public class ObraAutora {

    @EmbeddedId
    private ObraAutoraId id;

    @ManyToOne
    @MapsId("idObra")
    @JoinColumn(name = "id_obra")
    private Obra obra;

    @ManyToOne
    @MapsId("idAutora")
    @JoinColumn(name = "id_autora")
    private Autora autora;

    public ObraAutora() {}

    public ObraAutoraId getId() { return id; }

    public void setId(ObraAutoraId id) { this.id = id; }

    public Obra getObra() { return obra; }

    public void setObra(Obra obra) { this.obra = obra; }

    public Autora getAutora() { return autora; }

    public void setAutora(Autora autora) { this.autora = autora; }
}
