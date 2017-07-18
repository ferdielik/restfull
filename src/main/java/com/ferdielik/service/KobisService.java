package com.ferdielik.service;

import com.ferdielik.entity.Station;

import java.util.List;

public interface KobisService {
    List<Station> getAllStations();

    void parseAndSaveStations() throws Exception;
}
