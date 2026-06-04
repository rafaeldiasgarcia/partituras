package br.com.partiturasapi.partituras.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import br.com.partiturasapi.partituras.factory.PartituraFactory;
import br.com.partiturasapi.partituras.factory.PartituraResponseFactory;
import br.com.partiturasapi.partituras.mapper.PartituraMapper;
import br.com.partiturasapi.partituras.repository.PartituraRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ListarPartituraServiceTest {

    @Mock
    private PartituraRepository repository;

    @Mock
    private PartituraMapper mapper;

    @InjectMocks
    private ListarPartituraService service;

    @Test
    void deveListarPartituras() {
        var partituras = PartituraFactory.criarLista();
        var partitura = partituras.getFirst();
        var response = PartituraResponseFactory.listarResponse(partitura);

        when(repository.findAll()).thenReturn(partituras);
        when(mapper.toListarPartituraResponse(partitura)).thenReturn(response);

        var resultado = service.listar();

        assertThat(resultado).containsExactly(response);
        verify(repository).findAll();
        verify(mapper).toListarPartituraResponse(partitura);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremPartituras() {
        when(repository.findAll()).thenReturn(List.of());

        var resultado = service.listar();

        assertThat(resultado).isEmpty();
        verify(repository).findAll();
        verifyNoInteractions(mapper);
    }
}
