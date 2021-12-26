package com.company;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVParser {
    public List<Institution> institutions = new ArrayList<>();

    public CSVParser(String path) {
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] values;
            reader.readNext();
            while ((values = reader.readNext()) != null) {
                if (values[0].length() > 6) break;
                Institution institution = new Institution(Integer.parseInt(values[0]), values[1], values[17], checkEmptyDate(values[18]), checkEmptyDate(values[19]), Long.parseLong(values[20]), values[43]);
                institutions.add(institution);
            }
        } catch (IOException | CsvValidationException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static Date checkEmptyDate(String date) throws ParseException {
        if (date.isEmpty()) {
            return DateFormatCommon.dateFormat().parse("01.01.1900");
        }
        return DateFormatCommon.dateFormat().parse(date);
    }
}