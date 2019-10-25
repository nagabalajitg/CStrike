package com.strike.clean.rule;

import com.strike.clean.player.Agent;

public class GamePointCalc implements Rule
{
    private static final int WIN_MIN_POINTS = 5;
    private static final int WIN_DIFFERENCE_POINTS = 3;
    static Agent getWinner(Agent p1, Agent p2, boolean coinsExausted)
    {
        Agent player = null;

        int noOfPointsOfp1 = p1.getNumberOfPoints();
        int noOfPointsOfp2 = p2.getNumberOfPoints();

        if(noOfPointsOfp1 == noOfPointsOfp2)
        {
            return null;
        }
        else
        {
            int diff = 0;

            if(noOfPointsOfp1 > noOfPointsOfp2)
            {
                player = p1;
                diff = noOfPointsOfp1 - noOfPointsOfp2;
            }
            else
            {
                player = p2;
                diff = noOfPointsOfp2 - noOfPointsOfp1;
            }
            
            if(coinsExausted)
            {
                if(
                    player.getNumberOfPoints() >= WIN_MIN_POINTS
                            || diff >= WIN_DIFFERENCE_POINTS
                )
                {
                    return player;
                }
            }
            else
            {
                if(
                        player.getNumberOfPoints() >= WIN_MIN_POINTS
                                && diff >= WIN_DIFFERENCE_POINTS
                )
                {
                    return player;
                }
            }
        }

        return null;
    }
}
