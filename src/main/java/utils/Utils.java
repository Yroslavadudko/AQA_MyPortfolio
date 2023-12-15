package utils;

import java.util.List;
import java.util.Properties;

public class Utils {

    public static String getMarket(String market) {
        Properties prop = PropertyFileReader.getProperties("src/test/resources/data/markets.properties");
        return prop.getProperty(market);
    }

    public static boolean isProductPriceSorted(List<String> prices, boolean ascending) {
        for (int i = 0; i < prices.size() - 1; i++) {
            double currentVal = extractAndParsePrice(prices.get(i));
            double nextVal = extractAndParsePrice(prices.get(i + 1));

            if ((ascending && currentVal > nextVal) || (!ascending && currentVal < nextVal)) {
                return false;
            }
        }
        return true;
    }

    private static double extractAndParsePrice(String price) {
        return Double.parseDouble(price.replace("$", ""));
    }

    public static boolean isProductNamesSorted(List<String> phrases, boolean ascending) {
        // Check if the list is empty or has only one element
        if (phrases == null || phrases.size() <= 1) {
            return true;
        }
        // Iterate through the list and compare each string with its adjacent string
        for (int i = 0; i < phrases.size() - 1; i++) {
            String currentPhrase = phrases.get(i);
            String nextPhrase = phrases.get(i + 1);

            // Compare the current string with the next string based on the specified order
            int comparisonResult = ascending ? currentPhrase.compareTo(nextPhrase) : nextPhrase.compareTo(currentPhrase);

            // If the comparison result is not as expected, the list is not sorted
            if (comparisonResult > 0) {
                return false;
            }
        }
        // If all comparisons passed, the list is sorted
        return true;
    }
}
