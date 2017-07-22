package com.ferdielik.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ferdielik.dao.AnnounceDAO;
import com.ferdielik.entity.Announce;
import com.ferdielik.entity.AnnounceType;
import com.ferdielik.service.KouService;
import com.ferdielik.util.KouParser;

@Service
@Transactional(readOnly = false)
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
    public List<Announce> getAnnounces(AnnounceType type)
    {
        return announceDAO.getAnnounces(type);
    }

    @Override
    public void parseAndSaveAnnounces() throws Exception
    {
        kouParser.parseAllAnnounces().forEach(announce ->
        {
            try
            {
                announceDAO.saveOrUpdate(announce);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        });
    }
}
