//Fiona Au
//
//Email fau@u.rochester.edu
//NetID fau
//CSC 172 Lab T/R 2:00-3:15
//Project #2

package Project2;

public class Node<T extends Comparable<T>> {
	public Line data;
	public Node leftChild;
	public Node rightChild;
	public int size;

	public Node(Line data, Node leftChild, Node rightChild) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public Node(Line data) {
		this.data = data;
	}
	
	public Node() {

	}
	
	public static void nodeToString(Line line) {
		System.out.println("Node to String: line.p1.x " + line.p1.x + " line.p1.y " + line.p1.y + " line.p2.x " + line.p2.x + " line.p2.y " + line.p2.y);
	}
	
	public static void nodeToString(Node node) {
		System.out.println("Node to String: line.p1.x " + node.data.p1.x + " line.p1.y " + node.data.p1.y + " line.p2.x " + node.data.p2.x + " line.p2.y " + node.data.p2.y);
	}
	
}