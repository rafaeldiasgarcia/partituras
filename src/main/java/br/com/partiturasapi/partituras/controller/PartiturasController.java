package br.com.partiturasapi.partituras.controller;

import br.com.partiturasapi.partituras.dto.AtualizarPartituraRequest;
import br.com.partiturasapi.partituras.dto.AtualizarPartituraResponse;
import br.com.partiturasapi.partituras.dto.CriarPartituraRequest;
import br.com.partiturasapi.partituras.dto.CriarPartituraResponse;
import br.com.partiturasapi.partituras.dto.DetalharPartituraResponse;
import br.com.partiturasapi.partituras.dto.ListarPartituraResponse;
import br.com.partiturasapi.partituras.service.AtualizarPartituraService;
import br.com.partiturasapi.partituras.service.CriarPartituraService;
import br.com.partiturasapi.partituras.service.DetalharPartituraService;
import br.com.partiturasapi.partituras.service.ExcluirPartituraService;
import br.com.partiturasapi.partituras.service.ListarPartituraService;
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

@RestController
@RequestMapping("/partituras")
public class PartiturasController {

    private final CriarPartituraService criarPartituraService;
    private final AtualizarPartituraService atualizarPartituraService;
    private final DetalharPartituraService detalharPartituraService;
    private final ListarPartituraService listarPartituraService;
    private final ExcluirPartituraService excluirPartituraService;

    public PartiturasController(
        CriarPartituraService criarPartituraService,
        AtualizarPartituraService atualizarPartituraService,
        DetalharPartituraService detalharPartituraService,
        ListarPartituraService listarPartituraService,
        ExcluirPartituraService excluirPartituraService
    ) {
        this.criarPartituraService = criarPartituraService;
        this.atualizarPartituraService = atualizarPartituraService;
        this.detalharPartituraService = detalharPartituraService;
        this.listarPartituraService = listarPartituraService;
        this.excluirPartituraService = excluirPartituraService;
    }

    @PostMapping
    public ResponseEntity<CriarPartituraResponse> criar(@Valid @RequestBody CriarPartituraRequest request) {
        CriarPartituraResponse response = criarPartituraService.criar(request);
        URI location = URI.create("/partituras/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ListarPartituraResponse>> listar() {
        return ResponseEntity.ok(listarPartituraService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharPartituraResponse> detalhar(@PathVariable UUID id) {
        return ResponseEntity.ok(detalharPartituraService.detalhar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtualizarPartituraResponse> atualizar(
        @PathVariable UUID id,
        @Valid @RequestBody AtualizarPartituraRequest request
    ) {
        return ResponseEntity.ok(atualizarPartituraService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        excluirPartituraService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
