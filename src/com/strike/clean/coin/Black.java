package com.strike.clean.coin;

public class Black extends CoinProperties
{
    public static final CoinType COIN_TYPE = CoinType.BLACK;

    public Black(int coinNumber)
    {
        super(COIN_TYPE , coinNumber);
    }
}
