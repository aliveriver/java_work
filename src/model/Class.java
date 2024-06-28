package model;

// 班级表模型，参数同数据库
public class Class {
    private int class_id;
    private int major_id;
    private String class_name;
    private int university_id;

    public Class() {
    }

    public Class(int class_id, int major_id, String class_name, int university_id) {
        this.class_id = class_id;
        this.major_id = major_id;
        this.class_name = class_name;
        this.university_id = university_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
        this.major_id = major_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(int university_id) {
        this.university_id = university_id;
    }
}
