package distributedsystems.a1.services;

import distributedsystems.a1.DTO.DeviceDTO;
import distributedsystems.a1.DTO.SensorDTO;
import distributedsystems.a1.DTO.builder.SensorBuilder;
import distributedsystems.a1.entities.Device;
import distributedsystems.a1.entities.Sensor;
import distributedsystems.a1.repositories.DeviceRepo;
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
    private DeviceRepo deviceRepo;

    @Autowired
    public SensorServiceImpl(SensorRepo sensorRepo, DeviceRepo deviceRepo) {
        this.sensorRepo = sensorRepo;
        this.deviceRepo = deviceRepo;
    }

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
        Device device = deviceRepo.findById(sensorDTO.getDeviceID()).get();
        Sensor sensor = SensorBuilder.toEntity(sensorDTO, device);
        sensor = sensorRepo.save(sensor);
        LOGGER.debug("Sensor with id {} was inserted in db", sensor.getSensorID());
        return sensor.getSensorID();
    }

    @Override
    public void deleteSensor(Long sensorID){
        if(sensorRepo.findById(sensorID).isPresent()){
            LOGGER.error("Sensor with id {} was not found in db", sensorID);
            throw new ResourceNotFoundException(Sensor.class.getSimpleName() + " with id: " + sensorID);
        }
        deviceRepo.deleteById(sensorID);
    }

    @Override
    public Long updateSensor(Long sensorID, SensorDTO sensorDTO){
        Optional<Sensor> optionalSensor = sensorRepo.findById(sensorID);
        if(!optionalSensor.isPresent()){
            LOGGER.error("Sensor with id {} was not found in db", sensorID);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + sensorID);

        }

        Sensor sensor = optionalSensor.get();

        sensor.setDescription(sensorDTO.getDescription());
        sensor.setMax(sensorDTO.getMax());
        sensor.setDevice(deviceRepo.findById(sensorDTO.getDeviceID()).get());

        sensorRepo.save(sensor);

        LOGGER.debug("Sensor with id {} was updated in db", sensor.getSensorID());
        return sensor.getSensorID();
    }
}
