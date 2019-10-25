package com.strike.clean.actions;


import java.util.logging.Level;
import java.util.logging.Logger;


public class CleanStrikeStarter
{
    private static final Logger LOGGER = Logger.getLogger(CleanStrikeStarter.class.getName());
   
    public static void main(String[] arg)
    {
        try
        {
            new GameController(Controller.InputMode.TERMINAL).invokeGame();    
        }
        catch(Exception e)
        {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        
    }
}
