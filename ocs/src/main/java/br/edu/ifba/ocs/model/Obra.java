package br.edu.ifba.ocs.model;



import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    private String titulo;

    private String resumo;

    @Column(name = "palavras_chave")
    private String palavrasChave;

    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;

    @Column(name = "url_arquivo")
    private String urlArquivo;

    @ManyToOne(optional = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JdbcTypeCode(SqlTypes.CHAR)
    @JoinColumn(name = "id_conta")
    private Conta conta;

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


