package com.strike.clean.coin;

public class Striker extends CoinProperties
{
    public static final CoinType COIN_TYPE = CoinType.STRIKER;

    public Striker(int coinNumber)
    {
        super(COIN_TYPE , coinNumber);
    }
}
