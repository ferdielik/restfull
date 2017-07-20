package com.ferdielik.service;

import java.io.IOException;

import com.ferdielik.entity.Btc;

/**
 * Created by stuff on 7/18/2017.
 */
public interface BtcService
{

    void parse() throws IOException;

    void move() throws IOException;

    Btc get() throws IOException;
}
