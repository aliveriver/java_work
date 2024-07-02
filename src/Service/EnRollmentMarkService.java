package Service;

import DAO.EnRollmentMarkDAO;
import model.EnRollmentMark;

import java.util.ArrayList;

abstract public class EnRollmentMarkService {
    private static EnRollmentMarkDAO es = new EnRollmentMarkDAO();

    public static void Create(model.EnRollmentMark e){
        es.Create(e);
    }

    public static void Update(model.EnRollmentMark e){
        es.Update(e);
    }

    public static model.EnRollmentMark SelectById(int id){
        return es.SelectById(id);
    }

    public static ArrayList<EnRollmentMark> SelectAll() {
        return es.SelectAll();
    }

    public static void DeleteById(int id){
        es.DeleteById(id);
    }

    public static model.EnRollmentMark SelectByOther(int university_id,int department_id,int major_id){
        return es.SelectByOther(university_id,department_id,major_id);
    }
}
