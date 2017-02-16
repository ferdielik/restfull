package com.ferdielik.service;

import java.util.List;

import com.ferdielik.entity.Announce;

/**
 * Created by ferdielik on 16/02/2017.
 */
public interface KouService
{
    List<Announce> getAllAnnounces();

    void parseAndSaveAnnounces() throws Exception;
}
