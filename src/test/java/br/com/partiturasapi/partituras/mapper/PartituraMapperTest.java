package br.com.partiturasapi.partituras.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.partiturasapi.partituras.dto.AtualizarPartituraRequest;
import br.com.partiturasapi.partituras.dto.CriarPartituraRequest;
import br.com.partiturasapi.partituras.entity.Partitura;
import br.com.partiturasapi.partituras.factory.PartituraFactory;
import br.com.partiturasapi.partituras.factory.PartituraMapperFactory;
import br.com.partiturasapi.partituras.factory.PartituraRequestFactory;
import org.junit.jupiter.api.Test;

class PartituraMapperTest {

    private final PartituraMapper mapper = PartituraMapperFactory.criar();

    @Test
    void deveMapearCriarRequestParaEntity() {
        CriarPartituraRequest request = PartituraRequestFactory.criarRequest();

        Partitura entity = mapper.toEntity(request);

        assertThat(entity.getTitulo()).isEqualTo(request.titulo());
        assertThat(entity.getCompositor()).isEqualTo(request.compositor());
        assertThat(entity.getInstrumento()).isEqualTo(request.instrumento());
        assertThat(entity.getNivel()).isEqualTo(request.nivel());
        assertThat(entity.getTom()).isEqualTo(request.tom());
        assertThat(entity.getArquivoUrl()).isEqualTo(request.arquivoUrl());
        assertThat(entity.getObservacoes()).isEqualTo(request.observacoes());
    }

    @Test
    void deveAtualizarEntityComDadosDoRequest() {
        AtualizarPartituraRequest request = PartituraRequestFactory.atualizarRequest();
        Partitura entity = PartituraFactory.criar();

        mapper.updateEntity(request, entity);

        assertThat(entity.getTitulo()).isEqualTo(request.titulo());
        assertThat(entity.getCompositor()).isEqualTo(request.compositor());
        assertThat(entity.getInstrumento()).isEqualTo(request.instrumento());
        assertThat(entity.getNivel()).isEqualTo(request.nivel());
        assertThat(entity.getTom()).isEqualTo(request.tom());
        assertThat(entity.getArquivoUrl()).isEqualTo(request.arquivoUrl());
        assertThat(entity.getObservacoes()).isEqualTo(request.observacoes());
    }

    @Test
    void deveMapearEntityParaCriarResponse() {
        Partitura entity = PartituraFactory.criar();

        var response = mapper.toCriarResponse(entity);

        assertThat(response.id()).isEqualTo(entity.getId());
        assertThat(response.titulo()).isEqualTo(entity.getTitulo());
        assertThat(response.nivel()).isEqualTo(entity.getNivel());
    }

    @Test
    void deveMapearEntityParaAtualizarResponse() {
        Partitura entity = PartituraFactory.criarAtualizada();

        var response = mapper.toAtualizarResponse(entity);

        assertThat(response.id()).isEqualTo(entity.getId());
        assertThat(response.titulo()).isEqualTo(entity.getTitulo());
        assertThat(response.arquivoUrl()).isEqualTo(entity.getArquivoUrl());
    }

    @Test
    void deveMapearEntityParaListarResponse() {
        Partitura entity = PartituraFactory.criar();

        var response = mapper.toListarResponse(entity);

        assertThat(response.id()).isEqualTo(entity.getId());
        assertThat(response.compositor()).isEqualTo(entity.getCompositor());
        assertThat(response.instrumento()).isEqualTo(entity.getInstrumento());
    }

    @Test
    void deveMapearEntityParaDetalharResponse() {
        Partitura entity = PartituraFactory.criar();

        var response = mapper.toDetalharResponse(entity);

        assertThat(response.id()).isEqualTo(entity.getId());
        assertThat(response.tom()).isEqualTo(entity.getTom());
        assertThat(response.observacoes()).isEqualTo(entity.getObservacoes());
    }
}
