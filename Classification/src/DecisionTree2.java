import java.util.ArrayList;
import java.util.HashMap;

public class DecisionTree2 {
	public String getAttributeName(int AttributeID, String fileName){
		
			if(fileName.contains("heartDisease")){
				if(AttributeID==0)
					return "Age";
				else if(AttributeID ==1)
					return "Gender";
				else if(AttributeID ==2)
					return "Cp";
				else if(AttributeID ==3)
					return "Trestbps";
				else if(AttributeID ==4)
					return "Chol";
				else if(AttributeID ==5)
					return "Fbs";
				else if(AttributeID ==6)
					return "Restecg";					
				else if(AttributeID ==7)
					return "Thalach";					
				else if(AttributeID ==8)
					return "Exang";
				else if(AttributeID ==9)
					return "Oldpeak";
				else if(AttributeID ==10)
					return "Dlope";
				else if(AttributeID ==11)
					return "Ca";
				else if(AttributeID ==12)
					return "Thal";
				
			}
			
			return null;
		}

	
//	Data dataList = new Data();
	//return out the class type based on the attribute's feature types
	public int testDecisionTree(Node aNode, Jama.Matrix inputData,String fileName){
		if(aNode.isLeaf){
			return aNode.classType;
		}else{
			Node temp = new Node();
			int colNum = aNode.AttributeNumber;
//			System.out.println("AttributeNumber "+ colNum);
//			if(colNum ==0){
//				System.out.println("AttributeNumber "+ colNum);
//			}
			int featureClass = 0;
			for(int i=0;i<inputData.getRowDimension();i++){
				if(i==colNum){
					if(fileName.contains("wine")){
						featureClass =checkWineFeatureClassifer(inputData.get(i,0),colNum);
						break;
					}else if(fileName.contains("iris")){
						featureClass =checkIRISFeatureClassifer(inputData.get(i,0),colNum);
						break;
					}else if(fileName.contains("heartDisease")){
						featureClass =checkHeartDiseaseFeatureClassifer(inputData.get(i,0),colNum);
						break;
					}
				}
			}
//			System.out.println("featureClass " + featureClass);
//			if(colNum ==0){
//				System.out.println("aNode.chosenFeature.size() " + aNode.chosenFeature.size());
//				System.out.println("aNode.chosenFeature.get(0) " + aNode.chosenFeature.get(0));
//			}
			for(int i =0;i<aNode.chosenFeature.size();i++){
//				System.out.println("aNode.chosenFeature.get(i) " + aNode.chosenFeature.get(i) );				
				if(featureClass==aNode.chosenFeature.get(i)){
					return testDecisionTree(aNode.childNode.get(i),inputData,fileName);
				}
			}
		}
		return -1;
	}
	
