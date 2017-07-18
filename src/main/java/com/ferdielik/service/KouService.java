package com.ferdielik.service;

import com.ferdielik.entity.Announce;
import com.ferdielik.entity.AnnounceType;

import java.util.List;

/**
 * Created by ferdielik on 16/02/2017.
 */
public interface KouService {
    List<Announce> getAllAnnounces();

    List<Announce> getAnnounces(AnnounceType type);

    void parseAndSaveAnnounces() throws Exception;
}
