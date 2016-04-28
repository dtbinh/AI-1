//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class AttributeClassifierJUnk {
//	public HashMap<Integer,HashMap<Integer,Data>> WineFeature(ArrayList<Data> dataList, int colNum){
//		HashMap<Integer,HashMap<Integer,Data>> diffClassList = new HashMap<Integer,HashMap<Integer,Data>>();
//		
//		//checks all d type classes for same features
//		double localFeatureEntropy = 0;
//		//get all the values from one class type first
//		for(int i = 0;i <dataList.size();i++){
//			HashMap<Integer,Data> dataBaseOnColumn = new HashMap<Integer,Data>();
//			
//			//list all rows from one class
//			for(int j = 0;j<dataList.get(i).row.size();j++){
//				double currentValue = 0;
//				Data tempData = new Data();
////				int rowCounter = 0;
//				//gets value of the column saved inside the actual trainning data row from list in class i 
//				currentValue = dataList.get(i).row.get(j).get(colNum, 0);
//				
//				//Debug for IRISFeature counter
//				
////				System.out.println("Show Matrix");
////				showMatrix(dataList.get(i).row.get(j));
////				System.out.println("currentValue: " + currentValue );
////				for(int jj= 0;jj<dataBaseOnColumn.size();jj++){
////					System.out.println("dataBaseOnColumn.get(jj): " + dataBaseOnColumn.get(jj));
////				}
//				if(colNum == 0){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<6;ii++)
//							dataBaseOnColumn.put(ii, new Data());		//initialize
//					
//					if(currentValue<=11){	// 11- 
//						if(dataBaseOnColumn.containsKey(0))
//							tempData = dataBaseOnColumn.get(0);
//						
//						tempData.row.put(tempData.row.size(),dataList.get(i).row.get(j));
//						dataBaseOnColumn.put(0, tempData);
//					}else if(currentValue<=12){// 11 - 12
//						if(dataBaseOnColumn.containsKey(1))
//							tempData = dataBaseOnColumn.get(1);
//						
//						tempData.row.put(tempData.row.size(),dataList.get(i).row.get(j));
//						dataBaseOnColumn.put(1, tempData);
//					}else if(currentValue<=13){// 12-13
//						if(dataBaseOnColumn.containsKey(2))
//							tempData = dataBaseOnColumn.get(2);
//						
//						tempData.row.put(tempData.row.size(),dataList.get(i).row.get(j));
//						dataBaseOnColumn.put(2, tempData);
//					}else if(currentValue<=14){// 13-14
//						if(dataBaseOnColumn.containsKey(3))
//							tempData = dataBaseOnColumn.get(3);
//						
//						tempData.row.put(tempData.row.size(),dataList.get(i).row.get(j));
//						dataBaseOnColumn.put(3, tempData);
//					}else if(currentValue<=15){// 14-15
//						if(dataBaseOnColumn.containsKey(4))
//							tempData = dataBaseOnColumn.get(4);
//						
//						tempData.row.put(tempData.row.size(),dataList.get(i).row.get(j));
//						dataBaseOnColumn.put(4, tempData);
//					}else if(currentValue>15){// 15+
//						if(dataBaseOnColumn.containsKey(5))
//							tempData = dataBaseOnColumn.get(5);
//						
//						tempData.row.put(tempData.row.size(),dataList.get(i).row.get(j));
//						dataBaseOnColumn.put(5, tempData);
//					}
//				}else if(colNum == 1){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<10;ii++)
//							dataBaseOnColumn.put(ii, new Data());		//initialize
//					
//					if(currentValue<=1){
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=1.5){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=2){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=2.5){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=3){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue<=3.5){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue<=4){
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}else if(currentValue<=4.5){
//						if(dataBaseOnColumn.containsKey(7))
//							rowCounter = dataBaseOnColumn.get(7); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(7, rowCounter);
//					}else if(currentValue<=5){
//						if(dataBaseOnColumn.containsKey(8))
//							rowCounter = dataBaseOnColumn.get(8); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(8, rowCounter);
//					}else if(currentValue>5){
//						if(dataBaseOnColumn.containsKey(9))
//							rowCounter = dataBaseOnColumn.get(9); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(9, rowCounter);
//					}
//					
//				}else if(colNum == 2){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<9;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=1.6){
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=1.8){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=2){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=2.2){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=2.4){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue<=2.6){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue<=2.8){
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}else if(currentValue<=3){
//						if(dataBaseOnColumn.containsKey(7))
//							rowCounter = dataBaseOnColumn.get(7); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(7, rowCounter);
//					}else if(currentValue>3){
//						if(dataBaseOnColumn.containsKey(8))
//							rowCounter = dataBaseOnColumn.get(8); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(8, rowCounter);
//					}
//				}else if(colNum == 3){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<11;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=12){	//12
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=14){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=16){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=18){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=20){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue<=22){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue<=24){
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}else if(currentValue<=26){
//						if(dataBaseOnColumn.containsKey(7))
//							rowCounter = dataBaseOnColumn.get(7); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(7, rowCounter);
//					}else if(currentValue<=28){
//						if(dataBaseOnColumn.containsKey(8))
//							rowCounter = dataBaseOnColumn.get(8); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(8, rowCounter);
//					}else if(currentValue<=30){
//						if(dataBaseOnColumn.containsKey(9))
//							rowCounter = dataBaseOnColumn.get(9); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(9, rowCounter);
//					}else if(currentValue>30){
//						if(dataBaseOnColumn.containsKey(10))
//							rowCounter = dataBaseOnColumn.get(10); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(10, rowCounter);
//					}
//				}else if(colNum == 4){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<11;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=70){	//12
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=80){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=90){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=100){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=110){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue<=120){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue<=130){
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}else if(currentValue<=140){
//						if(dataBaseOnColumn.containsKey(7))
//							rowCounter = dataBaseOnColumn.get(7); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(7, rowCounter);
//					}else if(currentValue<=150){
//						if(dataBaseOnColumn.containsKey(8))
//							rowCounter = dataBaseOnColumn.get(8); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(8, rowCounter);
//					}else if(currentValue<=160){
//						if(dataBaseOnColumn.containsKey(9))
//							rowCounter = dataBaseOnColumn.get(9); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(9, rowCounter);
//					}else if(currentValue>160){
//						if(dataBaseOnColumn.containsKey(10))
//							rowCounter = dataBaseOnColumn.get(10); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(10, rowCounter);
//					}
//				}else if(colNum == 5){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<14;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=1){	//12
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=1.2){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=1.4){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=1.6){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=1.8){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue<=2){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue<=2.2){
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}else if(currentValue<=2.4){
//						if(dataBaseOnColumn.containsKey(7))
//							rowCounter = dataBaseOnColumn.get(7); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(7, rowCounter);
//					}else if(currentValue<=2.6){
//						if(dataBaseOnColumn.containsKey(8))
//							rowCounter = dataBaseOnColumn.get(8); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(8, rowCounter);
//					}else if(currentValue<=2.8){
//						if(dataBaseOnColumn.containsKey(9))
//							rowCounter = dataBaseOnColumn.get(9); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(9, rowCounter);
//					}else if(currentValue<=3){
//						if(dataBaseOnColumn.containsKey(10))
//							rowCounter = dataBaseOnColumn.get(10); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(10, rowCounter);
//					}else if(currentValue<=3.2){
//						if(dataBaseOnColumn.containsKey(11))
//							rowCounter = dataBaseOnColumn.get(11); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(11, rowCounter);
//					}else if(currentValue<=3.4){
//						if(dataBaseOnColumn.containsKey(12))
//							rowCounter = dataBaseOnColumn.get(12); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(12, rowCounter);
//					}else if(currentValue>3.4){
//						if(dataBaseOnColumn.containsKey(13))
//							rowCounter = dataBaseOnColumn.get(13); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(13, rowCounter);
//					}
//				}else if(colNum == 6){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<12;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=0.2){	//12
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=0.6){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=1){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=1.4){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=1.8){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue<=2.2){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue<=2.6){
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}else if(currentValue<=3){
//						if(dataBaseOnColumn.containsKey(7))
//							rowCounter = dataBaseOnColumn.get(7); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(7, rowCounter);
//					}else if(currentValue<=3.4){
//						if(dataBaseOnColumn.containsKey(8))
//							rowCounter = dataBaseOnColumn.get(8); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(8, rowCounter);
//					}else if(currentValue<=3.8){
//						if(dataBaseOnColumn.containsKey(9))
//							rowCounter = dataBaseOnColumn.get(9); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(9, rowCounter);
//					}else if(currentValue<=4.2){
//						if(dataBaseOnColumn.containsKey(10))
//							rowCounter = dataBaseOnColumn.get(10); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(10, rowCounter);
//					}else if(currentValue>4.2){
//						if(dataBaseOnColumn.containsKey(11))
//							rowCounter = dataBaseOnColumn.get(11); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(11, rowCounter);
//					}
//				}else if(colNum == 7){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<9;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=0.1){	//12
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=0.2){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=0.3){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=0.4){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=0.5){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue<=0.6){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue<=0.7){
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}else if(currentValue<=0.8){
//						if(dataBaseOnColumn.containsKey(7))
//							rowCounter = dataBaseOnColumn.get(7); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(7, rowCounter);
//					}else if(currentValue>0.8){
//						if(dataBaseOnColumn.containsKey(8))
//							rowCounter = dataBaseOnColumn.get(8); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(8, rowCounter);
//					}
//				}else if(colNum == 8){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<12;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=0.1){	//12
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=0.4){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=0.7){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=1){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=1.3){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue<=1.6){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue<=1.9){
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}else if(currentValue<=2.1){
//						if(dataBaseOnColumn.containsKey(7))
//							rowCounter = dataBaseOnColumn.get(7); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(7, rowCounter);
//					}else if(currentValue<=2.4){
//						if(dataBaseOnColumn.containsKey(8))
//							rowCounter = dataBaseOnColumn.get(8); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(8, rowCounter);
//					}else if(currentValue<=2.7){
//						if(dataBaseOnColumn.containsKey(9))
//							rowCounter = dataBaseOnColumn.get(9); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(9, rowCounter);
//					}else if(currentValue<=3){
//						if(dataBaseOnColumn.containsKey(10))
//							rowCounter = dataBaseOnColumn.get(10); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(10, rowCounter);
//					}else if(currentValue>3){
//						if(dataBaseOnColumn.containsKey(11))
//							rowCounter = dataBaseOnColumn.get(11); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(11, rowCounter);
//					}
//				}else if(colNum == 9){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<12;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=1){	//12
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=3){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=4){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=5){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=6){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue<=7){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue<=8){
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}else if(currentValue<=9){
//						if(dataBaseOnColumn.containsKey(7))
//							rowCounter = dataBaseOnColumn.get(7); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(7, rowCounter);
//					}else if(currentValue<=10){
//						if(dataBaseOnColumn.containsKey(8))
//							rowCounter = dataBaseOnColumn.get(8); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(8, rowCounter);
//					}else if(currentValue<=11){
//						if(dataBaseOnColumn.containsKey(9))
//							rowCounter = dataBaseOnColumn.get(9); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(9, rowCounter);
//					}else if(currentValue<=12){
//						if(dataBaseOnColumn.containsKey(10))
//							rowCounter = dataBaseOnColumn.get(10); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(10, rowCounter);
//					}else if(currentValue>12){
//						if(dataBaseOnColumn.containsKey(11))
//							rowCounter = dataBaseOnColumn.get(11); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(11, rowCounter);
//					}
//				}else if(colNum == 10){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<10;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=0.5){	//12
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=0.7){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=0.9){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=1){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=1.1){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue<=1.2){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue<=1.3){
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}else if(currentValue<=1.5){
//						if(dataBaseOnColumn.containsKey(7))
//							rowCounter = dataBaseOnColumn.get(7); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(7, rowCounter);
//					}else if(currentValue<=1.7){
//						if(dataBaseOnColumn.containsKey(8))
//							rowCounter = dataBaseOnColumn.get(8); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(8, rowCounter);
//					}else if(currentValue>1.7){
//						if(dataBaseOnColumn.containsKey(9))
//							rowCounter = dataBaseOnColumn.get(9); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(9, rowCounter);
//					}
//				}else if(colNum == 11){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<12;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=1){	//12
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=1.2){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=1.3){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=1.4){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=1.5){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue<=1.5){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue<=1.6){
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}else if(currentValue<=1.8){
//						if(dataBaseOnColumn.containsKey(7))
//							rowCounter = dataBaseOnColumn.get(7); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(7, rowCounter);
//					}else if(currentValue<=2){
//						if(dataBaseOnColumn.containsKey(8))
//							rowCounter = dataBaseOnColumn.get(8); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(8, rowCounter);
//					}else if(currentValue<=3){
//						if(dataBaseOnColumn.containsKey(9))
//							rowCounter = dataBaseOnColumn.get(9); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(9, rowCounter);
//					}else if(currentValue<=4){
//						if(dataBaseOnColumn.containsKey(10))
//							rowCounter = dataBaseOnColumn.get(10); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(10, rowCounter);
//					}else if(currentValue>4){
//						if(dataBaseOnColumn.containsKey(11))
//							rowCounter = dataBaseOnColumn.get(11); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(11, rowCounter);
//					}
//				}else if(colNum == 12){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<16;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=300){	//12
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=400){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=500){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=600){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=700){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue<=800){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue<=900){
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}else if(currentValue<=1000){
//						if(dataBaseOnColumn.containsKey(7))
//							rowCounter = dataBaseOnColumn.get(7); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(7, rowCounter);
//					}else if(currentValue<=1100){
//						if(dataBaseOnColumn.containsKey(8))
//							rowCounter = dataBaseOnColumn.get(8); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(8, rowCounter);
//					}else if(currentValue<=1200){
//						if(dataBaseOnColumn.containsKey(9))
//							rowCounter = dataBaseOnColumn.get(9); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(9, rowCounter);
//					}else if(currentValue<=1300){
//						if(dataBaseOnColumn.containsKey(10))
//							rowCounter = dataBaseOnColumn.get(10); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(10, rowCounter);
//					}else if(currentValue<=1400){
//						if(dataBaseOnColumn.containsKey(11))
//							rowCounter = dataBaseOnColumn.get(11); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(11, rowCounter);
//					}else if(currentValue<=1500){
//						if(dataBaseOnColumn.containsKey(12))
//							rowCounter = dataBaseOnColumn.get(12); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(12, rowCounter);
//					}else if(currentValue<=1600){
//						if(dataBaseOnColumn.containsKey(13))
//							rowCounter = dataBaseOnColumn.get(13); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(13, rowCounter);
//					}else if(currentValue<=1700){
//						if(dataBaseOnColumn.containsKey(14))
//							rowCounter = dataBaseOnColumn.get(14); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(14, rowCounter);
//					}else if(currentValue>1700){
//						if(dataBaseOnColumn.containsKey(15))
//							rowCounter = dataBaseOnColumn.get(15); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(15, rowCounter);
//					}
//				}
//				diffClassList.put(i,dataBaseOnColumn);		
//			}
//
//		}
//		return diffClassList;
//	}
//	
//	//PROBLEM: ONLY TAKES CLASS 1 of it in for column type
//	public HashMap<Integer,HashMap<Integer,Integer>> HeartDiseaseFeature(ArrayList<Data> dataList, int colNum){
//		HashMap<Integer,HashMap<Integer,Integer>> diffClassList = new HashMap<Integer,HashMap<Integer,Integer>>();
////		System.out.println("third dataList " + dataList.size());
//		//checks all d type classes for same features
//		double localFeatureEntropy = 0;
//		//get all the values from one class type first
//		for(int i = 0;i <dataList.size();i++){
//			HashMap<Integer,Integer> dataBaseOnColumn = new HashMap<Integer,Integer>();
//			
//			//list all rows from one class
//			for(int j = 0;j<dataList.get(i).row.size();j++){
//				
//				double currentValue = 0;
//				int rowCounter = 0;
//
//				//gets value of the column saved inside the actual trainning data row from list in class i 
//				currentValue = dataList.get(i).row.get(j).get(colNum, 0);
//				
//				//Debug for IRISFeature counter
//				
////				System.out.println("Show Matrix");
////				showMatrix(dataList.get(i).row.get(j));
////				System.out.println("currentValue: " + currentValue );
////				for(int jj= 0;jj<dataBaseOnColumn.size();jj++){
////					System.out.println("dataBaseOnColumn.get(jj): " + dataBaseOnColumn.get(jj));
////				}
//				if(colNum == 0){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<7;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=20){	// 4- 
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=30){// 4- 5
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=40){// 5-6
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=50){// 6-7
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=60){// 7-8
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue<=70){// 8+
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue>70){// 8+
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}
//				}else if(colNum == 1){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<3;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue==0){
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue==1){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else {
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}
//				}else if(colNum == 2){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<5;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=1){ 		//1--
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=2){	//1-2
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=3){	//2-3
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=4){	//3-4
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue>4){	//4-5
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}
//				}else if(colNum == 3){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<7;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=100){
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue <=120){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue <=140){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue <=160){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue <=180){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue <=200){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue >200){
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}
//				}else if(colNum == 4){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<8;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=100){
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue <=150){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue <=200){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue <=250){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue <=300){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue <=350){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue <=400){
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}else if(currentValue >400){
//						if(dataBaseOnColumn.containsKey(7))
//							rowCounter = dataBaseOnColumn.get(7); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(7, rowCounter);
//					}
//				}else if(colNum == 5){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<3;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue==0){
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue==1){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else {
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}
//				}else if(colNum == 6){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<3;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue==0){
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue==2){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else {
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}
//				}else if(colNum == 7){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<7;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=100){	//12
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=120){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=140){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=160){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=180){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue<=200){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue>200){
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}
//				}else if(colNum == 8){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<3;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue==0){
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue==1){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else {
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}
//				}else if(colNum == 9){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<10;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue==0){	//12
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=0.5){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=1){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=1.5){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=2){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue<=2.5){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue<=3){
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}else if(currentValue<=4){
//						if(dataBaseOnColumn.containsKey(7))
//							rowCounter = dataBaseOnColumn.get(7); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(7, rowCounter);
//					}else if(currentValue<=5){
//						if(dataBaseOnColumn.containsKey(8))
//							rowCounter = dataBaseOnColumn.get(8); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(8, rowCounter);
//					}else if(currentValue>5){
//						if(dataBaseOnColumn.containsKey(9))
//							rowCounter = dataBaseOnColumn.get(9); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(9, rowCounter);
//					}
//				}else if(colNum == 10){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<4;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue==0){
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue==1){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue==2){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue==3){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}
//				}else if(colNum == 11){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<4;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue==0){
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue==1){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue==2){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue==3){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}
//				}else if(colNum == 12){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<4;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue==3){
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue==6){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue==7){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else {
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}
//				}
//					
//			}
//			int temSize = dataList.get(i).row.size();
//			diffClassList.put(i,dataBaseOnColumn);
//		}
//		return diffClassList;
//	}
//	
//	public HashMap<Integer,HashMap<Integer,Integer>> IRISFeature(ArrayList<Data> dataList, int colNum){
//		HashMap<Integer,HashMap<Integer,Integer>> diffClassList = new HashMap<Integer,HashMap<Integer,Integer>>();
//		
//		//checks all d type classes for same features
//		double localFeatureEntropy = 0;
//		//get all the values from one class type first
//		for(int i = 0;i <dataList.size();i++){
//			HashMap<Integer,Integer> dataBaseOnColumn = new HashMap<Integer,Integer>();
//			
//			//list all rows from one class
//			for(int j = 0;j<dataList.get(i).row.size();j++){
//				double currentValue = 0;
//				int rowCounter = 0;
//
//				//gets value of the column saved inside the actual trainning data row from list in class i 
//				currentValue = dataList.get(i).row.get(j).get(colNum, 0);
//				
//				//Debug for IRISFeature counter
//				
////				System.out.println("Show Matrix");
////				showMatrix(dataList.get(i).row.get(j));
////				System.out.println("currentValue: " + currentValue );
////				for(int jj= 0;jj<dataBaseOnColumn.size();jj++){
////					System.out.println("dataBaseOnColumn.get(jj): " + dataBaseOnColumn.get(jj));
////				}
//				if(colNum == 0){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<6;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=4){	// 4- 
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=5){// 4- 5
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=6){// 5-6
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=7){// 6-7
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=8){// 7-8
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue>8){// 8+
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}
//				}else if(colNum == 1){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<5;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=2){
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=2.5){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=3){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=3.5){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue>=4){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}
//				}else if(colNum == 2){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<7;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=1){ 		//1--
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue<=2){	//1-2
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue<=3){	//2-3
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue<=4){	//3-4
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue<=5){	//4-5
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue<=6){	//5-6
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}else if(currentValue<=7){	//6-7
//						if(dataBaseOnColumn.containsKey(6))
//							rowCounter = dataBaseOnColumn.get(6); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(6, rowCounter);
//					}
//				}else if(colNum == 3){
//					if(dataBaseOnColumn.isEmpty())
//						for(int ii=0;ii<6;ii++)
//							dataBaseOnColumn.put(ii, 0);		//initialize
//					
//					if(currentValue<=0.5){
//						if(dataBaseOnColumn.containsKey(0))
//							rowCounter = dataBaseOnColumn.get(0); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(0, rowCounter);
//					}else if(currentValue <=1){
//						if(dataBaseOnColumn.containsKey(1))
//							rowCounter = dataBaseOnColumn.get(1); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(1, rowCounter);
//					}else if(currentValue <=1.5){
//						if(dataBaseOnColumn.containsKey(2))
//							rowCounter = dataBaseOnColumn.get(2); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(2, rowCounter);
//					}else if(currentValue <=2){
//						if(dataBaseOnColumn.containsKey(3))
//							rowCounter = dataBaseOnColumn.get(3); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(3, rowCounter);
//					}else if(currentValue <=2.5){
//						if(dataBaseOnColumn.containsKey(4))
//							rowCounter = dataBaseOnColumn.get(4); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(4, rowCounter);
//					}else if(currentValue >3){
//						if(dataBaseOnColumn.containsKey(5))
//							rowCounter = dataBaseOnColumn.get(5); 
//						rowCounter = rowCounter+1;
//						dataBaseOnColumn.put(5, rowCounter);
//					}
//				}
//				diffClassList.put(i,dataBaseOnColumn);		
//			}
//
//		}
//		return diffClassList;
//	}
//}
