package br.edu.ifba.ocs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    @Size(max = 255, message = "O e-mail excede o limite de 255 caracteres")
    @Column(unique = true, length = 255)
    private String email;

    @Size(max = 255, message = "O nome da instituição excede o limite")
    @Column(length = 255)
    private String instituicao;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 255, message = "O nome excede o limite")
    @Column(length = 255)
    private String nome;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, max = 255, message = "A senha deve ter entre 8 e 255 caracteres")
    @Column(name = "senha_hash", length = 255)
    private String senhaHash;

    @Enumerated(EnumType.STRING)
    @Column(length = 255, nullable = false)
    private Perfil perfil;

    @Column(name = "validado", columnDefinition = "TINYINT(1)", nullable = false)
    private boolean validado = false;

    public Conta() {}


    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String inst) { this.instituicao = inst; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String hash) { this.senhaHash = hash; }

    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }

    public boolean isValidado() { return validado; }
    public void setValidado(boolean validado) { this.validado = validado; }
}