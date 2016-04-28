import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Data {
//	HashMap<Integer, ArrayList<String>> other;
//	HashMap<Integer, ArrayList<String>> row;
//	
//	HashMap<Integer, ArrayList<String>> testRow;
//	HashMap<Integer, ArrayList<String>> testOther;
	Jama.Matrix meanType;
	Jama.Matrix variance;
	HashMap<Integer, Jama.Matrix> row;	
	HashMap<Integer, ArrayList<String>> other;
	
	HashMap<Integer, Jama.Matrix> testRow;
	HashMap<Integer, ArrayList<String>> testOther;
	String fileName = "";
	int classType = 0;	//number of classes
	int dDimension = 0;
	
	public void setMean(Jama.Matrix aMatrix){
		meanType = new Jama.Matrix(aMatrix.getRowDimension(),aMatrix.getColumnDimension());
		meanType = aMatrix;
	}
	
	public void setVariance(Jama.Matrix aMatrix){
		variance = new Jama.Matrix(aMatrix.getRowDimension(),aMatrix.getColumnDimension());
		variance = aMatrix;
	}
	public Data(){
		testOther = new HashMap<Integer,ArrayList<String>>();
		testRow = new HashMap<Integer,Jama.Matrix>();
		other = new HashMap<Integer,ArrayList<String>>();
		row = new HashMap<Integer,Jama.Matrix>();
	}
	public void setup(String name, int total){
		fileName = name;
		classType = total;
	}
	public Data(String fileName, int type){
		classType = type;
		String DfileName = fileName;//"zoo.csv";
		File file = new File(DfileName);
		
		try {
//			avoid  = new ArrayList<Integer>();
			Scanner inputStream = new Scanner(file);
			int j = 0;int jj =0; int totalRow = 0; 
			other = new HashMap<Integer,ArrayList<String>>();
			testOther = new HashMap<Integer,ArrayList<String>>();
			row = new HashMap<Integer,Jama.Matrix>();
			testRow = new HashMap<Integer,Jama.Matrix>();
			int testing = 0; int classIndex = 0;
			
			while(inputStream.hasNext()){
				
				String data = inputStream.next(); // return next thing
				String[] value = data.split(",");
				int size = value.length - 1;
//				if(value[size].equals(type)){
				if(fileName.contains("wine"))
					classIndex = 0;
				else if(fileName.contains("heartDisease"))
					classIndex = size;
				else if(fileName.contains("iris")){
					classIndex = size;
					switch(value[size]){
						case "Iris-setosa":
							value[size] = 0 + "";
							break;
						case "Iris-versicolor":
							value[size] = 1 + "";
							break;
						case "Iris-virginica":
							value[size] = 2 + "";
							break;
					}
				}
				if(value[classIndex].equals(type+"")){
					ArrayList<String> columns = new ArrayList<String>();
					ArrayList<String> otherColumns = new ArrayList<String>();
					for(int i =0;i<value.length;i++){
//						if(isInteger(value[i])&& i != (value.length-1)){
						if(fileName.contains("wine")){
							if(isInteger(value[i])&& i != 0){
								columns.add(value[i]);
							}else{
								otherColumns.add(value[i]);
							}
						}else if(fileName.contains("heartDisease")){
							if(i== size){
								otherColumns.add(value[i]);
							}else if(isInteger(value[i])){
								columns.add(value[i]);
							}else{
								otherColumns.add(value[i]);
							}
						}else if(fileName.contains("iris")){
							if(i == (size)){
								otherColumns.add(value[i]);
							}else if(isInteger(value[i])){
								columns.add(value[i]);
							}else{
								otherColumns.add(value[i]);
							}
						}else{
							if(isInteger(value[i])){
								columns.add(value[i]);
							}else{
								otherColumns.add(value[i]);
							}
						}
					}
					Jama.Matrix xVector = new Jama.Matrix(columns.size(),1);
					dDimension = columns.size();
					for(int ii=0;ii<columns.size();ii++){
						xVector.set(ii, 0,Double.parseDouble(columns.get(ii)));
					}

					row.put(jj, xVector);
					other.put(jj, otherColumns);
					jj++;
					testing++;
				}
			}
			inputStream.close();
			System.out.println("row " + row.get(0));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean isInteger(String s) {
		if(s.isEmpty()) return false;
		for(int i = 0; i < s.length(); i++) {
	        if(s.charAt(i)== '0' || s.charAt(i)== '1' ||s.charAt(i)== '2' ||s.charAt(i)== '3' ||s.charAt(i)== '4' ||s.charAt(i)== '5' ||
	        		s.charAt(i)== '6' ||s.charAt(i)== '7' ||s.charAt(i)== '8' ||s.charAt(i)== '9' ||s.charAt(i)== '.') {
	            
	        }else{
	        	return false;
	        }
	    }
	    return true;
	}
	public Data copyData(Data orig){
		Data temp = new Data();
		/*
			Jama.Matrix meanType;
			Jama.Matrix variance;
		*/
	
		if(!orig.row.isEmpty()){
			for(int i = 0;i<orig.row.size();i++){
				temp.row.put(i, orig.row.get(i).copy());
			}
		}
		if(!orig.other.isEmpty()){
			for(int i = 0;i<orig.other.size();i++){
				ArrayList<String> tempOther = new ArrayList<String>();
				for(int j = 0;j<orig.other.get(i).size();j++){
					tempOther.add(orig.other.get(i).get(j));
				}
				temp.other.put(i, tempOther);
				
			}
		}
		if(!orig.testRow.isEmpty()){
			for(int i = 0;i<orig.testRow.size();i++){
				temp.testRow.put(i, orig.testRow.get(i).copy());
			}
		}
		if(!orig.testOther.isEmpty()){
			for(int i = 0;i<orig.testOther.size();i++){
				ArrayList<String> tempOther = new ArrayList<String>();
				for(int j = 0;j<orig.testOther.get(i).size();j++){
					tempOther.add(orig.testOther.get(i).get(j));
				}
				temp.testOther.put(i, tempOther);
				
			}
		}
		temp.fileName = orig.fileName;
		temp.classType = orig.classType;
		temp.dDimension = orig.dDimension;
		
		return temp;
	}
}
