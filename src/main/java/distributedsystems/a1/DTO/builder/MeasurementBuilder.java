package distributedsystems.a1.DTO.builder;

import distributedsystems.a1.DTO.DeviceDTO;
import distributedsystems.a1.DTO.MeasurementDTO;
import distributedsystems.a1.DTO.UserDTO;
import distributedsystems.a1.entities.Device;
import distributedsystems.a1.entities.Measurement;
import distributedsystems.a1.entities.User;
import org.modelmapper.ModelMapper;

public class MeasurementBuilder {
    private MeasurementBuilder() {
    }

    public static MeasurementDTO toMeasurementDTO(Measurement measurement) {
        ModelMapper modelMapper = new ModelMapper();
        MeasurementDTO measurementDTO = modelMapper.map(measurement, MeasurementDTO.class);
        return measurementDTO;
    }

    public static Measurement toEntity( MeasurementDTO  measurementDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);
        return measurement;
    }
}
