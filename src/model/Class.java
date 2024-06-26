package model;

// 班级表模型，参数同数据库
public class Class {
    private int class_id;
    private int major_id;
    private String major_name;


    public Class(int class_id, int major_id, String major_name) {
        this.class_id = class_id;
        this.major_id = major_id;
        this.major_name = major_name;
    }

    public Class() {
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

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }
}
