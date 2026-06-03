package br.com.partiturasapi.partituras.factory;

import br.com.partiturasapi.partituras.mapper.PartituraMapper;

public final class PartituraMapperFactory {

    private PartituraMapperFactory() {
    }

    public static PartituraMapper criar() {
        return new PartituraMapper();
    }
}
