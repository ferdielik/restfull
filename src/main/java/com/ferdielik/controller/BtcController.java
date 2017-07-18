package com.ferdielik.controller;

import com.ferdielik.service.BtcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by ferdielik on 29/01/2017.
 */
@Controller
@RequestMapping("/rest/btc")
public class BtcController {
    @Autowired
    private BtcService btcService;

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity get() throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(btcService.get());
    }

    @ResponseBody
    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public ResponseEntity move() throws Exception {
        btcService.parse();
        return ResponseEntity.ok().body(true);
    }

    @ResponseBody
    @RequestMapping(value = "/parse", method = RequestMethod.POST)
    public ResponseEntity parse() throws Exception {
        btcService.parse();
        return ResponseEntity.ok().body(true);
    }
}
