package com.staxter.message;

import java.util.UUID;

/**
 * This class aims to wrapper the crucial information for sending a message,
 * (receiver and the content)
 * 
 * @author cresende
 */
public class Message
{
    /**
     * Content of the message
     */
    private String message;

    /**
     * Id of the player to send the message
     */
    private UUID playerToId;


    /**
     * Constructor to creating a instance of this object using the fields
     * 
     * @param playerToId id of the player to send the message
     * @param message the content of the message
     */
    public Message(UUID playerToId, String message)
    {
        super();
        this.playerToId = playerToId;
        this.message = message;
    }

    /**
     * Returns the message.
     * 
     * @return a String containing the message of this Message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Returns the playerToId.
     * 
     * @return a UUID containing the playerToId of this Message
     */
    public UUID getPlayerToId()
    {
        return playerToId;
    }

}
