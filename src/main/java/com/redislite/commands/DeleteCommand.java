package com.redislite.commands;

import com.redislite.storage.KeyValueStore;

public class DeleteCommand implements Command {
    @Override
    public String execute(String[] args) {
        if (args.length != 2) {
            return "-ERR wrong number of arguments for 'del' command\r\n";
        }
        
        String key = args[1];
        if (KeyValueStore.getInstance().delete(key)) {
            return ":1\r\n";
        } else {
            return ":0\r\n";
        }
    }
}
