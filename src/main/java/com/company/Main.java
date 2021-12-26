package com.company;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException, IOException {
        CSVParser parser = new CSVParser("table.csv");
        SqlAction sql = new SqlAction();
//        for (var a: parser.institutions) {
//            System.out.println(a);
//        }
        //ПЕРВОНАЧАЛЬНОЕ ЗАПОЛНЕНИЕ ТАБЛИЦЫ
        //parser.institutions.forEach(sql::addValue);


        //1 ЗАДАНИЕ
        Map<Integer, Long> hashMap = new LinkedHashMap<>();
        for (var i = 2006; i < 2021; i++) {
            long startDate = DateFormatCommon.dateFormat().parse(String.format("01.01.%s", i)).getTime();
            long endDate = DateFormatCommon.dateFormat().parse(String.format("31.12.%s", i)).getTime();
            hashMap.put(i, Tasks.firstTask(sql.connection, startDate, endDate));
        }

        var dataset = new DefaultCategoryDataset();
        for (var i : hashMap.entrySet()) {
            System.out.println(" год завершения строительства : " + i.getKey() + "   -   объем финансирования : " + i.getValue() + " рублей");
            dataset.setValue(i.getValue(), "общий объём финансирования", i.getKey());
        }
        //ГРАФИК
        JFreeChart chart = ChartFactory.createBarChart("Объём финансирования", "Год", "Объём финансирования", dataset);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(NumberFormat.getIntegerInstance());
        ChartUtils.saveChartAsPNG(new File("graphic.png"), chart, 1200, 600);
        System.out.println("\n");


        //2 ЗАДАНИЕ
        Tasks.secondTask(sql.connection);
        System.out.println("\n");

        //3 ЗАДАНИЕ
        Tasks.thirdTask(sql.connection);
    }
}
