package calculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class OnTimeValenceCalculatorTest {

		
	@Test
	public void calculateValenceTest(){
		Double expected = 5d;
		Double observed = 23d;
		Double answer = -3600d;
		
		OnTimeValenceCalculator calc = new OnTimeValenceCalculator();
		assertEquals(answer, calc.calculateValence(observed, expected), answer/(10^6));
		
		expected = -5d;
		answer = 5600d;
		assertEquals(answer, calc.calculateValence(observed, expected), answer/(10^6));
	}

}
