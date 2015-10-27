package calculator;

/**
 * Class to hold bus route information
 * @author chad
 *
 */
public class BusRoute {
	
	private int routeNumber;
	private int numOfPassengers;
	private int minutesEarlyLate;
	private Double valence;
	
	public BusRoute(int routeNum, int numPassengers, int minutes){
		routeNumber = routeNum;
		numOfPassengers = numPassengers;
		minutesEarlyLate = minutes;
	}
	

	public int getRouteNumber() {
		return routeNumber;
	}
	public void setRouteNumber(int routeNumber) {
		this.routeNumber = routeNumber;
	}
	public int getNumOfPassengers() {
		return numOfPassengers;
	}
	public void setNumOfPassengers(int numOfPassengers) {
		this.numOfPassengers = numOfPassengers;
	}
	public int getMinutesEarlyLate() {
		return minutesEarlyLate;
	}
	public void setMinutesEarlyLate(int minutesEarlyLate) {
		this.minutesEarlyLate = minutesEarlyLate;
	}

	public Double getValence() {
		return valence;
	}

	public void setValence(Double valence) {
		this.valence = valence;
	}
	
	/**
	 * Calculates the valence for the given route based on the minutesEarlyLate value and the passed in
	 * expected value
	 * @param expected
	 */
	public void calculate(Double expected){
		setValence(-1000d*((getMinutesEarlyLate() - expected)/expected));
	}
	
	
	

}
