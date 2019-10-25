package com.strike.clean.player;

import com.strike.clean.coin.Coin;
import com.strike.clean.rule.Rule;

import java.util.List;

public interface Agent
{
    public void addCoin(Coin coin);
    public void addPoints(int points);
    public void addCoins(List<Coin> coins);
    public void addOutcome(Rule.Outcome outcome);

    public String getAgentID();
    public int getNumberOfPoints();
    public List<Coin> getPocketCoinsList();
    public List<Rule.Outcome> getLastThreeOutComes();
}
