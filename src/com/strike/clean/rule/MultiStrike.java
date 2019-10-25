package com.strike.clean.rule;

import com.strike.clean.coin.Coin;
import com.strike.clean.player.Agent;
import com.strike.clean.tracker.Tracker;

import java.util.List;

public class MultiStrike implements Rule
{
    static final Outcome OUTCOME_TYPE = Outcome.MULTI_STRIKE;
    static final int OUTCOME_TYPE_INT = OUTCOME_TYPE.getOutcome();
    private static final int COINTS_TO_REMOVE = 2;
    private static final int POINTS_TO_ADD = 2;

    static void execute(Agent player, int blackCoins, Tracker tracker)
    {
        List<Coin> pocketList = tracker.getNoOfBlackCoins(blackCoins);
        int pocketSize = pocketList.size();
        player.addPoints(POINTS_TO_ADD);

        if(pocketSize - COINTS_TO_REMOVE >= 2)
        {
            for (int i = 0; i < pocketSize - COINTS_TO_REMOVE ; i++)
            {
                player.addCoin(pocketList.remove(0));
            }
        }
        
        tracker.resizeCoins(pocketList);
    }
}
