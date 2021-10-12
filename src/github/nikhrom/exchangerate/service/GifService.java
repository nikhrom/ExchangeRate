package github.nikhrom.exchangerate.service;


import github.nikhrom.exchangerate.util.PropertiesUtil;

import lombok.*;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class GifService {
    static final GifService INSTANCE = new GifService();
    static final CurrencyService CURRENCY_SERVICE = CurrencyService.getInstance();

    static final String API_KEY = "giphy.api_key";
    static final String GIF_URL_KEY = "giphy.gif.url";
    static final String GIPHY_TAG_RATE_HIGHER_KEY = "giphy.tag.rate_higher";
    static final String GIPHY_TAG_RATE_LOWER_KEY = "giphy.tag.rate_lower";


    public String getGifUrlByRateLowerTag() {
        return getGifUrlByTag(PropertiesUtil.get(GIPHY_TAG_RATE_LOWER_KEY));
    }

    public String getGifUrlByRateHigherTag() {
        return getGifUrlByTag(PropertiesUtil.get(GIPHY_TAG_RATE_HIGHER_KEY));
    }

    @SneakyThrows
    public String getGifUrlByTag(String tag) {
        var body = HttpClient.newHttpClient()
                .send(buildRequestByTag(tag), HttpResponse.BodyHandlers.ofString())
                .body();

        var jsonObject = new JSONObject(body);


        return (String) jsonObject.getJSONObject("data")
                .getJSONObject("images")
                .getJSONObject("fixed_height_small")
                .get("url");
    }

    private HttpRequest buildRequestByTag(String tag) {
        URI uri = URI.create(String.format("%s?api_key=%s&tag=%s",
                PropertiesUtil.get(GIF_URL_KEY),
                PropertiesUtil.get(API_KEY),
                tag)
        );


        return HttpRequest.newBuilder(uri)
                .GET()
                .build();
    }


    public static GifService getInstance() {
        return INSTANCE;
    }
}
