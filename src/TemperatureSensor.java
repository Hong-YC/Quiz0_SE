import java.io.FileNotFoundException;

public class TemperatureSensor extends Sensor{
    TemperatureSensor(Device device, Patient patient, String factorDataSet) throws FileNotFoundException {

        super(device, patient, factorDataSet);

    }

    @Override
    public double measure(){
        return read();
    }
}
