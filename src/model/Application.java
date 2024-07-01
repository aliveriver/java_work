package model;

// 志愿表模型，参数同数据库
public class Application {
    private int application_id;
    private int student_id;
    private int university_id;
    private int department_id;
    private int major_id;
    private int is_adjustment;

    public Application() {
    }

    public Application(int application_id, int student_id, int university_id, int department_id, int major_id, int is_adjustment) {
        this.application_id = application_id;
        this.student_id = student_id;
        this.university_id = university_id;
        this.department_id = department_id;
        this.major_id = major_id;
        this.is_adjustment = is_adjustment;
    }

    public int getApplication_id() {
        return application_id;
    }

    public void setApplication_id(int application_id) {
        this.application_id = application_id;
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

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
        this.major_id = major_id;
    }

    public int getIs_adjustment() {
        return is_adjustment;
    }

    public void setIs_adjustment(int is_adjustment) {
        this.is_adjustment = is_adjustment;
    }
}
