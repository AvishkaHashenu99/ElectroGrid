package util;
import com.Model.Power_Consumption;

public class CalcUtility {
	public static int[] calculateUsedUnits(int currentReading, String actno, String date) {
		
		// getReading[0] - carry the state of the database, getReading[1] - carry the previous reading value
		int[] getReading = Power_Consumption.readPreviousReading(date, actno);
		
		if(getReading[0]<0) {
			return getReading;
		}
		
		getReading[1] =  currentReading - getReading[1];
		
		return getReading;
	}
}
