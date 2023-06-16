package Project;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class getMapData {
	 public static java.util.HashMap<Byte, Integer> getMap(){
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
	        
	        java.util.List<Map.Entry<Byte, Integer> > list =
	                new java.util.LinkedList<Map.Entry<Byte, Integer>>(frequencyMap.entrySet());
	  
	         // Sort the list
	         java.util.Collections.sort(list, new java.util.Comparator<Map.Entry<Byte, Integer> >() {
	             public int compare(Map.Entry<Byte, Integer> o1,
	                                Map.Entry<Byte, Integer> o2)
	             {
	                 return (o1.getValue()).compareTo(o2.getValue());
	             }
	         });
	          
	         // put data from sorted list to hashmap
	         java.util.HashMap<Byte, Integer> temp = new java.util.LinkedHashMap<Byte, Integer>();
	         for (Map.Entry<Byte, Integer> aa : list) {
	             temp.put(aa.getKey(), aa.getValue());
	         }
	        
//	         Display the frequency of each byte
	        for (Map.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
	            byte b = entry.getKey();
	            int frequency = entry.getValue();
	            System.out.println("Byte: " + b + ", Frequency: " + frequency);
	        }
	        
	        return frequencyMap;
	    }
	 public static void main(String[] args) {
		getMap();
	}
}