	public Node decisionTree(ArrayList<AttributeInfo>AttributeList,Node parentNode,Data dataList){
		AttributeInfo current = new AttributeInfo();
		current = current.copyAttributeInfo(AttributeList.get(0));
		AttributeList.remove(0);
		
		Node currNode = new Node();
		if(parentNode.isRoot&&parentNode.parentRoot==false)
			currNode = parentNode;
		
		currNode.AttributeNumber = current.AttributeID;	
		currNode.AttributeName = getAttributeName(current.AttributeID, dataList.fileName);
		//generate subset
		HashMap<Integer,Data> featureClass = new HashMap<Integer, Data>();
		for(int k = 0;k<dataList.row.size();k++){
			int featureType = 0;
			if(dataList.fileName.contains("wine")){
				featureType = checkWineFeatureClassifer(dataList.row.get(k).get(current.AttributeID, 0),current.AttributeID);
			}else if(dataList.fileName.contains("iris")){
				featureType = checkIRISFeatureClassifer(dataList.row.get(k).get(current.AttributeID, 0),current.AttributeID);
			}else if(dataList.fileName.contains("heartDisease")){
				featureType = checkHeartDiseaseFeatureClassifer(dataList.row.get(k).get(current.AttributeID, 0),current.AttributeID);
			}
			Data temp = new Data();
			if(featureClass.containsKey(featureType)){
				temp = temp.copyData(featureClass.get(featureType));
				temp.row.put(temp.row.size(), dataList.row.get(k));
				temp.other.put(temp.other.size(), dataList.other.get(k));
				
				featureClass.put(featureType, temp);
			}else{
				temp.classType = dataList.classType;
				temp.fileName = dataList.fileName;
				temp.row.put(temp.row.size(), dataList.row.get(k));
				temp.other.put(temp.other.size(), dataList.other.get(k));
				featureClass.put(featureType, temp);
			}
		
		}
//		for(int i = 0;i<featureClass.get(0).other.size();i++){
//			System.out.println("featureClass.get(0).other " + featureClass.get(0).other.get(i));
//		}
		if(AttributeList.size()>1){
			for(int i =0;i<featureClass.size();i++){
				//calc each feature class entropy
				if(featureClass.containsKey(i)){
					double classEntropy = 0;
					classEntropy = calcEntropyFeature(featureClass.get(i));
					if(classEntropy == 0){
						Node leafNode = new Node();
						leafNode.parentFeature = i;
						leafNode.AttributeNumber=currNode.AttributeNumber;
						leafNode.isLeaf = true;
						leafNode.classType = Integer.parseInt(featureClass.get(i).other.get(0).get(0));
						
						currNode.chosenFeature.add(leafNode.parentFeature);
						currNode.childNode.add(leafNode);
					}else {
						ArrayList<AttributeInfo> newFeatureGainList = new ArrayList<AttributeInfo>();
						for(int j =0;j<AttributeList.size();j++){	
							newFeatureGainList.add(informationGain(featureClass.get(i),AttributeList.get(j).AttributeID));
						}
						newFeatureGainList = sortGainList(newFeatureGainList);
						if(currNode.isRoot)
							currNode.parentRoot = true;
						
						currNode.chosenFeature.add(i);
						currNode.childNode.add(decisionTree(newFeatureGainList, currNode,featureClass.get(i)));
					}
				}
			}
			return currNode;
		}else{
			for(int i =0;i<featureClass.size();i++){
				//calc each feature class entropy
				if(featureClass.containsKey(i)){
					double classEntropy = 0;
					classEntropy = calcEntropyFeature(featureClass.get(i));
					if(classEntropy == 0){
						Node leafNode = new Node();
						leafNode.parentFeature = i;
						leafNode.AttributeNumber=currNode.AttributeNumber;
						leafNode.isLeaf = true;
						leafNode.classType = Integer.parseInt(featureClass.get(i).other.get(0).get(0));
						
						currNode.chosenFeature.add(leafNode.parentFeature);
						currNode.childNode.add(leafNode);
					}else {
						HashMap<Integer,Integer> counter = new HashMap<Integer,Integer>();
						for(int j = 0;j<featureClass.get(i).other.size();j++){
							String temp = featureClass.get(i).other.get(j)+"";
							temp = temp.replace("[", "");temp = temp.replace("]", "");
							
							int key = Integer.parseInt(temp);
							int localCounter =0;
							if(counter.containsKey(key))
								localCounter = counter.get(key);
								
							localCounter = localCounter + 1;
							counter.put(key, localCounter);		
						}
						
						int key = 0;int biggest = 0;
						for(int j =0;j<featureClass.get(i).other.size();j++){
							if(counter.containsKey(j)){
								if(biggest < counter.get(j)){
									biggest = counter.get(j);
									key = j;
								}
							}
						}
						Node leafNode = new Node();
						leafNode.parentFeature = i;
						leafNode.AttributeNumber=currNode.AttributeNumber;
						leafNode.isLeaf = true;
						leafNode.classType = key;
						
						currNode.chosenFeature.add(leafNode.parentFeature);
						currNode.childNode.add(leafNode);
					}
				}
			}
			return currNode;
		}
	}
	
