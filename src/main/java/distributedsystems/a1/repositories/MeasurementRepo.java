package distributedsystems.a1.repositories;

import distributedsystems.a1.entities.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepo extends JpaRepository<Measurement, Long> {
}
