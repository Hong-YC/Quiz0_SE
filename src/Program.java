import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {
    private Database db;
    private List<Pair<Patient, Integer>> frequency;
    private Map<Device, Pair<Double, Double>> safeRange;
    private double monitorPeriod;

    Program(double monitorPeriod){
        this.monitorPeriod = monitorPeriod;
        frequency = new ArrayList<>();
        safeRange = new HashMap<>();
    }

    private void initDatabase(){
        db = new Database(getPatientList());
    }

    public void addPatient(Patient patient, int freq){
        frequency.add(new Pair<>(patient, freq));
    }
    public void addSafeRange(Device device, double lb, double ub){
        safeRange.put(device, new Pair<>(lb, ub));
    }

    private List<Patient> getPatientList(){
        List<Patient> patients = new ArrayList<>();
        for(Pair<Patient, Integer> pr: frequency){
            patients.add(pr.getX());
        }
        return patients;
    }


    public void Run(){
        //initialize database
        initDatabase();

        for(int time = 0; time <= monitorPeriod; time++){
            //loop through all Patient
            for(Pair<Patient, Integer> pf: frequency){
                int freq = pf.getY();
                if(time % freq == 0){
                    Patient p = pf.getX();
                    for(Device d: p.getDevice()){
                        double factor = readFactor(d);
                        if(factor == -1){
                            //-1: Device fail
                            System.out.printf("[%d] %s falls\n", time, d.getName());
                        }
                        else if(!checkRange(d, factor)){
                            //Device working, factor out of range
                            System.out.printf("[%d] %s is in danger! Cause: %s %.1f\n", time, p.getName(), d.getName(), factor);

                        }
                        db.store(p,d,time,factor);
                    }
                }
            }



        }

    }
    public void displayDatabase(){
        db.Display();
    }

    private double readFactor(Device device){
        return device.measure();
    }

    private boolean checkRange(Device device, double factor){
        Pair<Double, Double> range = safeRange.get(device);
        Double lowerBound = range.getX();
        Double upperBound = range.getY();
        if(factor < lowerBound || upperBound < factor){
            return false;
        }
        return true;
    }

}
