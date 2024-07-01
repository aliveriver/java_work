package MenuPackage;
import model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//录取情况的类 Admission situation
//这个类能够显示现在的学生数量（各种信息），
public class AdmS{
    private int university_id;
    private int department_id;
    private int Dcount;//Department counts
    private ArrayList<Student> Slist ;
    public AdmS() {
        Dcount = 0;
        university_id = 0;
        department_id = 0;
    }
    public AdmS(int university_id,int department_id) {
        this.university_id = university_id;
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
    public  void SortStudentDependsOnScore()
    {
        Collections.sort(Slist);
    }
}
