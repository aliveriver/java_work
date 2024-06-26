package model;

// 志愿分配表，参数同数据库
public class Admission {
    private int admission_id;
    private int student_id;
    private int university_id;
    private int major_id;
    private int department_id;

    public Admission(int admission_id, int student_id, int university_id, int major_id, int department_id) {
        this.admission_id = admission_id;
        this.student_id = student_id;
        this.university_id = university_id;
        this.major_id = major_id;
        this.department_id = department_id;
    }

    public Admission() {
    }

    public int getAdmission_id() {
        return admission_id;
    }

    public void setAdmission_id(int admission_id) {
        this.admission_id = admission_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
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
}
