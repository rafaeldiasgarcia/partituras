package br.com.partiturasapi.partituras.service;

import br.com.partiturasapi.partituras.entity.Partitura;
import br.com.partiturasapi.partituras.repository.PartituraRepository;
import br.com.partiturasapi.shared.exception.ResourceNotFoundException;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ExcluirPartituraService {

    private final PartituraRepository repository;

    public ExcluirPartituraService(PartituraRepository repository) {
        this.repository = repository;
    }

    public void excluir(UUID id) {
        Partitura partitura = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Partitura não encontrada"));

        repository.delete(partitura);
    }
}
