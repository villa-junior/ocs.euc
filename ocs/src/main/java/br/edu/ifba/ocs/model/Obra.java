package br.edu.ifba.ocs.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "obra")
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id_obra", length = 36)
    private UUID id;

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 255, message = "O título está muito longo (máximo 255 caracteres)")
    private String titulo;

    @NotBlank(message = "O resumo é obrigatório")
    @Size(max = 255, message = "O resumo está muito longo (máximo 255 caracteres)")
    private String resumo;

    @Size(max = 255, message = "As palavras-chave estão muito longas")
    @Column(name = "palavras_chave")
    private String palavrasChave;

    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;

    @NotBlank(message = "O link do arquivo é obrigatório")
    @Size(max = 255, message = "A URL do arquivo está muito longa")
    @Column(name = "url_arquivo")
    private String urlArquivo;

    @NotNull(message = "A categoria é obrigatória")
    @ManyToOne(optional = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JdbcTypeCode(SqlTypes.CHAR)
    @JoinColumn(name = "id_conta")
    private Conta conta;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_registro")
    private LocalDate dataRegistro;

    public Obra() {}

    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getResumo() { return resumo; }

    public void setResumo(String resumo) { this.resumo = resumo; }

    public String getPalavrasChave() { return palavrasChave; }

    public void setPalavrasChave(String palavrasChave) { this.palavrasChave = palavrasChave; }

    public Integer getAnoPublicacao() { return anoPublicacao; }

    public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }

    public String getUrlArquivo() { return urlArquivo; }

    public void setUrlArquivo(String urlArquivo) { this.urlArquivo = urlArquivo; }

    public Categoria getCategoria() { return categoria; }

    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Conta getConta() { return conta; }

    public void setConta(Conta conta) { this.conta = conta; }

    public LocalDate getDataRegistro() { return dataRegistro; }

    public void setDataRegistro(LocalDate dataRegistro) { this.dataRegistro = dataRegistro; }
}


