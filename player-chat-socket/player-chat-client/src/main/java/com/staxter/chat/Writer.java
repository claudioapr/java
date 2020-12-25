package com.staxter.chat;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * This class is responsible to write in the socket
 *
 * @author cresende
 */
public class Writer
{
    /**
     * Logger for Writer
     */
    private final static Logger LOG = Logger.getLogger(Writer.class.getName());

    private PrintWriter writer;

    private Socket socket;

    /**
     * Sent message counter
     */
    private Integer sentCounter = 1;


    public Writer(Socket socket)
    {
        this.socket = socket;

        try
        {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        }
        catch (IOException e)
        {
            LOG.severe(e.getMessage());
        }
    }

    void writeTo(String message)
    {
        writer.println(String.format("%s %d", message, sentCounter++));

        if ("leave".equalsIgnoreCase(message) || "end".equalsIgnoreCase(message))
        {
            try
            {
                socket.close();
            }
            catch (IOException e)
            {
                LOG.severe(e.getMessage());
            }
            System.exit(0);
        }
    }

    public void resetCounter()
    {
        sentCounter = 1;
    }
}
