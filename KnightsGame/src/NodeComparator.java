import java.util.Comparator;

public class NodeComparator implements Comparator<Node2> {

	@Override
	public int compare(Node2 node1, Node2 otherNode) {
		// TODO Auto-generated method stub
		return Integer.compare(node1.hCost, otherNode.hCost);	
//		int i = Integer.compare(node1.hCost, otherNode.hCost);
//		if(i==0)
//			return 1;
//		else
//			return i;
	}
	
}
