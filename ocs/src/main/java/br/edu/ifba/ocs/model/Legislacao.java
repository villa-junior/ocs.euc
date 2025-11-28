package br.edu.ifba.ocs.model;



import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "legislacao")
public class Legislacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_legislacao")
    private Integer id;

    private String numero;

    private LocalDate data;

    private String descricao;

    @Column(name = "url_arquivo")
    private String urlArquivo;

    public Legislacao() {}

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getNumero() { return numero; }

    public void setNumero(String numero) { this.numero = numero; }

    public LocalDate getData() { return data; }

    public void setData(LocalDate data) { this.data = data; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getUrlArquivo() { return urlArquivo; }

    public void setUrlArquivo(String urlArquivo) { this.urlArquivo = urlArquivo; }
}
