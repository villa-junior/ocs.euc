package br.edu.ifba.ocs.model;



import jakarta.persistence.*;

@Entity
@IdClass(ObraAutoraId.class)
public class ObraAutora {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_obra")
    private Obra obra;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_autora")
    private Autora autora;

    public ObraAutora() {
    }

    public ObraAutora(Obra obra, Autora autora) {
        this.obra = obra;
        this.autora = autora;
    }

    public Obra getObra() { return obra; }
    public void setObra(Obra obra) { this.obra = obra; }

    public Autora getAutora() { return autora; }
    public void setAutora(Autora autora) { this.autora = autora; }
}
