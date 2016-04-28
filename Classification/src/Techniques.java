import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Object;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFrame;


public class Techniques{
//	static String fileName = "wine.csv";
//	static String fileName = "iris.csv";
	static String fileName = "heartDisease.csv";
	
	public static void  main(String[]  args) {	
		boolean isDecisionTree = true;
//		boolean isDecisionTree = false;
		
		String classificationType = "Optimal";
//		String classificationType = "Naive";	//used in setMeanVariance()
//		String classificationType = "Linear";	//used in classifier() //take average of two covariance matrix


//		String setup = "10Fold";
		String setup = "LeaveOneOut";

		Node rNode = new Node();
		boolean isGetRNode = false;
		int range = 0;
		ArrayList<Data> dataList;
		Data testData = new Data();
		
		if(fileName.contains("wine")){
			range = 3;
			dataList = new ArrayList<Data>(range);
			
			//stores each class into the datalist, original
			for(int i =0;i<range;i++){
				Data temp = setData(fileName,i+1);
				dataList.add(temp);
			}

		}else if(fileName.contains("iris")){
			range = 3;
			dataList = new ArrayList<Data>(range);
			
			//stores each class into the datalist, original
			for(int i =0;i<range;i++){
				Data temp = setData(fileName,i);
				dataList.add(temp);
				showMatrix(temp.row.get(0));
			}

		}else if(fileName.contains("heartDisease")){
			range = 5;
			dataList = new ArrayList<Data>(range);

			//stores each class into the datalist, original
			for(int i =0;i<range;i++){
				Data temp = setData(fileName,i);
				dataList.add(temp);
			}
		}else{
			System.out.println("no file found");
			dataList = new ArrayList<Data>(range);
		}
		
		if(isDecisionTree){
			Double totalAccuracy = 0.0d;int roundNumber = 10;
			
			
			if(setup.equals("10Fold")){
				for(int k = 0; k<roundNumber; k++){
					ArrayList<Data> tempDataList = new ArrayList<Data>(range);
					Data allTestData = new Data();	//test data
//					ArrayList<Data> tempDataList = new ArrayList<Data>(range);
//					Data allTestData = new Data();
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
					totalData.fileName = fileName;
					totalData.dDimension = tempDataList.get(0).row.get(0).getRowDimension();
					for(int i =0;i<range;i++){ 
						for(int j =0;j<dataList.get(i).row.size();j++){
							totalData.row.put(totalData.row.size(), dataList.get(i).row.get(j));
							totalData.other.put(totalData.other.size(), dataList.get(i).other.get(j));
						}
					}
//					for(int j =0;j<totalData.other.size();j++)
//						System.out.println("totalData.other.get(j) "+ totalData.other.get(j));
						
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
//						System.out.println("i "+ i + " attributeValue " + attributeValue );
					}
					
					featureGainList = aTree2.sortGainList(featureGainList);
//					for(int kkk=0;kkk<featureGainList.size();kkk++){
//						System.out.println("fAttributeID" + featureGainList.get(kkk).AttributeID);
//						System.out.println("fgainValue " + featureGainList.get(kkk).gainValue);
//					}
					Node rootNode = new Node();
					rootNode.isRoot = true;
//					aTree2.dataList = totalData.copyData(totalData);
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
//						System.out.println(" FOUND CLASS " + testClass);
						String s = allTestData.testOther.get(i).get(0); s = s.replace("[","");s = s.replace("]","");
//						System.out.println(" ACTUAL CLASS " + s);

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
//				int k = 0;
					ArrayList<Data> tempDataList = new ArrayList<Data>(range);
					Data allTestData = new Data();	//test data
					
					Data totalData = new Data();	//trainning data
					totalData.classType = range;
					totalData.fileName = fileName;
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
//						System.out.println("i "+ i + " attributeValue " + attributeValue );
					}
					
					featureGainList = aTree2.sortGainList(featureGainList);
					
					Node rootNode = new Node();
					rootNode.isRoot = true;
//					aTree2.dataList = totalData.copyData(totalData);
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
//						System.out.println(" FOUND CLASS " + testClass);
						String s = allTestData.testOther.get(i).get(0); s = s.replace("[","");s = s.replace("]","");
//						System.out.println(" ACTUAL CLASS " + s);

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
		}else{
			
			
			Double totalAccuracy = 0.0d;
			//parse datalist into 10 folds test and trainning set
			//do it ten times
			if(setup.equals("10Fold")){
				//ten rounds
				for(int j = 0; j<10; j++){
					ArrayList<Data> tempDataList = new ArrayList<Data>(range);
					Data allTestData = new Data();
	//				System.out.println("dataList.size(): " + dataList.size());
					//loops each class and store it in
					for(int i =0;i<dataList.size();i++){
						Data temp = new Data();
						temp = tenFold(dataList.get(i),j);
						temp = setMeanVariance(temp,classificationType);
						tempDataList.add(temp);
	//					System.out.println("allTestData.testRow.size(): " + allTestData.testRow.size());
	//					System.out.println("temp.testRow.size(): " + temp.testRow.size());
						for(int jj=0;jj<temp.testRow.size();jj++){
							allTestData.testRow.put(allTestData.testRow.size(),temp.testRow.get(jj));
							allTestData.testOther.put(allTestData.testOther.size(),temp.testOther.get(jj));
						}
					}
	//				System.out.println("allTestData.testRow.size()" + allTestData.testRow.size());
					//do predictions shit here
					Double oneRoundAvgAccuracy = 0.0d;
					for(int ii= 0;ii<allTestData.testRow.size();ii++){
						oneRoundAvgAccuracy = oneRoundAvgAccuracy + checkAccuracy(allTestData,tempDataList,classificationType);
					}
					oneRoundAvgAccuracy = oneRoundAvgAccuracy / allTestData.testRow.size();
					totalAccuracy = totalAccuracy + (oneRoundAvgAccuracy);
					System.out.println("oneRoundAvgAccuracy: " + oneRoundAvgAccuracy + "%");
				}
				totalAccuracy = (totalAccuracy/10);
				System.out.println("totalAccuracy: " + totalAccuracy + "%");
			}else if(setup.equals("LeaveOneOut")){
				int totalTest = 0;
				for(int i = 0;i <dataList.size();i++){
					totalTest = dataList.get(i).row.size();
				}
				
				int currIndex = 0;
				for(int j = 0; j<totalTest;j++){
					ArrayList<Data> tempDataList = new ArrayList<Data>(range);
					Data allTestData = new Data();
	//				System.out.println("dataList.size(): " + dataList.size());
					
					//loops each class and store it in
					for(int i =0;i<dataList.size();i++){
						Data temp = new Data();
						if(currIndex<dataList.get(i).row.size())
							temp = leaveOneOut(dataList.get(i),currIndex);
						else
							currIndex = currIndex - dataList.get(i).row.size();
						temp = setMeanVariance(temp,classificationType);
						tempDataList.add(temp);
	//						System.out.println("allTestData.testRow.size(): " + allTestData.testRow.size());
	//					System.out.println("temp.testRow.size(): " + temp.testRow.size());
						for(int jj=0;jj<temp.testRow.size();jj++){
							allTestData.testRow.put(allTestData.testRow.size(),temp.testRow.get(jj));
							allTestData.testOther.put(allTestData.testOther.size(),temp.testOther.get(jj));
						}	
					}
	//				System.out.println("allTestData.testRow.size()" + allTestData.testRow.size());
					//do predictions shit here
					
					Double oneRoundAvgAccuracy = 0.0d;
					for(int ii= 0;ii<allTestData.testRow.size();ii++){
						oneRoundAvgAccuracy = oneRoundAvgAccuracy + checkAccuracy(allTestData,tempDataList,classificationType);
					}
					oneRoundAvgAccuracy = oneRoundAvgAccuracy / allTestData.testRow.size();
					totalAccuracy = totalAccuracy + (oneRoundAvgAccuracy);
	//				System.out.println("oneRoundAvgAccuracy: " + oneRoundAvgAccuracy + "%");
					
					currIndex = currIndex + 1;	
					
				}
				totalAccuracy = (totalAccuracy/totalTest);
				System.out.println("totalTest: " + totalTest);
				System.out.println("totalAccuracy: " + totalAccuracy + "%");
			}	
		}
	}
	
	
	
