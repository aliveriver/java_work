package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
//抽象类
abstract public class Database { //数据库初始化
    //MySQL 的数据库建立
//    private static String url = "jdbc:mysql://localhost:3306/java_work?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai";
//    private static String username = "root";
//    private static String password = "123456";
    private static Connection conn;

    //Linx的Connection
    //PgAdmin 4 是一个用于管理PostgreSQL数据库的工具，而不是MySQL。 **** postgresql-x64-15
    private static String url = "jdbc:postgresql://127.0.0.1:5433/university";
    private static String username = "postgres";
    private static String password = "Miku2020";
    public static void setConnection()throws Exception{
        //注册驱动 分别是Mysql 和 postgres驱动
        //Class.forName("com.mysql.jdbc.Driver");
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(url,username,password);
    }

    public static Connection getConnection(){
        return conn;
    }

    public static void closeConnection()throws Exception{
        conn.close();
    }
}
