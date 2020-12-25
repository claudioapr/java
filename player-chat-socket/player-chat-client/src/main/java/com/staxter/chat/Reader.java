package com.staxter.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * This class is the reader from the socket it foes in a look check always
 * whether there is message
 *
 * @author cresende
 */
public class Reader extends Thread
{

    /**
     * Logger for Reader
     */
    private final static Logger LOG = Logger.getLogger(Reader.class.getName());

    private BufferedReader reader;

    @SuppressWarnings("unused")
    private Socket socket;

    /**
     * Received message counter
     */
    private Integer receivedCounter = 1;

    /**
     * Limit of message that this player is allow to send or receive
     */
    private Integer limitOfMessage = 10;

    private Player player;


    public Reader(Socket socket, Player player)
    {
        this.player = player;
        this.socket = socket;
        try
        {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        }
        catch (IOException e)
        {
            LOG.severe(e.getMessage());
        }
    }

    public void run()
    {

        while (true)
        {
            try
            {
                String response = reader.readLine();

                if (!"ALONE".equalsIgnoreCase(response))
                {
                    LOG.info(response);

                    player.getWriter().writeTo(response);

                    receivedCounter++;

                    if (receivedCounter > limitOfMessage)
                    {
                        player.getWriter().writeTo("end");
                    }
                }
                else
                {
                    player.getWriter().resetCounter();
                }

            }
            catch (IOException e)
            {
                LOG.info(e.getMessage());
                break;
            }
        }
    }
}
