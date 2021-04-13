import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Database {
    private List<patientLog> patients;
    public Database(List<Patient> patients){
        this.patients = new ArrayList<>();
        for(Patient p: patients){
            this.patients.add(new patientLog(p));
        }
    }
    //Should I store Patient in it?
    private class DeviceLog{
        //(time, value)
        private List<Pair<Integer, Double>> logs;
        private Device device;

        public DeviceLog(Device device){
            this.device = device;
            logs = new ArrayList();
        }
        public void addLog(int time, double value){
            Pair<Integer, Double> log = new Pair<>(time, value);
            logs.add(log);
        }
        //print all the log
        public void print(){
            //DeviceType DeviceName
            System.out.printf("%s %s\n", device.getType(), device.getName());
            for(Pair<Integer, Double> log: logs){
                System.out.printf("[%d] %.1f\n", log.getX(), log.getY());

            }
        }
        public boolean checkDevice(Device device){
            return this.device.equals(device);
        }
    }

    private class patientLog{
        private Patient patient;
        private List<DeviceLog> devices;
        public patientLog(Patient patient){
            this.patient = patient;
            devices = new ArrayList<>();
            List<Device> patientDevice = patient.getDevice();
            for(Device d: patientDevice){
                devices.add(new DeviceLog(d));
            }
        }
        public void addLog(Device device, int time, double value){
            //First loop through all stored devices
            for(DeviceLog d: devices){
                if(d.checkDevice(device)){
                    d.addLog(time, value);
                    return;
                }
            }
        }
        //check whether the input and the store patient are the same
        public boolean checkPatient(Patient patient){
            return this.patient.equals(patient);
        }
        public void print(){
            System.out.println("patient " +  patient.getName());
            for(DeviceLog d: devices){
                d.print();
            }
        }

    }
    public void store(Patient patient, Device device, int time, double value){
        //First loop through all stored patients
        for(patientLog p: patients){
            if(p.checkPatient(patient)){
                p.addLog(device, time, value);
                return;
            }
        }

    }
    public void Display(){
        for(patientLog p: patients){
            p.print();
        }

        
    }
}