	public static Data setData(String fileName,int typeA){
		System.out.println("fileName: " + fileName);
		System.out.println("typeA: " + typeA);
		Data type = new Data(fileName,typeA);
		return type;
	}
	
	public static Data leaveOneOut(Data aData, int round){
		Data leaveOneOut = new Data();
		leaveOneOut.classType = aData.classType;

		for(int i = 0;i<aData.row.size();i++){
			if(i== round){
				leaveOneOut.testRow.put(0, aData.row.get(i));
				leaveOneOut.testOther.put(0, aData.other.get(i));
			}else{
				leaveOneOut.row.put(leaveOneOut.row.size(), aData.row.get(i));
				leaveOneOut.other.put(leaveOneOut.other.size(), aData.other.get(i));
			}
			
		}
		return leaveOneOut;
	}
	
//10 fold- Data Set up
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
	
	public static Data setMeanVariance(Data type,String classificationType){	
		Jama.Matrix meanType1 = new Jama.Matrix(type.dDimension,1);
		meanType1 = calcMean(type.row);
		Jama.Matrix variance1 = new Jama.Matrix(type.row.get(0).getRowDimension(),type.row.get(0).getRowDimension());
		if(classificationType.equals("Naive")){
//			public static Jama.Matrix calcVarianceMatrixNaive(Jama.Matrix mean,HashMap<Integer, Jama.Matrix> xVectorList){
			variance1 = calcVarianceMatrixNaive(meanType1, type.row);
		}else{
			variance1 = calcVarianceMatrix(meanType1, type.row);
		}
		type.setMean(meanType1);
		type.setVariance(variance1);
		
		return type;
	}
	
	
	//Classification
	public static double checkAccuracy(Data testData,ArrayList<Data> dataList, String classificationType){
		//test test data set
		ArrayList<Integer> classList = new ArrayList<Integer>();
		classList.add(0);classList.add(0);classList.add(0);classList.add(0);
		int correctGuess = 0; int totalGuess = 0;
		for(int i =0;i<testData.testRow.size();i++){
//			System.out.println("totalGuess " + totalGuess);
//			System.out.println("showMatrix(testData.testRow.get(i)\n" );showMatrix(testData.testRow.get(i));
//			System.out.println("testData.testRow.size()" + testData.testRow.size());
//			System.out.println("testData.testOther.get(i).get(0): "+ testData.testOther.get(i).get(0));
			int typeWin = 0;int pointHolder = 0;
			classList = new ArrayList<Integer>();
			classList.add(0);classList.add(0);classList.add(0);classList.add(0);
			
			typeWin = classifier(dataList.get(1-1),dataList.get(2-1),testData.testRow.get(i),classificationType);
//			System.out.println(" Result Typewin " + typeWin);
			pointHolder = classList.get(typeWin);
			pointHolder = pointHolder+1;
			classList.set(typeWin, pointHolder);
			
			typeWin = classifier(dataList.get(1-1),dataList.get(3-1),testData.testRow.get(i),classificationType);
//			System.out.println(" Result Typewin " + typeWin);
			pointHolder = classList.get(typeWin);
			pointHolder = pointHolder+1;
			classList.set(typeWin, pointHolder);
			
			typeWin = classifier(dataList.get(2-1),dataList.get(3-1),testData.testRow.get(i),classificationType);
//			System.out.println(" Result Typewin " + typeWin);
			pointHolder = classList.get(typeWin);
			pointHolder = pointHolder+1;
			classList.set(typeWin, pointHolder);
			
			typeWin = 0;
			typeWin = findWinningClass(classList);
			int typeSize = testData.testOther.get(i).size();
			if(typeWin == Integer.parseInt(testData.testOther.get(i).get(0)) && fileName.contains("wine") ){
				correctGuess = correctGuess+1;
			}else if(typeWin == Integer.parseInt(testData.testOther.get(i).get((testData.testOther.get(i).size()-1))) 
					&& (fileName.contains("heartDisease") || fileName.contains("iris")) ){
				correctGuess = correctGuess+1;
			}
			totalGuess = totalGuess + 1;
		}
		double accuracy = Double.parseDouble(correctGuess+"")/Double.parseDouble(totalGuess+"") *100;
//		System.out.println("CorrectGuess: " + correctGuess);
//		System.out.println("totalGuess: " + totalGuess);
//		System.out.println("accuracy : " + accuracy  + "%");
		
		return accuracy;
	}
	
	
	
