package com.strike.clean.rule;

import com.strike.clean.player.Agent;

import java.util.Iterator;

public class SuccessiveLoss implements Rule
{
    private static final int POINTS_TO_ADD = -1;

    static void execute(Agent player)
    {
        boolean decrement = Boolean.TRUE;
        Iterator<Outcome> outcomes = player.getLastThreeOutComes().iterator();

        while(outcomes.hasNext())
        {
            if(outcomes.next() != Outcome.NONE)
            {
                decrement = Boolean.FALSE;
            }
        }

        if(decrement)
        {
            player.addPoints(POINTS_TO_ADD);
        }
    }
}
