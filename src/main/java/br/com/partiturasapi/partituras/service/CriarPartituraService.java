package br.com.partiturasapi.partituras.service;

import br.com.partiturasapi.partituras.dto.CriarPartituraRequest;
import br.com.partiturasapi.partituras.dto.CriarPartituraResponse;
import br.com.partiturasapi.partituras.entity.Partitura;
import br.com.partiturasapi.partituras.mapper.PartituraMapper;
import br.com.partiturasapi.partituras.repository.PartituraRepository;
import br.com.partiturasapi.partituras.validator.PartituraValidatorService;
import org.springframework.stereotype.Service;

@Service
public class CriarPartituraService {

    private final PartituraRepository repository;
    private final PartituraMapper mapper;
    private final PartituraValidatorService validatorService;

    public CriarPartituraService(
        PartituraRepository repository,
        PartituraMapper mapper,
        PartituraValidatorService validatorService
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.validatorService = validatorService;
    }

    public CriarPartituraResponse criar(CriarPartituraRequest request) {
        Partitura partitura = mapper.toEntity(request);
        validatorService.validate(partitura);
        Partitura salva = repository.save(partitura);
        return mapper.toCriarPartituraResponse(salva);
    }
}
