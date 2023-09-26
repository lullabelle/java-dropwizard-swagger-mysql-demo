package org.kainos.ea.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Orderdao {
    private static Connection conn;

    private static Connection getConnection() throws SQLException{
        String user, password, host, name;

          if (conn != null && !conn.isClosed())  {
              try{
                  FileInputStream propStream = new FileInputStream("db.properties");

                  Properties props = new Properties();
                  props.load(propStream);

                  user = props.getProperty("user");
                  password = props.getProperty("password");
                  host = props.getProperty("host");
                  name = props.getProperty("name");

                  if (user ==null || password ==null || host == null)
                   throw new IllegalArgumentException("Properties file must exist " +
                           "and must contain a user, password name and host properties.");

                  conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + name + "?useSSL=false", user, password);
                  return conn;
              }catch (Exception e){
                  System.err.println(e.getMessage());
              }

          }
        return null;
    }
}
