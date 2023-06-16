package Project;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Data implements Comparable<Data>,Comparator<Data> {
    byte data;
    int frequency;
    Data(byte data , int frequency ){
        this.data = data;
        this.frequency = frequency;
    }

	public int compareTo(Data other) {
        return this.frequency - other.frequency;
    }

	@Override
	public int compare(Data o1, Data o2) {
		return o1.frequency==o2.frequency?0:o1.frequency>o2.frequency?1:-1;
	}
}
public class Test  {
    static class Node implements Comparable<Node>{
        Data variable;
        Node left;
        Node right;
        // Creating a Constructor
        Node(Data variable){
            this.variable = new Data(variable.data , variable.frequency);
            this.left = null;
            this.right = null;
        }
//        A default constructor
        Node(){
        	this.variable = new Data(Byte.MIN_VALUE ,0);
        	this.left = null;
        	this.right = null;
        }

		@Override
		public int compareTo(Node o) {
			return this.variable.frequency - o.variable.frequency;
		}
    }
//    Getting the Map from Data and Converting it to the Map
    public static void createNodes() {
    	java.util.HashMap<Byte, Integer> map = getMapData.getMap();
    	java.util.ArrayList<Data> list = new java.util.ArrayList<>();
    	for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
			Byte key = entry.getKey();
			Integer val = entry.getValue();
			Data temp = new Data(key , val);
			list.add(temp);
		}
//    	display(list);
    	creatingHuffmanNode(list);
    }
//    	Displaying the Nodes
    public static void display(ArrayList<Data> list) {
    	for(int i=0 ; i<list.size() ; i++) {
    		System.out.println(list.get(i).data+"---"+list.get(i).frequency);
    	}
    }
//    Creating Huffman Node
//    Successful
    public static void creatingHuffmanNode(java.util.ArrayList<Data> list) {
    	System.out.println("Testing Code");
    	java.util.Queue<Node> queue = new java.util.PriorityQueue<>();
//    	Data data = new Data(list.get(0).data , list.get(0).frequency);
//		Node temp = new Node(data);
//		queue.add(temp);
    	for(int i=0 ; i<list.size() ; i++) {
    		Data data = new Data(list.get(i).data , list.get(i).frequency);
    		Node temp = new Node(data);
    		queue.add(temp);
    	}
    	Node Head = new Node();
    	while(!queue.isEmpty()) {
    		Node a = queue.poll();
    		if(queue.peek()==null) {
    			Head = a;
    			break;
    		}else if(queue.peek().variable.frequency>a.variable.frequency) {
    			Node c = new Node();
    			c.left = a;
    			c.right = queue.poll();
    			c.variable.frequency = c.left.variable.frequency + c.right.variable.frequency;
    			queue.add(c);
    		}else {
    			Node b = queue.poll();
    			if(b.variable.frequency<=queue.peek().variable.frequency) {
    				Node d = new Node();
    				d.left = b;
    				d.right = queue.poll();
    				d.variable.frequency = d.left.variable.frequency + d.right.variable.frequency;
    				queue.add(d);
    				queue.add(a);
    			}
    		}
    	}
    	generateHuffmanCode(list, Head);
    	System.out.println("-----------------");
    	displayNode(Head);
    }
//    Generation Of Huffman Code
//    Creating a Static class to ensure for visited Array
    static class Visited{
    	byte data;
    	int huffmanCode;
    	boolean visited;
    	Visited(byte data){
    		this.data = data;
    		this.huffmanCode = 0;
    		this.visited = false;
    	}
    }
    public static void generateHuffmanCode(java.util.ArrayList<Data> list , Node Head) {
    	System.out.println(Head.variable.data);
    	Visited[] arr = new Visited[list.size()];
    	for(int i=0 ; i<arr.length ; i++) {
    		arr[i]= new Visited(list.get(i).data);
    	}
    	System.out.println("Creation Of Array Successful");
    	for(int i=0 ; i<arr.length ; i++) {
//    		setHuffmanCode(arr, Head, arr[i], 0, 0);
    	}
    	System.out.println("Success");    	
//    	Displaying the Huffman Code
    	for(int i=0 ; i<arr.length ; i++) {
//    		System.out.println(arr[i].data+" -----> "+arr[i].huffmanCode);
    	}
    }
//    Using Dikshatra's Algorithm to Get the Shortest Path inorder to get the Huffman Code
    public static void setHuffmanCode(Visited[] arr , Node Head , Visited destination ,int code , int i) {
    	System.out.println(Head.variable.data+"---------"+destination.data);
    	if(Head.variable.data==destination.data && (Head.left==null || Head.right==null) && Head!=null) {
    		arr[Head.variable.data].huffmanCode = code;
    	}else {
    		if(Head.left!=null) {
    			code = (1*(int)Math.pow(10, i))+code;
    			Head = Head.left;
    			setHuffmanCode(arr, Head, destination, code, i+1);
    		}else {
    			code = code+(0*(int)Math.pow(10, i));
    			Head = Head.right;
    			setHuffmanCode(arr, Head , destination, code, i+1);
    		}
    	}
    }
    
//    Display Successful
    
    public static void displayNode(Node head) {
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node a = queue.poll();
            if(a.variable.data!=Byte.MIN_VALUE)
                System.out.println(a.variable.data) ;
            if (a.left != null)
                queue.add(a.left);
            if (a.right != null)
                queue.add(a.right);
        }
    }
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
        
//         Display the frequency of each byte
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