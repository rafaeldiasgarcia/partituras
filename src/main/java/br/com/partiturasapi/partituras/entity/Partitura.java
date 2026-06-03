package br.com.partiturasapi.partituras.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Entity
@Table(name = "partituras")
public class Partitura {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "compositor", nullable = false)
    private String compositor;

    @Column(name = "instrumento", nullable = false)
    private String instrumento;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", nullable = false)
    private NivelPartitura nivel;

    @Column(name = "tom")
    private String tom;

    @Column(name = "arquivo_url")
    private String arquivoUrl;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private OffsetDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private OffsetDateTime atualizadoEm;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCompositor() {
        return compositor;
    }

    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public NivelPartitura getNivel() {
        return nivel;
    }

    public void setNivel(NivelPartitura nivel) {
        this.nivel = nivel;
    }

    public String getTom() {
        return tom;
    }

    public void setTom(String tom) {
        this.tom = tom;
    }

    public String getArquivoUrl() {
        return arquivoUrl;
    }

    public void setArquivoUrl(String arquivoUrl) {
        this.arquivoUrl = arquivoUrl;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public OffsetDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(OffsetDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public OffsetDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(OffsetDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    @PrePersist
    public void prePersist() {
        OffsetDateTime agora = OffsetDateTime.now(ZoneOffset.UTC);

        if (id == null) {
            id = UUID.randomUUID();
        }

        if (criadoEm == null) {
            criadoEm = agora;
        }

        atualizadoEm = agora;
    }

    @PreUpdate
    public void preUpdate() {
        atualizadoEm = OffsetDateTime.now(ZoneOffset.UTC);
    }
}
