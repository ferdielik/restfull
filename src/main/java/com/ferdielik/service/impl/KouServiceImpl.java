package com.ferdielik.service.impl;

import com.ferdielik.dao.AnnounceDAO;
import com.ferdielik.entity.Announce;
import com.ferdielik.entity.AnnounceType;
import com.ferdielik.service.KouService;
import com.ferdielik.util.KouParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ferdielik on 16/02/2017.
 */
@Service
public class KouServiceImpl implements KouService {
    @Autowired
    private KouParser kouParser;

    @Autowired
    private AnnounceDAO announceDAO;

    @Override
    public List<Announce> getAllAnnounces() {
        return announceDAO.getAll();
    }

    @Override
    public List<Announce> getAnnounces(AnnounceType type) {
        return announceDAO.getAnnounces(type);
    }

    @Override
    public void parseAndSaveAnnounces() throws Exception {
        kouParser.parseAllAnnounces().forEach(announce ->
        {
            try {
                announceDAO.saveOrUpdate(announce);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
}
