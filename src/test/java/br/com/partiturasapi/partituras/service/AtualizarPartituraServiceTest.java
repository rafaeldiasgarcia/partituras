package br.com.partiturasapi.partituras.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.partiturasapi.partituras.factory.PartituraFactory;
import br.com.partiturasapi.partituras.factory.PartituraRequestFactory;
import br.com.partiturasapi.partituras.factory.PartituraResponseFactory;
import br.com.partiturasapi.partituras.mapper.PartituraMapper;
import br.com.partiturasapi.partituras.repository.PartituraRepository;
import br.com.partiturasapi.partituras.validator.PartituraValidatorService;
import br.com.partiturasapi.shared.exception.BusinessException;
import br.com.partiturasapi.shared.exception.ResourceNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AtualizarPartituraServiceTest {

    @Mock
    private PartituraRepository repository;

    @Mock
    private PartituraMapper mapper;

    @Mock
    private PartituraValidatorService validatorService;

    @InjectMocks
    private AtualizarPartituraService service;

    private br.com.partiturasapi.partituras.entity.Partitura partitura;

    @BeforeEach
    void setUp() {
        partitura = PartituraFactory.criar();
    }

    @Test
    void deveAtualizarPartituraComSucesso() {
        var id = PartituraFactory.ID_PADRAO;
        var request = PartituraRequestFactory.atualizarRequest();
        var response = PartituraResponseFactory.atualizarResponse();

        when(repository.findById(id)).thenReturn(Optional.of(partitura));
        when(repository.save(partitura)).thenReturn(partitura);
        when(mapper.toAtualizarPartituraResponse(partitura)).thenReturn(response);

        var resultado = service.atualizar(id, request);

        assertThat(resultado).isEqualTo(response);
        verify(repository).findById(id);
        verify(mapper).updateEntity(request, partitura);
        verify(validatorService).validate(partitura);
        verify(repository).save(partitura);
        verify(mapper).toAtualizarPartituraResponse(partitura);
    }

    @Test
    void deveLancarExcecaoAoAtualizarPartituraInexistente() {
        var id = PartituraFactory.criarIdInexistente();
        var request = PartituraRequestFactory.atualizarRequest();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.atualizar(id, request))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessage(PartituraFactory.MENSAGEM_PARTITURA_NAO_ENCONTRADA);

        verify(repository).findById(id);
        verify(mapper, never()).updateEntity(request, partitura);
        verify(repository, never()).save(partitura);
        verify(mapper, never()).toAtualizarPartituraResponse(partitura);
    }

    @Test
    void naoDeveSalvarQuandoValidacaoFalharAoAtualizar() {
        var id = PartituraFactory.ID_PADRAO;
        var request = PartituraRequestFactory.atualizarRequest();
        var excecao = new BusinessException(PartituraFactory.MENSAGEM_NIVEL_OBRIGATORIO);

        when(repository.findById(id)).thenReturn(Optional.of(partitura));
        org.mockito.Mockito.doThrow(excecao).when(validatorService).validate(partitura);

        assertThatThrownBy(() -> service.atualizar(id, request))
            .isInstanceOf(BusinessException.class)
            .hasMessage(PartituraFactory.MENSAGEM_NIVEL_OBRIGATORIO);

        verify(repository).findById(id);
        verify(mapper).updateEntity(request, partitura);
        verify(validatorService).validate(partitura);
        verify(repository, never()).save(partitura);
        verify(mapper, never()).toAtualizarPartituraResponse(partitura);
    }
}
