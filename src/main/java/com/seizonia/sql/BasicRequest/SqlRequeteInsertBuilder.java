package com.seizonia.sql.BasicRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class SqlRequeteInsertBuilder {

    private Map<String, Object> objectMap;
    private String table_name;
    private Connection connection;

    /**
     * @param table_name -> the name of the table where you want insert someone
     * @param connection
     */

    public SqlRequeteInsertBuilder(String table_name, Connection connection){
        objectMap = new LinkedHashMap<>();
        this.table_name = table_name;
        this.connection = connection;
    }

    public void generateRequest() {
        StringBuilder sql_data = new StringBuilder();
        StringBuilder sql_value = new StringBuilder();
        objectMap.forEach((entry, key) -> {
            sql_data.append((sql_data.toString().equalsIgnoreCase("")) ? entry : "," + entry);
            sql_value.append((sql_value.toString().equalsIgnoreCase("")) ? "?" : ",?");
        });
        PreparedStatement statement = null;
        try {

            statement = connection.prepareStatement("INSERT INTO " + table_name + "(" + sql_data + ") VALUES (" + sql_value + ")");
            int i = 1;
            for (Object o : objectMap.values()) {
                statement.setObject(i, o);
                i++;
            }
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("");
            System.out.println("Une erreur SQL est arrivé: " + this.getClass().getName());
        } finally {
            try {
                statement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public SqlRequeteInsertBuilder add(String data, Object value){
        this.objectMap.put(data, value);
        return this;
    }

    public Map<String, Object> getObjectMap() { return objectMap; }
}
