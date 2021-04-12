import java.io.FileNotFoundException;

public class Device {
    private Patient monitoredPatient;
    private Sensor sensor;
    private String name;

    public Device(String name) {
        this.name = name;
    }

    private void attachedSensor(String sensorType, String factorDataSet) throws FileNotFoundException {
        sensor = SensorFactory.valueOf(sensorType).getInstance(this, monitoredPatient, factorDataSet);
    }

    // attach the device to the patient, specify the sensor type
    public void monitorPatient(Patient patient, String sensorType, String factorDataSet) throws FileNotFoundException {
        monitoredPatient = patient;
        attachedSensor(sensorType, factorDataSet);
        monitoredPatient.attach(this);

    }
    public double measure(){
        if(sensor != null){
            return sensor.measure();
        }
        System.out.println("Not attached to Patient yet!");
        return -1;
    }

    public void print(){
        System.out.println("This device use sensor: " + sensor.toString());
        System.out.println("Attached to patient: " + monitoredPatient.getName());
    }

}
