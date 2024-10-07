package com.redislite;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.redislite.commands.Command;
import com.redislite.commands.CommandFactory;
import com.redislite.utils.RESPParser;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.setClientSocket(socket);
    }

    private void setClientSocket(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
                BufferedOutputStream out = new BufferedOutputStream(
                        clientSocket.getOutputStream())) {
            RESPParser parser = new RESPParser();
                String[] tokens = parser.parse(in);
                Command command = CommandFactory.getCommand(tokens);
                String response = command.execute(tokens);
                out.write(response.getBytes());
                out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
