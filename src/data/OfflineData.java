package data;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The offline data strategy
 */
public class OfflineData implements Data {

    /**
     * takes the currency rate/course from the offline database.
     * @param betrag is the desired amount of money you want to know the change course of
     * @param currency the base currency
     * @param targetcurrencies the target currency
     * @return sends the produced return values to the display function
     */
    @Override
    public String get_rates(String betrag, String currency, List<String> targetcurrencies) {
        String api = null;
        try {
            api = new String(Files.readAllBytes(Paths.get("src/resources/OfflineData.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject obj = new JSONObject(api);
        ArrayList<String> calculatedrates = new ArrayList<>();
        if (currency.equals("EUR")) {
            for (String targetcurrency : targetcurrencies) {
                float rate = obj.getJSONObject("rates").getFloat(targetcurrency);
                calculatedrates.add((Float.parseFloat(betrag) * rate) + " " + targetcurrency + " (Kurs: " + rate + ")");
            }
        } else {
            float currencyrate = obj.getJSONObject("rates").getFloat(currency);
            for (String targetcurrency : targetcurrencies) {
                float rate = obj.getJSONObject("rates").getFloat(targetcurrency);
                calculatedrates.add((Float.parseFloat(betrag) / currencyrate * rate) + " " + targetcurrency + " (Kurs: " + rate / currencyrate + ")");
            }
        }
        return Data.super.display(betrag, currency, calculatedrates, obj.get("date").toString());
    }
}