	/******************************************************************************************************************/
	/******************************************************************************************************************/	
	//										InfoGain For List														  //
	/******************************************************************************************************************/
	/******************************************************************************************************************/
	
	
	public AttributeInfo informationGain(Data dataSet,int colNum){
		AttributeInfo attributeLocal = new AttributeInfo(colNum);
		double gainValue = 0;
		double setEntropy = calcEntropyClassType(dataSet);
		attributeLocal = AttributeValueSum(dataSet, colNum);
		gainValue = setEntropy + attributeLocal.gainValue;
		attributeLocal.gainValue = gainValue;
		return attributeLocal;
	}
	
	//gets the total value from specific Attribute base on colNum
	public AttributeInfo AttributeValueSum(Data dataSet,int colNum){
		HashMap<Integer,Data> dataByAttribute= new HashMap<Integer,Data>();
		AttributeInfo attributeLocal = new AttributeInfo(colNum);
		double featureSum = 0;
		
		if(dataSet.fileName.contains("heartDisease")){
			dataByAttribute = attributeClassify(dataSet,colNum);
		}else if(dataSet.fileName.contains("iris")){
			dataByAttribute = attributeClassify(dataSet,colNum);
		}else if(dataSet.fileName.contains("wine")){
			//contains all feature class within that one attribute
			//0 = class 1 in Sweak
			dataByAttribute = attributeClassify(dataSet,colNum);
		}
		
		
		for(int i = 0; i<dataByAttribute.size();i++){
			if(dataByAttribute.containsKey(i)){
				if(dataByAttribute.get(i).row.size()>0){
					double tempFeatureEntropy = calcEntropyFeature(dataByAttribute.get(i));
					double sizeDivide = Double.parseDouble(dataByAttribute.get(i).row.size()+"")/Double.parseDouble(dataSet.row.size()+"");
					featureSum = featureSum - sizeDivide*tempFeatureEntropy;
					attributeLocal.setFeatureEntropySet(i,tempFeatureEntropy);
				}
			}
		}
		attributeLocal.featureDataSet = dataByAttribute;
		attributeLocal.gainValue= featureSum;
		return attributeLocal;
	}

	public double calcEntropyFeature(Data dataList){
		HashMap<Integer,Double> probability = new HashMap<Integer,Double>();
		double total = 0;

		probability = calcProbFeature(dataList);

		for(int i =0;i<dataList.classType ;i++)
			if(probability.get(i) != null)
				total= total - Double.parseDouble(probability.get(i)+"")*(Math.log(Double.parseDouble(probability.get(i)+""))/Math.log(2));
		
		return total;
	}
	
	public HashMap<Integer,Double> calcProbFeature(Data dataList){
		HashMap<Integer,Integer> countClass = new HashMap<Integer,Integer>();
		HashMap<Integer,Double> probability = new HashMap<Integer,Double>();
		
		for(int i =0;i<dataList.row.size() ;i++){
			for(int j =0;j<=dataList.classType;j++){
				int tempTotal= 0;
				
				if(dataList.other.get(i).contains(j+"")){
					if(countClass.containsKey(j)){
						tempTotal = countClass.get(j);
					}
					tempTotal = tempTotal +1;
					countClass.put(j, tempTotal);
				}
				
			}
		}
		int total = 0;
		
		for(int j =0;j<=dataList.classType;j++){
			if(countClass.containsKey(j))
				total = total + countClass.get(j);
		}
		
		for(int i =0;i<=dataList.classType ;i++){
			if(countClass.containsKey(i)){
				double tempProb = Double.parseDouble(countClass.get(i)+"")/Double.parseDouble(total+"");
				probability.put(i, tempProb);
			}
		}
		return probability;
	}
	
	public double calcEntropyClassType(Data dataList){
		HashMap<Integer,Double> probability = new HashMap<Integer,Double>();
		double total = 0;

		probability = calcProbClassType(dataList);
		
		for(int i =0;i<dataList.classType ;i++)
			if(probability.get(i) != null)
				total= total - Double.parseDouble(probability.get(i)+"")*(Math.log(Double.parseDouble(probability.get(i)+""))/Math.log(2));			
		
		return total;
	}
	
