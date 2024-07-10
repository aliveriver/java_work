package DAO;

import common.Database;
import model.Class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ClassDAO {

    public int Create(model.Class c) {
        int classId = -1;
        try {
            String sql = "INSERT INTO classes (major_id, university_id, class_name) VALUES(?,?,?)";
            Database.setConnection();
            Connection conn = Database.getConnection();
            PreparedStatement PStat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            PStat.setInt(1, c.getMajor_id());
            PStat.setInt(2, c.getUniversity_id());
            PStat.setString(3, c.getClass_name());

            int row = PStat.executeUpdate();
            if (row > 0) {
                ResultSet rs = PStat.getGeneratedKeys();
                if (rs.next()) {
                    classId = rs.getInt(1);
                }
                rs.close();
            }
            PStat.close();
            Database.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classId;
    }


    public void Update(model.Class c){
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM classes WHERE class_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, c.getClass_id());
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String update_sql = "UPDATE classes set major_id=?,University_id=?,Class_name=? where class_id = ?";
            PreparedStatement UPStat = conn.prepareStatement(update_sql);
            UPStat.setInt(4, c.getClass_id());
            UPStat.setString(3, c.getClass_name());
            UPStat.setInt(2, c.getUniversity_id());
            UPStat.setInt(1, c.getMajor_id());


            int row = UPStat.executeUpdate();
            if (row > 0) {
                //System.out.println("success update");
            } else {
                //System.out.println("fail to update");
            }
            UPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public model.Class SelectById(int id){
        Class test = new Class();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM classes WHERE class_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found");
            }
            Srow.next();
            test.setClass_id(Srow.getInt("class_id"));
            test.setMajor_id(Srow.getInt("major_id"));
            test.setUniversity_id(Srow.getInt("University_id"));
            test.setClass_name(Srow.getString("class_name"));
            SPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println("Success Update");
        return test;
    }

    public ArrayList<Class> SelectAll(){
        ArrayList<Class> test = new ArrayList<Class>();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM classes ";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("this table is empty");
            }
            while(Srow.next()){
                Class a = new Class(Srow.getInt("class_id"),
                        Srow.getInt("major_id"),
                        Srow.getString("class_name"),
                        Srow.getInt("University_id")
                        );
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
            String select_sql = "SELECT * FROM classes WHERE class_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String Dsql = "DELETE FROM classes WHERE class_id = ?";
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
