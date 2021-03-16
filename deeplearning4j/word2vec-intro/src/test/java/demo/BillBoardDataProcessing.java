package demo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.nd4j.linalg.io.ClassPathResource;

import java.io.*;
import java.util.List;

public class BillBoardDataProcessing {
    public static void main(String[] args) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(new ClassPathResource("billboard_lyrics_1964-2015.csv").getFile().getAbsolutePath()));
        List<String[]> list = reader.readAll();
        try {
            FileWriter writer = new FileWriter(new File("billboard_lyrics_1964-2015.txt"));
            for(String[] row : list) {
                System.out.println(row[4]);
                writer.write(row[4]);
                writer.write(System.lineSeparator());
            }
            writer.flush();
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
