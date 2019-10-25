package com.strike.clean.tracker;

import com.strike.clean.coin.Coin;

import java.util.List;

public interface Tracker
{
    public void removeCoins(int n);
    public void resizeCoins(List<Coin> coins);

    public Coin getRedCoin();
    public boolean hasRedCoin();
    public int getNoAvailCoins();
    public boolean isCoinsExhausted();
    public int getNoAvailBlackCoins();
    public List<Coin> getNoOfBlackCoins(int n);
}
