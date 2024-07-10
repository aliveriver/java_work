package Service;

import DAO.ClassDAO;
import model.Class;

import java.util.ArrayList;

abstract public class ClassService {
    private static ClassDAO cs = new ClassDAO();

    public static int Create(model.Class c){
        return new ClassDAO().Create(c);
    }

    public static void Update(model.Class c){
        cs.Update(c);
    }

    public static model.Class SelectById(int id){
        return cs.SelectById(id);
    }

    public static ArrayList<Class> SelectAll() {
        return cs.SelectAll();
    }

    public static void DeleteById(int id){
        cs.DeleteById(id);
    }
}
