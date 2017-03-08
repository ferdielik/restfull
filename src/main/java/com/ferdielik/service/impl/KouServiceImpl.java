package com.ferdielik.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferdielik.dao.AnnounceDAO;
import com.ferdielik.entity.Announce;
import com.ferdielik.service.KouService;
import com.ferdielik.util.KouParser;

/**
 * Created by ferdielik on 16/02/2017.
 */
@Service
public class KouServiceImpl implements KouService
{
    @Autowired
    private KouParser kouParser;

    @Autowired
    private AnnounceDAO announceDAO;

    @Override
    public List<Announce> getAllAnnounces()
    {
        return announceDAO.getAll();
    }

    @Override
    public void parseAndSaveAnnounces() throws Exception
    {
        //todo: find a better way

        Set<Announce> announces = new HashSet<>(getAllAnnounces());
        announceDAO.deleteAll();
        announces.addAll(kouParser.parseAllAnnounces());
        announces.forEach(a ->
        {
            announceDAO.saveOrUpdate(a.clone());
        });
    }
}
