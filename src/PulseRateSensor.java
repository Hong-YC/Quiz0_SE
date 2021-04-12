
public class PulseRateSensor extends Sensor{
    PulseRateSensor(Device device, Patient patient){
        super(device, patient);
    }
    @Override
    public double measure(){
        return 0;
    }
}
