package com.ferdielik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ferdielik.service.KobisService;

/**
 * Created by ferdielik on 29/01/2017.
 */
@Controller
@RequestMapping("/rest/kobis")
public class KobisController
{
    @Autowired
    private KobisService kobisService;

    @ResponseBody
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public ResponseEntity getStations()
    {
        return ResponseEntity.status(HttpStatus.OK).body(kobisService.getAllStations());
    }

    @ResponseBody
    @RequestMapping(value = "/parse", method = RequestMethod.POST)
    public ResponseEntity parseMainStations() throws Exception
    {
        kobisService.parseAndSaveStations();

        return ResponseEntity.ok().body(true);
    }
}