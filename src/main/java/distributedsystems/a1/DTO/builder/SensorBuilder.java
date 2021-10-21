package distributedsystems.a1.DTO.builder;

import distributedsystems.a1.DTO.DeviceDTO;
import distributedsystems.a1.DTO.SensorDTO;
import distributedsystems.a1.DTO.UserDTO;
import distributedsystems.a1.entities.Measurement;
import distributedsystems.a1.entities.Sensor;
import distributedsystems.a1.entities.User;
import org.modelmapper.ModelMapper;

public class SensorBuilder {

    private SensorBuilder(){}

    public static SensorDTO toSensorDTO(Sensor sensor){
        ModelMapper modelMapper = new ModelMapper();
        SensorDTO sensorDTO = modelMapper.map(sensor, SensorDTO.class);
        return sensorDTO;
    }

    public static Sensor toEntity(SensorDTO sensorDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Sensor sensor = modelMapper.map(sensorDTO, Sensor.class);
        return sensor;
    }
}
