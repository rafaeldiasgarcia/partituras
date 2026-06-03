package br.com.partiturasapi.partituras.validator;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import br.com.partiturasapi.partituras.entity.Partitura;
import br.com.partiturasapi.partituras.factory.PartituraFactory;
import br.com.partiturasapi.shared.exception.BusinessException;
import org.junit.jupiter.api.Test;

class PartituraNivelValidatorTest {

    private final PartituraNivelValidator validator = PartituraFactory.criarNivelValidator();

    @Test
    void deveAceitarPartituraComNivelValido() {
        Partitura partitura = PartituraFactory.criar();

        assertThatCode(() -> validator.validate(partitura)).doesNotThrowAnyException();
    }

    @Test
    void deveRejeitarPartituraSemNivel() {
        Partitura partitura = PartituraFactory.criarSemNivel();

        assertThatThrownBy(() -> validator.validate(partitura))
            .isInstanceOf(BusinessException.class)
            .hasMessage(PartituraFactory.MENSAGEM_NIVEL_OBRIGATORIO);
    }
}
