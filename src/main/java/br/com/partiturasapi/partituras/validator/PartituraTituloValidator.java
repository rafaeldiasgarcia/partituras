package br.com.partiturasapi.partituras.validator;

import br.com.partiturasapi.partituras.entity.Partitura;
import br.com.partiturasapi.shared.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class PartituraTituloValidator implements PartituraValidator {

    @Override
    public void validate(Partitura partitura) {
        if (partitura.getTitulo() == null || partitura.getTitulo().isBlank()) {
            throw new BusinessException("Título da partitura é obrigatório");
        }
    }
}