	public HashMap<Integer,Double> calcProbClassType(Data dataList){
		HashMap<Integer,Integer> countClass = new HashMap<Integer,Integer>();
		HashMap<Integer,Double> probability = new HashMap<Integer,Double>();
		
		for(int i =0;i<dataList.row.size() ;i++){
			for(int j =0;j<=dataList.classType;j++){
				int tempTotal= 0;
				
				if(dataList.other.get(i).contains(j+"")){
					if(countClass.containsKey(j)){
						tempTotal = countClass.get(j);
					}
					tempTotal = tempTotal +1;
					countClass.put(j, tempTotal);
				}
			}
		}
		int total = 0;
		
		for(int j =0;j<=countClass.size();j++){
			if(countClass.containsKey(j))
				total = total + countClass.get(j);
		}
		
		for(int i =0;i<=countClass.size();i++){
			if(countClass.containsKey(i)){
				double tempProb = Double.parseDouble(countClass.get(i)+"")/Double.parseDouble(total+"");
				probability.put(i, tempProb);
			}
		}
		return probability;
	}
	
	public static ArrayList<AttributeInfo> sortGainList(ArrayList<AttributeInfo> list){
		ArrayList<AttributeInfo>  inputList = new ArrayList<AttributeInfo>();
		ArrayList<AttributeInfo>  sList = new ArrayList<AttributeInfo>();
		for(int i = 0;i<list.size();i++){
			AttributeInfo temp = new AttributeInfo();
			temp = temp.copyAttributeInfo(list.get(i));
			inputList.add(temp);
			
		}
		if(inputList.size()>1){
			for(int i= 0;i<inputList.size();i++){
				if(i<(inputList.size()-1)){
					for(int j = (i+1);j<inputList.size();j++){
						if(inputList.get(i).gainValue<inputList.get(j).gainValue){
							AttributeInfo temp = new AttributeInfo();
							temp = temp.copyAttributeInfo(inputList.get(i));
							inputList.set(i, inputList.get(j));
							inputList.set((j), temp);
						}
					}
				}
			}
		}
		return inputList;
	}
	
	public HashMap<Integer,Data> attributeClassify(Data dataList,int colNum){
		HashMap<Integer,Data> dataBaseOnAttribute = new HashMap<Integer,Data>();
		
		for(int i = 0;i <dataList.row.size();i++){
			double currentValue = 0;
			Data tempData = new Data();
			tempData.classType = dataList.classType;
			tempData.fileName = dataList.fileName;
			currentValue = dataList.row.get(i).get(colNum, 0);
			
			int fClass = 0;
			if(dataList.fileName.contains("wine")){
				fClass = checkWineFeatureClassifer(currentValue,colNum);
				if(dataBaseOnAttribute.isEmpty()){
					for(int ii=0;ii<2;ii++){
						tempData = new Data();tempData.setup(dataList.fileName, dataList.classType);
						dataBaseOnAttribute.put(ii, tempData);		//initialize
						
					}
				}
			}else if(dataList.fileName.contains("iris")){
				fClass = checkIRISFeatureClassifer(currentValue,colNum);
				if(dataBaseOnAttribute.isEmpty()){
					for(int ii=0;ii<2;ii++){
						tempData = new Data();tempData.setup(dataList.fileName, dataList.classType);
						dataBaseOnAttribute.put(ii, tempData);		//initialize
						
					}
				}
			}else if(dataList.fileName.contains("heartDisease")){
				fClass = checkHeartDiseaseFeatureClassifer(currentValue,colNum);
				if(dataBaseOnAttribute.isEmpty()){
					for(int ii=0;ii<3;ii++){
						tempData = new Data();tempData.setup(dataList.fileName, dataList.classType);
						dataBaseOnAttribute.put(ii, tempData);		//initialize
						
					}
				}
			}
			if(dataBaseOnAttribute.containsKey(fClass))
				tempData = dataBaseOnAttribute.get(fClass);
			
			tempData.row.put(tempData.row.size(),dataList.row.get(i));
			tempData.other.put(tempData.other.size(),dataList.other.get(i));
			
			dataBaseOnAttribute.put(fClass, tempData);
		}
//		for(int j =0;j<dataBaseOnAttribute.size();j++){
//			for(int i = 0;i <dataBaseOnAttribute.get(j).row.size();i++){
//				System.out.println("checking feature" + dataBaseOnAttribute.get(j).row.get(i).get(colNum, 0));
//			}
//		}
		return dataBaseOnAttribute;
	}
	
