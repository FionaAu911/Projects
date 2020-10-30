//Fiona Au
//
//Email fau@u.rochester.edu
//NetID fau
//CSC 172 Lab T/R 2:00-3:15
//Project #2

package Project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JFrame;


///Users/owner/Downloads/input5-2yes.txt
///Users/owner/Downloads/input5.txt

public class Main {
	
	private static String input;
	static Tree tree = new Tree();
	static ArrayList<Line> lines = new ArrayList<Line>();
	static ArrayList<Node> nodes = new ArrayList<Node>();
//	static JFrame frame = new JFrame("Canvas");
//	static Canvas canvas = new Canvas();
	static int externalNodes = 0;
	static int pathCounter = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
//		frame.add(canvas);
//		frame.setSize(500, 500);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
		File file = new File("/Users/owner/Downloads/input5-2yes.txt");
		Scanner fileScanner = new Scanner(file);
		input = fileScanner.nextLine(); //first line
		int lines = Integer.valueOf(input);
		for (int i = 0; i < lines; i++) { //scans in only the lines
			input = fileScanner.nextLine();
			addToTree(input); //TURN A LINE OF COORDINATES INTO A NODE THEN STORE IN TREE DEPENDING ON LOCATION
		}
		while (fileScanner.hasNext()) { //scans in the two points to test
			double[] doubles;
			input = fileScanner.nextLine();
			doubles = splitPoints(input);
			Point point1 = createPoint1(doubles);
			Point point2 = createPoint2(doubles);
			if (testSameRegion(point1, point2) == true) {
				System.out.println("Points are not separated by a line."); //in same region
			} else {
				System.out.println("Points are separated by a line."); //in different regions
				Line separation = new Line();
				separation = findSeparation(point1, point2);
				System.out.print("Points are separated by the line with points (");
				System.out.print(separation.p1.x + ", ");
				System.out.print(separation.p1.y + ") and (");
				System.out.print(separation.p2.x + ", ");
				System.out.println(separation.p2.y + ").");
			}
		}
		int externalNodes = externalNodes();
		System.out.println("The total number of external nodes is " + externalNodes + ".");
		int externalPathCount = externalPathCount();
		System.out.println("The total number of paths is " + pathCounter + ".");
		System.out.println("The average path length is " + pathCounter/externalNodes + ".");
	}
	
	public static int externalPathCount() {
		pathCounter = externalPathCount(tree.root) * 2 + 1;
		return pathCounter;
	}
	
	public static int externalPathCount(Node node) {
		while (node.leftChild != null) {
			externalPathCount(node.leftChild);
			pathCounter++;
			if (node.leftChild == null) {
				while (node.rightChild != null) {
					externalPathCount(node.rightChild);
					pathCounter++;
					if (node.rightChild == null) {
						break;
					}
				}
			}
			break;
		}
		while (node.rightChild != null) {
			externalPathCount(node.rightChild);
			pathCounter++;
			if (node.rightChild == null) {
				while (node.leftChild != null) {
					externalPathCount(node.leftChild);
					pathCounter++;
					if (node.leftChild == null) {
						break;
					}
				}
			}
			break;
		}
		return pathCounter;
	}
		
	public static int externalNodes() {
		externalNodes = externalNodes(tree.root);
		return externalNodes;
	}
	
	private static int externalNodes(Node node) {
		if (node.leftChild != null) {
			externalNodes(node.leftChild);
			externalNodes++;
		}
		if (node.rightChild != null) {
			externalNodes(node.rightChild);
			externalNodes++;
		}
		return externalNodes;
	}
	
	private static ArrayList<Integer> countPath2(Node node, Point point, ArrayList<Integer> path) {
		if (node == null) { //hit a region
			return path;
		}
		if (clockwiseTest(point, node) == 1) { //clockwise
			path.add(1);
			traverse(node.rightChild, point, path);
		} else if (clockwiseTest(point, node) == -1) { //clockwise
			path.add(-1);
			traverse(node.rightChild, point, path);
		}
		return path;
	}
	
	public static Line findSeparation(Point point1, Point point2) {
		Line line = new Line();
		line = findSeparation2(point1, point2);
		return line;
	}
	
	private static Line findSeparation2(Point point1, Point point2) {
		for (int i = 0; i < lines.size(); i++) {
			if (preIntersection(point1, point2, lines.get(i)) == true) {
				return lines.get(i);
			}
		}
		return null;
	}
	
	private static boolean preIntersection(Point point1, Point point2, Line line) {
		Line newLine = new Line(point1, point2);
		Node node = new Node(line);
		if (intersection(newLine, node) == true) {
			return true;
		}
		return false;
	}
	
	public static boolean testSameRegion(Point point1, Point point2) {
		ArrayList<Integer> path1 = new ArrayList<Integer>(); //point1
		ArrayList<Integer> path2 = new ArrayList<Integer>(); //point2
		path1 = traverse(tree.root, point1, path1);
		path2 = traverse(tree.root, point2, path2);
		if (path1.equals(path2)) {
			return true;
		} else {
			return false;
		}
	}
	
	private static ArrayList<Integer> traverse(Node node, Point point, ArrayList<Integer> path) {
		if (node == null) { //hit a region
			return path;
		}
		if (clockwiseTest(point, node) == 1) { //clockwise
			path.add(1);
			traverse(node.rightChild, point, path);
		} else if (clockwiseTest(point, node) == -1) { //clockwise
			path.add(-1);
			traverse(node.rightChild, point, path);
		}
		return path;
	}
	
	private static int clockwiseTest(Point point, Node node) {
		Point p1 = new Point();
		Point p2 = new Point();
		p1 = node.data.p1;
		p2 = node.data.p2;
		int result = ccwPoint(point, p1, p2);
		return result;
	}
	
	private static int ccwPoint(Point p0, Point p1, Point p2) {
		double dx1 = p1.x - p0.x;
		double dy1 = p1.y - p0.y;
		double dx2 = p2.x - p0.x;
		double dy2 = p2.y - p0.y;
		if (dx1 * dy2 > dy1 * dx2) return -1; //COUNTER
		else if (dx1 * dy2< dy1 * dx2) return 1; //CLOCKWISE
		else if ((dx1 * dx1 + dy1 * dy1) < (dx2 * dx2 + dy2 * dy2)) return 1; //CLOCKWISE
		else return 0; //COLINEAR
	}
	
	public static Point createPoint1(double[] doubles) {
		Point point1 = new Point();
		point1.x = doubles[0];
		point1.y = doubles[1];
		return point1;
	}
	
	public static Point createPoint2(double[] doubles) {
		Point point2 = new Point();
		point2.x = doubles[2];
		point2.y = doubles[3];
		return point2;
	}
	
	public static double[] splitPoints(String input) {
		double[] doubles = new double[4];
		Point point1 = new Point();
		Point point2 = new Point();
		String[] coordinates = input.split("\\s+");
		double point1x = Double.valueOf(coordinates[0]);
		double point1y = Double.valueOf(coordinates[1]);
		double point2x = Double.valueOf(coordinates[2]);
		double point2y = Double.valueOf(coordinates[3]);
		doubles[0] = point1x;
		doubles[1] = point1y;
		doubles[2] = point2x;
		doubles[3] = point2y;
		return doubles;
	}
	
	private static void addToTree(String input) {
		Node node = new Node();
		Line line = new Line();
		node = parseString(input); //TURN A LINE OF COORDINATES INTO A NODE
			line = briefHelper(node);
			test(line);
	}
	
	private static Line briefHelper(Node node) {
		Point p1 = new Point();
		Point p2 = new Point();
		p1 = node.data.p1;
		p2 = node.data.p2;
		Line line = new Line(p1, p2);
		return line;
	}
	
	private static Node parseString(String input) {
		Node node = new Node();
		String[] coordinates = input.split("\\s+");
		double startX = Double.valueOf(coordinates[0]);
		double startY = Double.valueOf(coordinates[1]);
		double endX = Double.valueOf(coordinates[2]);
		double endY = Double.valueOf(coordinates[3]);
		double x1, x2, y1, y2;
		x1 = startX * 500;
		x2 = startY * 500;
		y1 = endX * 500;
		y2 = endY * 500;
//		System.out.println("fda" + (int) x1);
//		Canvas.x1 = (int) x1;
//		System.out.println("fda" + Canvas.x1);
//		System.out.println("fda" + (int) x2);
//		Canvas.x1 = (int) x2;
//		System.out.println("fda" + Canvas.x2);
//		System.out.println("fda" + (int) y1);
//		Canvas.x1 = (int) y1;
//		System.out.println("fda" + Canvas.y1);
//		System.out.println("fda" + (int) y2);
//		Canvas.x1 = (int) y2;
//		System.out.println("fda" + Canvas.y2);
//		Canvas.linePoints((int) x1, (int) x2, (int) y1, (int) y2);
		node = turntoNode(startX, startY, endX, endY);
		return node;
	}
	
	private static Node turntoNode(double startX, double startY, double endX, double endY) {
		Point start = new Point(startX, startY);
		Point end = new Point(endX, endY);
		Line line = new Line(start, end);
		Node newNode = new Node();
		newNode.data = line;
		return newNode;
	}
	
	public static void test(Line line) {
		lines.add(line);
		firstTest(line, tree.root);
	}
	
	private static Node firstTest(Line line, Node node) {
		if (tree.root == null) {
			tree.root = tree.createRoot(line.p1.x, line.p1.y, line.p2.x, line.p2.y);
			return node;
		}
		if (node == null) { //basis case: if you reach a leaf
			Node newNode = new Node(line);
			nodes.add(newNode);
			node = newNode;
		} else {
			if (intersection(line, node) == true) { //if there is an intersection
				node.rightChild = firstTest(line, node.rightChild);
				node.leftChild = firstTest(line, node.leftChild);
			} else { //no intersection
				if (clockwiseTest(line, node) == 1) {
					node.rightChild = firstTest(line, node.rightChild);
				} else if (clockwiseTest(line, node) == -1) {
					node.leftChild = firstTest(line, node.leftChild);
				}
			}
		}
		return node;
	}
	
	public static boolean intersection(Line line, Node node) {
		Point p1 = new Point(line.p1.x, line.p1.y);
		Point q1 = new Point(line.p2.x, line.p2.y);
		Point p2 = new Point(node.data.p1.x, node.data.p1.y);
		Point q2 = new Point(node.data.p2.x, node.data.p2.y);
		int o1 = orientation(p1, q1, p2); 
	    int o2 = orientation(p1, q1, q2); 
	    int o3 = orientation(p2, q2, p1); 
	    int o4 = orientation(p2, q2, q1);

	    if (o1 != o2 && o3 != o4)
	        return true;
	  
	    if (o1 == 0 && onLine(p1, p2, q1)) return true;
	    if (o2 == 0 && onLine(p1, q2, q1)) return true; 
	    if (o3 == 0 && onLine(p2, p1, q2)) return true; 
	 	if (o4 == 0 && onLine(p2, q1, q2)) return true; 
	    return false;
	}
	
	private static int orientation(Point p, Point q, Point r) { 
	    double outcome = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
	    if (outcome == 0) return 0; // colinear 
	    if (outcome > 0) return 1; //clockwise
	    if (outcome < 0) return 2; //counterclockwise
		
	    return -1;
	}
	
	private static boolean onLine(Point p, Point q, Point r) {
		if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) && 
			q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
			return true;
		return false;	
	}
	
	public static int clockwiseTest(Line line, Node node) {
		Point p0 = new Point();
		p0 = line.p1;
		Point p1 = new Point();
		p1 = node.data.p1;
		Point p2 = new Point();
		p2 = node.data.p2;
		int result = ccw(p0, p1, p2);
		return result;
	}
	
	private static int ccw(Point p0, Point p1, Point p2) {
		double dx1 = p1.x - p0.x;
		double dy1 = p1.y - p0.y;
		double dx2 = p2.x - p0.x;
		double dy2 = p2.y - p0.y;
		if (dx1 * dy2 > dy1 * dx2) return -1; //COUNTER
		else if (dx1 * dy2< dy1 * dx2) return 1; //CLOCKWISE
		else if ((dx1 * dx1 + dy1 * dy1) < (dx2 * dx2 + dy2 * dy2)) return 1; //CLOCKWISE
		else return 0; //COLINEAR
	}
}
