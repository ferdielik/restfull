package com.ferdielik.service;

import java.io.IOException;

import com.ferdielik.entity.Btc;

public interface BtcService
{
    void parse() throws IOException;

    Btc get() throws IOException;
}
