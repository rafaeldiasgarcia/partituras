package br.com.partiturasapi.partituras.validator;

import br.com.partiturasapi.partituras.entity.Partitura;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PartituraValidatorService {

    private final List<PartituraValidator> validators;

    public PartituraValidatorService(List<PartituraValidator> validators) {
        this.validators = validators;
    }

    public void validate(Partitura partitura) {
        validators.forEach(validator -> validator.validate(partitura));
    }
}