	/******************************************************************************************************************/
	/******************************************************************************************************************/	
	//												heartDisease															  //
	/******************************************************************************************************************/
	/******************************************************************************************************************/
	public int checkHeartDiseaseFeatureClassifer(double currentValue, int colNum){
		if(colNum == 0){
//			if(currentValue<=45){	return 0; }else if(currentValue>45){ return 1;}
			if(currentValue<=41){
				return 0;
			}else if(currentValue<=53){
				return 1;
			}else if(currentValue>53){
				return 2;
			}
		}else if(colNum == 1){
			if(currentValue==0){
				return 0;
			}else if(currentValue==1){
				return 1;
			}
			
		}else if(colNum == 2){
			if(currentValue<=2){
				return 0;
			}else if(currentValue>2){
				return 1;
			}
		}else if(colNum == 3){
//			if(currentValue<=150){	return 0; }else if(currentValue>150){ return 1;}
			if(currentValue<=120.5){
				return 0;
			}else if(currentValue<=147){
				return 1;
			}else if(currentValue>147){
				return 2;
			}
		}else if(colNum == 4){
//			if(currentValue<=250){	return 0; }else if(currentValue>250){ return 1;}
			if(currentValue<=235.5){
				return 0;
			}else if(currentValue<=345){
				return 1;
			}else if(currentValue>345){
				return 2;
			}
		}else if(colNum == 5){
			if(currentValue==0){
				return 0;
			}else if(currentValue==1){
				return 1;
			}
		}else if(colNum == 6){
			if(currentValue==0){
				return 0;
			}else if(currentValue==2){
				return 1;
			}
		}else if(colNum == 7){
//			if(currentValue<=150){	return 0; }else if(currentValue>150){ return 1;}
			if(currentValue<=103.75){
				return 0;
			}else if(currentValue<=136.5){
				return 1;
			}else if(currentValue>136.5){
				return 2;
			}
		}else if(colNum == 8){
			if(currentValue==0){
				return 0;
			}else if(currentValue==1){
				return 1;
			}
		}else if(colNum == 9){
//			if(currentValue<=2.5){	return 0; }else if(currentValue>2.5){ return 1;}

			if(currentValue<=1.55){
				return 0;
			}else if(currentValue<=3.1){
				return 1;
			}else if(currentValue>3.1){
				return 2;
			}
		}else if(colNum == 10){
//			if(currentValue<=2.5){	return 0; }else if(currentValue>2.5){ return 1;}
			if(currentValue==1){
				return 0;
			}else if(currentValue==2){
				return 1;
			}else if(currentValue==3){
				return 2;
			}
		}else if(colNum == 11){
//			if(currentValue<=2.5){	return 0; }else if(currentValue>2.5){ return 1;}

			if(currentValue==0){
				return 0;
			}else if(currentValue==1){
				return 1;
			}else if(currentValue>=2){
				return 2;
			}
		}else if(colNum == 12){
			if(currentValue==3){
				return 0;
			}else if(currentValue==6){
				return 1;
			}
			else if(currentValue==7){
				return 2;
			}
		}
		return -1;
	}
	
	/******************************************************************************************************************/
	/******************************************************************************************************************/	
	//												IRIS															  //
	/******************************************************************************************************************/
	/******************************************************************************************************************/
	public int checkIRISFeatureClassifer(double currentValue, int colNum){
		if(colNum == 0){
			if(currentValue<=6){	// 11- 
				return 0;
			}else if(currentValue>6){// 11 - 12
				return 1;
			}
		}else if(colNum == 1){
			if(currentValue>=3){
				return 0;
			}else if(currentValue<3){
				return 1;
			}
			
		}else if(colNum == 2){
			if(currentValue<=4){
				return 0;
			}else if(currentValue>4){
				return 1;
			}
		}else if(colNum == 3){
			if(currentValue<=2){	//12
				return 0;
			}else if(currentValue>2){
				return 1;
			}
		}
		return -1;
	}
	
