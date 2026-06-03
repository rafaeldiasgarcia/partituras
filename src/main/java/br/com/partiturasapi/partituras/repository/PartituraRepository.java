package br.com.partiturasapi.partituras.repository;

import br.com.partiturasapi.partituras.entity.Partitura;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartituraRepository extends JpaRepository<Partitura, UUID> {
}
