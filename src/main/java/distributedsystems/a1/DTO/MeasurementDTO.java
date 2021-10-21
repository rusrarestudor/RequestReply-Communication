package distributedsystems.a1.DTO;

import distributedsystems.a1.entities.Sensor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

public class MeasurementDTO extends RepresentationModel<MeasurementDTO> {

    public MeasurementDTO(Long measurementID, LocalDateTime timeStamp, double value, Sensor sensor) {
        this.measurementID = measurementID;
        this.timeStamp = timeStamp;
        this.value = value;
        this.sensor = sensor;
    }

    private Long measurementID;

    private LocalDateTime timeStamp;

    private double value;

    private Sensor sensor;

    public Long getMeasurementID() { return measurementID; }

    public void setMeasurementID(Long measurementID) { this.measurementID = measurementID; }

    public LocalDateTime getTimeStamp() { return timeStamp; }

    public void setTimeStamp(LocalDateTime timeStamp) { this.timeStamp = timeStamp; }

    public double getValue() { return value; }

    public void setValue(double value) { this.value = value; }

    public Sensor getSensor() { return sensor; }

    public void setSensor(Sensor sensor) { this.sensor = sensor; }
}
