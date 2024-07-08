package Service;

import DAO.MajorDAO;
import model.Major;

import java.util.ArrayList;

abstract public class MajorService {
    private static MajorDAO ms = new MajorDAO();

    public static void Create(model.Major m){
        ms.Create(m);
    }

    public static void Update(model.Major m){
        ms.Update(m);
    }

    public static model.Major SelectById(int id){
        return ms.SelectById(id);
    }

    public static ArrayList<Major> SelectAll() {
        return ms.SelectAll();
    }

    public static void DeleteById(int id){
        ms.DeleteById(id);
    }

    public static ArrayList<Integer> SelectByDepartment_id(int id) {return ms.SelectByDepartment_id(id);}

    public static int getMajorIdByName(String name) {
        return ms.getMajorIdByName(name);
    }
}
