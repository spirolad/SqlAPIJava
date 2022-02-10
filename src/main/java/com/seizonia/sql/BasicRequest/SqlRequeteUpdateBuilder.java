package com.seizonia.sql.BasicRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class SqlRequeteUpdateBuilder {

    private Map<String, Object> objectMap;
    private String table_name;
    private Connection connection;
    private String where;
    private Object value;


    public SqlRequeteUpdateBuilder(String table_name, Connection connection, String where, Object value){
        objectMap = new LinkedHashMap<String, Object>();
        this.table_name = table_name;
        this.connection = connection;
        this.where = where;
        this.value = value;
    }

    public void generateRequest() {
        StringBuilder sql_value = new StringBuilder();
        objectMap.forEach((entry, key) -> {
            sql_value.append((sql_value.toString().equalsIgnoreCase("")) ? entry+" = ?" : ", "+entry+" = ?");
        });
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE " + table_name + " SET " + sql_value + " WHERE " + where + " = ?");
            int i = 1;
            for (Object o : objectMap.values()) {
                statement.setObject(i, o);
                i++;
            }
            statement.setObject(i, value);
            statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Une erreur SQL est arrivé: " + this.getClass().getName());
        } finally {
            try {
                statement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public SqlRequeteUpdateBuilder add(String data, Object value){
        this.objectMap.put(data, value);
        return this;
    }

    public Map<String, Object> getObjectMap() { return objectMap; }

}
