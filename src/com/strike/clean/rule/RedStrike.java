package com.strike.clean.rule;

import com.strike.clean.player.Agent;
import com.strike.clean.tracker.Tracker;

public class RedStrike implements Rule
{
    static final Outcome OUTCOME_TYPE = Outcome.RED_STRIKE;
    static final int OUTCOME_TYPE_INT = OUTCOME_TYPE.getOutcome();
    private static final int POINTS_TO_ADD = 3;

    static void execute(Agent player, Tracker tracker)
    {
        player.addCoin(tracker.getRedCoin());
    }
}
