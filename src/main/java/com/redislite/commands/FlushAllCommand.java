package com.redislite.commands;

import com.redislite.storage.KeyValueStore;

public class FlushAllCommand implements Command {
    @Override
    public String execute(String[] args) {
        KeyValueStore.getInstance().flushAll();
        return "OK\r\n";
    }
}

