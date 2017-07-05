package com.ferdielik.service;

import java.util.List;

import com.ferdielik.entity.Station;

public interface KobisService
{
    List<Station> getAllStations();

    void parseAndSaveStations() throws Exception;
}
