package com.seizonia.sql.BasicRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlRequeteSelectBuilder {

    private String table_name, where;
    private Connection connection;
    private String select;
    private Object value;

    /**
     *
     * @param table_name -> the name of the table
     * @param connection -> the connection of your request
     * @param select -> what you want select. * if you want select all of the data
     * @param where -> the column where you want get
     * @param value -> the value of the where
     *
     */

    public SqlRequeteSelectBuilder(String table_name, Connection connection, String select, String where, Object value){
        this.table_name = table_name;
        this.connection = connection;
        this.select = select;
        this.where = where;
        this.value = value;
    }

    public SqlRequeteSelectBuilder(String table_name, Connection connection, String select){
        this.table_name = table_name;
        this.connection = connection;
        this.select = select;
    }

    /**
     * @return you get the resultset of the request builder
     * @dontbestupid : CLOSE THE RESULTSET
     */

    public ResultSet generateRequest() {
        PreparedStatement statement = null;
        try {
            String request = "SELECT " + select + " FROM " + table_name + ((value == null) ? "" : " WHERE " + where + " = ?");
            statement = connection.prepareStatement(request);
            if(value != null) statement.setObject(1, value);
            return statement.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("[SoCore] Une erreur SQL est arrivé: " + this.getClass().getName());
        }
        return null;
    }
}
