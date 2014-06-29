package com.minegusta.mgraces.util;

import java.util.Random;

public class RandomInt
{
    private static Random rand = new Random();

    public static int getRandom(int maximum)
    {
        return rand.nextInt(maximum);
    }
}
