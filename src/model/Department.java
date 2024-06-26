package model;

// 院系表模型，参数同数据库
public class Department {
    private int department_id;
    private int unversity_id;
    private String name;

    public Department(int department_id, int unversity_id, String name) {
        this.department_id = department_id;
        this.unversity_id = unversity_id;
        this.name = name;
    }

    public Department() {
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getUnversity_id() {
        return unversity_id;
    }

    public void setUnversity_id(int unversity_id) {
        this.unversity_id = unversity_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
