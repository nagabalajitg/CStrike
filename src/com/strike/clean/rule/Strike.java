package com.strike.clean.rule;

import com.strike.clean.player.Agent;
import com.strike.clean.tracker.Tracker;

class Strike implements Rule
{
    static final Outcome OUTCOME_TYPE = Outcome.STRIKE;
    static final int OUTCOME_TYPE_INT = OUTCOME_TYPE.getOutcome();

    private static final int POINTS_TO_ADD = 1;

    static void execute(Agent player, Tracker tracker)
    {
        player.addCoins(tracker.getNoOfBlackCoins(1));
        player.addPoints(POINTS_TO_ADD);
    }
}