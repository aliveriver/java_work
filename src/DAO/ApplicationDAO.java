package DAO;

import common.Database;
import model.Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ApplicationDAO {

    public void Create(model.Application a){
        try {
            String sql = "INSERT INTO applications (application_id,student_id,university_id,department_id,major_id,is_adjustment) VALUES(?,?,?,?,?,?)";
            Database.setConnection();
            Connection conn = Database.getConnection();
            PreparedStatement PStat = conn.prepareStatement(sql);
            PStat.setInt(1, a.getApplication_id());
            PStat.setInt(2, a.getStudent_id());
            PStat.setInt(3, a.getUniversity_id());
            PStat.setInt(4, a.getDepartment_id());
            PStat.setInt(5, a.getMajor_id());
            PStat.setInt(6, a.getIs_adjustment());


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

    public void Update(model.Application a){
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM applications WHERE application_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, a.getApplication_id());
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String update_sql = "UPDATE applications set student_id=?,university_id=?,department_id=?,major_id=?,is_adjustment=? where application_id = ?";
            PreparedStatement UPStat = conn.prepareStatement(update_sql);
            UPStat.setInt(6, a.getApplication_id());
            UPStat.setInt(5, a.getIs_adjustment());
            UPStat.setInt(4, a.getMajor_id());
            UPStat.setInt(3, a.getDepartment_id());
            UPStat.setInt(2, a.getUniversity_id());
            UPStat.setInt(1, a.getStudent_id());


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

    public model.Application SelectById(int id){
        Application test = new Application();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM applications WHERE application_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found");
            }
            Srow.next();
            test.setApplication_id(Srow.getInt("application_id"));
            test.setStudent_id(Srow.getInt("student_id"));
            test.setUniversity_id(Srow.getInt("university_id"));
            test.setDepartment_id(Srow.getInt("department_id"));
            test.setMajor_id(Srow.getInt("major_id"));
            test.setIs_adjustment(Srow.getInt("is_adjustment"));

            SPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Success Update");
        return test;
    }
    public ArrayList<model.Application> SelectBystudent_id(int id)
    {
        ArrayList<Application> temp = new ArrayList<>();
        try
        {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM applications WHERE student_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found");
            }
            while(Srow.next())
            {
                Application test = new Application(Srow.getInt("application_id"),
                        Srow.getInt("student_id"),
                        Srow.getInt("university_id"),
                        Srow.getInt("department_id"),
                        Srow.getInt("major_id"),
                        Srow.getInt("is_adjustment")
                );
                temp.add(test);
            }
            SPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Success Select");
        return temp;
    }
    public ArrayList<Application> SelectAll(){
        ArrayList<Application> test = new ArrayList<Application>();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM applications ";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("this table is empty");
            }
            while(Srow.next()){
                Application a = new Application(Srow.getInt("application_id"),
                        Srow.getInt("student_id"),
                        Srow.getInt("university_id"),
                        Srow.getInt("department_id"),
                        Srow.getInt("major_id"),
                        Srow.getInt("is_adjustment")
                );
                test.add(a);
            }
            SPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Success Select");
        return test;
    }

    public void DeleteById(int id){
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM applications WHERE application_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String Dsql = "DELETE FROM applications WHERE application_id = ?";
            PreparedStatement DPStat = conn.prepareStatement(Dsql);
            DPStat.setInt(1, id);
            int row = DPStat.executeUpdate();
            if (row!=1) {
                throw new Exception("Fail to Delete or Delete more than one");
            }
            System.out.println("success Delete");
            DPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
