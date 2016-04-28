import java.util.ArrayList;

import javax.swing.JFrame;

public class AccuracyTest {
	static void DecisionTree(String fileName,String setup,int range, ArrayList<Data> dataList){
		Double totalAccuracy = 0.0d;int roundNumber = 10;
		boolean isGetRNode = false;
		Node rNode = new Node();

		if(setup.equals("10Fold")){
			for(int k = 0; k<roundNumber; k++){
				ArrayList<Data> tempDataList = new ArrayList<Data>(range);
				Data allTestData = new Data();	//test data
	//			ArrayList<Data> tempDataList = new ArrayList<Data>(range);
	//			Data allTestData = new Data();
	//				System.out.println("dataList.size(): " + dataList.size());
				//loops each class and store it in
				for(int i =0;i<dataList.size();i++){
					Data temp = new Data();
					temp = tenFold(dataList.get(i),k);
					tempDataList.add(temp);
	//					System.out.println("allTestData.testRow.size(): " + allTestData.testRow.size());
	//					System.out.println("temp.testRow.size(): " + temp.testRow.size());
					for(int jj=0;jj<temp.testRow.size();jj++){
						allTestData.testRow.put(allTestData.testRow.size(),temp.testRow.get(jj));
						allTestData.testOther.put(allTestData.testOther.size(),temp.testOther.get(jj));
					}
				}
				
				//put all class into one Data
				Data totalData = new Data();	//trainning data
				totalData.classType = range;
				totalData.fileName = dataList.get(0).fileName;
				totalData.dDimension = tempDataList.get(0).row.get(0).getRowDimension();
				for(int i =0;i<range;i++){ 
					for(int j =0;j<dataList.get(i).row.size();j++){
						totalData.row.put(totalData.row.size(), dataList.get(i).row.get(j));
						totalData.other.put(totalData.other.size(), dataList.get(i).other.get(j));
					}
				}
	//			for(int j =0;j<totalData.other.size();j++)
	//				System.out.println("totalData.other.get(j) "+ totalData.other.get(j));
					
				System.out.println("totalData.size() " + totalData.row.size());
				DecisionTree2 aTree2 = new DecisionTree2();
				double classEntropy = 0;
				classEntropy = aTree2.calcEntropyClassType(totalData);
				System.out.println("classEntropy " + classEntropy);
				ArrayList<AttributeInfo> featureGainList = new ArrayList<AttributeInfo>();
				
				for(int i =0; i<totalData.dDimension;i++){
					AttributeInfo attributeValue = new AttributeInfo(); 
					attributeValue = aTree2.informationGain(totalData, i);
					featureGainList.add(attributeValue);
	//				System.out.println("i "+ i + " attributeValue " + attributeValue );
				}
				
				featureGainList = aTree2.sortGainList(featureGainList);
	//			for(int kkk=0;kkk<featureGainList.size();kkk++){
	//				System.out.println("fAttributeID" + featureGainList.get(kkk).AttributeID);
	//				System.out.println("fgainValue " + featureGainList.get(kkk).gainValue);
	//			}
				Node rootNode = new Node();
				rootNode.isRoot = true;
	//			aTree2.dataList = totalData.copyData(totalData);
				Node root = aTree2.decisionTree(featureGainList, rootNode,totalData.copyData(totalData));
				
				if(!isGetRNode){
					rNode = root;
					isGetRNode = true;
					
					//Display graph
					DecisionTreeGraphView frame = new DecisionTreeGraphView(rNode);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(400, 320);
					frame.setVisible(true);
				}
				int correct = 0; int total = 0;
				for(int i = 0;i<allTestData.testRow.size();i++){
					int testClass = aTree2.testDecisionTree(root, allTestData.testRow.get(i),fileName);
	//				System.out.println(" FOUND CLASS " + testClass);
					String s = allTestData.testOther.get(i).get(0); s = s.replace("[","");s = s.replace("]","");
	//				System.out.println(" ACTUAL CLASS " + s);
	
					int actualClass = Integer.parseInt(s);
					if(testClass == actualClass){
						correct = correct +1;
					}
					total = total+1;
				}
				
				System.out.println("Correct = " + correct);
				System.out.println("total = " + total);
				Double oneRoundAccuracy = Double.parseDouble(correct+"")/Double.parseDouble(total+"");
				totalAccuracy = totalAccuracy + oneRoundAccuracy;
			}
	
			totalAccuracy = totalAccuracy/Double.parseDouble(roundNumber+"");
			System.out.println("totalAccuracy : " + totalAccuracy );
			
			
			
			
		}if(setup.equals("LeaveOneOut")){
			int totalTest = 0;
			for(int i = 0;i <dataList.size();i++){
				totalTest = dataList.get(i).row.size();
			}
			totalAccuracy = 0.0d;
			roundNumber = totalTest;
			for(int k = 0;k<roundNumber;k++){
	//		int k = 0;
				ArrayList<Data> tempDataList = new ArrayList<Data>(range);
				Data allTestData = new Data();	//test data
				
				Data totalData = new Data();	//trainning data
				totalData.classType = range;
				totalData.fileName = dataList.get(0).fileName;
				totalData.dDimension = dataList.get(0).row.get(0).getRowDimension();
				for(int i =0;i<range;i++){ 
					for(int j =0;j<dataList.get(i).row.size();j++){
						if(k>dataList.get(i).row.size()){
							if((k-(range*dataList.get(i).row.size()))==j){
								allTestData.testRow.put(allTestData.testRow.size(), dataList.get(i).row.get(j));
								allTestData.testOther.put(allTestData.testOther.size(), dataList.get(i).other.get(j));
							}else{
								totalData.row.put(totalData.row.size(), dataList.get(i).row.get(j));
								totalData.other.put(totalData.other.size(), dataList.get(i).other.get(j));
							}
						}else{
							if(k==j){
								allTestData.testRow.put(allTestData.testRow.size(), dataList.get(i).row.get(j));
								allTestData.testOther.put(allTestData.testOther.size(), dataList.get(i).other.get(j));
							}else{
								totalData.row.put(totalData.row.size(), dataList.get(i).row.get(j));
								totalData.other.put(totalData.other.size(), dataList.get(i).other.get(j));
							}
						}
	
					}
				}
				
				System.out.println("totalData.size() " + totalData.row.size());
				DecisionTree2 aTree2 = new DecisionTree2();
				double classEntropy = 0;
				classEntropy = aTree2.calcEntropyClassType(totalData);
				System.out.println("classEntropy " + classEntropy);
				ArrayList<AttributeInfo> featureGainList = new ArrayList<AttributeInfo>();
				
				for(int i =0; i<totalData.dDimension;i++){
					AttributeInfo attributeValue = new AttributeInfo(); 
					attributeValue = aTree2.informationGain(totalData, i);
					featureGainList.add(attributeValue);
	//				System.out.println("i "+ i + " attributeValue " + attributeValue );
				}
				
				featureGainList = aTree2.sortGainList(featureGainList);
				
				Node rootNode = new Node();
				rootNode.isRoot = true;
	//			aTree2.dataList = totalData.copyData(totalData);
				Node root = aTree2.decisionTree(featureGainList, rootNode,totalData.copyData(totalData));
				if(!isGetRNode){
					rNode = root;
					//Display graph
					DecisionTreeGraphView frame = new DecisionTreeGraphView(rNode);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(400, 320);
					frame.setVisible(true);
					isGetRNode = true;
				}
				
				int correct = 0; int total = 0;
				for(int i = 0;i<allTestData.testRow.size();i++){
					int testClass = aTree2.testDecisionTree(root, allTestData.testRow.get(i),fileName);
	//				System.out.println(" FOUND CLASS " + testClass);
					String s = allTestData.testOther.get(i).get(0); s = s.replace("[","");s = s.replace("]","");
	//				System.out.println(" ACTUAL CLASS " + s);
	
					int actualClass = Integer.parseInt(s);
					if(testClass == actualClass){
						correct = correct +1;
					}
					total = total+1;
				}
				
				System.out.println("Correct = " + correct);
				System.out.println("total = " + total);
				Double oneRoundAccuracy = Double.parseDouble(correct+"")/Double.parseDouble(total+"");
				totalAccuracy = totalAccuracy + oneRoundAccuracy;
			}
			totalAccuracy = totalAccuracy/Double.parseDouble(roundNumber+"");
			System.out.println("totalAccuracy : " + totalAccuracy );
			
		}
		
		
		
		System.out.println("done");
	}
	
	
	public static Data tenFold(Data aData, int round){
		//taking row data and placing 1/10 of it into testing
		int dataSize = aData.row.size();
//		System.out.println("dataSize*0.1 " + (dataSize*0.1));
		int otherRowSize = aData.other.size();
		Data tenFoldData = new Data();
		tenFoldData.classType = aData.classType;
		//go through row data and take 1/10 out
		int j = 0;
		for(int i = 0;i<aData.row.size();i++){
			if(i>=(round*(dataSize*0.1))&&i<((round*(dataSize*0.1))+(dataSize*0.1))){
				//these are the data you want to use
				tenFoldData.testRow.put(j, aData.row.get(i));
				tenFoldData.testOther.put(j, aData.other.get(i));
				j++;
			}else{
				tenFoldData.row.put(tenFoldData.row.size(), aData.row.get(i));
				tenFoldData.other.put(tenFoldData.other.size(), aData.other.get(i));
			}
		}
		
//		System.out.println("tenFoldData.testRow.size(): " + tenFoldData.testRow.size());
		return tenFoldData;
	}
}
