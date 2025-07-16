package utils;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            reader.skip(1); // Skip header
            data = reader.readAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
