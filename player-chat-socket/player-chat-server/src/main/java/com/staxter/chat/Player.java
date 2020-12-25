package com.staxter.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Player class holds the player information such as name, and handle the
 * reading and write from and in the socket server
 *
 * @author cresende
 */
public class Player extends Thread
{

    private Socket socket;

    private MessengerApp server;

    private PrintWriter writer;

    private String name;


    public Player(Socket socket, MessengerApp server)
    {
        this.socket = socket;
        this.server = server;
    }

    public void run()
    {
        try
        {
            InputStream input = socket.getInputStream();
            @SuppressWarnings("resource")
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            if (server.getPlayers().size() == 1)
            {
                writer.println("ALONE");
            }

            name = reader.readLine();
            server.broadcast(name, this);

            String clientMessage = "";

            while (true)
            {
                clientMessage = reader.readLine();

                if ("end".equalsIgnoreCase(clientMessage))
                {
                    System.exit(0);
                }
                if ("leave".equalsIgnoreCase(clientMessage))
                {
                    server.leaveChat(this);
                    socket.close();
                }
                server.broadcast(clientMessage, this);

            }

        }
        catch (IOException ex)
        {

        }
    }

    /**
     * Sends a message to the client.
     */
    public void sendMessage(String message)
    {
        writer.println(message);
    }
}
