import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class trackDrivingHistory {
	
	
	
	private static HashMap<String , driverHistory > driverHistoryDetail; 
			
			public static List<String> _result ;
			
		
		
	
	/*
	 * this method check whether to process adding the driver or the trip 
	 */
	
	public static List<String> processingDiverInfo(String fileName) throws IOException {
		
		
		driverHistoryDetail = new HashMap<String,driverHistory >();
		_result = new ArrayList<>();
	
		
		FileInputStream file = null;
		Scanner scan = null;

        try {
        	
        	file = new FileInputStream(fileName);
        	scan = new Scanner(file);
        	
        	while(scan.hasNextLine()){
        		String line = scan.nextLine();
        		
        		String [] word = line.split(" ");
        		
        		if(word[0].equals("Driver")){
        			
        			//add this driver to the system;
        			String driverName = word[1];
        			
        			addDriver(driverName);
        		}
        		else if (word[0].equals("Trip")){
        			
        			String name = word[1];
        			String startTime = word[2];
        			String endTime = word[3];
        			String miles = word[4];
        			
        			
        			
        			// this is the current trip for ths driver 
        			addCurrentDriverTrip(name,startTime ,endTime ,miles);
       
        		}
        	}

           
        } 

        catch (IOException e) {
            e.printStackTrace();
        } 

        finally {
            try {
                if (file != null)
                	file.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        fileWriter();
	
      return _result;
      
		
	}
	
	// adding the driver's current trip detail
	private static void addCurrentDriverTrip(String name, String startTime, String endTime, String miles) {
		
		if(driverHistoryDetail.get(name)!=null){
			
			//getting the deatil from currentDriver
			
			driverHistory currentDriverhistory = driverHistoryDetail.get(name);
			
			//singleTrip singletrip = new singleTrip(startTime , endTime ,miles );
			
			double totalDistanceAddingCurrent = currentDriverhistory.getCurrentDistanceSoFar()
					                             + Double.parseDouble(miles);
			double totalTimeAddingCurrent = currentDriverhistory.getTotalTimeSoFar() + getCurrentTripMinutes(startTime, endTime);
			
			double currentAverageSpeed = Math.round(totalDistanceAddingCurrent / totalTimeAddingCurrent ) * 60;
			
			
			//this check the requirement for the speed 
			if(currentAverageSpeed >= 5 && currentAverageSpeed <= 100 ){
				currentDriverhistory.setCurrentDistanceSoFar(totalDistanceAddingCurrent);
				currentDriverhistory.setTotalTimeSoFar(totalTimeAddingCurrent);
				
			}
				
		}
		
	}
	
	
	
	
	// printing the summary in file
	
	public static void fileWriter() throws IOException{
		
		
		
		FileWriter writer = new FileWriter("output.txt", true);
		BufferedWriter bw = new BufferedWriter(writer);
		
		List<driverHistory>  listDriverHistory = new ArrayList(driverHistoryDetail.values());
		listDriverHistory.sort((h1, h2) -> Double.valueOf(h2.getCurrentDistanceSoFar()).compareTo(Double.valueOf(h1.getCurrentDistanceSoFar())));
		
		for(driverHistory driverhistory : listDriverHistory){
		   
			
			
			double currentAverageSpeed = Math.round((driverhistory.getCurrentDistanceSoFar() / driverhistory.getTotalTimeSoFar() ) * 60);
			
			
			String driverNameWithDistance = driverhistory.getDriverName() + ":" +
					Math.round(driverhistory.getCurrentDistanceSoFar()) + " miles";
			String averageSpeed = "";
			if (Math.round(driverhistory.getCurrentDistanceSoFar()) == 0) {
				averageSpeed = "";
			} else {
				averageSpeed = " @ " + currentAverageSpeed + " mph";
			}

			bw.write(driverNameWithDistance + averageSpeed + System.lineSeparator());
			_result.add(driverNameWithDistance + averageSpeed);

		}
		
		
		bw.close();
		
	}
	


	// this is adding the driver name and checking if the driver is already added or not
	// then setting the name of driver to the driver history
	public static void addDriver(String driverName){
		
		if(driverHistoryDetail.get(driverName) == null){
			driverHistory driverhistory = new driverHistory();
			driverhistory.setDriverName(driverName);
			
			driverHistoryDetail.put(driverName, driverhistory);
			
		}
		
	}
	
	
	// get the current trip minutes 
public static double getCurrentTripMinutes(String startTime , String endTime){
		
		
		String [] currentStartTime = startTime.split(":");
		String [] currentEndTime = endTime.split(":");
		double currentTripTime = 0;
		
		try{
			if(Double.parseDouble(currentEndTime[0]) >= Double.parseDouble(currentStartTime[0])){
				
				currentTripTime = (((Double.parseDouble(currentEndTime[0]) 
						         - Double.parseDouble(currentStartTime[0]))*60 )
						         + Math.abs(Double.parseDouble(currentEndTime[1]) 
						        		 - Double.parseDouble(currentStartTime[1])));
				
				
				
			}
			else{
				_result.add("Invalid time error");
				
			}
		}
		catch (NumberFormatException e){
			throw e;
		}
		return currentTripTime;
		
		
	}




}
