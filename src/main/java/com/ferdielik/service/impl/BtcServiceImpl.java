package com.ferdielik.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferdielik.btc.BtcParser;
import com.ferdielik.entity.Btc;
import com.ferdielik.service.BtcService;

/**
 * Created by stuff on 7/18/2017.
 */
@Service
@Transactional
public class BtcServiceImpl implements BtcService
{
    private static String FILE_PATH = "/tmp/btc";

    @Autowired
    private BtcParser btcParser;

    @Override
    public void parse()
    {
        try
        {
            Btc parse = btcParser.build();
            buildDiffs(parse);

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(FILE_PATH), parse);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Btc get()
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(FILE_PATH), Btc.class);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private void buildDiffs(Btc btc)
    {
        addDiff(btc, "btcTurk", btc.getBtcTurk());
        addDiff(btc, "koinim", btc.getKoinim());
        addDiff(btc, "paribu", btc.getParibu());
    }

    private void addDiff(Btc btc, String label, double btcTurk)
    {
        double usdVal = btc.getBitstamp() * btc.getDollar();
        btc.getDiffs().put(label, btcTurk - usdVal);
    }

}
