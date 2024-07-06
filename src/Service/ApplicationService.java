package Service;

import DAO.ApplicationDAO;
import model.Application;
import java.util.HashMap;
import java.util.Map;

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

    public static ArrayList<Application> SelectBystudent_id(int id){
        return as.SelectBystudent_id(id);
    }
    // 批量获取学生的志愿信息ApplicationService.java
    public static Map<Integer, ArrayList<Application>> SelectByStudentIds(ArrayList<Integer> studentsIdList) {
        Map<Integer, ArrayList<Application>> applicationsMap = new HashMap<>();

        for (int id : studentsIdList) {
            ArrayList<Application> applications = ApplicationService.SelectBystudent_id(id); // 使用service层的方法
            if (applications != null) {
                applicationsMap.put(id, applications);
            }
        }

        return applicationsMap;
    }
}
