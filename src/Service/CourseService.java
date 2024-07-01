package Service;

import DAO.CourseDAO;
import model.Course;

import java.util.ArrayList;

abstract public class CourseService {
    private static CourseDAO cs = new CourseDAO();

    public static void Create(model.Course c){
        cs.Create(c);
    }

    public static void Update(model.Course c){
        cs.Update(c);
    }

    public static model.Course SelectById(int id){
        return cs.SelectById(id);
    }

    public static ArrayList<Course> SelectAll() {
        return cs.SelectAll();
    }

    public static void DeleteById(int id){
        cs.DeleteById(id);
    }
}
