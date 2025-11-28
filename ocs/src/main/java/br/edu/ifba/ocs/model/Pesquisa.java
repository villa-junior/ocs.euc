package br.edu.ifba.ocs.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pesquisa")
public class Pesquisa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pesquisa")
    private Integer id;

    private String titulo;

    private String descricao;

    private String status;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "url_participante")
    private String urlParticipante;

    @Column(name = "url_organizador")
    private String urlOrganizador;

    @Column(name = "arquivo_resultados")
    private String arquivoResultados;

    @ManyToOne
    @JoinColumn(name = "id_conta")
    private Conta conta;

    public Pesquisa() {}

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public LocalDate getDataInicio() { return dataInicio; }

    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }

    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public String getUrlParticipante() { return urlParticipante; }

    public void setUrlParticipante(String urlParticipante) { this.urlParticipante = urlParticipante; }

    public String getUrlOrganizador() { return urlOrganizador; }

    public void setUrlOrganizador(String urlOrganizador) { this.urlOrganizador = urlOrganizador; }

    public String getArquivoResultados() { return arquivoResultados; }

    public void setArquivoResultados(String arquivoResultados) { this.arquivoResultados = arquivoResultados; }

    public Conta getConta() { return conta; }

    public void setConta(Conta conta) { this.conta = conta; }
}
