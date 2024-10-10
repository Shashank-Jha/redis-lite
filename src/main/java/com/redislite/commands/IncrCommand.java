package com.redislite.commands;

import com.redislite.storage.KeyValueStore;

public class IncrCommand implements Command {
    @Override
    public String execute(String[] args) {
        if (args.length != 2) {
            return "-ERR wrong number of arguments for 'incr' command\r\n";
        }

        String key = args[1];
        String value = KeyValueStore.getInstance().get(key);

        try {
            int intValue = Integer.parseInt(value);
            intValue++;
            KeyValueStore.getInstance().set(key, Integer.toString(intValue));
            return "(integer) " + intValue + "\r\n";
        } catch (NumberFormatException e) {
            return "-ERR value is not an integer\r\n";
        }
    }
}

