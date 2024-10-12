package com.redislite.commands;

import com.redislite.storage.KeyValueStore;

public class IncrByCommand implements Command {
    @Override
    public String execute(String[] args) {
        if (args.length != 3) {
            return "-ERR wrong number of arguments for 'incrby' command\r\n";
        }

        String key = args[1];
        String value = KeyValueStore.getInstance().get(key);

        try {
            int increment = Integer.parseInt(args[2]);
            int intValue = Integer.parseInt(value);
            intValue += increment;
            KeyValueStore.getInstance().set(key, Integer.toString(intValue));
            return ":" + intValue + "\r\n";
        } catch (NumberFormatException e) {
            return "-ERR value is not an integer\r\n";
        }
    }
}

