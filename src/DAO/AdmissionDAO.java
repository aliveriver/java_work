package DAO;

import common.Database;
import model.Admission;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdmissionDAO {
    public void Create(model.Admission a){
        try {
            String sql = "INSERT INTO admissions (admission_id,student_id,university_id,department_id,major_id) VALUES(?,?,?,?,?)";
            Database.setConnection();
            Connection conn = Database.getConnection();
            PreparedStatement PStat = conn.prepareStatement(sql);
            PStat.setInt(1, a.getAdmission_id());
            PStat.setInt(2, a.getStudent_id());
            PStat.setInt(3, a.getUniversity_id());
            PStat.setInt(4, a.getDepartment_id());
            PStat.setInt(5, a.getMajor_id());


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

    public void Update(model.Admission a){
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM admissions WHERE admission_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, a.getAdmission_id());
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String update_sql = "UPDATE admissions set student_id=?,university_id=?,department_id=?,major_id=? where admission_id = ?";
            PreparedStatement UPStat = conn.prepareStatement(update_sql);
            UPStat.setInt(5, a.getAdmission_id());
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

    public model.Admission SelectById(int id){
        Admission test = new Admission();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM admissions WHERE admission_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found");
            }
            Srow.next();
            test.setAdmission_id(Srow.getInt("admission_id"));
            test.setStudent_id(Srow.getInt("student_id"));
            test.setUniversity_id(Srow.getInt("university_id"));
            test.setDepartment_id(Srow.getInt("department_id"));
            test.setMajor_id(Srow.getInt("major_id"));

            SPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Success Select");
        return test;
    }

    public ArrayList<Admission> SelectAll(){
        ArrayList<Admission> test = new ArrayList<Admission>();
        try {
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM admissions ";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("this table is empty");
            }
            while(Srow.next()){
                Admission a = new Admission(Srow.getInt("admission_id"),
                        Srow.getInt("student_id"),
                        Srow.getInt("university_id"),
                        Srow.getInt("department_id"),
                        Srow.getInt("major_id")
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
            String select_sql = "SELECT * FROM admissions WHERE admission_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found or not unique");
            }
            SPStat.close();

            String Dsql = "DELETE FROM admissions WHERE admission_id = ?";
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

    public ArrayList<Student> SelectStudentByUniversity(int university_id){
        ArrayList<Student> result = new ArrayList<Student>();
        try{
            Database.setConnection();
            Connection conn = Database.getConnection();
            String select_sql = "SELECT * FROM admissions join students on students.student_id = admissions.student_id WHERE admissions.university_id = ?";
            PreparedStatement SPStat = conn.prepareStatement(select_sql);
            SPStat.setInt(1, university_id);
            ResultSet Srow = SPStat.executeQuery();
            if (!Srow.isBeforeFirst()) {
                System.out.println("Fail to Select");
                throw new Exception("ID is not found");
            }
            while(Srow.next()){
                Student a = new Student(Srow.getInt("student_id"),
                        Srow.getString("name"),
                        Srow.getString("gender"),
                        Srow.getInt("age"),
                        Srow.getInt("score"),
                        Srow.getInt("class_id")
                );
                result.add(a);
            }
            SPStat.close();
            Database.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Success Select");
        return result;
    }
}
