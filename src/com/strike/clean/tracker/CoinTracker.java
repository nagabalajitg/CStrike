package com.strike.clean.tracker;

import com.strike.clean.coin.Black;
import com.strike.clean.coin.Coin;
import com.strike.clean.coin.Red;
import com.strike.clean.coin.Striker;

import java.util.ArrayList;
import java.util.List;

public class CoinTracker implements Tracker
{
    private final Coin REDCOIN = new Red(1);
    private final Coin STRIKER = new Striker(1);
    private final List<Black> BLACKCOINS = new ArrayList<>(9);

    private boolean hasRedCoin;

    public CoinTracker()
    {
        hasRedCoin = Boolean.TRUE;
        for (int i = 0; i < 9; i++)
        {
            BLACKCOINS.add( new Black( i + 1 ));
        }
    }

    @Override
    public void resizeCoins(List<Coin> coins) {
        int size = coins.size();

        for (int index = 0; index < size; index ++)
        {
            if(coins.get(index).getCoinType() == Coin.CoinType.RED)
            {
                this.hasRedCoin = Boolean.TRUE;
                continue;
            }
            this.BLACKCOINS.add((Black) coins.get(index));
        }
    }

    public void takeRedCoin()
    {
        this.hasRedCoin = Boolean.FALSE;
    }

    @Override
    public List<Coin> getNoOfBlackCoins(int n)
    {
        List<Coin> requiredCoins = new ArrayList<>(n);

        for (int i = 0 ; i < n ; i++)
        {
            requiredCoins.add(this.BLACKCOINS.remove(0));
        }

        return requiredCoins;
    }

    @Override
    public int getNoAvailCoins()
    {
        return this.BLACKCOINS.size() + (this.hasRedCoin ? 1 : 0);
    }

    @Override
    public int getNoAvailBlackCoins()
    {
        return this.BLACKCOINS.size();
    }

    @Override
    public boolean isCoinsExhausted()
    {
        return !this.hasRedCoin && this.getNoAvailBlackCoins() == 0;
    }

    @Override
    public Coin getRedCoin()
    {
        takeRedCoin();
        return this.REDCOIN;
    }

    @Override
    public boolean hasRedCoin()
    {
        return this.hasRedCoin;
    }

    @Override
    public void removeCoins(int n)
    {
        if(this.BLACKCOINS.size() > n)
        {
            takeRedCoin();
        }

        for (int i = 0; i < n - 1 ; i++)
        {
            this.BLACKCOINS.remove(i);
        }
    }
}
