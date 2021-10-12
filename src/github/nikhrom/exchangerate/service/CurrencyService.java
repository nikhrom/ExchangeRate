package github.nikhrom.exchangerate.service;


import github.nikhrom.exchangerate.dto.Currency;
import github.nikhrom.exchangerate.util.PropertiesUtil;

import lombok.*;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class CurrencyService {

    static final CurrencyService INSTANCE = new CurrencyService();

    static final String CURRENCY_NAME_KEY = "currency.name";
    static final String APP_ID_KEY = "rates.app_id";
    static final String RATES_URL_KEY = "rates.url";
    static final String RATES_URL_HISTORICAL_KEY = "rates.url.historical";



    public static CurrencyService getInstance(){
        return INSTANCE;
    }

    public boolean rateIsHigherThanYesterday(Currency currency){
        return rateIsHigherThanYesterday(currency,
                Currency.valueOf(PropertiesUtil.get(CURRENCY_NAME_KEY)));
    }

    public boolean rateIsHigherThanYesterday(Currency first, Currency second){
        return getExchangeRate(buildRequestForLatest(), first, second) <
                getExchangeRate(buildRequestForHistorical(), first, second);
    }

    @SneakyThrows
    private double getExchangeRate(HttpRequest request, Currency from, Currency to){
        String body = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString())
                .body();

        return getExchangeRateAgainstUsd(body, from) /
                getExchangeRateAgainstUsd(body, to);
    };


    private double getExchangeRateAgainstUsd(String jsonString, Currency currency) {
        JSONObject json = new JSONObject(jsonString);

        String rate = json.getJSONObject("rates")
                .get(currency.name()).toString();

        return Double.parseDouble(rate);
    }

    private HttpRequest buildRequestForLatest(){
        URI uri = URI.create(String.format("%s/latest.json?app_id=%s",
                PropertiesUtil.get(RATES_URL_KEY),
                PropertiesUtil.get(APP_ID_KEY)));

        return HttpRequest.newBuilder(uri)
                .GET()
                .build();
    };

    private HttpRequest buildRequestForHistorical(){
        LocalDate yesterdayDate = LocalDate.now().minusDays(1L);

        URI uri = URI.create(String.format("%s/%s.json?app_id=%s",
                PropertiesUtil.get(RATES_URL_HISTORICAL_KEY),
                yesterdayDate.toString(),
                PropertiesUtil.get(APP_ID_KEY)));

        return HttpRequest.newBuilder(uri).build();
    };

}
