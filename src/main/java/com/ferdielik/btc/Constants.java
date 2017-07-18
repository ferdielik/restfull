package com.ferdielik.btc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by stuff on 7/18/2017.
 */
public interface Constants {

    String BTC_TURK = "btcTurk";
    String BITSTAMP = "bitstamp";
    String KOINIM = "koinim";
    String PARIBU = "paribu";

    Map<String, String> URLS = new HashMap<String, String>() {{
        put(BTC_TURK, "https://www.btcturk.com/api/ticker");
        put(BITSTAMP, "https://www.bitstamp.net/api/v2/ticker/btcusd");
    }};
}
