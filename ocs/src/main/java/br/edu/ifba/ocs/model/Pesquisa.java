package br.edu.ifba.ocs.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Pesquisa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPesquisa;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDate dataInicio;

    private LocalDate dataFim;
    private String urlParticipante;
    private String urlOrganizador;
    private String arquivoResultados;

    @ManyToOne
    @JoinColumn(name = "id_conta", nullable = false)
    private Conta conta;

    public enum Status {
        em_andamento, concluida
    }

    public Pesquisa() {
    }

    public Pesquisa(String titulo, String descricao, Status status, LocalDate dataInicio,
                    LocalDate dataFim, String urlParticipante, String urlOrganizador,
                    String arquivoResultados, Conta conta) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.urlParticipante = urlParticipante;
        this.urlOrganizador = urlOrganizador;
        this.arquivoResultados = arquivoResultados;
        this.conta = conta;
    }

    // Getters e Setters omitidos por espaço, mas você pode mantê-los iguais aos anteriores
}
