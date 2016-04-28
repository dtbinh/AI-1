import java.util.ArrayList;
import java.util.HashMap;

public class AttributeInfo {
	double gainValue = 0;
	int AttributeID = 0;
	ArrayList<String>AttributeName = new ArrayList<String>();
	//attribute feature class
	HashMap<Integer,Data> featureDataSet = new HashMap<Integer,Data>();
	HashMap<Integer,Double> featureEntropySet = new HashMap<Integer,Double>();
//	int numFeature, int totalSize, 
	public AttributeInfo(){
		featureDataSet = new HashMap<Integer,Data>();
		AttributeID = 0;
		gainValue = 0;
	}
	public AttributeInfo(int currID){
		featureDataSet = new HashMap<Integer,Data>();
		AttributeID = currID;
		gainValue = 0;
	}
	public AttributeInfo(double entropy, int currID){
//		featureClassSize = numFeature;
//		totalAttributeSize = totalSize;
		gainValue =entropy;
		AttributeID = currID;
//		addData(inputData);
	}
	public void setFeatureEntropySet(int key, double input){
		featureEntropySet.put(key, input);
	}
	
	public void printFeatureEntropy(){
		for(int i = 0;i<featureEntropySet.size();i++){
			System.out.print(featureEntropySet.get(i) + " | ");
		}
	}
	public void addData(Data input){
		Data temp = new Data();
		temp = temp.copyData(input);
		featureDataSet.put(featureDataSet.size(),temp);
	}
	public AttributeInfo copyAttributeInfo(AttributeInfo input){
		AttributeInfo temp = new AttributeInfo();
		temp.gainValue = input.gainValue;
		temp.AttributeID = input.AttributeID;
		
//		for(int i = 0;i<input.featureDataSet.size();i++)
//			temp.featureDataSet.put(temp.featureDataSet.size(), input.featureDataSet.get(i));

//		for(int i = 0;i<input.featureEntropySet.size();i++)
//			temp.featureEntropySet.put(temp.featureEntropySet.size(), input.featureEntropySet.get(i));
		
		return temp;
	}
	public String toString(){
//		return "featureClassSize " + featureClassSize 
//				+ " totalAttributeSize " + totalAttributeSize 
		return "entropyFeature " + gainValue
				+ " featureID " + AttributeID;
	}
}
