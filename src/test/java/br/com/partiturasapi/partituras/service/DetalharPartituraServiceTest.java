package br.com.partiturasapi.partituras.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.partiturasapi.partituras.factory.PartituraFactory;
import br.com.partiturasapi.partituras.factory.PartituraResponseFactory;
import br.com.partiturasapi.partituras.mapper.PartituraMapper;
import br.com.partiturasapi.partituras.repository.PartituraRepository;
import br.com.partiturasapi.shared.exception.ResourceNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DetalharPartituraServiceTest {

    @Mock
    private PartituraRepository repository;

    @Mock
    private PartituraMapper mapper;

    @InjectMocks
    private DetalharPartituraService service;

    private br.com.partiturasapi.partituras.entity.Partitura partitura;

    @BeforeEach
    void setUp() {
        partitura = PartituraFactory.criar();
    }

    @Test
    void deveDetalharPartituraExistente() {
        var id = PartituraFactory.ID_PADRAO;
        var response = PartituraResponseFactory.detalharResponse();

        when(repository.findById(id)).thenReturn(Optional.of(partitura));
        when(mapper.toDetalharPartituraResponse(partitura)).thenReturn(response);

        var resultado = service.detalhar(id);

        assertThat(resultado).isEqualTo(response);
        verify(repository).findById(id);
        verify(mapper).toDetalharPartituraResponse(partitura);
    }

    @Test
    void deveLancarExcecaoAoDetalharPartituraInexistente() {
        var id = PartituraFactory.criarIdInexistente();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.detalhar(id))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessage(PartituraFactory.MENSAGEM_PARTITURA_NAO_ENCONTRADA);

        verify(repository).findById(id);
        verify(mapper, never()).toDetalharPartituraResponse(partitura);
    }
}
