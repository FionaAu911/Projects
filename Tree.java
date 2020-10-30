//Fiona Au
//
//Email fau@u.rochester.edu
//NetID fau
//CSC 172 Lab T/R 2:00-3:15
//Project #2

package Project2;

public class Tree {
	
	public Node root;
	
	Tree(Node root){
		this.root = root;
	}
	
	Tree(){
	
	}
	
	static void printLevelOrder(Node root) { 
	    int h = height(root); 
	    int i; 
	    for (i=1; i<=h; i++) 
	    { 
	        printGivenLevel(root, i); 
	        System.out.println(); 
	    } 
	}
	
	static void printGivenLevel(Node root, int level) { 
	    if (root == null) 
	        return; 
	    if (level == 1) 
	        System.out.println(root.data); 
	    else if (level > 1) 
	    { 
	        printGivenLevel(root.leftChild, level-1); 
	        printGivenLevel(root.rightChild, level-1); 
	    } 
	}
	
	static int height(Node node) { 
        if (node == null) 
            return 0; 
        else 
        { 
            /* compute the depth of each subtree */
            int lDepth = height(node.leftChild); 
            int rDepth = height(node.rightChild); 
   
            /* use the larger one */
            if (lDepth > rDepth) 
                return (lDepth + 1); 
             else 
                return (rDepth + 1); 
        } 
    } 

	public Node createRoot(Double startX, Double startY, Double endX, Double endY) {
		Point point1 = new Point(startX, startY);
		Point point2 = new Point(endX, endY);
		Line newLine = new Line(point1, point2);
		Node root = new Node(newLine, null, null);
		return root;
	}
}
