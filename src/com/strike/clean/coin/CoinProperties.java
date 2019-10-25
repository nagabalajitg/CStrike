package com.strike.clean.coin;

public abstract class CoinProperties implements Coin
{
    private int coinNumber = 0;
    private CoinType coinType = null;

    public CoinProperties(CoinType coinType, int coinNumber)
    {
        this.coinType = coinType;
        this.coinNumber = coinNumber;
    }

    @Override
    public CoinType getCoinType()
    {
        return coinType;
    }

    @Override
    public int getCoinNumber()
    {
        return coinNumber;
    }
}
