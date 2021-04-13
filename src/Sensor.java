import javax.imageio.IIOException;
import java.io.*;

public abstract class Sensor {
    private Patient patient;
    private Device device;
    private BufferedReader reader;
    private File dataFile;

    Sensor(Device device, Patient patient, String factorDataSet) throws FileNotFoundException {
        this.patient = patient;
        this.device = device;
        dataFile = new File(factorDataSet);
        reader = new BufferedReader(new FileReader(dataFile));

    }
    protected double read(){
        try{
            String line = reader.readLine();
            //Reach end of file
            if(line == null){
                return -1;
            }
            double value =  Double.parseDouble(line);

            return value;
        }catch(IOException ex){
            return -1;
        }
    }
    public void Stop(){
        try {
            reader.close();
        }catch(Exception ex){
            System.out.println("File close fail!");
        }
    }

    public abstract double measure();
}

