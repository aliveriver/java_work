package DAO;

import common.Database;
import model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CourseDAO {

    public void Create(model.Course c){
        try {
            String sql = "INSERT INTO courses (major_id,department_id,Course_name) VALUES(?,?,?)";
            Database.setConnection();
            Connection conn = Database.getConnection();
            PreparedStatement PStat = conn.prepareStatement(sql);
            PStat.setInt(1, c.getMajor_id());
            PStat.setInt(2, c.getDepartment_id());
            PStat.setString(3, c.getCourse_name());

            int row = PStat.executeUpdate();
            if (row > 0) {
                //System.out.println("success insert");
            } else {
                //System.out.println("fail to insert");
            }
            PStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Update(model.Course c){
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM courses WHERE Courses_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, c.getCourse_id());
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String update_sql = "UPDATE courses set major_id=?,department_id=?,Course_name=? where Courses_id = ?";
            PreparedStatement UPStat = conn.prepareStatement(update_sql);
            UPStat.setInt(4, c.getCourse_id());
            UPStat.setString(3, c.getCourse_name());
            UPStat.setInt(2, c.getDepartment_id());
            UPStat.setInt(1, c.getMajor_id());


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

    public model.Course SelectById(int id){
        Course test = new Course();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM courses WHERE Courses_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found");
            }
            Srow.next();
            test.setCourse_id(Srow.getInt("Courses_id"));
            test.setMajor_id(Srow.getInt("major_id"));
            test.setDepartment_id(Srow.getInt("department_id"));
            test.setCourse_name(Srow.getString("course_name"));
            SPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println("Success Update");
        return test;
    }

    public ArrayList<Course> SelectAll(){
        ArrayList<Course> test = new ArrayList<Course>();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM courses ";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("this table is empty");
            }
            while(Srow.next()){
                Course a = new Course(Srow.getInt("Courses_id"),
                        Srow.getInt("major_id"),
                        Srow.getInt("department_id"),
                        Srow.getString("course_name"));
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

    public void DeleteById(int id){
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM courses WHERE Courses_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String Dsql = "DELETE FROM courses WHERE Courses_id = ?";
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
    public ArrayList<Course> SelectByMajorId(int major_id) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM courses WHERE major_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, major_id);
            ResultSet Srow = SPStat.executeQuery();
            while (Srow.next()) {
                Course course = new Course();
                course.setCourse_id(Srow.getInt("course_id"));
                course.setCourse_name(Srow.getString("course_name"));
                courses.add(course);
            }
            SPStat.close();
            Database.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    public String getCourseNameById(int id) {
        String courseName = null;
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT course_name FROM courses WHERE Courses_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (Srow.next()) {
                courseName = Srow.getString("course_name");
            }
            SPStat.close();
            Database.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseName;
    }

}
