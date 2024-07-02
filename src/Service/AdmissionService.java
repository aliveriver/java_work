package Service;

import DAO.AdmissionDAO;
import model.Admission;
import model.Student;

import java.util.ArrayList;

abstract public class AdmissionService {
    private static AdmissionDAO as = new AdmissionDAO();

    public static void Create(model.Admission a){
        as.Create(a);
    }

    public static void Update(model.Admission a){
        as.Update(a);
    }

    public static model.Admission SelectById(int id){
        return as.SelectById(id);
    }

    public static ArrayList<Admission> SelectAll() {
        return as.SelectAll();
    }

    public static void DeleteById(int id){
        as.DeleteById(id);
    }

    public static ArrayList<Student> SelectStudentByUniversity(int university_id){
        return as.SelectStudentByUniversity(university_id);
    }
}
