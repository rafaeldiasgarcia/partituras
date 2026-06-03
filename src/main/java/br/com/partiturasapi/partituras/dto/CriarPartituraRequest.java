package br.com.partiturasapi.partituras.dto;

import br.com.partiturasapi.partituras.entity.NivelPartitura;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CriarPartituraRequest(
    @NotBlank(message = "titulo é obrigatório")
    @Size(max = 255, message = "titulo deve ter no máximo 255 caracteres")
    String titulo,

    @NotBlank(message = "compositor é obrigatório")
    @Size(max = 255, message = "compositor deve ter no máximo 255 caracteres")
    String compositor,

    @NotBlank(message = "instrumento é obrigatório")
    @Size(max = 255, message = "instrumento deve ter no máximo 255 caracteres")
    String instrumento,

    @NotNull(message = "nivel é obrigatório")
    NivelPartitura nivel,

    @Size(max = 100, message = "tom deve ter no máximo 100 caracteres")
    String tom,

    @Size(max = 500, message = "arquivoUrl deve ter no máximo 500 caracteres")
    String arquivoUrl,

    String observacoes
) {
}
