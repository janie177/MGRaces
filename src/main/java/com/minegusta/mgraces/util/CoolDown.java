package com.minegusta.mgraces.util;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class CoolDown
{
    public static void newCooldown(UUID uuid, ConcurrentMap<UUID, Long> map)
    {
        map.put(uuid, System.currentTimeMillis());
    }

    public static boolean cooledDown(UUID uuid, ConcurrentMap<UUID, Long> map, int coolDown)
    {
        return !map.containsKey(uuid) || TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - map.get(uuid)) >= coolDown;
    }

    public static long getRemainingTime(UUID uuid, ConcurrentMap<UUID, Long> map)
    {
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - map.get(uuid));
    }
}
