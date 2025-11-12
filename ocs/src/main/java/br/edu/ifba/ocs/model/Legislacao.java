package br.edu.ifba.ocs.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Legislacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLegislacao;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private String urlArquivo;

    public Legislacao() {
    }

    public Legislacao(String numero, LocalDate data, String descricao, String urlArquivo) {
        this.numero = numero;
        this.data = data;
        this.descricao = descricao;
        this.urlArquivo = urlArquivo;
    }

    public Integer getIdLegislacao() { return idLegislacao; }
    public void setIdLegislacao(Integer idLegislacao) { this.idLegislacao = idLegislacao; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getUrlArquivo() { return urlArquivo; }
    public void setUrlArquivo(String urlArquivo) { this.urlArquivo = urlArquivo; }

    @Override
    public String toString() {
        return "Legislacao{" +
                "idLegislacao=" + idLegislacao +
                ", numero='" + numero + '\'' +
                ", data=" + data +
                '}';
    }
}
