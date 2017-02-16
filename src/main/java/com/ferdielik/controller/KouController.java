package com.ferdielik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ferdielik.service.KouService;

/**
 * Created by ferdielik on 29/01/2017.
 */
@Controller
@RequestMapping("/rest/kou")
public class KouController
{
    @Autowired
    private KouService kouService;

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity getMainAnnounces()
    {
        return ResponseEntity.status(HttpStatus.OK).body(kouService.getAllAnnounces());
    }

    @ResponseBody
    @RequestMapping(value = "/parse", method = RequestMethod.POST)
    public ResponseEntity parseMainAnnounces() throws Exception
    {
        kouService.parseAndSaveAnnounces();

        return ResponseEntity.ok().body(true);
    }
}
