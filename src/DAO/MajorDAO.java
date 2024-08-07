package DAO;

import common.Database;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MajorDAO {
    public void Create(model.Major m){
        try {
            String sql = "INSERT INTO majors (department_id,name) VALUES(?,?)";
            Database.setConnection();
            Connection conn = Database.getConnection();
            PreparedStatement PStat = conn.prepareStatement(sql);
            PStat.setInt(1, m.getDepartment_id());
            PStat.setString(2, m.getName());
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

    public void Update(model.Major m){
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM majors WHERE major_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, m.getMajor_id());
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String update_sql = "UPDATE majors set department_id=?,name=? where major_id = ?";
            PreparedStatement UPStat = conn.prepareStatement(update_sql);
            UPStat.setInt(3, m.getMajor_id());
            UPStat.setString(2, m.getName());
            UPStat.setInt(1, m.getDepartment_id());

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
    public Major SelectById(int id) {
        Major test = null; // 初始化为null
        Connection conn = null;
        PreparedStatement SPStat = null;
        ResultSet Srow = null;
        try {
            Database.setConnection();
            conn = Database.getConnection();
            String select_sql = "SELECT * FROM majors WHERE major_id = ?";
            SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select: ID is not found");
                return null; // 返回null表示未找到
            }
            Srow.next();
            test = new Major();
            test.setMajor_id(Srow.getInt("major_id"));
            test.setName(Srow.getString("name"));
            test.setDepartment_id(Srow.getInt("department_id"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (Srow != null) Srow.close();
                if (SPStat != null) SPStat.close();
                if (conn != null) Database.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return test;
    }


    public ArrayList<Integer> SelectByDepartment_id(int id){
        ArrayList<Integer> ids = new ArrayList<>();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT major_id FROM majors WHERE department_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found");
            }
            while(Srow.next())
            {
                ids.add(Srow.getInt("major_id"));
            }
            SPStat.close();
            Database.closeConnection();
        }catch(Exception e) {
            e.printStackTrace();
        }
        //System.out.println("Success Select");
        return ids;
    }
    public ArrayList<Major> SelectAll(){
        ArrayList<Major> test = new ArrayList<Major>();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM majors ";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("this table is empty");
            }
            while(Srow.next()){
                Major a = new Major(Srow.getInt("major_id"),
                        Srow.getInt("department_id"),Srow.getString("name"));
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
            String select_sql = "SELECT * FROM majors WHERE major_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String Dsql = "DELETE FROM majors WHERE major_id = ?";
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

    public int getMajorIdByName(String name) {
        int major_id = -1;
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT major_id FROM majors WHERE name = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setString(1, name);
            ResultSet Srow = SPStat.executeQuery();
            if (Srow.next()) {
                major_id = Srow.getInt("major_id");
            } else {
                System.out.println("Major not found");
            }
            SPStat.close();
            Database.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return major_id;
    }
}
