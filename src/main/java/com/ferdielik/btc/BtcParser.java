package com.ferdielik.btc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.ferdielik.entity.Btc;
import com.ferdielik.util.HttpUtil;

@Component
public class BtcParser
{

    public Btc build() throws Exception
    {
        Btc btc = new Btc();
        btc.setDollar(parseDollar());

        btc.setParibu(parseParibu());
        btc.setBtcTurk(getValue(Constants.BTC_API, "last"));
        btc.setBitstamp(getValue(Constants.BITSTAMP_API, "last"));
        btc.setKoinim(getValue(Constants.KOINIM_API, "last_order"));

        btc.setDate(new Date());
        return btc;
    }

    private double parseDollar() throws Exception
    {
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", Constants.USER_AGENT);
        headers.put("Referer", "https://www.garanti.com.tr/tr/");
        String response = HttpUtil.get("https://www.garanti.com.tr/proxy/asp/xml/icpiyasaX.xml", headers);


        Pattern p = Pattern.compile("<DESC>USD</DESC><LAST>[0-9],[0-9]+</LAST>"); //
        Matcher m = p.matcher(response);
        if (m.find())
        {
            String val = m.group().replaceAll("<DESC>USD</DESC><LAST>|</LAST>", "").replace(",", ".");
            return Double.parseDouble(val);
        }
        return 0;
    }

    private double parseParibu()
    {
        try
        {
            JSONObject en = new JSONObject(HttpUtil.get(Constants.PARIBU_API, null));
            return en.getJSONObject("BTC_TL").getDouble("last");
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    private double getValue(String url, String key)
    {
        try
        {
            JSONObject en = new JSONObject(HttpUtil.get(url, null));
            return en.getDouble(key);
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}
