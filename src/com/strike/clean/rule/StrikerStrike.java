package com.strike.clean.rule;


import com.strike.clean.player.Agent;

public class StrikerStrike implements Rule
{
    static final Outcome OUTCOME_TYPE = Outcome.STRIKER_STRIKE;
    static final int OUTCOME_TYPE_INT = OUTCOME_TYPE.getOutcome();
    private static final int POINTS_TO_ADD = -1;

    static void execute(Agent player)
    {
        player.addPoints(POINTS_TO_ADD);
    }
}
