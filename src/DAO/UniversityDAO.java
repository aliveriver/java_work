package DAO;

import common.Database;
import model.University;
import model.Major;

import java.sql.*;
import java.util.ArrayList;

public class UniversityDAO {

    public void Create(model.University u){
        try {
            String sql = "INSERT INTO universities (name,location) VALUES(?,?)";
            Database.setConnection();
            Connection conn = Database.getConnection();
            PreparedStatement PStat = conn.prepareStatement(sql);
            PStat.setString(1, u.getName());
            PStat.setString(2, u.getLocation());
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

    public void Update(model.University u){
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM universities WHERE university_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, u.getUniversity_id());
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String update_sql = "UPDATE universities set name=?,location=? where university_id = ?";
            PreparedStatement UPStat = conn.prepareStatement(update_sql);
            UPStat.setInt(3, u.getUniversity_id());
            UPStat.setString(1, u.getName());
            UPStat.setString(2, u.getLocation());
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

    public model.University SelectById(int id) {
        University test = new University();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM universities WHERE university_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found");
            }
            Srow.next();
            test.setLocation(Srow.getString("location"));
            test.setName(Srow.getString("name"));
            test.setUniversity_id(Srow.getInt("university_id"));
            SPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return test;
    }

    public ArrayList<University> SelectAll() {
        ArrayList<University> test = new ArrayList<University>();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM universities ";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("this table is empty");
            }
            while(Srow.next()){
                University a = new University(Srow.getInt("university_id"),Srow.getString("name"),Srow.getString("location"));
                test.add(a);
            }
            SPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return test;
    }

    public void DeleteById(int id) {
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM universities WHERE university_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String Dsql = "DELETE FROM universities WHERE university_id = ?";
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

    public int getUniversityIdByName(String name) {
        int university_id = -1;
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT university_id FROM universities WHERE name = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setString(1, name);
            ResultSet Srow = SPStat.executeQuery();
            if (Srow.next()) {
                university_id = Srow.getInt("university_id");
            } else {
                System.out.println("University not found");
            }
            SPStat.close();
            Database.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return university_id;
    }

}
