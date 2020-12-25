package com.staxter.chat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

/**
 * This is the player implementation in the client side
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
     * Host name of the socket server
     */
    private String hostname;

    /**
     * port number of socket server
     */
    private int port;

    /**
     * name of the player got from the java agrs
     */
    private String name;

    /**
     * Object that reads from socket
     */
    private Reader reader;

    /**
     * Object that writes in the socket
     */
    private Writer writer;


    /**
     * Returns the writer.
     * 
     * @return a Write containing the writer of this Player
     */
    public Writer getWriter()
    {
        return writer;
    }

    public Player(String hostname, int port, String name)
    {
        this.hostname = hostname;
        this.port = port;
        this.name = name;
    }

    public void create()
    {
        try
        {
            Socket socket = new Socket(hostname, port);

            reader = new Reader(socket, this);
            reader.start();
            writer = new Writer(socket);
            writer.writeTo(name);
        }
        catch (UnknownHostException e)
        {
            LOG.severe(e.getMessage());
        }
        catch (IOException e)
        {
            LOG.severe(e.getMessage());
        }

    }

    /**
     * Returns the name.
     * 
     * @return a String containing the name of this Player
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name.
     * 
     * @param name a String containing the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            return;
        }
        String username = args[0];

        String hostname = ConfigProperties.getProperty("socket.localhost", "localhost");
        int port = Integer.parseInt(ConfigProperties.getProperty("socket.port", "5555"));

        Player client = new Player(hostname, port, username);
        client.create();
    }
}
