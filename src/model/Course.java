package model;

// 课程表模型，参数同数据库
public class Course {
        private int course_id;
        private int major_id;
        private int department_id;
        private String course_name;

    public Course(int course_id, int major_id, int department_id, String course_name) {
        this.course_id = course_id;
        this.major_id = major_id;
        this.department_id = department_id;
        this.course_name = course_name;
    }

    public Course() {
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
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

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}
