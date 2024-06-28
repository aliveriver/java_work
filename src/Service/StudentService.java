package Service;

import DAO.StudentDAO;
import model.*;

import java.util.ArrayList;

abstract public class StudentService {
    private static StudentDAO ss = new StudentDAO();

    public static void Create(model.Student s){
        ss.Create(s);
    }

    public static void Upadte(model.Student s){
        ss.Upadte(s);
    }

    public static model.Student SelectById(int id){
        return ss.SelectById(id);
    }

    public static ArrayList<Student> SelectAll() {
        return ss.SelectAll();
    }

    public static ArrayList<Integer> SelectAllId() {
        return ss.SelectAllId();
    }

    public static void DeleteById(int id){
        ss.DeleteById(id);
    }
}
