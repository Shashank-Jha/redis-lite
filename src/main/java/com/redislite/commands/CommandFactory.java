package com.redislite.commands;

public class CommandFactory {
    public static Command getCommand(String[] args) {
        String command = args[0].toUpperCase();
        switch (command) {
            case "SET":
                return new SetCommand();
            case "GET":
                return new GetCommand();
            case "DEL":
                return new DeleteCommand();
            case "EXISTS":
                return new ExistsCommand();
            case "INCR":
                return new IncrCommand();
            case "INCRBY":
                return new IncrByCommand();
            case "FLUSHALL":
                return new FlushAllCommand();
            default:
                return new Command() {
                    @Override
                    public String execute(String[] args) {
                        return "-ERR unknown command\r\n";
                    }
                };
        }
    }
}
