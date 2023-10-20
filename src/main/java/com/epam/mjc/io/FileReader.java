package com.epam.mjc.io;

import java.io.*;
// https://github.com/IrynaMe/stage1-module6-io-task1/blob/21a894d16d9cc18db489ffabe46e5096bb9caa23/src/main/resources/Profile.txt


public class FileReader {

    public Profile getDataFromFile(File file) {

        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line;
            String name = null;
            Integer age = null;
            String email = null;
            Long phone = null;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    switch (key) {
                        case "Name":
                            name = value;
                            break;
                        case "Age":
                            age = Integer.parseInt(value);
                            break;
                        case "Email":
                            email = value;
                            break;
                        case "Phone":
                            phone = Long.parseLong(value);
                            break;
                        default:
                            break;
                    }
                }
            }

            return new Profile(name, age, email, phone);
        }
        catch (IOException e) {
            throw new RuntimeException("Error during reading data from file", e);
        }
    }
}