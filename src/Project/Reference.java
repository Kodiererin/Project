package Project;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedHashMap;


public class Reference {
	static class Data implements Comparable<Data> {
	    byte data;
	    int frequency;

	    Data(byte data, int frequency) {
	        this.data = data;
	        this.frequency = frequency;
	    }

	    public int compareTo(Data other) {
	        return this.frequency - other.frequency;
	    }
	}

    static class Node implements Comparable<Node> {
        Data variable;
        Node left;
        Node right;

        // Creating a Constructor
        Node(Data variable) {
            this.variable = new Data(variable.data, variable.frequency);
            this.left = null;
            this.right = null;
        }

        // A default constructor
        Node() {
            this.variable = new Data(Byte.MIN_VALUE, 0);
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(Node o) {
            return this.variable.compareTo(o.variable);
        }
    }

    // Getting the Map from Data and Converting it to the Map
    public static void createNodes() {
        Map<Byte, Integer> map = getMap();
        ArrayList<Data> list = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            Byte key = entry.getKey();
            Integer val = entry.getValue();
            Data temp = new Data(key, val);
            list.add(temp);
        }
        creatingHuffmanNode(list);
    }

    // Displaying the Nodes
    public static void display(ArrayList<Data> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).data + "---" + list.get(i).frequency);
        }
    }

    // Creating Huffman Node
    public static void creatingHuffmanNode(ArrayList<Data> list) {
        Queue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < list.size(); i++) {
            Data data = list.get(i);
            Node temp = new Node(data);
            queue.add(temp);
        }
        Node head = null;
        while (!queue.isEmpty()) {
            Node a = queue.poll();
            if (queue.peek() == null) {
                head = a;
                break;
            } else if (queue.peek().variable.frequency > a.variable.frequency) {
                Node c = new Node();
                c.left = a;
                c.right = queue.poll();
                c.variable.frequency = c.left.variable.frequency + c.right.variable.frequency;
                queue.add(c);
            } else {
                Node b = queue.poll();
                if (b.variable.frequency <= queue.peek().variable.frequency) {
                    Node d = new Node();
                    d.left = b;
                    d.right = queue.poll();
                    d.variable.frequency = d.left.variable.frequency + d.right.variable.frequency;
                    queue.add(d);
                    queue.add(a);
                }
            }
        }
        generateHuffmanCode(list, head);
        System.out.println("-----------------");
        displayNode(head);
    }

    // Generation Of Huffman Code
    static class Visited {
        byte data;
        int huffmanCode;
        boolean visited;

        Visited(byte data) {
            this.data = data;
            this.huffmanCode = 0;
            this.visited = false;
        }
    }

    public static void generateHuffmanCode(ArrayList<Data> list, Node head) {
        System.out.println(head.variable.data);
        Visited[] arr = new Visited[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Visited(list.get(i).data);
        }
        System.out.println("Creation Of Array Successful");
        for (int i = 0; i < arr.length; i++) {
//            setHuffmanCode(arr, head, arr[i], 0, 0);
        }
        System.out.println("Success");
        // Displaying the Huffman Code
        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i].data + " -----> " + arr[i].huffmanCode);
        }
    }

    // Using Dijkstra's Algorithm to Get the Shortest Path in order to get the Huffman
    // Code
    public static void setHuffmanCode(Visited[] arr, Node head, Visited destination, int code, int i) {
        if (head.variable.data == destination.data && (head.left == null || head.right == null) && head != null) {
            arr[destination.data].huffmanCode = code;
        } else {
            if (head.left != null) {
                code = (1 << i) | code;
                head = head.left;
                setHuffmanCode(arr, head, destination, code, i + 1);
            } else {
                code = code | (0 << i);
                head = head.right;
                setHuffmanCode(arr, head, destination, code, i + 1);
            }
        }
    }

    // Display Successful
    public static void displayNode(Node head) {
        Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node a = queue.poll();
            if (a.variable.data != Byte.MIN_VALUE)
                System.out.println(a.variable.data);
            if (a.left != null)
                queue.add(a.left);
            if (a.right != null)
                queue.add(a.right);
        }
    }

    public static HashMap<Byte, Integer> getMap() {
        HashMap<Byte, Integer> frequencyMap = new HashMap<>();
//        String filePath = "C:\\Users\\Ujjwal\\Desktop\\Algorithm_Design_Project\\Eclipse\\Algorithm_Design_Project\\src\\Project\\TestImage.jpg";
        // Replace with the actual file path
        String filePath = "nodes.dat";
//        HashMap<Byte, Integer> map = getMap(filePath);
//        createNodes(map);
        
        try (FileInputStream fis = new FileInputStream(filePath)) {
            int byteRead;
            while ((byteRead = fis.read()) != -1) {
                frequencyMap.merge((byte) byteRead, 1, Integer::sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Map.Entry<Byte, Integer>> list = new ArrayList<>(frequencyMap.entrySet());
        list.sort(Comparator.comparing(Map.Entry::getValue));

        HashMap<Byte, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<Byte, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }

        // Display the frequency of each byte
        for (Map.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
            byte b = entry.getKey();
            int frequency = entry.getValue();
            System.out.println("Byte: " + b + ", Frequency: " + frequency);
        }

        return frequencyMap;
    }

    public static void main(String[] args) {
        createNodes();
    }
}
