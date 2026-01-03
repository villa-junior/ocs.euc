package br.edu.ifba.ocs.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id_conta", length = 36)
    private UUID id;

    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "senha_hash")
    private String senhaHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;

    private String instituicao;

    public Conta() {}

    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getSenhaHash() {return senhaHash;}

    public void setSenhaHash(String senhaHash) {this.senhaHash = senhaHash;}

    public String getInstituicao() {return instituicao;}

    public void setInstituicao(String instituicao) {this.instituicao = instituicao;}

    public Perfil getPerfil() {return perfil;}

    public void setPerfil(Perfil perfil) {this.perfil = perfil;}
}
