package br.edu.ifba.ocs.model;

import jakarta.persistence.*;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConta;

    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, name = "senha_hash")
    private String senhaHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;

    private String instituicao;

    public enum Perfil {
        admin, pesquisador, visitante, associada
    }

    public Conta() {
    }

    public Conta(String nome, String email, String senhaHash, Perfil perfil, String instituicao) {
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.perfil = perfil;
        this.instituicao = instituicao;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "idConta=" + idConta +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", perfil=" + perfil +
                ", instituicao='" + instituicao + '\'' +
                '}';
    }
}

