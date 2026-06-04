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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CriarPartituraServiceTest {

    @Mock
    private PartituraRepository repository;

    @Mock
    private PartituraMapper mapper;

    @Mock
    private PartituraValidatorService validatorService;

    @InjectMocks
    private CriarPartituraService service;

    private br.com.partiturasapi.partituras.entity.Partitura partitura;

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
        when(mapper.toCriarPartituraResponse(partitura)).thenReturn(response);

        var resultado = service.criar(request);

        assertThat(resultado).isEqualTo(response);
        verify(mapper).toEntity(request);
        verify(validatorService).validate(partitura);
        verify(repository).save(partitura);
        verify(mapper).toCriarPartituraResponse(partitura);
    }

    @Test
    void naoDeveSalvarQuandoValidacaoFalhar() {
        var request = PartituraRequestFactory.criarRequest();

        when(mapper.toEntity(request)).thenReturn(partitura);
        var excecao = new BusinessException(PartituraFactory.MENSAGEM_TITULO_OBRIGATORIO);

        org.mockito.Mockito.doThrow(excecao).when(validatorService).validate(partitura);

        assertThatThrownBy(() -> service.criar(request))
            .isInstanceOf(BusinessException.class)
            .hasMessage(PartituraFactory.MENSAGEM_TITULO_OBRIGATORIO);

        verify(mapper).toEntity(request);
        verify(validatorService).validate(partitura);
        verify(repository, never()).save(partitura);
        verify(mapper, never()).toCriarPartituraResponse(partitura);
    }
}
