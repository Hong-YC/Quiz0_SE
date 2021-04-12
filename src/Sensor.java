public abstract class Sensor {
    Patient patient;
    Device device;
    Sensor(Device device, Patient patient){
        this.patient = patient;
        this.device = device;
    }
    public abstract double measure();
}

