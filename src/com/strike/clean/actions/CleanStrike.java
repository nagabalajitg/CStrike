package com.strike.clean.actions;

import com.strike.clean.player.Agent;
import com.strike.clean.rule.Rule;
import com.strike.clean.tracker.CoinTracker;
import com.strike.clean.tracker.Tracker;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CleanStrike implements Game
{
	private Agent winner;
	private Agent player1;
	private Agent player2;
	private Agent currentPlayer;
	private boolean isGameComplete;
	private final Tracker TRACKER = new CoinTracker();
	private String result = ActionContrants.RESULT_NOT_SET;

	private static final Logger LOGGER = Logger.getLogger(CleanStrike.class.getName());

	public CleanStrike(Agent player1, Agent player2)
	{
		this.currentPlayer = this.player1 = player1;
		this.player2 = player2;
	}

	public boolean executeOutCome(int playType, int coinCount)
	{
		try
		{
			Rule.executeOutcome(playType, coinCount ,TRACKER, currentPlayer);
			winner = Rule.getWinner(player1, player2, TRACKER.isCoinsExhausted());
			
			if(winner != null)
			{
				this.isGameComplete = Boolean.TRUE;
				this.result = winner.getAgentID() + ActionContrants.PLAYER_WON
							+ player1.getNumberOfPoints() + "-" + player2.getNumberOfPoints();
			}
			else 
			{
				if(TRACKER.isCoinsExhausted())
				{
					this.isGameComplete = Boolean.TRUE;
					this.result = ActionContrants.NO_WIN+ 
							+ player1.getNumberOfPoints() + "-" + player2.getNumberOfPoints();
				}
				else 
				{
					if(currentPlayer.equals(player2))
					{
						currentPlayer = player1;
					}
					else
					{
						currentPlayer = player2;
					}
				}
			}
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		
		return isGameComplete;
	}

	public String getResult()
	{
		return this.result;
	}
	
	public Agent getWinner()
	{
		return this.winner;
	}
	
	public Agent getCurrentPlaying()
	{
		return this.currentPlayer;
	}
	
	public Tracker getTracker()
	{
		return this.TRACKER;
	}
}
