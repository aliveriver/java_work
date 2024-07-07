package model;

// 专业表模型，参数同数据库
public class Major {
    private int major_id;
    private int department_id;//统一规定，外码为空时为0
    private String name;

    public Major(int major_id, int department_id, String name) {
        this.major_id = major_id;
        this.department_id = department_id;
        this.name = name;
    }
    public Major(int department_id, String name) {
        this.department_id = department_id;
        this.name = name;
    }
    public Major() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
