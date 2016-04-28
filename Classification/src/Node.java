import java.util.ArrayList;

public class Node {
	boolean isRoot = false;
	Node parentNode;
	int parentFeature= 0;
	boolean parentRoot = false;
	
	int AttributeNumber =0;
	String AttributeName = "";
	ArrayList<Integer>chosenFeature = new ArrayList<Integer>();
	ArrayList<Node> childNode = new ArrayList<Node>();
	
	boolean isLeaf = false;
	int classType = 0;
	
}
