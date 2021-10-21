package distributedsystems.a1.DTO.builder;

import distributedsystems.a1.DTO.DeviceDTO;
import distributedsystems.a1.DTO.UserDTO;
import distributedsystems.a1.entities.Device;
import distributedsystems.a1.entities.User;
import org.modelmapper.ModelMapper;

public class DeviceBuilder {

    private DeviceBuilder(){ }

    public static DeviceDTO toDeviceDTO(Device device){
        ModelMapper modelMapper = new ModelMapper();
        DeviceDTO deviceDTO = modelMapper.map(device, DeviceDTO.class);
        return deviceDTO;
    }

    public static Device toEntity(DeviceDTO deviceDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Device device = modelMapper.map(deviceDTO, Device.class);
        return device;
    }

}
