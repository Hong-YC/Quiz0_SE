import java.io.FileNotFoundException;

public class BloodPressureSensor extends Sensor{
    BloodPressureSensor(Device device, Patient patient, String factorDataSet) throws FileNotFoundException {

        super(device, patient, factorDataSet);

    }

    @Override
    public double measure(){
        return read();
    }
}
