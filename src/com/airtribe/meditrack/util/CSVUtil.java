package com.airtribe.meditrack.util;

import java.io.*;
import java.util.*;

public class CSVUtil {

    public static void saveToCSV(String fileName, List<String> rows) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String row : rows) {
                writer.write(row);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> loadFromCSV(String fileName) {
        List<String> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                rows.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static String[] parseCSVLine(String line) {
        return line.split(",");
    }
}