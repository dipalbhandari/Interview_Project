import java.io.IOException;

public class Driver {

	public static void main(String[] args) throws IOException {
		
		
		
		// this run when you use command line 
		if(args.length>=1){
			
			//trackDrivingHistory.processingDiverInfo(args[0]);
		}
		else{
			String filename = "data/input5.txt";
			trackDrivingHistory.processingDiverInfo(filename);
		}

	}

	

}
