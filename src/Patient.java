import java.util.ArrayList;
import java.util.List;


public class Patient {
    private String name;
    private double pulseRate;
    private double bloodPressure;
    private double temperature;

    private List<Device> attachedDevice;
    Patient(String name){
        this.name = name;
        attachedDevice = new ArrayList<>();
    }
    public void attach(Device device){
        attachedDevice.add(device);
    }


    public double getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(double bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPulseRate() {
        return pulseRate;
    }

    public void setPulseRate(double pulseRate) {
        this.pulseRate = pulseRate;
    }

    public String getName() {
        return name;
    }
}
