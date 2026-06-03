package br.com.partiturasapi.partituras.factory;

import br.com.partiturasapi.partituras.dto.AtualizarPartituraRequest;
import br.com.partiturasapi.partituras.dto.CriarPartituraRequest;

public final class PartituraRequestFactory {

    private PartituraRequestFactory() {
    }

    public static CriarPartituraRequest criarRequest() {
        return new CriarPartituraRequest(
            PartituraFactory.TITULO_PADRAO,
            PartituraFactory.COMPOSITOR_PADRAO,
            PartituraFactory.INSTRUMENTO_PADRAO,
            PartituraFactory.NIVEL_PADRAO,
            PartituraFactory.TOM_PADRAO,
            PartituraFactory.ARQUIVO_URL_PADRAO,
            PartituraFactory.OBSERVACOES_PADRAO
        );
    }

    public static AtualizarPartituraRequest atualizarRequest() {
        return new AtualizarPartituraRequest(
            PartituraFactory.TITULO_ATUALIZADO,
            PartituraFactory.COMPOSITOR_PADRAO,
            PartituraFactory.INSTRUMENTO_PADRAO,
            PartituraFactory.NIVEL_ATUALIZADO,
            PartituraFactory.TOM_PADRAO,
            PartituraFactory.ARQUIVO_URL_ATUALIZADO,
            PartituraFactory.OBSERVACOES_ATUALIZADA
        );
    }
}
