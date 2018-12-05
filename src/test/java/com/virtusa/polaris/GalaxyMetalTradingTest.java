package com.virtusa.polaris;

import com.virtusa.polaris.exception.GalaxyTradeException;
import com.virtusa.polaris.metalstrading.GalaxyMetalTrading;
import com.virtusa.polaris.metalstrading.RateCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * test cases for galaxy metal trading class
 *
 * @author Sachin Murthy
 */
public class GalaxyMetalTradingTest {

    private static final Logger LOGGER = LogManager.getLogger(GalaxyMetalTradingTest.class);

    @Rule
    public ExpectedException expException = ExpectedException.none();

    @BeforeClass
    public static void setUp() {
        // set up code goes here
        LOGGER.info("Initial data set up");
        RateCalculator.setMetalRateMap(TestData.getMetalRateMap());
        RateCalculator.setRomanEquivalentMetalMap(TestData.getRomanEquivalentMetalMap());
    }

    @AfterClass
    public static void tearDown() {
        // clean up code goes here
        LOGGER.info("After test data clean up");
    }

    @Test
    public void testQueryRequestQuestionFormat1WithOutMetal() {

        GalaxyMetalTrading galaxyMetal = new GalaxyMetalTrading();
        long output = galaxyMetal
                .saleQuery("how much is pish tegj glob glob ?");
        Assert.assertEquals(42, output);
    }

    @Test
    public void testQueryRequestQuestionFormat2WithMetal(){

        GalaxyMetalTrading galaxyMetal = new GalaxyMetalTrading();
        long output1 = galaxyMetal
                .saleQuery("how many Credits is glob prok silver ?");
        Assert.assertEquals(68, output1);

        long output2 = galaxyMetal
                .saleQuery("how many Credits is glob prok iron ?");
        Assert.assertEquals(782, output2);

        long output3 = galaxyMetal
                .saleQuery("how many Credits is glob prok gold ?");
        Assert.assertEquals(57800, output3);

    }

    @Test
    public void testQueryRequestWrongQuestion() {

        GalaxyMetalTrading galaxyMetal = new GalaxyMetalTrading();
       long output = galaxyMetal
                .saleQuery("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

        Assert.assertEquals(400, output);
    }

    @Test
    public void testRegularExpression() {

        final Pattern pattern = Pattern.compile("how much is(.+?)\\?");
        final Matcher matcher = pattern
                .matcher("how much is pish tegj glob glob ?");
        matcher.find();
    }

}
