package model;

// 学生表，参数同数据库
public class Student implements Comparable{

    private int student_id;
    private String name;
    private String gender;
    private int age;
    private int score;
    private int class_id;//统一规定，外码不存在时，为0

    public Student() {
    }

    public Student(int student_id, String name, String gender, int age, int score, int class_id) {
        this.student_id = student_id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.score = score;
        this.class_id = class_id;
    }

    //getter
    public int getStudent_id() {
        return student_id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getScore() {
        return score;
    }

    public int getClass_id() {
        return class_id;
    }

    //setter
    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }
    public void PresentInfo()
    {
        System.out.print("Student_id: "+student_id);
        System.out.print("Name: "+name);
        System.out.print("Gender: "+gender);
        System.out.print("Age: "+age);
        System.out.print("Score: "+score);
        System.out.println("Class_id: "+class_id);

    }


    //重写比较
    @Override
    public int compareTo(Object o) {
        Student s = (Student) o;
        if (this.score <= s.score) {
            return 1;
        } else {
            return -1;
        }
    }
}
