package com.redislite;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Redis{
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6379)) {
            System.out.println("Redis clone started on port 6379");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
