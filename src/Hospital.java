import java.util.ArrayList;
import java.util.List;


public class Hospital {
    List<Patient> ward ;
    Hospital(){
        ward = new ArrayList<>();
    }
    public void addPatient(Patient patient){
        ward.add(patient);
    }
}
