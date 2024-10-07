package com.redislite.commands;

public interface Command {
    String execute(String[] args);
}