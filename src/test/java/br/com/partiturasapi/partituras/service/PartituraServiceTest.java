package br.com.partiturasapi.partituras.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.partiturasapi.partituras.entity.Partitura;
import br.com.partiturasapi.partituras.factory.PartituraFactory;
import br.com.partiturasapi.partituras.factory.PartituraRequestFactory;
import br.com.partiturasapi.partituras.factory.PartituraResponseFactory;
import br.com.partiturasapi.partituras.mapper.PartituraMapper;
import br.com.partiturasapi.partituras.repository.PartituraRepository;
import br.com.partiturasapi.partituras.validator.PartituraValidatorService;
import br.com.partiturasapi.shared.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PartituraServiceTest {

    @Mock
    private PartituraRepository repository;

    @Mock
    private PartituraMapper mapper;

    @Mock
    private PartituraValidatorService validatorService;

    @InjectMocks
    private PartituraService service;

    private Partitura partitura;

    @BeforeEach
    void setUp() {
        partitura = PartituraFactory.criar();
    }

    @Test
    void deveCriarPartituraComSucesso() {
        var request = PartituraRequestFactory.criarRequest();
        var response = PartituraResponseFactory.criarResponse();

        when(mapper.toEntity(request)).thenReturn(partitura);
        when(repository.save(partitura)).thenReturn(partitura);
        when(mapper.toCriarResponse(partitura)).thenReturn(response);

        var resultado = service.criar(request);

        assertThat(resultado).isEqualTo(response);
        verify(validatorService).validate(partitura);
        verify(repository).save(partitura);
    }

    @Test
    void deveListarPartituras() {
        var partituras = PartituraFactory.criarLista();
        var partituraListada = partituras.getFirst();
        var response = PartituraResponseFactory.listarResponse(partituraListada);

        when(repository.findAll()).thenReturn(partituras);
        when(mapper.toListarResponse(partituraListada)).thenReturn(response);

        var resultado = service.listar();

        assertThat(resultado).containsExactly(response);
    }

    @Test
    void deveDetalharPartituraExistente() {
        var id = PartituraFactory.ID_PADRAO;
        var response = PartituraResponseFactory.detalharResponse();

        when(repository.findById(id)).thenReturn(Optional.of(partitura));
        when(mapper.toDetalharResponse(partitura)).thenReturn(response);

        var resultado = service.detalhar(id);

        assertThat(resultado).isEqualTo(response);
    }

    @Test
    void deveLancarExcecaoAoDetalharPartituraInexistente() {
        var id = PartituraFactory.criarIdInexistente();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.detalhar(id))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessage(PartituraFactory.MENSAGEM_PARTITURA_NAO_ENCONTRADA);
    }

    @Test
    void deveAtualizarPartituraComSucesso() {
        var id = PartituraFactory.ID_PADRAO;
        var request = PartituraRequestFactory.atualizarRequest();
        var response = PartituraResponseFactory.atualizarResponse();

        when(repository.findById(id)).thenReturn(Optional.of(partitura));
        when(repository.save(partitura)).thenReturn(partitura);
        when(mapper.toAtualizarResponse(partitura)).thenReturn(response);

        var resultado = service.atualizar(id, request);

        assertThat(resultado).isEqualTo(response);
        verify(mapper).updateEntity(request, partitura);
        verify(validatorService).validate(partitura);
        verify(repository).save(partitura);
    }

    @Test
    void deveRemoverPartituraExistente() {
        var id = PartituraFactory.ID_PADRAO;

        when(repository.findById(id)).thenReturn(Optional.of(partitura));

        service.remover(id);

        verify(repository).delete(partitura);
    }

    @Test
    void naoDeveRemoverPartituraInexistente() {
        var id = PartituraFactory.criarIdInexistente();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.remover(id))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessage(PartituraFactory.MENSAGEM_PARTITURA_NAO_ENCONTRADA);

        verify(repository, never()).delete(any());
    }
}
