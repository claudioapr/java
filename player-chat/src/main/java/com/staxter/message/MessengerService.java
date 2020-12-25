package com.staxter.message;

import java.util.UUID;

import com.staxter.player.Player;

/**
 * Interface holding the contract for the MessengerService
 * 
 * @author cresende
 */
public interface MessengerService
{
    /**
     * Register a player in the messenger
     * 
     * @param player the player to be registered
     * @return The if of the registering process
     */
    public UUID register(Player player);

    /**
     * Given a message it enqueue it to be processed
     * 
     * @param message the message to be send
     */
    public void sendMessage(Message message);

    /**
     * Given two players it join them to a chat channel
     * 
     * @param playerOne to be connected
     * @param playerTwo to be connected
     */
    public void joinPlayersToConversation(Player playerOne, Player playerTwo);

    /**
     * Start the messenger processing
     */
    public void start();
}
