package br.edu.ifba.ocs.model;


import jakarta.persistence.*;

@Entity
public class Autora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAutora;

    @Column(nullable = false)
    private String nome;

    private String email;
    private String instituicao;
    private String lattes;

    public Autora() {
    }

    public Autora(String nome, String email, String instituicao, String lattes) {
        this.nome = nome;
        this.email = email;
        this.instituicao = instituicao;
        this.lattes = lattes;
    }

    public Integer getIdAutora() { return idAutora; }
    public void setIdAutora(Integer idAutora) { this.idAutora = idAutora; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }

    public String getLattes() { return lattes; }
    public void setLattes(String lattes) { this.lattes = lattes; }

    @Override
    public String toString() {
        return "Autora{" +
                "idAutora=" + idAutora +
                ", nome='" + nome + '\'' +
                ", instituicao='" + instituicao + '\'' +
                '}';
    }
}
