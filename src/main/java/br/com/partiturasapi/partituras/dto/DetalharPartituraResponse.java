package br.com.partiturasapi.partituras.dto;

import br.com.partiturasapi.partituras.entity.NivelPartitura;
import java.time.OffsetDateTime;
import java.util.UUID;

public record DetalharPartituraResponse(
    UUID id,
    String titulo,
    String compositor,
    String instrumento,
    NivelPartitura nivel,
    String tom,
    String arquivoUrl,
    String observacoes,
    OffsetDateTime criadoEm,
    OffsetDateTime atualizadoEm
) {
}
