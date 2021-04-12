import java.io.FileNotFoundException;

public enum SensorFactory{
    PulseRateSensor{
        @Override
        public Sensor getInstance(Device device, Patient patient, String factorDataSet) throws FileNotFoundException {
            return new PulseRateSensor(device, patient, factorDataSet);

        }
    };

    public abstract Sensor getInstance(Device device, Patient patient, String factorDataSet) throws FileNotFoundException;
    }
