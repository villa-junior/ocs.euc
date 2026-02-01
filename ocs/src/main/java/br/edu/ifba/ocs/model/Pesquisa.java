package br.edu.ifba.ocs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "pesquisa")
public class Pesquisa {

    public enum Status {
        EM_ANDAMENTO, CONCLUIDA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id_pesquisa", length = 36)
    private UUID id;

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 255, message = "O título está muito longo! Máximo de 255 caracteres.")
    private String titulo;

    @Size(max = 255, message = "A descrição está muito longa! Máximo de 255 caracteres.")
    private String descricao;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull(message = "A data de início é obrigatória")
    @PastOrPresent(message = "A data de início não pode ser futura")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "data_inicio")
    private LocalDate dataInicio;


    @PastOrPresent(message = "A data de fim não pode ser futura")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Size(max = 255, message = "A URL do participante é muito longa.")
    @Column(name = "url_participante")
    private String urlParticipante;

    @Size(max = 255, message = "A URL do organizador é muito longa.")
    @Column(name = "url_organizador")
    private String urlOrganizador;

    @Size(max = 255, message = "O nome do arquivo é muito longo.")
    @Column(name = "arquivo_resultados")
    private String arquivoResultados;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    @JoinColumn(name = "id_conta", nullable = false)
    private Conta conta;
    public Pesquisa() {}

    @PrePersist
    @PreUpdate
    private void validarDatas() {
        if (dataInicio != null && dataFim != null && dataFim.isBefore(dataInicio)) {
            throw new IllegalArgumentException("A data de fim não pode ser anterior à data de início.");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getUrlParticipante() {
        return urlParticipante;
    }

    public void setUrlParticipante(String urlParticipante) {
        this.urlParticipante = urlParticipante;
    }

    public String getUrlOrganizador() {
        return urlOrganizador;
    }

    public void setUrlOrganizador(String urlOrganizador) {
        this.urlOrganizador = urlOrganizador;
    }

    public String getArquivoResultados() {
        return arquivoResultados;
    }

    public void setArquivoResultados(String arquivoResultados) {
        this.arquivoResultados = arquivoResultados;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}