	/******************************************************************************************************************/
	/******************************************************************************************************************/	
	//												Wine															  //
	/******************************************************************************************************************/
	/******************************************************************************************************************/
	public int checkWineFeatureClassifer(double currentValue, int colNum){
		if(colNum == 0){
			if(currentValue<=12){	// 11- 
				return 0;
			}else if(currentValue>12){// 11 - 12
				return 1;
			}
		}else if(colNum == 1){
			if(currentValue>=2.5){
				return 0;
			}else if(currentValue<2.5){
				return 1;
			}
		}else if(colNum == 2){
			if(currentValue<=2.3){
				return 0;
			}else if(currentValue>2.3){
				return 1;
			}
		}else if(colNum == 3){
			if(currentValue<=21){	//12
				return 0;
			}else if(currentValue>21){
				return 1;
			}
		}else if(colNum == 4){
			if(currentValue<=115){	//12
				return 0;
			}else if(currentValue>115){
				return 1;
			}
		}else if(colNum == 5){
			if(currentValue<=2.2){	//12
				return 0;
			}else if(currentValue>2.2){
				return 1;
			}
		}else if(colNum == 6){
			if(currentValue<=2.2){	//12
				return 0;
			}else if(currentValue>2.2){
				return 1;
			}
		}else if(colNum == 7){
			if(currentValue<=0.45){	//12
				return 0;
			}else if(currentValue>0.45){
				return 1;
			}
		}else if(colNum == 8){
			if(currentValue<=1.45){	//12
				return 0;
			}else if(currentValue>1.45){
				return 1;
			}
		}else if(colNum == 9){
			if(currentValue<=6.5)
				return 0;
			else if(currentValue>6.5)
				return 1;
		}else if(colNum == 10){
			if(currentValue<=1.25)
				return 0;
			else if(currentValue>1.25)
				return 1;
		}else if(colNum == 11){
			if(currentValue<=2.5)
				return 0;
			else if(currentValue>2.5)
				return 1;
		}else if(colNum == 12){
			if(currentValue<=1000)
				return 0;
			else if(currentValue>1000)
				return 1;
		}
		return -1;
	}
	
	
	/*
	  
	 //put all class into one Data
			Data totalData = new Data();	//trainning data
			totalData.classType = range;
			totalData.fileName = fileName;
			totalData.dDimension = dataList.get(0).row.get(0).getRowDimension();
			for(int i =0;i<range;i++){ 
				for(int j =0;j<dataList.get(i).row.size();j++){
					totalData.row.put(totalData.row.size(), dataList.get(i).row.get(j));
					totalData.other.put(totalData.other.size(), dataList.get(i).other.get(j));
				}
			}
			HashMap<Integer,Double> min = new HashMap<Integer,Double>();
			HashMap<Integer,Double> max = new HashMap<Integer,Double>();
			for(int i=0;i<totalData.row.size();i++){
				for(int j = 0;j<totalData.row.get(i).getRowDimension();j++){
					if(min.containsKey(j)){
						if(totalData.row.get(i).get(j, 0)<min.get(j)){
							min.put(j,totalData.row.get(i).get(j, 0));
						}
					}else{
						min.put(j, totalData.row.get(i).get(j, 0));
					}
					if(max.containsKey(j)){
						if(totalData.row.get(i).get(j, 0)>max.get(j)){
							max.put(j,totalData.row.get(i).get(j, 0));
						}						
					}else{
						max.put(j, totalData.row.get(i).get(j, 0));
					}
				}
			}
			for(int i=0;i<min.size();i++){
				System.out.println("i: " + i);
				System.out.println("  min: " + min.get(i));
				System.out.println("  max: " + max.get(i));
				double temp = ((max.get(i)- min.get(i))/4);
				double temp2 = temp + min.get(i);
				System.out.println("  first class" + temp2);
				temp2 = temp2+ temp;
				System.out.println("  second class" + temp2);
				temp2 = temp2+ temp;
				System.out.println("  third class" + temp2);
			} 
	 
	 */
}