	public static int findWinningClass(ArrayList<Integer> list){
		int winClass = 0; int value = 0;
		for(int i = 0; i<list.size();i++){
			if(list.get(i)>value){
				value = list.get(i);
				winClass = i;
			}
		}
		
		return winClass;
	}
	

	public static int classifier(Data type1, Data type2, Jama.Matrix xVector, String classificationType){
		double classType = 0.0f;
		if(classificationType.equals("Linear")){
			Jama.Matrix tempV = calcVarianceLinear(type1.variance,type2.variance);
			classType = classify(tempV,tempV,xVector,type1.meanType,type2.meanType);
		}else{
			classType = classify(type1.variance,type2.variance,xVector,type1.meanType,type2.meanType);
		}
		if(classType>0){	//typeA
			return type1.classType;
		}else if(classType<=0){//typeB
			return type2.classType;
		}else{
			return 0;
		}
	}
	
	public static double Mahalanobis(Jama.Matrix variance, Jama.Matrix mean, Jama.Matrix xVector){
		Jama.Matrix temp = xVector.minus(mean).transpose().times(variance.inverse()).times(xVector.minus(mean));
		return temp.get(0, 0);
	}
	
	public static double classify(Jama.Matrix variance1, Jama.Matrix variance2, Jama.Matrix xVector1, Jama.Matrix mean1, Jama.Matrix mean2){
		double temp = 0.0f;

		temp = Math.log(variance2.det())- Math.log(variance1.det())+Mahalanobis(variance2,mean2,xVector1)-Mahalanobis(variance1,mean1,xVector1);
		return temp;
	}
	public static Jama.Matrix calcVarianceLinear(Jama.Matrix variance1, Jama.Matrix variance2){
		Jama.Matrix tempM = new Jama.Matrix(variance1.getRowDimension(),variance1.getColumnDimension());
		for(int i = 0;i<variance1.getRowDimension();i++){
			for(int j = 0; j<variance1.getColumnDimension();j++){
				double temp = 0.0f;
				temp = (variance1.get(i, j) + variance2.get(i, j))/2;
				tempM.set(i, j, temp);
			}
		}
		return tempM;
	}
	public static Jama.Matrix calcVarianceMatrixNaive(Jama.Matrix mean,HashMap<Integer, Jama.Matrix> xVectorList){
		Jama.Matrix temp = new Jama.Matrix(mean.getRowDimension(),mean.getRowDimension());
		for(int i=0;i<xVectorList.size();i++){
			for(int j =0; j<mean.getRowDimension();j++){
				for(int k =0; k<mean.getRowDimension();k++){
					if(k == j){
						double currVariance = 0.0d;
						currVariance = temp.get(j, k);
						currVariance = currVariance + ((xVectorList.get(i).get(j, 0) - mean.get(j, 0))*(xVectorList.get(i).get(j, 0) - mean.get(j, 0)));
						temp.set(j, k, currVariance);
					}else{
						temp.set(j, k, 0);
					}
				}
			}
		}
		for(int i=0;i<mean.getRowDimension();i++){
			for(int j =0; j<mean.getRowDimension();j++){
				if(i == j){
					double currVariance = 0.0d;
					currVariance = temp.get(i, j);
					currVariance = currVariance/(xVectorList.size()-1);
	
					temp.set(i, j,currVariance);
				}
			}
		}
		return temp;
	}
	
