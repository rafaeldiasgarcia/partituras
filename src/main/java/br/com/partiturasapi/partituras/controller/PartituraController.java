package br.com.partiturasapi.partituras.controller;

import br.com.partiturasapi.partituras.dto.AtualizarPartituraRequest;
import br.com.partiturasapi.partituras.dto.AtualizarPartituraResponse;
import br.com.partiturasapi.partituras.dto.CriarPartituraRequest;
import br.com.partiturasapi.partituras.dto.CriarPartituraResponse;
import br.com.partiturasapi.partituras.dto.DetalharPartituraResponse;
import br.com.partiturasapi.partituras.dto.ListarPartituraResponse;
import br.com.partiturasapi.partituras.service.PartituraService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/partituras")
public class PartituraController {

    private final PartituraService service;

    public PartituraController(PartituraService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriarPartituraResponse> criar(@Valid @RequestBody CriarPartituraRequest request) {
        CriarPartituraResponse response = service.criar(request);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.id())
            .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ListarPartituraResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharPartituraResponse> detalhar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.detalhar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtualizarPartituraResponse> atualizar(
        @PathVariable UUID id,
        @Valid @RequestBody AtualizarPartituraRequest request
    ) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
