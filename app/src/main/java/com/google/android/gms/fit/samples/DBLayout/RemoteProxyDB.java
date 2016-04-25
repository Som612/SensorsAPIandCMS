package com.google.android.gms.fit.samples.DBLayout;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Created by sheyda on 4/25/16.
 */
public class RemoteProxyDB {


    public static final String connectionString = "jdbc:mysql://mysql2.gear.host/countmysteps";
    public static final String dbUsername = "countmysteps";
    public static final String dbPassword = "java2016!";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String TABLE_NAME_FRIENDS = "friends_table";
    public static final String TABLE_NAME_USERS = "users_table";

    private Connection connection;

    private String TAG = RemoteProxyDB.class.getSimpleName();


    public RemoteProxyDB() {
        connection = null;

        try {

            Class.forName(DRIVER);
            connection = DriverManager.getConnection(connectionString, dbUsername, dbPassword);




        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }


    }

    public  boolean addUserData(String Username, String Password){

        boolean result = false;

        try {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO " + TABLE_NAME_USERS + "(username, password) " + "VALUES(?,?)");
            ps.setString(1, Username);
            ps.setString(2, Password);

            result = ps.execute();
            ps.close();

        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }

        return result;
    }

    public  boolean addFriendsData(String Username, String f1, String f2, String f3, String f4, String f5){

        boolean result = false;

        try {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO " + TABLE_NAME_FRIENDS + "(username, password) " + "VALUES(?,?,?,?,?,?)");
            ps.setString(1, Username);
            ps.setString(2, f1);
            ps.setString(3, f2);
            ps.setString(4, f3);
            ps.setString(5, f4);
            ps.setString(6, f5);

            result = ps.execute();
            ps.close();

        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }

        return result;
    }


    public HashMap<String, String> getFriendsList(String Username){

        HashMap<String, String> list = new HashMap<String, String>();
        String query = "SELECT * FROM " + TABLE_NAME_FRIENDS + " WHERE Username='" + Username
                + "'";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs.next()){
                list.put("username", rs.getString("Username"));
                list.put("f1", rs.getString("f1"));
                list.put("f2", rs.getString("f2"));
                list.put("f3", rs.getString("f3"));
                list.put("f4", rs.getString("f4"));
                list.put("f5", rs.getString("f5"));

            }


        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }

        return list;
    }

    public HashMap<String, String> getPassword(String Username){

        HashMap<String, String> list = new HashMap<String, String>();
        String query = "SELECT * FROM " + TABLE_NAME_USERS + " WHERE Username='" + Username
                + "'";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs.next()){
                list.put("username", rs.getString("Username"));
                list.put("password", rs.getString("Password"));

            }


        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }

        return list;
    }



}