	public static Jama.Matrix calcVarianceMatrix(Jama.Matrix mean,HashMap<Integer, Jama.Matrix> xVectorList){
		Jama.Matrix temp = new Jama.Matrix(mean.getRowDimension(),mean.getRowDimension());
		for(int i=0;i<xVectorList.size();i++){
			for(int j =0; j<mean.getRowDimension();j++){
				for(int k = 0; k<mean.getRowDimension();k++){
					double currVariance = 0.0d;
					currVariance = temp.get(j, k);
					currVariance = currVariance + ((xVectorList.get(i).get(j, 0) - mean.get(j, 0))*(xVectorList.get(i).get(k, 0) - mean.get(k, 0)));
					temp.set(j, k, currVariance);
				}
			}
		}
		for(int i=0;i<mean.getRowDimension();i++){
			for(int j =0; j<mean.getRowDimension();j++){
				double currVariance = 0.0d;
				currVariance = temp.get(i, j);
				currVariance = currVariance/(xVectorList.size()-1);

				temp.set(i, j,currVariance);
			}
		}
		return temp;
	}
	
	public static void showMatrix(Jama.Matrix meanType1){
		for(int i =0;i<meanType1.getRowDimension();i++){
			for(int j =0;j<meanType1.getColumnDimension();j++){
				System.out.print(meanType1.get(i, j)+" ");
			}
			System.out.println("");
		}
	}
	
	public static Jama.Matrix calcMean(HashMap<Integer, Jama.Matrix> data){
		ArrayList<String> tempMeanCol = new ArrayList<String>();

		for(int i = 0;i<data.size();i++){
			for(int j = 0 ;j<data.get(i).getRowDimension();j++){
				if(tempMeanCol.isEmpty()|| tempMeanCol.size()<=j){
					tempMeanCol.add(data.get(i).get(j, 0)+"");
				}else{
					float temptotal = 0;
					temptotal = Float.parseFloat(tempMeanCol.get(j))+ Float.parseFloat(data.get(i).get(j, 0)+"");
					tempMeanCol.set(j, temptotal+"");
				}
			}
		}
		
		for(int i =0; i<tempMeanCol.size();i++){
			Float mean = 0.0f;
			if(!tempMeanCol.get(i).equals("")){
				mean = Float.parseFloat(tempMeanCol.get(i)) / data.size();
				tempMeanCol.set(i, mean+"");
//				System.out.print(tempMeanCol.get(i) + " ");
			}
		}
		Jama.Matrix meanM = new Jama.Matrix(tempMeanCol.size(),1);
		for(int i =0;i<tempMeanCol.size();i++){
//			meanM.assignValue(Float.parseFloat(tempMeanCol.get(i)), i, 1);			
			meanM.set(i, 0, Double.parseDouble(tempMeanCol.get(i)));
		}
		return meanM;
	}	
}
