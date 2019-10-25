package com.strike.clean.rule;


import com.strike.clean.player.Agent;
import com.strike.clean.tracker.Tracker;

import java.util.ArrayList;
import java.util.List;

public interface Rule
{
    public static final int STRIKE = 1;
    public static final int MULTI_STRIKE = 2;
    public static final int RED_STRIKE = 3;
    public static final int STRIKER_STRIKE = 4;
    public static final int DEFUNCT_COIN = 5;
    public static final int NONE = 6;

    public static enum Outcome
    {
        STRIKE(1),MULTI_STRIKE(2),RED_STRIKE(3),STRIKER_STRIKE(4),DEFUNCT_COIN(5),
        NONE(6);

        int outcome;
        private static final List<Outcome> OUTCOME_LIST_NO_COUNT = new ArrayList<Outcome>(){{
            add(NONE);
            add(STRIKE);
            add(STRIKER_STRIKE);
        }};
       
        private static final List<Integer> OUTCOME_LIST_NO_COUNT_INT= new ArrayList<Integer>(){{
            add(NONE.getOutcome());
            add(STRIKE.getOutcome());
            add(STRIKER_STRIKE.getOutcome());
        }};
        
        private Outcome(int outcome)
        {
            this.outcome = outcome;
        }

        public int getOutcome()
        {
            return this.outcome;
        }

        public static Outcome getOutcome(int outcome)
        {
            for (Outcome aOutcome : Outcome.values())
            {
                if(aOutcome.getOutcome() == outcome)
                {
                    return aOutcome;
                }
            }

            throw new IllegalStateException(outcome+"");
        }
        
        public static List<Integer> getNoCountIntList()
        {
            return OUTCOME_LIST_NO_COUNT_INT;
        }
    }

    public static void executeOutcome(int outcome, int noOfBlackCoins, Tracker tracker, Agent player)
    {
//        System.out.println("outcome => " + outcome + " noOfBlackCoins =>" + noOfBlackCoins);
//        System.out.println("player => " + player.getAgentID());

        player.addOutcome(Outcome.getOutcome(outcome));

        switch (outcome)
        {
            case STRIKE:
                Strike.execute(player, tracker);
                break;
            case MULTI_STRIKE:
                MultiStrike.execute(player, noOfBlackCoins ,tracker);
                break;
            case RED_STRIKE:
                RedStrike.execute(player, tracker);
                break;
            case STRIKER_STRIKE:
                StrikerStrike.execute(player);
                SuccessiveLoss.execute(player);
                break;
            case DEFUNCT_COIN:
                DefunctCoin.execute(player, noOfBlackCoins, tracker);
                SuccessiveLoss.execute(player);
                break;
            case NONE:
                SuccessiveLoss.execute(player);
        }
    }

    public static Agent getWinner(Agent p1, Agent p2, boolean isCoinExausted)
    {
        return GamePointCalc.getWinner(p1, p2, isCoinExausted);
    }
}
