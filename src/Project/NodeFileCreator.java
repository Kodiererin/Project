package Project;

import java.io.FileOutputStream;
import java.io.IOException;

public class NodeFileCreator {
    public static void main(String[] args) {
        // Define the data and frequency for the nodes
        byte[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int[] frequency = { 20, 15, 10, 5, 8, 12, 7, 18, 3, 6 };

        // Create a file with the node data and frequency
        String filePath = "nodes.dat";
        createNodeFile(filePath, data, frequency);

        System.out.println("Node file created: " + filePath);
    }

    private static void createNodeFile(String filePath, byte[] data, int[] frequency) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            // Write the number of nodes
            fos.write(data.length);

            // Write the data and frequency for each node
            for (int i = 0; i < data.length; i++) {
                fos.write(data[i]);
                fos.write(frequency[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
