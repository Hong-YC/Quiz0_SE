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


}
