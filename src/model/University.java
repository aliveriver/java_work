package model;

// 大学表，参数同数据库
public class University {
    private int university_id;
    private String name;
    private String location;

    public University(){
    }
    public University(int id,String name,String location){
        this.university_id=id;
        this.name=name;
        this.location=location;
    }

    //getter
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getUniversity_id() {
        return university_id;
    }

    //setter
    public void setUniversity_id(int university_id) {
        this.university_id = university_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}