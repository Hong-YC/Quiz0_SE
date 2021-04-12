import java.io.*;

public class PulseRateSensor extends Sensor{

    PulseRateSensor(Device device, Patient patient, String factorDataSet) throws FileNotFoundException {

        super(device, patient, factorDataSet);

    }

    @Override
    public double measure(){
        return read();
    }


}
