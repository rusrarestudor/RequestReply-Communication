package distributedsystems.a1.services;

import distributedsystems.a1.DTO.MeasurementDTO;
import distributedsystems.a1.DTO.builder.MeasurementBuilder;
import distributedsystems.a1.entities.Measurement;
import distributedsystems.a1.repositories.MeasurementRepo;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeasurementServiceImpl implements  MeasurementService{

    private static final Logger LOGGER = LoggerFactory.getLogger(MeasurementService.class);
    private MeasurementRepo measurementRepo;

    @Autowired
    public MeasurementServiceImpl(MeasurementRepo measurementRepo) { this.measurementRepo = measurementRepo; }

    @Override
    public List<MeasurementDTO> findMeasurements() {
        List<Measurement> measurementList = measurementRepo.findAll();
        return measurementList.stream()
                .map(MeasurementBuilder::toMeasurementDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MeasurementDTO findMeasurementById(Long id) {
        Optional<Measurement> measurementOptional = measurementRepo.findById(id);
        if (!measurementOptional.isPresent()) {
            LOGGER.error("Measurement with id {} was not found in db", id);
            throw new ResourceNotFoundException(Measurement.class.getSimpleName() + " with id: " + id);
        }
        return MeasurementBuilder.toMeasurementDTO(measurementOptional.get());
    }

    @Override
    public Long insertMeasurement(MeasurementDTO measurementDTO) {
        Measurement measurement = MeasurementBuilder.toEntity(measurementDTO);
        measurement = measurementRepo.save(measurement);
        LOGGER.debug("Measurement with id {} was inserted in db", measurement.getMeasurementID());
        return measurement.getMeasurementID();
    }
}
