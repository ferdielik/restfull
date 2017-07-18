package com.ferdielik.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferdielik.btc.BtcParser;
import com.ferdielik.dao.BtcDAO;
import com.ferdielik.entity.Btc;
import com.ferdielik.service.BtcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Created by stuff on 7/18/2017.
 */
@Service
public class BtcServiceImpl implements BtcService {

    private static String FILE_PATH = "/tmp/btc";
    
    @Autowired
    private BtcParser btcParser;

    @Autowired
    private BtcDAO btcDAO;

    public static void main(String[] args) throws Exception {
        new BtcServiceImpl().run();
    }

    void run() throws Exception {
        btcParser = new BtcParser();
        parse();
        System.out.println(get());
    }


    @Override
    public void parse() throws IOException {
        Btc parse = btcParser.parse();
        ObjectMapper mapper = new ObjectMapper();
        buildDiffs(parse);
        mapper.writeValue(new File(FILE_PATH), parse);
    }

    private void buildDiffs(Btc btc) {

        addDiff(btc, "btcTurk", btc.getBtcTurk());
        addDiff(btc, "koinim", btc.getKoinim());
        addDiff(btc, "paribu", btc.getParibu());

    }

    private void addDiff(Btc btc, String label, double btcTurk) {
        double usdVal = btc.getBitstamp() * btc.getDollar();
        btc.getDiffs().put(label, usdVal - btcTurk);
    }

    @Override
    public void move() throws IOException {
        btcDAO.saveOrUpdate(get());
    }

    @Override
    public Btc get() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(FILE_PATH), Btc.class);
    }
}
