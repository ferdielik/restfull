package com.ferdielik.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferdielik.dao.StationDAO;
import com.ferdielik.entity.Station;
import com.ferdielik.service.KobisService;
import com.ferdielik.util.KobisParser;

@Service
public class KobisServiceImpl implements KobisService
{
    @Autowired
    private KobisParser kobisParser;

    @Autowired
    private StationDAO stationDAO;

    @Override
    public List<Station> getAllStations()
    {
        return stationDAO.getAll();
    }

    @Override
    public void parseAndSaveStations() throws Exception
    {
        kobisParser.parseAllStations().forEach(station ->
        {
            try
            {
                stationDAO.saveOrUpdate(station);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        });
    }
}
