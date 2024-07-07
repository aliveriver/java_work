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
            String sql = "INSERT INTO applications (application_id,student_id,university_id,department_id,major_id,is_adjustment) VALUES (?,?,?,?,?,?)";
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Update(model.Application a){
        try{
            Database.setConnection();
            Connection conn = Database.getConnection();
            String sql = "SELECT * FROM applications WHERE application_id = ?";
            PreparedStatement PStat0 = conn.prepareStatement(sql);
            PStat0.setInt(1,a.getApplication_id());
            ResultSet rs = PStat0.executeQuery();
            if(!rs.next()){
                System.out.println("update no result.");
            }else{
                String sqlUpdate = "UPDATE applications SET student_id=?,university_id=?,department_id=?,major_id=?,is_adjustment=? WHERE application_id=?";
                PreparedStatement PStat = conn.prepareStatement(sqlUpdate);
                PStat.setInt(1, a.getStudent_id());
                PStat.setInt(2, a.getUniversity_id());
                PStat.setInt(3, a.getDepartment_id());
                PStat.setInt(4, a.getMajor_id());
                PStat.setInt(5, a.getIs_adjustment());
                PStat.setInt(6, a.getApplication_id());

                int row = PStat.executeUpdate();
                if (row > 0) {
                    System.out.println("success update");
                } else {
                    System.out.println("fail to update");
                }
                PStat.close();
                Database.closeConnection();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public model.Application SelectById(int id){
        model.Application result = null;
        try{
            String sql = "SELECT * FROM applications WHERE application_id = ?";
            Database.setConnection();
            Connection conn = Database.getConnection();
            PreparedStatement PStat = conn.prepareStatement(sql);
            PStat.setInt(1, id);
            ResultSet rs = PStat.executeQuery();

            while(rs.next()){
                result = new Application();
                result.setApplication_id(rs.getInt("application_id"));
                result.setStudent_id(rs.getInt("student_id"));
                result.setUniversity_id(rs.getInt("university_id"));
                result.setDepartment_id(rs.getInt("department_id"));
                result.setMajor_id(rs.getInt("major_id"));
                result.setIs_adjustment(rs.getInt("is_adjustment"));
            }

            PStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Application> SelectAll() {
        ArrayList<Application> applications = new ArrayList<>();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String sql = "SELECT * FROM applications";
            PreparedStatement PStat = conn.prepareStatement(sql);
            ResultSet rs = PStat.executeQuery();

            while (rs.next()) {
                Application application = new Application();
                application.setApplication_id(rs.getInt("application_id"));
                application.setStudent_id(rs.getInt("student_id"));
                application.setUniversity_id(rs.getInt("university_id"));
                application.setDepartment_id(rs.getInt("department_id"));
                application.setMajor_id(rs.getInt("major_id"));
                application.setIs_adjustment(rs.getInt("is_adjustment"));
                applications.add(application);
            }

            PStat.close();
            Database.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applications;
    }

    public void DeleteById(int id){
        try{
            Database.setConnection();
            Connection conn = Database.getConnection();
            String sql = "DELETE FROM applications WHERE application_id = ?";
            PreparedStatement PStat = conn.prepareStatement(sql);
            PStat.setInt(1, id);

            int row = PStat.executeUpdate();
            if (row > 0) {
                System.out.println("success delete");
            } else {
                System.out.println("fail to delete");
            }

            PStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Application> SelectBystudent_id(int student_id) {
        ArrayList<Application> applications = new ArrayList<>();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String sql = "SELECT * FROM applications WHERE student_id = ?";
            PreparedStatement PStat = conn.prepareStatement(sql);
            PStat.setInt(1, student_id);
            ResultSet rs = PStat.executeQuery();

            while (rs.next()) {
                Application application = new Application();
                application.setApplication_id(rs.getInt("application_id"));
                application.setStudent_id(rs.getInt("student_id"));
                application.setUniversity_id(rs.getInt("university_id"));
                application.setDepartment_id(rs.getInt("department_id"));
                application.setMajor_id(rs.getInt("major_id"));
                application.setIs_adjustment(rs.getInt("is_adjustment"));
                applications.add(application);
            }

            PStat.close();
            Database.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applications;
    }

    public int GetMaxApplicationId() {
        int maxId = 0;
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String sql = "SELECT MAX(application_id) AS max_id FROM applications";
            PreparedStatement PStat = conn.prepareStatement(sql);
            ResultSet rs = PStat.executeQuery();

            if (rs.next()) {
                maxId = rs.getInt("max_id");
            }

            PStat.close();
            Database.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxId;
    }
}
