import java.io.FileNotFoundException;

public enum SensorFactory{
    PulseRateSensor{
        @Override
        public Sensor getInstance(Device device, Patient patient, String factorDataSet) throws FileNotFoundException {
            return new PulseRateSensor(device, patient, factorDataSet);

        }
    },
    BloodPressureSensor{
        @Override
        public Sensor getInstance(Device device, Patient patient, String factorDataSet) throws FileNotFoundException {
            return new BloodPressureSensor(device, patient, factorDataSet);

        }
    },
    TemperatureSensor{
        @Override
        public Sensor getInstance(Device device, Patient patient, String factorDataSet) throws FileNotFoundException {
            return new TemperatureSensor(device, patient, factorDataSet);

        }
    };

    public abstract Sensor getInstance(Device device, Patient patient, String factorDataSet) throws FileNotFoundException;
    }
