package com.strike.clean.actions;

import com.strike.clean.player.Agent;
import com.strike.clean.player.HumanPlayer;
import com.strike.clean.rule.Rule;
import com.strike.clean.tracker.Tracker;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameController implements Controller
{
	private InputMode mode;
	private JSONArray outcomeJSON;
	private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
	private final Game game = new CleanStrike(new HumanPlayer("Player 1"),new HumanPlayer("Player 2"));
	
	private static final Logger LOGGER = Logger.getLogger(GameController.class.getName());
	
	public GameController(InputMode mode)
	{
		this.mode = mode;
	}
	
	public void setOutcomes(JSONArray outcomeJSON)
	{
		this.outcomeJSON = outcomeJSON;
	}
	
	public void invokeGame() throws Exception
	{
		if(mode == InputMode.JSON)
		{
			doJSONplay();
		}
		else
		{
			doTerminalPlay();
		}
	}
	
	public Agent getWinner()
	{
		return game.getWinner();
	}
	
	public String getGameResult()
	{
		return game.getResult();	
	}
	
	private void doTerminalPlay() throws Exception
	{
		boolean continueLoop = Boolean.TRUE;
		
		do
		{
			int ip[] = getTerminalInput();
			continueLoop = !game.executeOutCome(ip[0], ip[1]);
		}
		while(continueLoop);

		System.out.println(game.getResult());
	}
	
	private int[] getTerminalInput() throws Exception
	{
		String[] ops = new String[2];
		int[] inputPair = new int[2];
		Agent player = game.getCurrentPlaying();
		Tracker tracker = game.getTracker();

		String message = "\n" + player.getAgentID() + " : \n" + ActionContrants.MESSAGE;
		message += ActionContrants.BLACK_COINS_MESSAGE + tracker.getNoAvailBlackCoins();
		message += ActionContrants.RED_COINS_MESSAGE + tracker.hasRedCoin() +"\n";

		System.out.println(message);
		String op = READER.readLine();

		if(op.contains(","))
		{
			ops = op.split(",");
		}
		else
		{
			ops[0] = op;
		}

		boolean askInputAgain = Boolean.FALSE;
		int outcome = Integer.parseInt(ops[0]);

		if(outcome >= Rule.Outcome.STRIKE.getOutcome() || outcome <= Rule.Outcome.NONE.getOutcome())
		{
			if(!Rule.Outcome.getNoCountIntList().contains(outcome))
			{
				if(ops.length <= 1)
				{
					System.out.println(ActionContrants.COIN_COUNT_MESSAGE);
					askInputAgain = Boolean.TRUE;
				}
				else if(Integer.parseInt(ops[0]) == Rule.Outcome.RED_STRIKE.getOutcome() && !tracker.hasRedCoin())
				{
					System.out.println(ActionContrants.NO_RED_COIN_MESSAGE);
					askInputAgain = Boolean.TRUE;
				}
				else if(Integer.parseInt(ops[1]) > tracker.getNoAvailCoins())
				{
					System.out.println(ActionContrants.LESS_COIN_MESSAGE);
					askInputAgain = Boolean.TRUE;
				}
				else
				{
					inputPair[0] = Integer.parseInt(ops[0]);
					inputPair[1] = Integer.parseInt(ops[1]);
				}
			}
			else
			{
				inputPair[0] = Integer.parseInt(ops[0]);
				inputPair[1] = 0;
			}
		}
		else
		{
			askInputAgain = Boolean.TRUE;
			System.out.println(ActionContrants.VALID_MESSAGE);
		}

		if(askInputAgain)
		{
			System.out.println(ActionContrants.TRY_MESSAGE);
			inputPair = getTerminalInput();
		}
		
		return inputPair;
	}
	
	private void doJSONplay() throws Exception
	{
		Objects.requireNonNull(outcomeJSON);
		
		for(int i = 0; i < outcomeJSON.length() ; i++)
		{
			try
			{
				JSONArray outcome = outcomeJSON.getJSONArray(i);
				boolean isComplete = game.executeOutCome(
						outcome.getInt(0),
						outcome.getInt(1)
				);

				if(isComplete)
				{
					if((i + 1) != outcomeJSON.length())
					{
						throw new IllegalStateException(ActionContrants.EXTRA_OUTCOME_MESSAGE);
					}
					break;
				}
			}
			catch(Exception e)
			{
				if(e instanceof IllegalStateException)
				{
					throw e;
				}
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
		}
	}
}