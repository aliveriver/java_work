package MenuPackage;

import Service.ClassService;
import Service.CourseService;
import Service.DepartmentService;
import Service.MajorService;
import Service.StudentService;
import Service.UniversityService;
import model.Admission;
import model.Class;
import model.Course;
import model.Major;
import model.Student;

import java.util.ArrayList;
import java.util.Scanner;


abstract public class StudentController {
    public static void StuController() {
        boolean left = true;
        while(left) {
            System.out.println("1. 添加学生信息");//不需要classid和主码
            System.out.println("2. 修改学生信息");
            System.out.println("3. 查看学生分班情况");
            System.out.println("4. 查看学生专业情况");//admission 表格
            System.out.println("5. 查看学生专业课程");
            System.out.println("6. 返回主菜单");
            System.out.print("请选择: ");
            int Choice;
            Scanner scanner = new Scanner(System.in);
            Choice = scanner.nextInt();
            switch(Choice){
                case 1:
                    CreateStudent();
                    break;
                case 2:
                    UpdateStudent();
                    break;
                case 3:
                    SearchStudentClassInfo();
                    break;
                case 4:
                    SearchStudentMajorInfo();
                    break;
                case 5:
                    SearchStudentCourseInfo();
                    break;
                case 6:
                    return;//退出系统
                default:
                    break;
            }
        }
    }

    private static void CreateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入学生姓名: ");
        String name = scanner.nextLine();
        System.out.print("请输入学生性别: ");
        String gender = scanner.nextLine();
        System.out.print("请输入学生年龄: ");
        int age = scanner.nextInt();
        System.out.print("请输入学生分数: ");
        int score = scanner.nextInt();

        Student student = new Student();
        student.setName(name);
        student.setGender(gender);
        student.setAge(age);
        student.setScore(score);

        StudentService.Create(student);
        System.out.println("学生信息已添加");
    }

    private static void UpdateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入学生ID: ");
        int student_id = scanner.nextInt();

        Student student = StudentService.SelectById(student_id);
        if (student == null) {
            System.out.println("未找到学生ID: " + student_id);
            return;
        }

        scanner.nextLine(); // 清除缓冲区
        System.out.print("请输入新的学生姓名(当前: " + student.getName() + "): ");
        String name = scanner.nextLine();
        System.out.print("请输入新的学生性别(当前: " + student.getGender() + "): ");
        String gender = scanner.nextLine();
        System.out.print("请输入新的学生年龄(当前: " + student.getAge() + "): ");
        int age = scanner.nextInt();
        System.out.print("请输入新的学生分数(当前: " + student.getScore() + "): ");
        int score = scanner.nextInt();

        student.setName(name);
        student.setGender(gender);
        student.setAge(age);
        student.setScore(score);

        StudentService.Update(student);
        System.out.println("学生信息已更新");
    }

    private static void SearchStudentClassInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入学生ID: ");
        int student_id = scanner.nextInt();

        Student student = StudentService.SelectById(student_id);
        if (student == null) {
            System.out.println("未找到学生ID: " + student_id);
            return;
        }

        int class_id = student.getClass_id();
        if (class_id == 0) {
            System.out.println("该学生未被分配班级");
            return;
        }

        Class studentClass = ClassService.SelectById(class_id);
        if (studentClass == null) {
            System.out.println("未找到班级ID: " + class_id);
            return;
        }

        int major_id = studentClass.getMajor_id();
        if (major_id == 0) {
            System.out.println("该班级未被分配专业");
            return;
        }

        Major major = MajorService.SelectById(major_id);
        if (major == null) {
            System.out.println("未找到专业ID: " + major_id);
            return;
        }

        System.out.println("学生班级信息: ");
        System.out.println("班级ID: " + studentClass.getClass_id());
        System.out.println("班级名称: " + studentClass.getClass_name());
        System.out.println("所属专业ID: " + studentClass.getMajor_id());
        System.out.println("专业名称: " + major.getName());
    }

    private static void SearchStudentMajorInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入学生ID: ");
        int student_id = scanner.nextInt();

        Student student = StudentService.SelectById(student_id);
        if (student == null) {
            System.out.println("未找到学生ID: " + student_id);
            return;
        }

        int class_id = student.getClass_id();
        if (class_id == 0) {
            System.out.println("该学生未被分配班级");
            return;
        }

        Class studentClass = ClassService.SelectById(class_id);
        if (studentClass == null) {
            System.out.println("未找到班级ID: " + class_id);
            return;
        }

        int major_id = studentClass.getMajor_id();
        if (major_id == 0) {
            System.out.println("该班级未被分配专业");
            return;
        }

        Major major = MajorService.SelectById(major_id);
        if (major == null) {
            System.out.println("未找到专业ID: " + major_id);
            return;
        }

        System.out.println("学生专业信息: ");
        System.out.println("专业ID: " + major.getMajor_id());
        System.out.println("专业名称: " + major.getName());
    }

    private static void SearchStudentCourseInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入学生ID: ");
        int student_id = scanner.nextInt();

        Student student = StudentService.SelectById(student_id);
        if (student == null) {
            System.out.println("未找到学生ID: " + student_id);
            return;
        }

        int class_id = student.getClass_id();
        if (class_id == 0) {
            System.out.println("该学生未被分配班级");
            return;
        }

        Class studentClass = ClassService.SelectById(class_id);
        if (studentClass == null) {
            System.out.println("未找到班级ID: " + class_id);
            return;
        }

        int major_id = studentClass.getMajor_id();
        if (major_id == 0) {
            System.out.println("该班级未被分配专业");
            return;
        }

        ArrayList<Course> courses = CourseService.SelectByMajorId(major_id);
        if (courses.isEmpty()) {
            System.out.println("未找到该专业的课程");
            return;
        }

        System.out.println("学生课程信息: ");
        for (Course course : courses) {
            String courseName = CourseService.getCourseNameById(course.getCourse_id());
            System.out.println(courseName + " ");
        }
    }

}
