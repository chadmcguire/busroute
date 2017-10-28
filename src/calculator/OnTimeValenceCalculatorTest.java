package calculator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class OnTimeValenceCalculatorTest {

		
	@Test
	public void calculateTest(){
		BusRoute route = new BusRoute(30, 10, 23);
		Double expected = 5d;
		Double answer = -3600d;
		
		route.calculate(expected);
		assertEquals(answer, route.getValence(), answer/(10^6));
		
		expected = -5d;
		answer = 5600d;
		route.calculate(expected);
		assertEquals(answer, route.getValence(), answer/(10^6));
	}
	
	@Test
	public void sortRoutesTest(){
		ArrayList<BusRoute> routeList = new ArrayList<>();
		routeList.add(new BusRoute(1, 10, 5));
		routeList.add(new BusRoute(1, 13, -5));
		routeList.add(new BusRoute(1, 3, 15));
		routeList.add(new BusRoute(2, 10, 5));
		routeList.add(new BusRoute(2, 10, -2));
		routeList.add(new BusRoute(2, 11, -5));
		routeList.add(new BusRoute(3, 20, -4));
		routeList.add(new BusRoute(3, 10, 5));
		routeList.add(new BusRoute(1, 10, 5));
		routeList.add(new BusRoute(1, 10, 3));
		routeList.add(new BusRoute(1, 10, 9));
		
		OnTimeValenceCalculator calc = new OnTimeValenceCalculator();
		HashMap<Integer, ArrayList<BusRoute>> sortedList = calc.sortRoutes(routeList);
		
		//Should have 4 entries for route 1
		assertTrue(6 == sortedList.get(1).size());
		//Should have 3 entries for route 2
		assertTrue(3 == sortedList.get(2).size());
		//Should have 2 entries for route 3
		assertTrue(2 == sortedList.get(3).size());
	}
	
	@Test
	public void calculateExpectedTest(){
		ArrayList<BusRoute> routeList = new ArrayList<>();
		routeList.add(new BusRoute(1, 10, 5));
		routeList.add(new BusRoute(1, 13, -5));
		routeList.add(new BusRoute(1, 3, 15));
		
		Double expected = (5 -5 + 15)/3d;
		OnTimeValenceCalculator calc = new OnTimeValenceCalculator();
		
		assertEquals(expected, calc.calculateExpected(routeList));
		
	}

	@Test	
	public void sortRoutesTest(){
                ArrayList<BusRoute> routeList = new ArrayList<>();
                routeList.add(new BusRoute(3, 10, 5));
                routeList.add(new BusRoute(3, 13, -5));
                routeList.add(new BusRoute(3, 3, 15));
                routeList.add(new BusRoute(2, 10, 5));
                routeList.add(new BusRoute(1, 10, -2));
                routeList.add(new BusRoute(2, 11, -5));
                routeList.add(new BusRoute(2, 20, -4));
                routeList.add(new BusRoute(3, 10, 5));
                routeList.add(new BusRoute(1, 10, 5));
                routeList.add(new BusRoute(3, 10, 3));
                routeList.add(new BusRoute(1, 10, 9));

                OnTimeValenceCalculator calc = new OnTimeValenceCalculator();
                HashMap<Integer, ArrayList<BusRoute>> sortedList = calc.sortRoutes(routeList);

                //Should have 4 entries for route 1
                assertTrue(6 == sortedList.get(1).size());
                //Should have 3 entries for route 2
                assertTrue(3 == sortedList.get(2).size());
                //Should have 2 entries for route 3
                assertTrue(2 == sortedList.get(3).size());
        }	
	
	@Test
	public void calculateValenceTest(){
		ArrayList<BusRoute> routeList = new ArrayList<>();
		routeList.add(new BusRoute(1,10,23));
		routeList.add(new BusRoute(1, 10, 15));
		routeList.add(new BusRoute(1, 10, -5));
		
		OnTimeValenceCalculator calc = new OnTimeValenceCalculator();
		
		calc.calculateValence(routeList);
		
		//Route 1 = -1090.9090
		Double answer = -1090.9090d;
		assertEquals(answer, routeList.get(0).getValence(), 0.009);
		
		// Route 2 = -363.6363
		answer = -363.6363d;
		assertEquals(answer, routeList.get(1).getValence(),0.009);

		// Route 3 = 1454.5454
		answer = 1454.5454d;
		assertEquals(answer, routeList.get(2).getValence(), 0.009);
	}

}
