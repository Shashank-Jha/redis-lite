package com.redislite.utils;

import java.io.BufferedReader;
import java.io.IOException;

public class RESPParser {

    public String[] parse(BufferedReader reader) throws IOException {
        
        String inputLine = reader.readLine();

        // Check for RESP array
        if (inputLine.startsWith("*")) {
            int argCount = Integer.parseInt(inputLine.substring(1));
            String[] tokens = new String[argCount];

            for (int i = 0; i < argCount; i++) {
                String lengthLine = reader.readLine();

                // Handle bulk strings (prefixed by $)
                if (lengthLine.startsWith("$")) {
                    int length = Integer.parseInt(lengthLine.substring(1));
                    char[] value = new char[length];
                    reader.read(value, 0, length);
                    reader.readLine(); // Consume CRLF after the bulk string
                    tokens[i] = new String(value);
                } 
                // Handle integers (prefixed by :)
                else if (lengthLine.startsWith(":")) {
                    tokens[i] = lengthLine.substring(1); // Extract integer as string
                } 
                // Throw an error for unsupported types
                else {
                    throw new IOException("Unexpected RESP format");
                }
            }

            return tokens;
        }

        // If it's not an array, fall back to splitting the input by spaces
        return inputLine.split(" ");
    }

}
