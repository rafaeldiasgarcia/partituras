package br.com.partiturasapi.partituras.mapper;

import br.com.partiturasapi.partituras.dto.AtualizarPartituraRequest;
import br.com.partiturasapi.partituras.dto.AtualizarPartituraResponse;
import br.com.partiturasapi.partituras.dto.CriarPartituraRequest;
import br.com.partiturasapi.partituras.dto.CriarPartituraResponse;
import br.com.partiturasapi.partituras.dto.DetalharPartituraResponse;
import br.com.partiturasapi.partituras.dto.ListarPartituraResponse;
import br.com.partiturasapi.partituras.entity.Partitura;
import org.springframework.stereotype.Component;

@Component
public class PartituraMapper {

    public Partitura toEntity(CriarPartituraRequest request) {
        Partitura partitura = new Partitura();
        partitura.setTitulo(request.titulo());
        partitura.setCompositor(request.compositor());
        partitura.setInstrumento(request.instrumento());
        partitura.setNivel(request.nivel());
        partitura.setTom(request.tom());
        partitura.setArquivoUrl(request.arquivoUrl());
        partitura.setObservacoes(request.observacoes());
        return partitura;
    }

    public void updateEntity(AtualizarPartituraRequest request, Partitura partitura) {
        partitura.setTitulo(request.titulo());
        partitura.setCompositor(request.compositor());
        partitura.setInstrumento(request.instrumento());
        partitura.setNivel(request.nivel());
        partitura.setTom(request.tom());
        partitura.setArquivoUrl(request.arquivoUrl());
        partitura.setObservacoes(request.observacoes());
    }

    public CriarPartituraResponse toCriarPartituraResponse(Partitura partitura) {
        return new CriarPartituraResponse(
            partitura.getId(),
            partitura.getTitulo(),
            partitura.getCompositor(),
            partitura.getInstrumento(),
            partitura.getNivel(),
            partitura.getTom(),
            partitura.getArquivoUrl(),
            partitura.getObservacoes(),
            partitura.getCriadoEm(),
            partitura.getAtualizadoEm()
        );
    }

    public AtualizarPartituraResponse toAtualizarPartituraResponse(Partitura partitura) {
        return new AtualizarPartituraResponse(
            partitura.getId(),
            partitura.getTitulo(),
            partitura.getCompositor(),
            partitura.getInstrumento(),
            partitura.getNivel(),
            partitura.getTom(),
            partitura.getArquivoUrl(),
            partitura.getObservacoes(),
            partitura.getCriadoEm(),
            partitura.getAtualizadoEm()
        );
    }

    public ListarPartituraResponse toListarPartituraResponse(Partitura partitura) {
        return new ListarPartituraResponse(
            partitura.getId(),
            partitura.getTitulo(),
            partitura.getCompositor(),
            partitura.getInstrumento(),
            partitura.getNivel(),
            partitura.getTom(),
            partitura.getArquivoUrl(),
            partitura.getObservacoes(),
            partitura.getCriadoEm(),
            partitura.getAtualizadoEm()
        );
    }

    public DetalharPartituraResponse toDetalharPartituraResponse(Partitura partitura) {
        return new DetalharPartituraResponse(
            partitura.getId(),
            partitura.getTitulo(),
            partitura.getCompositor(),
            partitura.getInstrumento(),
            partitura.getNivel(),
            partitura.getTom(),
            partitura.getArquivoUrl(),
            partitura.getObservacoes(),
            partitura.getCriadoEm(),
            partitura.getAtualizadoEm()
        );
    }
}
