package br.com.partiturasapi.partituras.service;

import br.com.partiturasapi.partituras.dto.AtualizarPartituraRequest;
import br.com.partiturasapi.partituras.dto.AtualizarPartituraResponse;
import br.com.partiturasapi.partituras.entity.Partitura;
import br.com.partiturasapi.partituras.mapper.PartituraMapper;
import br.com.partiturasapi.partituras.repository.PartituraRepository;
import br.com.partiturasapi.partituras.validator.PartituraValidatorService;
import br.com.partiturasapi.shared.exception.ResourceNotFoundException;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class AtualizarPartituraService {

    private final PartituraRepository repository;
    private final PartituraMapper mapper;
    private final PartituraValidatorService validatorService;

    public AtualizarPartituraService(
        PartituraRepository repository,
        PartituraMapper mapper,
        PartituraValidatorService validatorService
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.validatorService = validatorService;
    }

    public AtualizarPartituraResponse atualizar(UUID id, AtualizarPartituraRequest request) {
        Partitura partitura = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Partitura não encontrada"));

        mapper.updateEntity(request, partitura);
        validatorService.validate(partitura);
        Partitura atualizada = repository.save(partitura);
        return mapper.toAtualizarPartituraResponse(atualizada);
    }
}
