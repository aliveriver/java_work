package Service;

import DAO.DepartmentDAO;
import model.Department;

import java.util.ArrayList;

abstract public class DepartmentService {
    private static DepartmentDAO ds = new DepartmentDAO();

    public static void Create(model.Department d){
        ds.Create(d);
    }

    public static void Upadte(model.Department d){
        ds.Upadte(d);
    }

    public static model.Department SelectById(int id){
        return ds.SelectById(id);
    }

    public static ArrayList<Department> SelectAll() {
        return ds.SelectAll();
    }

    public static void DeleteById(int id){
        ds.DeleteById(id);
    }
}
