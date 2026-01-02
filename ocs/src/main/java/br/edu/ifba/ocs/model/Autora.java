package br.edu.ifba.ocs.model;



import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "autora")
public class Autora {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id_autora", length = 36)
    private UUID id;

    private String nome;

    private String email;

    private String instituicao;

    private String lattes;

    public Autora() {}

    public UUID getId() {return id;}

    public void setId(UUID id) { this.id = id;}

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getInstituicao() { return instituicao; }

    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }

    public String getLattes() { return lattes; }

    public void setLattes(String lattes) { this.lattes = lattes; }
}
