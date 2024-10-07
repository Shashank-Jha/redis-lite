package com.redislite.storage;

import java.util.HashMap;
import java.util.Map;

public class KeyValueStore {
    private static KeyValueStore instance;
    private final Map<String, String> store;

    private KeyValueStore() {
        store = new HashMap<>();
    }

    public static KeyValueStore getInstance() {
        if (instance == null) {
            instance = new KeyValueStore();
        }
        return instance;
    }

    public void set(String key, String value) {
        store.put(key, value);
    }

    public String get(String key) {
        return store.get(key);
    }
}
