package com.minegusta.mgraces.data;

import com.google.common.collect.Maps;
import com.minegusta.mgraces.player.MGPlayer;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public class TempData
{
    public static ConcurrentMap<UUID, MGPlayer> raceMap = Maps.newConcurrentMap();
}
