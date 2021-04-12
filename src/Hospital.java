import java.util.HashSet;
import java.util.Set;

public class Hospital {
    Set<Patient> ward ;
    Hospital(){
        ward = new HashSet();
    }
    public void addPatient(Patient patient){
        ward.add(patient);
    }
}
