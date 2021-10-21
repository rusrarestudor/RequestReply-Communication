package distributedsystems.a1.services;


import distributedsystems.a1.DTO.SensorDTO;

import java.util.List;

public interface SensorService {
    public List<SensorDTO> findSensors();
    public SensorDTO findSensorById(Long id);
    public Long insertSensor(SensorDTO sensorDTO);
}
