package data;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.*;
public class OnlineData implements Data{
    @Override
    public String get_rates(String betrag, String currency, List<String> targetcurrencies) {
        URL api = null;
        JSONTokener tokener = null;
        try {
            api = new URL("https://api.exchangeratesapi.io/latest?base=" + currency);
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        try {
            tokener = new JSONTokener(api.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject obj = new JSONObject(tokener);
        ArrayList<String> calculatedrates = new ArrayList<>();
        for (String targetcurrency : targetcurrencies) {
            float rate = obj.getJSONObject("rates").getFloat(targetcurrency);
            calculatedrates.add((Float.parseFloat(betrag) * rate) + " " + targetcurrency + " (Kurs: " + rate+ ")");
        }
        return Data.super.display(betrag, currency, calculatedrates, obj.get("date").toString());
    }
}
