package br.com.partiturasapi.partituras.validator;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import br.com.partiturasapi.partituras.entity.Partitura;
import br.com.partiturasapi.partituras.factory.PartituraFactory;
import br.com.partiturasapi.shared.exception.BusinessException;
import org.junit.jupiter.api.Test;

class PartituraTituloValidatorTest {

    private final PartituraTituloValidator validator = PartituraFactory.criarTituloValidator();

    @Test
    void deveAceitarPartituraComTituloValido() {
        Partitura partitura = PartituraFactory.criar();

        assertThatCode(() -> validator.validate(partitura)).doesNotThrowAnyException();
    }

    @Test
    void deveRejeitarPartituraComTituloNulo() {
        Partitura partitura = PartituraFactory.criar();
        partitura.setTitulo(null);

        assertThatThrownBy(() -> validator.validate(partitura))
            .isInstanceOf(BusinessException.class)
            .hasMessage(PartituraFactory.MENSAGEM_TITULO_OBRIGATORIO);
    }

    @Test
    void deveRejeitarPartituraComTituloEmBranco() {
        Partitura partitura = PartituraFactory.criarSemTitulo();

        assertThatThrownBy(() -> validator.validate(partitura))
            .isInstanceOf(BusinessException.class)
            .hasMessage(PartituraFactory.MENSAGEM_TITULO_OBRIGATORIO);
    }
}
