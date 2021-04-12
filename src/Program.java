import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {
    private Database db;
    private List<Pair<Patient, Integer>> frequency;
    private Map<Pair<Patient, String>, Pair<Double, Double>> safeRange;
    private double monitorPeriod;
    Program(double monitorPeriod){
        this.monitorPeriod = monitorPeriod;
        db = new Database();
        frequency = new ArrayList<>();
        safeRange = new HashMap<>();
    }
    public void addPatient(Patient patient, int freq){
        frequency.add(new Pair<>(patient, freq));
    }
    public void addSafeRange(Patient patient, String type, double lb, double ub){
        safeRange.put(new Pair<>(patient, type), new Pair<>(lb, ub));
    }


    public void Run(){
        for(int time = 0; time <= monitorPeriod; time++){
            //loop through all Patient



        }

    }
    public void displayDatabase(){
        db.Display();
    }

    private boolean checkRange(Patient patient, String type, double factor){
        Pair<Double, Double> range = safeRange.get(new Pair(patient, type));
        Double lowerBound = range.getX();
        Double upperBound = range.getY();
        if(factor < lowerBound || upperBound < factor){
            return false;
        }
        return true;
    }

}
