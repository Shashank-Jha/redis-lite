package com.redislite.commands;

import com.redislite.storage.KeyValueStore;

public class GetCommand implements Command {
    @Override
    public String execute(String[] args) {
        if (args.length < 2) {
            return "-ERR wrong number of arguments\r\n";
        }
        String value = KeyValueStore.getInstance().get(args[1]);
        if (value == null) {
            return "$-1\r\n";
        }
        return "$" + value.length() + "\r\n" + value + "\r\n";
    }
}
