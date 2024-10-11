package com.redislite.commands;

import com.redislite.storage.KeyValueStore;

public class IncrCommand implements Command {
    @Override
    public String execute(String[] tokens) {
        if (tokens.length != 2) {
            return "-ERR wrong number of arguments for 'incr' command\r\n";
        }

        String key = tokens[1];
        KeyValueStore store = KeyValueStore.getInstance();

        // Retrieve the current value, if null treat it as zero
        String value = store.get(key);
        int currentValue = 0;

        if (value != null) {
            try {
                currentValue = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return "-ERR value is not an integer or out of range\r\n";
            }
        }

        // Increment the value
        currentValue++;
        store.set(key, String.valueOf(currentValue));

        // Return the new value in RESP integer format
        return ":" + currentValue + "\r\n";  // Correct RESP format for integers
    }
}
