package DAO;

import common.Database;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class StudentDAO {
    public void Create(model.Student s){
        try {

            String sql = "INSERT INTO students (student_id,name,gender,age,score,class_id) VALUES(?,?,?,?,?,?)";
            Database.setConnection();
            Connection conn = Database.getConnection();
            PreparedStatement PStat = conn.prepareStatement(sql);
            PStat.setInt(1, s.getStudent_id());
            PStat.setString(2, s.getName());
            PStat.setString(3, s.getGender());
            PStat.setInt(4, s.getAge());
            PStat.setInt(5, s.getScore());
            if(s.getClass_id()==0)
                PStat.setNull(6, Types.INTEGER);
            else
                PStat.setInt(6, s.getClass_id());

            int row = PStat.executeUpdate();
            if (row > 0) {
                System.out.println("success insert");
            } else {
                System.out.println("fail to insert");
            }
            PStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Update(model.Student s){
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM students WHERE student_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, s.getStudent_id());
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String update_sql = "UPDATE students set name=?,gender=?,age=?,score=?,class_id=? where student_id = ?";
            PreparedStatement UPStat = conn.prepareStatement(update_sql);
            UPStat.setInt(6, s.getStudent_id());
            UPStat.setString(1, s.getName());
            UPStat.setString(2, s.getGender());
            UPStat.setInt(3, s.getAge());
            UPStat.setInt(4, s.getScore());
            if(s.getClass_id()==0)
                UPStat.setNull(5, Types.INTEGER);
            else
                UPStat.setInt(5, s.getClass_id());

            int row = UPStat.executeUpdate();
            if (row > 0) {
                //System.out.println("success update");
            } else {
                System.out.println("fail to update");
            }
            UPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public model.Student SelectById(int id){
        Student test = new Student();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM students WHERE student_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found");
            }
            Srow.next();
            test.setStudent_id(Srow.getInt("student_id"));
            test.setName(Srow.getString("name"));
            test.setGender(Srow.getString("gender"));
            test.setAge(Srow.getInt("age"));
            test.setScore(Srow.getInt("score"));
            test.setClass_id(Srow.getInt("class_id"));
            SPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println("Success Select");
        return test;
    }

    public ArrayList<Student> SelectAll(){
        ArrayList<Student> test = new ArrayList<Student>();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM students ";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("this table is empty");
            }
            while(Srow.next()){
                Student a = new Student(Srow.getInt("student_id")
                        ,Srow.getString("name"),Srow.getString("gender"),
                        Srow.getInt("age"),Srow.getInt("score"),
                        Srow.getInt("class_id"));
                test.add(a);
            }
            SPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println("Success Select");
        return test;
    }
    public ArrayList<Integer> SelectAllId(){
        ArrayList<Integer> test = new ArrayList<Integer>();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT student_id FROM students ";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("this table is empty");
            }
            while(Srow.next()){
                test.add(Srow.getInt("student_id"));
            }
            SPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println("Success Select");
        return test;
    }
    public void DeleteById(int id){
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM students WHERE student_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String Dsql = "DELETE FROM students WHERE student_id = ?";
            PreparedStatement DPStat = conn.prepareStatement(Dsql);
            DPStat.setInt(1, id);
            int row = DPStat.executeUpdate();
            if (row!=1) {
                throw new Exception("Fail to Delete or Delete more than one");
            }
            //System.out.println("success Delete");
            DPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
