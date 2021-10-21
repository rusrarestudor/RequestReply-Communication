package distributedsystems.a1.services;

import distributedsystems.a1.DTO.SensorDTO;
import distributedsystems.a1.DTO.builder.SensorBuilder;
import distributedsystems.a1.entities.Sensor;
import distributedsystems.a1.repositories.SensorRepo;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SensorServiceImpl implements SensorService{

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorService.class);
    private SensorRepo sensorRepo;

    @Autowired
    public SensorServiceImpl(SensorRepo sensorRepo) { this.sensorRepo = sensorRepo; }

    @Override
    public List<SensorDTO> findSensors() {
        List<Sensor> sensorList = sensorRepo.findAll();
        return sensorList.stream()
                .map(SensorBuilder::toSensorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SensorDTO findSensorById(Long id) {
        Optional<Sensor> sensorOptional = sensorRepo.findById(id);
        if (!sensorOptional.isPresent()) {
            LOGGER.error("Sensor with id {} was not found in db", id);
            throw new ResourceNotFoundException(Sensor.class.getSimpleName() + " with id: " + id);
        }
        return SensorBuilder.toSensorDTO(sensorOptional.get());
    }

    @Override
    public Long insertSensor(SensorDTO sensorDTO) {
        Sensor sensor = SensorBuilder.toEntity(sensorDTO);
        sensor = sensorRepo.save(sensor);
        LOGGER.debug("Sensor with id {} was inserted in db", sensor.getSensorID());
        return sensor.getSensorID();
    }
}
