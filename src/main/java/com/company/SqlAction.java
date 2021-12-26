package com.company;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlAction {
    private static final String CON = "jdbc:sqlite:institutions.sqlite";

    public final Connection connection;

    public SqlAction() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(CON);
    }

    public void addLine(Institution value) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO general(id, title, object_action, start_date_work, end_date_work, sum_funding, type_sport_complex) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, value.getId());
            statement.setObject(2, value.getTitle());
            statement.setObject(3, value.getObjectAction());
            statement.setObject(4, value.getStartDateWork());
            statement.setObject(5, value.getEndDateWork());
            statement.setObject(6, value.getSumFunding());
            statement.setObject(7, value.getTypeSportComplex());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
