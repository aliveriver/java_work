package Service;

import DAO.ApplicationDAO;
import model.Application;

import java.util.ArrayList;

abstract public class ApplicationService {
    private static ApplicationDAO as = new ApplicationDAO();

    public static void Create(model.Application a){
        as.Create(a);
    }

    public static void Update(model.Application a){
        as.Update(a);
    }

    public static model.Application SelectById(int id){
        return as.SelectById(id);
    }

    public static ArrayList<Application> SelectAll() {
        return as.SelectAll();
    }

    public static void DeleteById(int id){
        as.DeleteById(id);
    }
}
