import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.view.mxGraph;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;


public class DecisionTreeGraphView extends JFrame {
	/**
	 * 
	 */

//	private static final long serialVersionUID = -2707712944901661771L;
//	Object parent;
//	public DecisionTreeGraphView(Node rootNode)
//	{
//		
//
//		mxGraph graph = new mxGraph();
//		parent = graph.getDefaultParent();
//
//		graph.getModel().beginUpdate();
//		try
//		{
//			String temp = rootNode.AttributeNumber+"";
//			Object v1 = graph.insertVertex(parent, null, temp, 20, 20, 80,30);
//			Object v2 = graph.insertVertex(parent, null, "World!", 240, 150,
//					80, 30);
//			graph.insertEdge(parent, null, "Edge", v1, v2);
//		}
//		finally
//		{
//			graph.getModel().endUpdate();
//		}
//
//		mxGraphComponent graphComponent = new mxGraphComponent(graph);
//		getContentPane().add(graphComponent);
//	}
//	
	
	private static final long serialVersionUID = -2707712944901661771L;
	Object parent;
	public DecisionTreeGraphView(Node rootNode)
	{
		super("Hello, World!");

		mxGraph graph = new mxGraph();
		parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
			//Parent
			Object rootV = graph.insertVertex(parent, null, rootNode.AttributeName, 20, 20, 80,30);
			insertGraph(rootNode, rootV, graph);
//			for(int i =0;i<rootNode.childNode.size();i++){
//	    		if(rootNode.childNode.get(i).isLeaf){
//	        		String temp = "Class type = " +rootNode.classType;
//	        		Object childVertex = graph.insertVertex(parent, null, temp, 50, 50, 200, 100);
//
////	        		((mxCell)childVertex).setValue(temp);
//	                ((mxCell)childVertex).setStyle("fillColor=lightgreen;"
//	                        + "shape=ellipse");
//	    			graph.insertEdge(parent, null, "chosen feature class " + rootNode.chosenFeature.get(i), rootV, childVertex);
//	        	}else{
//		    		Object childVertex = graph.insertVertex(parent, null, "AttributeID : " + rootNode.childNode.get(i).AttributeNumber, 50, 50, 200, 100);
//					graph.insertEdge(parent, null, "chosen feature class " + rootNode.chosenFeature.get(i), rootV, childVertex);
////					insertGraph(rootNode.childNode.get(i), childVertex,graph);
//		    	}
//	    	}
		}
		finally
		{
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent,BorderLayout.CENTER);
		layoutGraph(graph);
		
//		saves image into jpg file
//		BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 1, Color.WHITE, true, null);
//		File outputfile = new File("imageTest.jpg");
//		try {
//			ImageIO.write(image, "jpg", outputfile);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
    private void insertGraph(Node currNode, Object nodeVertex, mxGraph graph ){
    	
    	for(int i =0;i<currNode.childNode.size();i++){
    		if(currNode.childNode.get(i).isLeaf){
    			System.out.println("currNode.classType " + currNode.childNode.get(i).classType);
    			String temp = "Class type = " +currNode.childNode.get(i).classType;
        		Object childVertex = graph.insertVertex(parent, null, temp, 50, 50, 200, 100);
//        		((mxCell)nodeVertex).setValue(temp);
                ((mxCell)childVertex).setStyle("fillColor=lightgreen;"
                        + "shape=ellipse");
                graph.insertEdge(parent, null, "chosen feature class " + currNode.chosenFeature.get(i), nodeVertex, childVertex);
        	}else{
	    		Object childVertex2 = graph.insertVertex(parent, null, "AttributeID : " + currNode.childNode.get(i).AttributeName, 50, 50, 200, 100);
				graph.insertEdge(parent, null, "chosen feature class " + currNode.chosenFeature.get(i), nodeVertex, childVertex2);				
				insertGraph(currNode.childNode.get(i), childVertex2,graph);
	    	}
    	}
    	
    }
    public void layoutGraph(mxGraph graph) {
        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
        layout.setOrientation(SwingConstants.NORTH);
        layout.execute(graph.getDefaultParent());
    }
  
}