package br.edu.ifba.ocs.model;



import jakarta.persistence.*;

@Entity
@Table(name = "autora")
public class Autora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autora")
    private Integer id;

    private String nome;

    private String email;

    private String instituicao;

    private String lattes;

    public Autora() {}

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getInstituicao() { return instituicao; }

    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }

    public String getLattes() { return lattes; }

    public void setLattes(String lattes) { this.lattes = lattes; }
}
