package com.strike.clean.coin;

public class Red extends CoinProperties
{
    public static final CoinType COIN_TYPE = CoinType.RED;

    public Red(int coinNumber)
    {
        super(COIN_TYPE , coinNumber);
    }
}
