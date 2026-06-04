package br.com.partiturasapi.partituras.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.partiturasapi.partituras.factory.PartituraFactory;
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
class ExcluirPartituraServiceTest {

    @Mock
    private PartituraRepository repository;

    @InjectMocks
    private ExcluirPartituraService service;

    private br.com.partiturasapi.partituras.entity.Partitura partitura;

    @BeforeEach
    void setUp() {
        partitura = PartituraFactory.criar();
    }

    @Test
    void deveExcluirPartituraExistente() {
        var id = PartituraFactory.ID_PADRAO;

        when(repository.findById(id)).thenReturn(Optional.of(partitura));

        service.excluir(id);

        verify(repository).findById(id);
        verify(repository).delete(partitura);
    }

    @Test
    void naoDeveExcluirPartituraInexistente() {
        var id = PartituraFactory.criarIdInexistente();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.excluir(id))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessage(PartituraFactory.MENSAGEM_PARTITURA_NAO_ENCONTRADA);

        verify(repository).findById(id);
        verify(repository, never()).delete(any());
    }
}
