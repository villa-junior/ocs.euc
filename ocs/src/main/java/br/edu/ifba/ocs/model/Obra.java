package br.edu.ifba.ocs.model;



import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idObra;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String resumo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String palavrasChave;

    @Column(nullable = false)
    private Integer anoPublicacao;

    @Column(nullable = false)
    private String urlArquivo;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_conta", nullable = false)
    private Conta conta;

    @Column(nullable = false)
    private LocalDate dataRegistro;

    public Obra() {
    }

    public Obra(String titulo, String resumo, String palavrasChave, Integer anoPublicacao,
                String urlArquivo, Categoria categoria, Conta conta, LocalDate dataRegistro) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.palavrasChave = palavrasChave;
        this.anoPublicacao = anoPublicacao;
        this.urlArquivo = urlArquivo;
        this.categoria = categoria;
        this.conta = conta;
        this.dataRegistro = dataRegistro;
    }

    public Integer getIdObra() { return idObra; }
    public void setIdObra(Integer idObra) { this.idObra = idObra; }

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

    @Override
    public String toString() {
        return "Obra{" +
                "idObra=" + idObra +
                ", titulo='" + titulo + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                ", categoria=" + categoria +
                ", conta=" + conta +
                '}';
    }
}

