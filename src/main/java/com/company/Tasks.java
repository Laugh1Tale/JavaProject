package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

public class Tasks {
    public static long firstTask(Connection connection, long startDate, long endDate) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT SUM(sum_funding) FROM general WHERE end_date_work <= ? AND end_date_work >= ? ")) {
            statement.setObject(1, endDate);
            statement.setObject(2, startDate);

            return statement.executeQuery().getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void secondTask(Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT AVG(sum_funding) FROM general WHERE start_date_work >= ? AND start_date_work <= ?")) {
            statement.setObject(1, DateFormatCommon.dateFormat().parse(String.format("01.01.%s", 2012)).getTime());
            statement.setObject(2, DateFormatCommon.dateFormat().parse(String.format("31.12.%s", 2012)).getTime());

            System.out.println("Средний общий объем финансирования за 2012 год: " + statement.executeQuery().getLong(1) + " рублей");
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void thirdTask(Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT title, sum_funding FROM general WHERE (type_sport_complex == 'многофункциональный спортивный комплекс' OR type_sport_complex == 'стадион') ORDER BY sum_funding DESC LIMIT 1")) {
            System.out.printf("Постройка среди многофункциональных спортивных комплексов и стадионов.\n");
            System.out.printf("Название постройки: %s\nОбщий объем финансирования постройки: %s рублей%n", statement.executeQuery().getString(1), statement.executeQuery().getLong(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
