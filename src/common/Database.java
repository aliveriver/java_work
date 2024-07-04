package common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

//抽象类
abstract public class Database { // 数据库初始化
    private static String url = "";
    private static String username = "";
    private static String password = "";
    private static String driver = "";
    private static Connection conn;

    public static void databaseconfig()throws Exception{
        FileInputStream fis = new FileInputStream("src/common/MySQL_config.properties");//根据数据库不同更改这个
        Properties pro = new Properties();
        pro.load(fis);
        fis.close();
        driver = pro.getProperty("driver");
        username = pro.getProperty("user");
        password = pro.getProperty("password");
        url = pro.getProperty("url")+"/"+pro.getProperty("name");
        if(driver.contains("mysql")){
            url = url + "?" + pro.getProperty("serverTimezone");
        }
    }

    public static void setConnection()throws Exception{
        Class.forName(driver);
        conn = DriverManager.getConnection(url,username,password);
    }

    public static Connection getConnection(){
        return conn;
    }

    public static void closeConnection()throws Exception{
        conn.close();
    }
}
