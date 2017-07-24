package com.ferdielik.util;

import java.util.List;

import com.ferdielik.btc.BtcParser;
import com.ferdielik.entity.Announce;
import com.ferdielik.entity.Btc;

/**
 * Created by ferdielik on 07/04/2017.
 */
public class App
{
    public static void main(String[] args) throws Exception
    {
        BtcParser btcParser = new BtcParser();
        Btc btc = btcParser.build();
        System.out.println("done." + btc);
    }
}
