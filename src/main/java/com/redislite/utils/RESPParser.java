package com.redislite.utils;

import java.io.IOException;
import java.io.BufferedReader;

public class RESPParser {

    public String[] parse(BufferedReader reader) throws IOException {
        String inputLine = reader.readLine();
        System.out.println("input is :: "+inputLine);
        if (inputLine.startsWith("*")) {
            // Number of arguments
            int argCount = Integer.parseInt(inputLine.substring(1));
            String[] tokens = new String[argCount];

            for (int i = 0; i < argCount; i++) {
                // Read the line that gives the length of the bulk string
                String lengthLine = reader.readLine();
                if (!lengthLine.startsWith("$")) {
                    throw new IOException("Expected bulk string length indicator");
                }

                // Read the actual bulk string value
                int length = Integer.parseInt(lengthLine.substring(1));
                char[] value = new char[length];
                reader.read(value, 0, length);  // Read the bulk string
                reader.readLine(); // Consume the CRLF after the bulk string

                tokens[i] = new String(value);
            }

            return tokens;
        }

        // If it's not an array, fall back to splitting the input by spaces
        return inputLine.split(" ");
    }

}
