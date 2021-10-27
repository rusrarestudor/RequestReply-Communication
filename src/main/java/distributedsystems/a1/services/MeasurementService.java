package distributedsystems.a1.services;

import distributedsystems.a1.DTO.MeasurementDTO;

import java.util.List;

public interface MeasurementService {
    public List<MeasurementDTO> findMeasurements();
    public MeasurementDTO findMeasurementById(Long id);
    public Long insertMeasurement(MeasurementDTO measurementDTO);
    public void deleteMeasurement(Long measurementID);
}
