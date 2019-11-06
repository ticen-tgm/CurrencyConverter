package data;

import java.util.List;

/**
 * Strategy interface
 */

public interface Data {

    /**
     * takes the currency rate/course from either the offline or online database.
     * @param betrag is the desired amount of money you want to know the change course of
     * @param currency the base currency
     * @param targetcurrencies the target currency
     * @return sends the produced return values to the display function
     */
    public String get_rates(String betrag, String currency, List<String> targetcurrencies);

    /**
     * the method that displays the file on the output window
     * @param betrag is the desired amount of money you want to know the change course of
     * @param currency the base currency
     * @param calculatedrates the target currency
     * @param date the date of today (after "Stand: "
     * @return returns a html formatted string
     */
    default String display(String betrag, String currency, List<String> calculatedrates, String date) {
        String s = "<p>" + betrag + " " + currency + " entsprechen</p>";
        for (String calculatedrate : calculatedrates) {
            s += "<ul><li> " + calculatedrate + " </li></ul>";
        }
        s += "<p>Stand: " + date + "</p>";
        return s;
    }

}
