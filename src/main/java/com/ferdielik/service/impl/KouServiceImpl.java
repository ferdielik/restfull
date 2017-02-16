package com.ferdielik.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ferdielik.dao.AnnounceDAO;
import com.ferdielik.entity.Announce;
import com.ferdielik.service.KouService;
import com.ferdielik.util.KouParser;

/**
 * Created by ferdielik on 16/02/2017.
 */
public class KouServiceImpl implements KouService
{
    @Autowired
    KouParser kouParser;

    @Autowired
    AnnounceDAO announceDAO;

    @Override
    public List<Announce> getAllAnnounces()
    {
        return announceDAO.getAll();
    }

    @Override
    public void parseAndSaveAnnounces() throws Exception
    {
        announceDAO.deleteAll();

        List<Announce> announces = kouParser.parseAllAnnounces();

        announces.forEach(announceDAO::saveOrUpdate);
    }
}
