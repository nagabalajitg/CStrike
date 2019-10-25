package com.strike.clean.actions;

public interface ActionContrants
{
	public static final String RESULT_NOT_SET = "result not set";
	public static final String PLAYER_WON = " won the game. Final Score: ";
	public static final String NO_WIN = "The game is draw. Final Score: ";
	public static final String MESSAGE = "1. Strike \n" + "2. Multi-strike \n" + "3. Red strike \n" + "4. Striker strike \n" + "5. Defunct coin \n" + "6. None \n" + "Note : For 2,3 & 5 enter no of Black coins seperated by comma\n" + "For Example : 2,2\n";

	public static final String BLACK_COINS_MESSAGE = "\n Black coins :";
	public static final String RED_COINS_MESSAGE = "\n Red coins :";
	
	public static final String COIN_COUNT_MESSAGE = "Should enter count with comma for this strike type.\n";
	public static final String NO_RED_COIN_MESSAGE = "Red coins is already taken.\n";
	public static final String LESS_COIN_MESSAGE = "Enter no of coins are not available.\n";
	public static final String VALID_MESSAGE = "Enter valid input\n";
	public static final String TRY_MESSAGE = "Try again.\n";
	public static final String EXTRA_OUTCOME_MESSAGE = "Extra outcomes found";
}