package MenuPackage;

import Service.StudentService;

import java.util.ArrayList;
import java.util.Scanner;

abstract public class StudentController {
    public static void StuController() {
        boolean left = true;//1.添加学生信息 2.修改学生信息 3.查看学生分班情况 4.查看学生专业情况 5.查看学生专业课程
        while(left)
        {
            System.out.println("1. 添加学生信息");//不需要classid和主码
            System.out.println("2. 修改学生信息");
            System.out.println("3. 查看学生分班情况");
            System.out.println("4. 查看学生专业情况");//admission 表格
            System.out.println("5. 查看学生专业课程");
            System.out.println("6. 返回主菜单");
            System.out.print("请选择");
            int Choice = 0;
            Scanner scanner = new Scanner(System.in);
            Choice = scanner.nextInt();
            switch(Choice){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    return;//退出系统
                default:
                    break;
            }
        }
    }
}
