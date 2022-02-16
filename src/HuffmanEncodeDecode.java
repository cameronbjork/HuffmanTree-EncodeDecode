import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**Program to encode and decode using a binary tree and min heap
 * 
 * @author Cameron Bjork
 *
 */
public class HuffmanEncodeDecode {
	
	/** Main
	 * 
	 * @param args - cmd line
	 */
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a file name: ");
		Scanner inFile = null;		
		
		//Read in File
		try {
			File myObj = new File(sc.nextLine());
			inFile = new Scanner(myObj);
		} catch (FileNotFoundException e) {
			System.out.println("Invalid File");
		} finally {
			
			//Translate file text to string then to char array
			String fileString = "";
			while(inFile.hasNextLine()) {
				fileString = fileString + inFile.nextLine() + "\n";
			}
			inFile.close();
			
			//Calculate frequency of characters
			HashMap<Character, Integer> heapChars = new HashMap<Character, Integer>();
			char fileCharString[] = fileString.toCharArray();
			
			for(char text:fileCharString) {
				if (!heapChars.containsKey(text)) {
					heapChars.put(text, 1);
				} else
					heapChars.put(text, heapChars.get(text) + 1);
			}
			
			//Creates minHeap with HuffTree's
			ArrayList<Character> keys = new ArrayList<>(heapChars.keySet());
			ArrayList <Integer> values = new ArrayList<>(heapChars.values());	
			MinHeap<HuffTree<Character>> mHeap = new MinHeap<>();
			
			for (int i = 0; i < keys.size(); i++) {
				mHeap.insert(new HuffTree<>(keys.get(i), values.get(i)));
			}
			
			//Removes 2, adds weights, and adds back to heap, 
			//finalTree is at root
			buildTree(mHeap);
			HuffTree<Character> finalTree = mHeap.removemin();
			HuffBaseNode<Character> rootNode = finalTree.root();
			
			System.out.println("--- TREE BUILT ---");
			
			String encodedString = "";
			HashMap<Character, String> encodedMap = new HashMap<>();
			encode(rootNode, encodedString, encodedMap);
			
			//Converts strings to 0 & 1, and prints
			//Clean number output
			String outputString = "";
			String finalEncodedString ="";
			String temp = "";
			for (char text:fileCharString) {
				temp = encodedMap.get(text);
				outputString += "[" + text + "= " + temp + "], ";
				finalEncodedString += temp;
			}
			System.out.println("--- LETTERS AND BINARY REPRESENTATION ---\n" + outputString + "\n");
			System.out.println("--- FINAL ENCODED TEXT --- \n" + finalEncodedString + "\n");
			
			//Store to output file
			System.out.print("Enter a file to send encoded text to: ");
			FileWriter fw;
			try {
				fw = new FileWriter(new File(sc.nextLine()));
				fw.write(finalEncodedString);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//Decode encoded text file and print
			System.out.print("Enter file location for decoded text: ");
			Scanner inFile2 = null;
			try {
				inFile2 = new Scanner(new File(sc.nextLine()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String encodedInput = "";
			while(inFile2.hasNextLine()) {
				encodedInput += inFile2.nextLine();
			}
			inFile2.close();
			
			outputString = decode(encodedInput, rootNode);
			System.out.println(outputString);
			sc.close();
			
		}
	}
	
	/**Builds tree, places at root of heap
	 * 
	 * @param heap - heap filled with HuffTree's to be sorted
	 */
	static void buildTree(MinHeap<HuffTree<Character>> heap) {
		HuffTree<Character> tmp1, tmp2, tmp3 = null;
		
		while (heap.heapSize() > 1) {
			tmp1 = heap.removemin();
			tmp2 = heap.removemin();
			tmp3 = new HuffTree<Character> (tmp1.root(), tmp2.root(), tmp1.weight() + tmp2.weight());
			heap.insert(tmp3);
		}
	}
	
	
	/**Store encoded values to a hashmap, and call until all leafs
	 * 
	 * @param root - HuffTree
	 * @param s - Final encoded string
	 * @param hm - Map to store encoded values
	 */
	static void encode(HuffBaseNode<Character> root, String s, HashMap<Character, String> hm) {
		if (root.isLeaf()) {
			hm.put((Character) root.element(), s); 
		}
		else {
			encode(((HuffInternalNode<Character>) root).left(), s + "0", hm);
			encode(((HuffInternalNode<Character>)root).right(), s + "1", hm);
		}
	}
	
	/**
	 * @param s - Encoded string
	 * @param root - Root of the huff tree
	 * @return OutputString
	 */
	static String decode(String s, HuffBaseNode<Character> root) {
		char charArray[] = s.toCharArray();
		HuffBaseNode<Character> tempNode = root;
		String out = "";
		
		for (int i=0; i < charArray.length; i++) {
			if (charArray[i] == '0')
				tempNode = tempNode.left();
			else
				tempNode = tempNode.right();
			
			if (tempNode.isLeaf()) {
				out += tempNode.element();
				tempNode = root;
			}
		}
		return out;
	}
	
	

}
