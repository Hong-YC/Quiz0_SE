
public enum SensorFactory{
    PulseRateSensor{
        @Override
        public Sensor getInstance(Device device, Patient patient){
            return new PulseRateSensor(device, patient);
        }
    };

    public abstract Sensor getInstance(Device device, Patient patient);
    }
