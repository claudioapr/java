package com.staxter.player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.staxter.message.LimitAction;
import com.staxter.message.Message;
import com.staxter.message.MessengerService;

/**
 * This class represent a player in the program, it holds the information of the
 * players such as name, players that it is connected to, exchange message
 * counters and so on
 * 
 * @author cresende
 */
public class Player
{
    /**
     * Logger for Player
     */
    private final static Logger LOG = Logger.getLogger(Player.class.getName());

    /**
     * Name of the player
     */
    private String name;

    /**
     * Id of the player when registered in the chat
     */
    private UUID id;

    /**
     * A instance of the manager of the queue
     */
    private MessengerService queue;

    /**
     * List of player that it is connected to
     */
    private List<Player> connectedTo = new ArrayList<Player>();

    /**
     * Sent message counter
     */
    private Integer sentCounter = 1;

    /**
     * Received message counter
     */
    private Integer receivedCounter = 1;

    /**
     * Limit of message that this player is allow to send or receive
     */
    private Integer limitOfMessage;

    /**
     * What action should be taken when the limit of message is reached
     */
    private LimitAction whenReachLimit;


    /**
     * Creates a new instance of Player.
     * 
     * @param name of the player or receive to be assigned
     * @param queue the queue to be injected
     * @param limitOfMessage Limit of message that this player is allow to send
     *            or receive to be assigned
     * @param whenReachLimit What action should be taken when the limit of
     *            message is reached to be assigned
     */
    public Player(String name, MessengerService queue, Integer limitOfMessage, LimitAction whenReachLimit)
    {
        super();
        this.name = name;
        this.queue = queue;
        this.id = queue.register(this);
        this.whenReachLimit = whenReachLimit;
        this.limitOfMessage = limitOfMessage;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the id
     */
    public UUID getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(UUID id)
    {
        this.id = id;
    }

    /**
     * When the Player receive a message from the queue, it prints on the
     * console the received message
     * 
     * @param message the actual message
     */
    public void onMessage(String message)
    {

        LOG.info(message);
        sendMessage(message);
        receivedCounter++;

        if (limitReached())
        {
            switch (whenReachLimit)
            {
            case END_SYSTEM:
                LOG.info(String.format("The system will be ended requested by the user [%s]", name));
                System.exit(0);
                break;
            case END_ONLY_CHAT:
                LOG.info(String.format("The chat will be ended requested by the user [%s]", name));
                endConversations();
                break;
            default:
                break;
            }
        }

    }

    /**
     * Check if the limit of message was reached
     * 
     * @return
     */
    private boolean limitReached()
    {
        if (receivedCounter == null || receivedCounter == 0)
        {
            return false;
        }

        return (receivedCounter > limitOfMessage && sentCounter > limitOfMessage);
    }

    /**
     * Send a message for a specific player
     * 
     * @param playerId the id of the player to send a message
     * @param message the actual message
     */
    public void sendMessage(UUID playerId, String message)
    {
        queue.sendMessage(new Message(playerId, String.format("%s %d", message, sentCounter++)));
    }

    /**
     * Send a message for all players(broadcasting the messagefor all connected
     * players)
     * 
     * @param message to send to the players
     */
    public void sendMessage(String message)
    {
        for (Player player : connectedTo)
        {
            sendMessage(player.getId(), message);
        }
    }

    /**
     * Connect this another player to this one, the player to be connect will be
     * added to the connected list
     * 
     * @param player
     */
    public void connectTo(Player player)
    {
        connectedTo.add(player);
    }

    /**
     * Clean the connection, removing it from the others chats
     */
    public void endConversations()
    {
        connectedTo.forEach(player -> player.connectedTo.remove(player));
        connectedTo.clear();
    }

}
