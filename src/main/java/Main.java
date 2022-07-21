import java.io.*;

public class Main {

    public static void main(String[] args) {
        new ParkingLotImpl();

        String filePath = "input.txt";
        File inputFile = new File(filePath);

        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String input;
            try {
                while ((input = br.readLine()) != null) {
                    ProcessInput.inputProcessor(input.trim());
                }
            } catch (IOException ex) {
                System.out.println("Error in reading the input file");
                ex.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified path.");
            e.printStackTrace();
        }
    }
}
