package Project;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Convertor {

    public static void main(String[] args) {
    	java.util.HashMap<Byte, Integer> frequencyMap = new HashMap<>();
    	String filePath = "C:\\Users\\Ujjwal\\Desktop\\Algorithm_Design_Project\\Eclipse\\Algorithm_Design_Project\\src\\Project\\TestImage.jpg"; 
        // Replace with the actual file path

        // Create a map to store byte frequencies
        // Map<Byte, Integer> frequencyMap = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(filePath)) {
            // Read the file byte by byte
            int byteRead;
            while ((byteRead = fis.read()) != -1) {
                // Update the frequency map
                frequencyMap.merge((byte) byteRead, 1, Integer::sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Display the frequency of each byte
        for (Map.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
            byte b = entry.getKey();
            int frequency = entry.getValue();
            System.out.println("Byte: " + b + ", Frequency: " + frequency);
        }
    }
}

