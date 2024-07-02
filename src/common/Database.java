package common;

import java.sql.Connection;
import java.sql.DriverManager;
//抽象类
abstract public class Database { //数据库初始化
    private static final String url = "jdbc:mysql://localhost:3306/java_work?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai";
    private static final String username = "root";
    private static final String password = "123456";
    private static Connection conn;

    public static void setConnection()throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url,username,password);
    }

    public static Connection getConnection(){
        return conn;
    }

    public static void closeConnection()throws Exception{
        conn.close();
    }
}
