package github.nikhrom.exchangerate.service;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class GifService {
    static final GifService INSTANCE = new GifService();

    static final String API_KEY = "giphy.api_key";
    static final String GIF_URL_KEY = "giphy.gif.url";
    static final String GIPHY_TAG_CURRENCY_ABOVE_KEY = "giphy.tag.currency_above";
    static final String GIPHY_TAG_CURRENCY_BELOW_KEY = "giphy.tag.currency_below";

}
