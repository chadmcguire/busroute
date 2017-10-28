package calculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class OnTimeValenceCalculator {
	
	public static void main(String[] args){
		OnTimeValenceCalculator calc = new OnTimeValenceCalculator();
		ArrayList<String> importList = calc.importCSV(args[0]);
		ArrayList<BusRoute> routes = calc.parseCSVData(importList);
		HashMap<Integer, ArrayList<BusRoute>> sortedList = calc.sortRoutes(routes);
		
		routes.clear();
		for(Integer routeNum :sortedList.keySet()){
			routes.addAll(calc.calculateValence(sortedList.get(routeNum)));
		}
		
		calc.exportToCSV("/home/chad/Downloads/BusValence.csv", routes);
		
		System.out.println("DONE!");
	}
	
	/**
	 * Imports the CSV file and parses it into a list of strings for the given filename
	 * @param fileName
	 * @return
	 */
	protected ArrayList<String> importCSV(String fileName){
		ArrayList<String> routeList = new ArrayList<>();
		BufferedReader buffer = null;
		try {
			String line = "";
			buffer = new BufferedReader(new FileReader(fileName));
			while ((line = buffer.readLine()) != null){
				routeList.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return routeList;
	}
	
	/**
	 * Writes a CSV file given a list of {@link BusRoute} objects
	 * @param fileName
	 * @param routes
	 */
	protected void exportToCSV(String fileName, ArrayList<BusRoute> routes){
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(fileName));
			bw.write("Route Number,Valence");
			bw.write("\n");
			for (BusRoute busRoute : routes) {
				bw.write(busRoute.getRouteNumber() + "," + busRoute.getValence());
				bw.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Parses the CSV data and converts it into {@link BusRoute} objects
	 * @param stringList
	 * @return
	 */
	protected ArrayList<BusRoute> parseCSVData(ArrayList<String> stringList){
		ArrayList<BusRoute> routeList = new ArrayList<>();
		for (String line : stringList) {
			String[] strings = line.split(",");
			BusRoute route = null;
			try {
				route = new BusRoute(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
				routeList.add(route);
			} catch (NumberFormatException e) {
				System.err.println("This bus route doesn't contain numbers. " + strings[0]);
			}
		}
		return routeList;
	}
	
	/**
	 * Combines the list of bus routes by route number.  All routes with the same route number will be
	 * combined into an {@link ArrayList} of {@link BusRoute} objects with the key being the route number
	 * @param routeList
	 * @return
	 */
	protected HashMap<Integer, ArrayList<BusRoute>> sortRoutes(ArrayList<BusRoute> routeList){
		HashMap<Integer, ArrayList<BusRoute>> sortedList = new HashMap<>();
		for (BusRoute busRoute : routeList) {
			if(sortedList.containsKey(busRoute.getRouteNumber())){
				sortedList.get(busRoute.getRouteNumber()).add(busRoute);
			} else {
				ArrayList<BusRoute> shortList = new ArrayList<>();
				shortList.add(busRoute);
				sortedList.put(busRoute.getRouteNumber(), shortList);
			}
		}
		return sortedList;
	}
	
	/**
	 * Calculates the expected value which is the average early/late time for the given list
	 * @param routeList
	 * @return
	 */
	protected Double calculateExpected(ArrayList<BusRoute> routeList){
		Double totalMinutes = 0d;
		for (BusRoute busRoute : routeList) {
			totalMinutes += busRoute.getMinutesEarlyLate();
		}
		return totalMinutes/routeList.size();
	}
	
	/**
	 * Calculates the valence for each route
	 * @param routeList
	 * @return
	 */
	protected ArrayList<BusRoute> calculateValence(ArrayList<BusRoute> routeList){
		double expected = calculateExpected(routeList);
		for (BusRoute busRoute : routeList) {
			busRoute.calculate(expected);
		}
		return routeList;
	}

		

}
