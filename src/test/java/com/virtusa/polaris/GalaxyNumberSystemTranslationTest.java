package com.virtusa.polaris;

import com.virtusa.polaris.metalstrading.GalaxyNumberSystemTranslation;
import org.junit.Assert;
import org.junit.Test;

/**
 * test cases for galaxy number system conversion.
 * 
 * @author Sachin Murthy
 * 
 */

public class GalaxyNumberSystemTranslationTest {

	@Test
	public void convertRomanToIntegerTest() {

		Assert.assertEquals(10,
				GalaxyNumberSystemTranslation.convertRomanNumberToInteger("X"));
		Assert.assertEquals(1984,
				GalaxyNumberSystemTranslation.convertRomanNumberToInteger("MCMLXXXIV"));
		Assert.assertEquals(1953,
				GalaxyNumberSystemTranslation.convertRomanNumberToInteger("MCMLIII"));
		Assert.assertEquals(3303,
				GalaxyNumberSystemTranslation.convertRomanNumberToInteger("MMMCCCIII"));
		Assert.assertEquals(1995,
				GalaxyNumberSystemTranslation.convertRomanNumberToInteger("MCMXCV"));
		Assert.assertEquals(17,
				GalaxyNumberSystemTranslation.convertRomanNumberToInteger("XVII"));

	}
}
