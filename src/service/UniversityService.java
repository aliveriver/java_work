package service;

import DAO.UniversityDAO;
import model.University;

import java.util.ArrayList;

abstract public class UniversityService {
    private static UniversityDAO ud=new UniversityDAO();

    public static void Create(model.University u){
        ud.Create(u);
    }

    public static void Upadte(model.University u){
        ud.Upadte(u);
    }

    public static model.University SelectById(int id){
        return ud.SelectById(id);
    }

    public static ArrayList<University> SelectAll() {
        return ud.SelectAll();
    }

    public static void DeleteById(int id){
        ud.DeleteById(id);
    }
}
