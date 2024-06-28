package MenuPackage;
import model.Student;

import java.util.ArrayList;
//录取情况的类 Admission situation
//这个类能够显示现在的学生数量（各种信息），
public class AdmS {
    private int university_id;
    private int major_id;
    private int department_id;
    private int Dcount;//major counts
    private ArrayList<Student> Slist ;
    public AdmS() {
        Dcount = 0;
        university_id = 0;
        major_id = 0;
        department_id = 0;
    }
    public AdmS(int university_id, int major_id, int department_id) {
        this.university_id = university_id;
        this.major_id = major_id;
        this.department_id = department_id;
        Dcount = 0;
        Slist = new ArrayList<>();
    }
    public int getUniversity_id() {
        return university_id;
    }
    public void setUniversity_id(int university_id) {
        this.university_id = university_id;
    }
    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
        this.major_id = major_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }
    public int getDcount() {
        return Dcount;
    }

    public void setDcount(int dcount) {
        Dcount = dcount;
    }
    public ArrayList<Student> getSlist() {
        return Slist;
    }

    public void setSlist(ArrayList<Student> slist) {
        Slist = slist;
    }

    public  void  AddStudent(Student student) {
        Slist.add(student);
    }
}
