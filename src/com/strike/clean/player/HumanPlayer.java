package com.strike.clean.player;

import com.strike.clean.coin.Coin;
import com.strike.clean.rule.Rule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class HumanPlayer implements Agent
{
    private String playerID;
    private int noOfPoints = 0;
    private final List<Rule.Outcome> OUTCOMELIST = new LinkedList<>();
    private final List<Coin> POCKETCOINS = new ArrayList<>(10);


    public HumanPlayer(String playerID)
    {
        Objects.requireNonNull(playerID);
        this.playerID = playerID;
    }

    @Override
    public String getAgentID()
    {
        return this.playerID;
    }

    @Override
    public void addCoin(Coin coin)
    {
        this.POCKETCOINS.add(coin);
    }

    @Override
    public void addCoins(List<Coin> coins)
    {
        this.POCKETCOINS.addAll(coins);
    }

    @Override
    public void addPoints(int points)
    {
        this.noOfPoints += points;
        if(this.noOfPoints < 0  )
        {
            this.noOfPoints = 0;
        }
    }

    @Override
    public List<Coin> getPocketCoinsList()
    {
        return this.POCKETCOINS;
    }

    @Override
    public int getNumberOfPoints()
    {
        return this.noOfPoints;
    }

    @Override
    public List<Rule.Outcome> getLastThreeOutComes()
    {
        int size = this.OUTCOMELIST.size();
        List<Rule.Outcome> lastThreeOutComes = new LinkedList<>();

        for(int index = size -1 ; index == size - 4 ; index--)
        {
            lastThreeOutComes.add(lastThreeOutComes.get(index));
        }

        return lastThreeOutComes;
    }

    @Override
    public void addOutcome(Rule.Outcome outcome)
    {
        this.OUTCOMELIST.add(outcome);
    }
}
