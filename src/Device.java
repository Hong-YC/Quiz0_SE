public class Device {
    private Patient monitoredPatient;
    private Sensor sensor;
    private void attachedSensor(String sensorType) {
        sensor = SensorFactory.valueOf(sensorType).getInstance(this, monitoredPatient);
    }

    // attach the device to the patient, specify the sensor type
    public void monitorPatient(Patient patient, String sensorType){
        monitoredPatient = patient;
        attachedSensor(sensorType);
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
