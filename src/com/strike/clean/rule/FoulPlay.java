package com.strike.clean.rule;

import com.strike.clean.coin.Coin;
import com.strike.clean.player.Agent;

import java.util.List;

public class FoulPlay implements Rule
{
    private static final int POINTS_TO_ADD = -1;

    static void execute(Agent player, List<Coin> pocketList)
    {
        player.addPoints(POINTS_TO_ADD);
    }
}
