package com.redislite;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Redis{
    private static final String REDIS_LOGO = 
        "                      _._                                                  \n" +
        "                 _.-``__ ''-._                                              \n" +
        "            _.-``    `.  `_.  ''-._           Redis Lite                   \n" +
        "        .-`` .-```.  ```\\/    _.,_ ''-._                                    \n" +
        "       (    '      ,       .-`  | `,    )     Running in standalone mode   \n" +
        "       |`-._`-...-` __...-.``-._|'` _.-'|     Port: 6379                    \n" +
        "       |    `-._   `._    /     _.-'    |     PID: 1                        \n" +
        "        `-._    `-._  `-./  _.-'    _.-'                                    \n" +
        "       |`-._`-._    `-.__.-'    _.-'_.-'|                                       \n" +
        "       |    `-._`-._        _.-'_.-'    |              _                        \n" +
        "       `-._    `-._`-.__.-'_.-'    _.-'              `-._                      \n" +
        "       |`-._`-._    `-.__.-'    _.-'_.-'|                 `-._                   \n" +
        "       |    `-._`-._        _.-'_.-'    |                     `-._               \n" +
        "       `-._    `-._`-.__.-'_.-'    _.-'                         `-._            \n" +
        "           `-._    `-.__.-'    _.-'                                 `-._        \n" +
        "               `-._        _.-'                                         `-._    \n" +
        "                   `-.__.-'                                                 `-._\n";
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6379)) {
            System.out.println(REDIS_LOGO);
            System.out.println("Redis Lite started on port 6379");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
