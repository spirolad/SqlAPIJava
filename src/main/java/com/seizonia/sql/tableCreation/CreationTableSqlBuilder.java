package com.seizonia.sql.tableCreation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;


public class CreationTableSqlBuilder {

    private Map<String, ColumnType> columnTypeMap;
    private String tableName;
    private Connection connection;

    public CreationTableSqlBuilder(String tableName, Connection connection) {
        this.columnTypeMap = new TreeMap<>();
        this.tableName = tableName;
        this.connection = connection;
    }

    public void generateRequest() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getRequestString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getRequestString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, ColumnType> columnTypeEntry : columnTypeMap.entrySet()) {
            builder.append((builder.toString().equals("")) ? "" : ",").append(columnTypeEntry.getKey()).append(" ").append(columnTypeEntry.getValue().getSqlValue().replace("%table%", columnTypeEntry.getKey()));
        }
        return "CREATE TABLE IF NOT EXISTS `" + tableName + "`(" + builder + ") ENGINE = InnoDB";
    }

    public void addColumn(String columnName, ColumnType columnType) {
        this.columnTypeMap.put(columnName, columnType);
    }

    public Map<String, ColumnType> getColumnTypeMap() {
        return columnTypeMap;
    }

}

