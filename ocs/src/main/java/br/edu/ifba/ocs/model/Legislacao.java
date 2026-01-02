package br.edu.ifba.ocs.model;



import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "legislacao")
public class Legislacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id_legislacao", length = 36)
    private UUID id;

    private String numero;

    private LocalDate data;

    private String descricao;

    @Column(name = "url_arquivo")
    private String urlArquivo;

    public Legislacao() {}

    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}

    public String getNumero() { return numero; }

    public void setNumero(String numero) { this.numero = numero; }

    public LocalDate getData() { return data; }

    public void setData(LocalDate data) { this.data = data; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getUrlArquivo() { return urlArquivo; }

    public void setUrlArquivo(String urlArquivo) { this.urlArquivo = urlArquivo; }
}
