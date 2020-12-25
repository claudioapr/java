package com.staxter;

import com.staxter.message.LimitAction;
import com.staxter.message.MessengerService;
import com.staxter.message.MessengerServiceImpl;
import com.staxter.player.Player;

/**
 * Entry point of the system, this are responsible to create the players and
 * initialize the conversation through the queue
 * 
 * @author cresende
 */
public class App
{

    /**
     * How much message the chat will allow
     */
    public final static Integer LIMIT_OF_MESSAGE = 10;


    /**
     * Main method that create and start a queue mechanism and create 2 playres
     * and make them talk
     * 
     * @param JAVA args
     */
    public static void main(String... args)
    {
        MessengerService queue = new MessengerServiceImpl();
        Player playerOne = new Player("Initiator", queue, LIMIT_OF_MESSAGE, LimitAction.END_SYSTEM);
        Player playerTwo = new Player("Player2", queue, LIMIT_OF_MESSAGE, LimitAction.DO_NOTHING);
        queue.joinPlayersToConversation(playerOne, playerTwo);
        playerOne.sendMessage("Hi");
        queue.start();

    }
}
