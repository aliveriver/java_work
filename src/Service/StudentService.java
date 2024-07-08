package Service;

import DAO.StudentDAO;
import model.*;
import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;

abstract public class StudentService {
    private static StudentDAO ss = new StudentDAO();

    public static void Create(model.Student s){
        ss.Create(s);
    }

    public static void Update(model.Student s){
        ss.Update(s);
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

    // 批量获取学生信息
    public static Map<Integer, Student> SelectByIds(ArrayList<Integer> studentsIdList) {
        Map<Integer, Student> studentsMap = new HashMap<>();

        for (int id : studentsIdList) {
            Student student = StudentService.SelectById(id); // 使用DAO层方法
            if (student != null) {
                studentsMap.put(id, student);
            }
        }
        return studentsMap;
    }

    public static void UpdateStudentClass(int studentId, int classId) {
        Student student = SelectById(studentId);
        if (student != null) {
            student.setClass_id(classId);
            Update(student);
        }
    }
}
