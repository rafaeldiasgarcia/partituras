package br.com.partiturasapi.partituras.factory;

import br.com.partiturasapi.partituras.dto.AtualizarPartituraResponse;
import br.com.partiturasapi.partituras.dto.CriarPartituraResponse;
import br.com.partiturasapi.partituras.dto.DetalharPartituraResponse;
import br.com.partiturasapi.partituras.dto.ListarPartituraResponse;
import br.com.partiturasapi.partituras.entity.Partitura;

public final class PartituraResponseFactory {

    private PartituraResponseFactory() {
    }

    public static CriarPartituraResponse criarResponse() {
        return criarResponse(PartituraFactory.criar());
    }

    public static CriarPartituraResponse criarResponse(Partitura partitura) {
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

    public static AtualizarPartituraResponse atualizarResponse() {
        return atualizarResponse(PartituraFactory.criarAtualizada());
    }

    public static AtualizarPartituraResponse atualizarResponse(Partitura partitura) {
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

    public static ListarPartituraResponse listarResponse() {
        return listarResponse(PartituraFactory.criar());
    }

    public static ListarPartituraResponse listarResponse(Partitura partitura) {
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

    public static DetalharPartituraResponse detalharResponse() {
        return detalharResponse(PartituraFactory.criar());
    }

    public static DetalharPartituraResponse detalharResponse(Partitura partitura) {
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
