package com.ferdielik.util;

import java.util.List;

import com.ferdielik.entity.Announce;

/**
 * Created by ferdielik on 07/04/2017.
 */
public class App
{
    public static void main(String[] args) throws Exception
    {
        KouParser kouParser = new KouParser();
        List<Announce> announces = kouParser.parseAllAnnounces();
        System.out.println("done.");
    }
}
