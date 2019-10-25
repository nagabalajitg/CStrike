package com.strike.clean.rule;

import com.strike.clean.player.Agent;
import com.strike.clean.tracker.Tracker;

public class DefunctCoin implements Rule
{
    static final Outcome OUTCOME_TYPE = Outcome.DEFUNCT_COIN;
    static final int OUTCOME_TYPE_INT = OUTCOME_TYPE.getOutcome();
    private static final int POINTS_TO_ADD = -2;

    static void execute(Agent player, int blackCoins, Tracker tracker)
    {
        tracker.removeCoins(blackCoins);
        player.addPoints(POINTS_TO_ADD);
    }
}
