import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private enum CommandType {
        Patient, Device, Other
    }
    private static final String[] DEVICECATA = {"PulseSensor", "BloodPressureSensor", "TemperatureSensor"};
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double MonitorPeriod = 0.0D;

        //Read the MonitorPeriod
        while(true) {
            System.out.println("Type a positive number:");
            try {
                MonitorPeriod = Double.parseDouble(reader.readLine());
                if(MonitorPeriod <= 0){
                    System.out.println("Please enter positive number!");
                    continue;
                }
                break;
            } catch (NumberFormatException var11) {
                System.out.println("Input Error");
            } catch (IOException ex) {
                System.out.println("Readline Error");
            }
        }
        //Initiate the program
        Program program = new Program(MonitorPeriod);

        //Read Patient & Sensor input
            //state variable which indicate the current Patient
        Patient state = null;
        try {
            //Read the first Patient
            String s = reader.readLine();
            do{
                String[] command = s.split("\\s+");
                CommandType t = checkCommandType(command);
                switch(t){
                    case Patient:
                        if(checkPatient(command)){
                            //construct the patient by the parameters, refresh the state
                            state = new Patient(command[1]);
                            program.addPatient(state, Integer.parseInt(command[2]));
                            break;
                        }
                        else{
                            System.out.println("Invalid patient input");
                            break;
                        }
                    case Device:
                        if(state == null){
                            System.out.println("Need to specify Patient first! ");
                            break;
                        }
                        //{device_category} {device_name} {factor_dataset_file} {safe_range_lower_bound} {safe_range_upper_bound}
                        if(checkDevice(command)){
                            //construct the device by the parameters, add it to patient?
                            Device device = new Device(command[1]);
                            device.monitorPatient(state, command[0], command[2]);
                            double lower_bound =  Double.parseDouble(command[3]);
                            double upper_bound =  Double.parseDouble(command[4]);
                            program.addSafeRange(state, command[0], lower_bound, upper_bound);
                        }
                        else{
                            System.out.println("Invalid Device input");

                        }
                        break;
                    default:
                        System.out.println("Invalid input");
                }


                s = reader.readLine();
            }while(!s.isEmpty());

            reader.close();

        }catch(IOException ex){
            System.out.println("Readline Error");
        }

        //Input end, start execute the program
        program.Run();
        program.displayDatabase();

    }

    //Use to check the type of the command( add patient or sensor or invalid)
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

    //Use to check whether the input parameter for Patient is valid
    private static boolean checkPatient(String[] s){
        if(s.length == 3){
            try{
                double PatientPeriod = Double.parseDouble(s[1]);
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

}
