package br.com.partiturasapi.partituras.service;

import br.com.partiturasapi.partituras.dto.DetalharPartituraResponse;
import br.com.partiturasapi.partituras.entity.Partitura;
import br.com.partiturasapi.partituras.mapper.PartituraMapper;
import br.com.partiturasapi.partituras.repository.PartituraRepository;
import br.com.partiturasapi.shared.exception.ResourceNotFoundException;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class DetalharPartituraService {

    private final PartituraRepository repository;
    private final PartituraMapper mapper;

    public DetalharPartituraService(PartituraRepository repository, PartituraMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DetalharPartituraResponse detalhar(UUID id) {
        Partitura partitura = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Partitura não encontrada"));

        return mapper.toDetalharPartituraResponse(partitura);
    }
}
