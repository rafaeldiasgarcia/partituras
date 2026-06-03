package br.com.partiturasapi.partituras.validator;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import br.com.partiturasapi.partituras.entity.Partitura;
import br.com.partiturasapi.partituras.factory.PartituraFactory;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

class PartituraValidatorServiceTest {

    @Test
    void deveExecutarTodosOsValidatorsNaOrdemInformada() {
        PartituraValidator primeiro = PartituraFactory.criarValidatorMock();
        PartituraValidator segundo = PartituraFactory.criarValidatorMock();
        PartituraValidatorService service = PartituraFactory.criarValidatorService(primeiro, segundo);
        Partitura partitura = PartituraFactory.criar();

        service.validate(partitura);

        InOrder inOrder = inOrder(primeiro, segundo);
        inOrder.verify(primeiro).validate(partitura);
        inOrder.verify(segundo).validate(partitura);
        verifyNoMoreInteractions(primeiro, segundo);
    }
}
