package com.strike.clean.actions;

import com.strike.clean.player.Agent;
import org.json.JSONArray;

public interface Controller
{
	public static enum InputMode
	{
		TERMINAL(1),JSON(2);

		int inputMode;
		private InputMode(int inputMode)
		{
			this.inputMode = inputMode;
		}
	}

	public Agent getWinner();
	public String getGameResult();
	public void invokeGame() throws Exception;
	public void setOutcomes(JSONArray outcomeJSON);
}
