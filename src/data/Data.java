package data;

import java.util.List;

public interface Data {
    public String get_rates(String betrag, String currency, List<String> targetcurrencies);

    default String display(String betrag, String currency, List<String> calculatedrates, String date) {
        String s = "<p>" + betrag + " " + currency + " entsprechen</p>";
        for (String calculatedrate : calculatedrates) {
            s += "<ul><li> " + calculatedrate + " </li></ul>";
        }
        s += "<p>Stand: " + date + "</p>";
        return s;
    }

}
