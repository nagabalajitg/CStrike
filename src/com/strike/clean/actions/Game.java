package com.strike.clean.actions;

import com.strike.clean.player.Agent;
import com.strike.clean.tracker.Tracker;

public interface Game
{
	public Agent getWinner();
	public String getResult();
	public Tracker getTracker();
	public Agent getCurrentPlaying();
	public boolean executeOutCome(int playType, int coinCount);
}
