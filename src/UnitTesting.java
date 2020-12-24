import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class UnitTesting {

	
	// when there are only drivers , no trip
	@Test
	public void driverWithoutTrip() throws IOException {
		String filename = "data/input.txt";
		List<String> expectedResult = new ArrayList<>();
		expectedResult.add("jack:0 miles");
		expectedResult.add("rose:0 miles");
		expectedResult.add("darwin:0 miles");
		Assert.assertEquals(expectedResult, trackDrivingHistory.processingDiverInfo(filename));
		
	}
	
	/*
	 * driver with entry but has no trip record
	 */
	@Test
	public void driverWithEntryNoTrip() throws IOException {
		String filename = "data/input2.txt";
		List<String> expectedResult = new ArrayList<>();
		expectedResult.add("Lauren:42 miles @ 34.0 mph");
		expectedResult.add("Dan:39 miles @ 47.0 mph");
		expectedResult.add("Kumi:0 miles");
		Assert.assertEquals(expectedResult, trackDrivingHistory.processingDiverInfo(filename));
		
	}
	
	/*
	 * single driver has multiple trip 
	 * 
	 */
	
	@Test
	public void singleDriverMultipleTrip() throws IOException {
		String filename = "data/input3.txt";
		List<String> expectedResult = new ArrayList<>();
		expectedResult.add("Lauren:42 miles @ 34.0 mph");
		expectedResult.add("Dan:39 miles @ 47.0 mph");
		expectedResult.add("Kumi:0 miles");
		Assert.assertEquals(expectedResult, trackDrivingHistory.processingDiverInfo(filename));
		
	}
	
	//speed are out of bound
	@Test
	public void speedOutOfBound() throws IOException {
		String filename = "data/input4.txt";
		List<String> expectedResult = new ArrayList<>();
		expectedResult.add("Chicago:32 miles @ 32.0 mph");
		expectedResult.add("Austin:0 miles");
		Assert.assertEquals(expectedResult, trackDrivingHistory.processingDiverInfo(filename));
		
	}
	
	// check time ie start time should be always behind the end time.
	
	@Test
	public void inValidTime() throws IOException {
		String filename = "data/input5.txt";
		List<String> expectedResult = new ArrayList<>();
		expectedResult.add("Invalid time error");
		expectedResult.add("Chicago:32 miles @ 32.0 mph");
		expectedResult.add("Austin:0 miles");
		Assert.assertEquals(expectedResult, trackDrivingHistory.processingDiverInfo(filename));
		
	}
	

	

}
