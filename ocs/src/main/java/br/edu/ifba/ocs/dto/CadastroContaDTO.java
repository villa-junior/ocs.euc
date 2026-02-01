package br.edu.ifba.ocs.dto;

import br.edu.ifba.ocs.model.Perfil;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CadastroContaDTO {

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    @Size(max = 255, message = "O e-mail excede o limite de 255 caracteres")
    private String email;


    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 255, message = "O nome excede o limite")
    private String nome;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, max = 255, message = "A senha deve ter entre 8 e 255 caracteres")
    private String senha;

    @Size(max = 255, message = "A instituição não pode exceder 255 caracteres")
    private String instituicao;

    @NotNull(message = "O perfil é obrigatório")
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
