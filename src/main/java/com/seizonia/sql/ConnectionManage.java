package com.seizonia.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ConnectionManage {

    private String ip, user, password, database;
    private Connection connection;

    public ConnectionManage(String ip, String user, String password, String database){
        this.ip = ip;
        this.user = user;
        this.password = password;
        this.database = database;
        try {
             connection();
             update();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void connection() throws SQLException {
        connection = DriverManager.getConnection( "jdbc:mysql://" + ip, user, password);
    }

    public void update() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    if(isConnected()) disconect();
                    connection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, new Date(), 7_200_000L);
    }

    public void disconect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Boolean isConnected() { return connection != null; }

    public Connection getConnection(){ return this.connection; }

}
