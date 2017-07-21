package com.ferdielik.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ferdielik.dao.StationDAO;
import com.ferdielik.entity.Station;
import com.ferdielik.service.KobisService;
import com.ferdielik.util.KobisParser;

@Service
@Transactional
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
                Station founded = stationDAO.getByName(station.getName());
                if (founded != null)
                    update(founded, station);
                else
                    stationDAO.saveOrUpdate(station);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        });
    }

    private void update(Station founded, Station station)
    {
        founded.setTown(station.getTown());
        founded.setStatus(station.getStatus());
        founded.setBicycle(station.getBicycle());
        founded.setEmpty(station.getEmpty());
        founded.setName(station.getName());
        founded.setLastUpdate(station.getLastUpdate());
        stationDAO.saveOrUpdate(founded);
    }
}
