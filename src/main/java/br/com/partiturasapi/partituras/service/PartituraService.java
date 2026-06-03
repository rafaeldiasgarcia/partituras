package br.com.partiturasapi.partituras.service;

import br.com.partiturasapi.partituras.dto.AtualizarPartituraRequest;
import br.com.partiturasapi.partituras.dto.AtualizarPartituraResponse;
import br.com.partiturasapi.partituras.dto.CriarPartituraRequest;
import br.com.partiturasapi.partituras.dto.CriarPartituraResponse;
import br.com.partiturasapi.partituras.dto.DetalharPartituraResponse;
import br.com.partiturasapi.partituras.dto.ListarPartituraResponse;
import br.com.partiturasapi.partituras.entity.Partitura;
import br.com.partiturasapi.partituras.mapper.PartituraMapper;
import br.com.partiturasapi.partituras.repository.PartituraRepository;
import br.com.partiturasapi.partituras.validator.PartituraValidatorService;
import br.com.partiturasapi.shared.exception.ResourceNotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PartituraService {

    private final PartituraRepository repository;
    private final PartituraMapper mapper;
    private final PartituraValidatorService validatorService;

    public PartituraService(
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
        return mapper.toCriarResponse(salva);
    }

    public List<ListarPartituraResponse> listar() {
        return repository.findAll()
            .stream()
            .map(mapper::toListarResponse)
            .toList();
    }

    public DetalharPartituraResponse detalhar(UUID id) {
        Partitura partitura = buscarPorId(id);
        return mapper.toDetalharResponse(partitura);
    }

    public AtualizarPartituraResponse atualizar(UUID id, AtualizarPartituraRequest request) {
        Partitura partitura = buscarPorId(id);
        mapper.updateEntity(request, partitura);
        validatorService.validate(partitura);
        Partitura atualizada = repository.save(partitura);
        return mapper.toAtualizarResponse(atualizada);
    }

    public void remover(UUID id) {
        Partitura partitura = buscarPorId(id);
        repository.delete(partitura);
    }

    private Partitura buscarPorId(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Partitura não encontrada"));
    }
}
