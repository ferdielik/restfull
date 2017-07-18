package com.ferdielik.btc;

import com.ferdielik.entity.Btc;
import com.ferdielik.util.HttpUtil;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BtcParser {

    private static final String BTC_API = "https://www.btcturk.com/api/ticker";
    private static final String BITSTAMP_API = "https://www.bitstamp.net/api/v2/ticker/btcusd";
    private static final String KOINIM_API = "https://koinim.com/ticker/";
    private static final String PARIBU_API = "";


    public Btc parse() {
        try {
            return build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private double getValue(String url, String key) throws Exception {
        String entity = HttpUtil.get(url, null);
        JSONObject en = new JSONObject(entity);
        return en.getDouble(key);
    }

    private Btc build() throws Exception {
        Btc btc = new Btc();

        btc.setDollar(parseDollar());
        btc.setKoinim(parseKoinim());
        btc.setParibu(parseParibu());

        btc.setBtcTurk(getValue(BTC_API, "last"));
        btc.setBitstamp(getValue(BITSTAMP_API, "last"));
        return btc;
    }

    double parseDollar() throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36");
        headers.put("Referer", "https://www.garanti.com.tr/tr/");
        String response = HttpUtil.get("https://www.garanti.com.tr/proxy/asp/xml/icpiyasaX.xml", headers);


        Pattern p = Pattern.compile("<DESC>USD</DESC><LAST>[0-9],[0-9]+</LAST>"); //
        Matcher m = p.matcher(response);
        if (m.find()) {
            String val = m.group().replaceAll("<DESC>USD</DESC><LAST>|</LAST>", "").replace(",", ".");
            return Double.parseDouble(val);
        }
        return 0;
    }

    double parseKoinim() throws Exception {
        return getValue(KOINIM_API, "last_order");

    }

    double parseParibu() throws Exception {
        Document doc = Jsoup.connect("https://www.paribu.com")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                .get();
        String html = doc.getElementsByTag("script").get(1).html();
        Pattern p = Pattern.compile("\"lst\":([0-9]+),"); //
        Matcher m = p.matcher(html);
        if (m.find()) {
            String val = m.group().replaceAll("\\D+", "");
            return Double.parseDouble(val);
        }
        return 0;
    }
}
