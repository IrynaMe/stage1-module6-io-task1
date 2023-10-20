package com.epam.mjc.io;

import java.io.*;

public class FileReader {
// https://github.com/IrynaMe/stage1-module6-io-task1/blob/21a894d16d9cc18db489ffabe46e5096bb9caa23/src/main/resources/Profile.txt

    public Profile getDataFromFile(File file) {
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

           /* fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream);
            reader = new BufferedReader(inputStreamReader);
*/
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
                            System.out.println("Unknown key");
                            break;
                    }
                } else {
                    System.out.println("Unknown line format");
                }
            }

            return new Profile(name, age, email, phone);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}