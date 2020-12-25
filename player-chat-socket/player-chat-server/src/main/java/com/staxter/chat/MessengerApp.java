package com.staxter.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

/**
 * This class is the messenger app that runs the socket server to allow clients
 * to connect to it
 *
 * @author cresende
 */
public class MessengerApp
{
    /**
     * Logger for MessengerApp
     */
    private final static Logger LOG = Logger.getLogger(MessengerApp.class.getName());

    /**
     * Port of the socket
     */
    private int port;

    /**
     * Playes connected
     */
    private List<Player> players = new CopyOnWriteArrayList<>();


    public MessengerApp(int port)
    {
        this.port = port;
    }

    public void execute()
    {
        try (ServerSocket serverSocket = new ServerSocket(port))
        {

            LOG.info(String.format("The Messenger Server is running in the port %d", port));
            while (true)
            {
                Socket socket = serverSocket.accept();
                LOG.info(String.format("New player just connected"));

                Player player = new Player(socket, this);
                players.add(player);
                player.start();

            }

        }
        catch (IOException e)
        {
            LOG.severe(e.getMessage());
        }
    }

    public static void main(String[] args)
    {

        int port = Integer.parseInt(ConfigProperties.getProperty("socket.port", "5555"));
        MessengerApp server = new MessengerApp(port);
        server.execute();
    }

    /**
     * Delivers a message from one user to others (broadcasting)
     */
    public void broadcast(String message, Player excludeUser)
    {
        for (Player player : players)
        {
            if (player != excludeUser)
            {
                player.sendMessage(message);
            }
        }
    }

    public void leaveChat(Player player)
    {
        players.remove(player);
    }

    /**
     * Returns the players.
     * 
     * @return a List<Player> containing the players of this MessengerApp
     */
    public List<Player> getPlayers()
    {
        return players;
    }
}
