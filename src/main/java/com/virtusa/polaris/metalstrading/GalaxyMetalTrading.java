package com.virtusa.polaris.metalstrading;

import com.virtusa.polaris.exception.GalaxyTradeException;
import com.virtusa.polaris.utils.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation class for galaxy metal trading.
 *
 * @author Sachin Murthy
 */
@Service
public class GalaxyMetalTrading {

    private static final Logger LOGGER = LogManager.getLogger(GalaxyMetalTrading.class);
    private final Map<String, String> quantityMap = RateCalculator.romanEquivalentMetalMap;
    private final Map<String[], Float> metalRateMap = RateCalculator.metalRateMap;
    private final String metal = AppConstants.METAL.getValue();
    private final String metalQuantity = AppConstants.METALQUANTITY.getValue();

    /**
     * Method accept input query for metal and return rate as per rate.
     * rate calculator.
     *
     * @param metalQuotation
     * @return long
     */
    public long saleQuery(String metalQuotation) {
        // extract question values {quotation+metal} from English statement.
        try{
            final String quotationText = extractQueryText(metalQuotation);
            // separate out token as quotation words list and metal list with in map
            final Map<String, List<String>> grpToken = separateOutTokens(Arrays
                    .asList(quotationText.trim().split(AppConstants.SPACE.getValue()))); // create token out of
            // those question values
            // convert input quotation words into roman equivalent and later into numbers.
            final long qty = inputQueryProcessor(grpToken);

            // calculate total metal value as metal rate x qty using rate
            // calculation matrix.
            if (grpToken.get(metal).size() != 0) {
                final long metalValue = calculateMetalValue(grpToken, qty);
                LOGGER.info(getDisplayMessage(metalQuotation, quotationText + "is " + metalValue + " Credits"));
                return metalValue;
            } else { // No metal quote, just quotation words into roman equivalent.
                LOGGER.info(getDisplayMessage(metalQuotation, quotationText + "is " + qty));
                return qty;
            }

        }catch (GalaxyTradeException e){
            LOGGER.error(getDisplayMessage(metalQuotation, "I have no idea what you are talking about"));
        }
        return 400;
    }

    /**
     * Use Rate Calculation matrix and find metal value.
     *
     * @param grpTokens - quotation words list + metal
     * @param qty       - input request
     * @return long
     */
    private long calculateMetalValue(Map<String, List<String>> grpTokens,
                                     long qty) {

        // extract name of metal
        final List<String> metalTokens = grpTokens.get(metal);
        float rate = 0;
        // roman number out of those quotation words
        String romanNumbers = Optional.empty().toString();
        // loop thru metal rate map.
        for (Map.Entry<String[], Float> entry : metalRateMap.entrySet()) {
            // metal is matched with rate matrix
            if (Arrays.asList(entry.getKey()).contains(metalTokens.get(0))) {

                // get quotation words token out of rate matrix
                List<String> ratestatements = Arrays.asList(entry.getKey());
                // rate statements into roman equivalent
                romanNumbers = prepareRomanNumer(ratestatements);
                // rate value of this metal
                rate = entry.getValue();
                break;
            }
        }

        // convert roman number into numeric form.

        final float numericValue = GalaxyNumberSystemTranslation
                .convertRomanNumberToInteger(romanNumbers);
        final float metalPrize = (rate / numericValue) * qty;

        return (long) metalPrize;
    }

    /**
     * For Processing input queries.
     *
     * @param grpTokens
     * @return long
     */
    private long inputQueryProcessor(Map<String, List<String>> grpTokens) {

        // get list of input quotations
        final List<String> quotationList = grpTokens.get(metalQuantity);
        // convert quotation into roman number
        final String romanNumber = prepareRomanNumer(quotationList);
        // numeric value of quotation list
        return GalaxyNumberSystemTranslation
                .convertRomanNumberToInteger(romanNumber);
    }

    /**
     * get roman number equivalent to quotation.
     *
     * @param @qtyTokens
     * @return string
     */
    private String prepareRomanNumer(List<String> qtyTokens) {
        final StringBuilder romanNumbers = new StringBuilder();
        for (String token : qtyTokens) {
            if (quantityMap.get(token) != null) {
                romanNumbers.append(quantityMap.get(token));
            }
        }
        return romanNumbers.toString();
    }

    /**
     * return map having two array list 1. metal --> name of metal 2. Query -->.
     * list of quotations  along with qty
     *
     * @param tokens
     * @return map<string                               ,                               list                               <                               string>>
     */
    private Map<String, List<String>> separateOutTokens(List<String> tokens) {
        final Map<String, List<String>> groupTokens = new HashMap<>();

        final List<String> quantity = new ArrayList<>();
        final List<String> metal = new ArrayList<>();
        // group into two array list as metal & quotation list
        for (String token : tokens) {
            if (quantityMap.get(token) != null) {
                quantity.add(token);
            } else {
                metal.add(token);
            }
        }
        groupTokens.put(this.metal, metal);
        groupTokens.put(metalQuantity, quantity);
        return groupTokens;
    }

    /**
     * use regular expression to extract quotation text out of question.
     *
     * @param @quotation
     * @return
     */
    private String extractQueryText(String query) throws GalaxyTradeException {
        // query format 1 -> for eg: how much is pish tegj glob glob ?
        // query format 2 -> for eg: how many Credits is glob prok silver ?
        final Pattern queryFormat1 = Pattern.compile("(how much is|how many Credits is)(.+?)\\?");
        String queryValue = getQueryValue(queryFormat1, query);
        if (queryValue == null) {
            throw new GalaxyTradeException("I have no idea what you are talking about","400");
        }
        return queryValue;
    }

    public String getDisplayMessage(String query, String queryResponse) {
        return "\n Query : " + query + " \n Response : " + queryResponse + " \n";
    }

    /**
     * returns matched expression.
     *
     * @param inputPattern
     * @param inputValue
     * @return
     */
    private String getQueryValue(final Pattern inputPattern, final String inputValue) {
        String questionValue = null;
        final Matcher matcher = inputPattern.matcher(inputValue);
        if (matcher.find()) {
            questionValue = matcher.group(2);
        }
        return questionValue;
    }
}
