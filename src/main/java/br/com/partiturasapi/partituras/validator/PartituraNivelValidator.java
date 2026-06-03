package br.com.partiturasapi.partituras.validator;

import br.com.partiturasapi.partituras.entity.Partitura;
import br.com.partiturasapi.shared.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class PartituraNivelValidator implements PartituraValidator {

    @Override
    public void validate(Partitura partitura) {
        if (partitura.getNivel() == null) {
            throw new BusinessException("Nível da partitura é obrigatório");
        }
    }
}
