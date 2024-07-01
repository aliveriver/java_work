package DAO;

import model.EnRollmentMark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EnRollmentMarkDAO {

    public void Create(model.EnRollmentMark e){
        try {
            String sql = "INSERT INTO enrollmentmark (Enrollment_id,university_id,department_id,major_id,RequiredScore,MRequiredN,DRequiredN) VALUES(?,?,?,?,?,?,?)";
            Database.setConnection();
            Connection conn = Database.getConnection();
            PreparedStatement PStat = conn.prepareStatement(sql);
            PStat.setInt(1, e.getEnrollment_id());
            PStat.setInt(2, e.getUniversity_id());
            PStat.setInt(3, e.getDepartment_id());
            PStat.setInt(4, e.getMajor_id());
            PStat.setInt(5, e.getRequiredScore());
            PStat.setInt(6, e.getMRequiredN());
            PStat.setInt(7, e.getDRequiredN());

            int row = PStat.executeUpdate();
            if (row > 0) {
                System.out.println("success insert");
            } else {
                System.out.println("fail to insert");
            }
            PStat.close();
            Database.closeConnection();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void Update(model.EnRollmentMark e){
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM enrollmentmark WHERE Enrollment_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, e.getEnrollment_id());
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String update_sql = "UPDATE enrollmentmark set university_id=?,department_id=?,major_id=?,RequiredScore=?,MRequiredN=?,DRequiredN=? where Enrollment_id = ?";
            PreparedStatement UPStat = conn.prepareStatement(update_sql);
            UPStat.setInt(7, e.getEnrollment_id());
            UPStat.setInt(6, e.getDepartment_id());
            UPStat.setInt(5, e.getMRequiredN());
            UPStat.setInt(4, e.getRequiredScore());
            UPStat.setInt(3, e.getMajor_id());
            UPStat.setInt(2, e.getDepartment_id());
            UPStat.setInt(1, e.getUniversity_id());


            int row = UPStat.executeUpdate();
            if (row > 0) {
                System.out.println("success update");
            } else {
                System.out.println("fail to update");
            }
            UPStat.close();
            Database.closeConnection();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public model.EnRollmentMark SelectById(int id){
        EnRollmentMark test = new EnRollmentMark();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM enrollmentmark WHERE Enrollment_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found");
            }
            Srow.next();
            test.setEnrollment_id(Srow.getInt("Enrollment_id"));
            test.setUniversity_id(Srow.getInt("university_id"));
            test.setDepartment_id(Srow.getInt("department_id"));
            test.setMajor_id(Srow.getInt("major_id"));
            test.setRequiredScore(Srow.getInt("RequiredScore"));
            test.setMRequiredN(Srow.getInt("MRequiredN"));
            test.setDRequiredN(Srow.getInt("DRequiredN"));

            SPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Success Select");
        return test;
    }

    public ArrayList<EnRollmentMark> SelectAll(){
        ArrayList<EnRollmentMark> test = new ArrayList<EnRollmentMark>();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM enrollmentmark ";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("this table is empty");
            }
            while(Srow.next()){
                EnRollmentMark a = new EnRollmentMark(Srow.getInt("Enrollment_id"),
                        Srow.getInt("university_id"),
                        Srow.getInt("department_id"),
                        Srow.getInt("major_id"),
                        Srow.getInt("RequiredScore"),
                        Srow.getInt("MRequiredN"),
                        Srow.getInt("DRequiredN")
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
            String select_sql = "SELECT * FROM enrollmentmark WHERE Enrollment_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String Dsql = "DELETE FROM enrollmentmark WHERE Enrollment_id = ?";
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
