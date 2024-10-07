package com.redislite.commands;

import com.redislite.storage.KeyValueStore;

public class SetCommand implements Command {
    
    @Override
    public String execute(String[] args) {
        if (args.length < 3) {
            return "-ERR wrong number of arguments\r\n";
        }
        KeyValueStore.getInstance().set(args[1], args[2]);
        return "+OK\r\n";
    }
}
