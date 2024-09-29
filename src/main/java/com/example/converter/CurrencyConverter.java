package com.example.converter;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import com.google.gson.Gson;

public class CurrencyConverter {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/USD";
    private static final String BRL_URL = "https://api.exchangerate-api.com/v4/latest/BRL";
    public static Rates getExchangeRates(String baseCurrency) throws Exception {
        String url = baseCurrency.equals("BRL") ? BRL_URL : API_URL;
        HttpResponse<JsonNode> response = Unirest.get(url).asJson();

        if (response.getStatus() != 200) {
            throw new RuntimeException("Falha na requisição: "+ response.getStatus());
        }
        Gson gson = new Gson();
        return gson.fromJson(response.getBody().toString(), Rates.class);
    }
}
