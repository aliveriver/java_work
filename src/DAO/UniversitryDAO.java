package DAO;

import model.University;

import java.sql.*;

public class UniversitryDAO {

    public void Create(model.University u){
        try {
            String sql = "INSERT INTO universities (university_id,name,location) VALUES(?,?,?)";
            Database.setConnection();
            Connection conn = Database.getConnection();
            PreparedStatement PStat = conn.prepareStatement(sql);
            PStat.setInt(1, u.getUniversity_id());
            PStat.setString(2, u.getName());
            PStat.setString(3, u.getLocation());
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

    public void Upadte(model.University u){
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM universities WHERE university_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, u.getUniversity_id());
            ResultSet Srow = SPStat.executeQuery();
            if (Srow.getRow()!=1) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String update_sql = "UPDATE universities set name=?,location=? where university_id = ?";
            PreparedStatement UPStat = conn.prepareStatement(update_sql);
            UPStat.setInt(1, u.getUniversity_id());
            UPStat.setString(2, u.getName());
            UPStat.setString(3, u.getLocation());
            int row = UPStat.executeUpdate();
            if (row > 0) {
                System.out.println("success update");
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
            if (Srow.getRow()!=1) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
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

    public void SelectAll() {

    }

    public void Delete(model.University u) {

    }
}
