import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;

public class Testing {
    private enum CommandType {
        Patient, Device, Other
    }
    private static final String[] DEVICECATA = {"PulseSensor", "BloodPressureSensor", "TemperatureSensor"};
    @Test
    public void testPair(){
        Patient p1 = new Patient("Haha");
        Patient p2 = new Patient("Hehe");
        Pair<Patient, String> pp = new Pair<>(p1, "I am Patient");
        Pair<Patient, String> ppp = new Pair<>(p1, "I am Patient");
        assertEquals(pp, ppp);
        assertEquals(pp.hashCode(), ppp.hashCode());
        Pair<Patient, String> pppp = new Pair<>(p2, "I am Patient");
        assertNotEquals(pp, pppp);
        assertNotEquals(pp.hashCode(), pppp.hashCode());
        Pair<Patient, String> ppppp = new Pair<>(p1, "I am a Patient");
        assertNotEquals(pp, ppppp);
        assertNotEquals(pp.hashCode(), ppppp.hashCode());
    }
    @Test
    public void testCommandType(){
        String s = "patient ha ha";
        String[] c = s.split("\\s+");
        assertEquals(CommandType.Patient, checkCommandType(c));

        s = "PulseSensor ha ha";
        c = s.split("\\s+");
        assertEquals(CommandType.Device, checkCommandType(c));

        s = "BloodPressureSensor ha ha";
        c = s.split("\\s+");
        assertEquals(CommandType.Device, checkCommandType(c));

        s = "TemperatureSensor ha ha";
        c = s.split("\\s+");
        assertEquals(CommandType.Device, checkCommandType(c));

        s = "TemperaddtureSensor ha ha";
        c = s.split("\\s+");
        assertEquals(CommandType.Other, checkCommandType(c));
    }

    @Test
    public void TestCheckPatient(){
        String s = "patient Mark 600";
        String[] c = s.split("\\s+");
        assertTrue(checkPatient(c));

        s = "patient Mark 60e";
        c = s.split("\\s+");
        assertFalse(checkPatient(c));

        s = "patient Mark -600";
        c = s.split("\\s+");
        assertFalse(checkPatient(c));

        s = "patient Mark 600 dd";
        c = s.split("\\s+");
        assertFalse(checkPatient(c));
    }


    @Test
    public void TestCheckDevice() {
        String s = "BloodPressureSensor sensor1 BloodPressureData1.dataset 150 200";
        String[] c = s.split("\\s+");
        assertTrue(checkDevice(c));

        s = "BloodPressureSensor sensor1 BloodPressureData1.dataset -150 200";
        c = s.split("\\s+");
        assertTrue(checkDevice(c));

        s = "BloodPressureSensor sensor1 BloodPressureData1.dataset 150 -200";
        c = s.split("\\s+");
        assertFalse(checkDevice(c));

        s = "BloodPressureSensor sensor1 BloodPressureData1.dataset 1500 200";
        c = s.split("\\s+");
        assertFalse(checkDevice(c));

        s = "BloodPressureSensor sensor1 BloodPressureData1.dataset 150 200 gg";
        c = s.split("\\s+");
        assertFalse(checkDevice(c));
    }


    static CommandType checkCommandType(String[] s){
        if(s != null){
            if(s[0].equals("patient")){
                return CommandType.Patient;
            }
            else if(Arrays.asList(DEVICECATA).contains(s[0])){
                return CommandType.Device;
            }

        }
        return CommandType.Other;
    }
    private static boolean checkPatient(String[] s){
        if(s.length == 3){
            try{
                double PatientPeriod = Double.parseDouble(s[2]);
                if(PatientPeriod <= 0){
                    System.out.println("Patient period need to be an positive number!");
                }
                else{
                    return true;
                }
            }catch(NumberFormatException var11) {
                System.out.println("Please type a number!");
            }
        }
        return false;
    }
    private static boolean checkDevice(String[] s){
        if(s.length == 5){
            try{
                double lowBound = Double.parseDouble(s[3]);
                double upBound = Double.parseDouble(s[4]);
                if(lowBound > upBound){
                    System.out.println("upper bound need to be larger than lower bound!!");

                }else{
                    return true;
                }

            }catch (NumberFormatException var11){
                System.out.println("Please type a number for lower and upper bound!");
            }


        }
        return false;
    }

    public static void main(String[] args) {

        Patient p = new Patient("haha");
        System.out.println(p.getClass().getName());
    }



}
