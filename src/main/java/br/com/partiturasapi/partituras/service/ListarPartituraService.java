package br.com.partiturasapi.partituras.service;

import br.com.partiturasapi.partituras.dto.ListarPartituraResponse;
import br.com.partiturasapi.partituras.mapper.PartituraMapper;
import br.com.partiturasapi.partituras.repository.PartituraRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ListarPartituraService {

    private final PartituraRepository repository;
    private final PartituraMapper mapper;

    public ListarPartituraService(PartituraRepository repository, PartituraMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ListarPartituraResponse> listar() {
        return repository.findAll()
            .stream()
            .map(mapper::toListarPartituraResponse)
            .toList();
    }
}
