package br.edu.ifba.ocs.dto;

import br.edu.ifba.ocs.model.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CadastroContaDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    private String instituicao;

    @NotNull
    private Perfil perfilDesejado;


    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }

    public Perfil getPerfilDesejado() { return perfilDesejado; }
    public void setPerfilDesejado(Perfil perfilDesejado) {
        this.perfilDesejado = perfilDesejado;
    }
}
