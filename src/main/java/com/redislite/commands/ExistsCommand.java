package com.redislite.commands;

import com.redislite.storage.KeyValueStore;

public class ExistsCommand implements Command {
    @Override
    public String execute(String[] args) {
        if (args.length != 2) {
            return "-ERR wrong number of arguments for 'exists' command\r\n";
        }
        
        String key = args[1];
        return KeyValueStore.getInstance().exists(key) ? "(integer) 1\r\n" : "(integer) 0\r\n";
    }
}
