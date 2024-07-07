package MenuPackage;

import Service.*;
import model.*;

import java.util.*;
//            System.out.println("1. 学生志愿信息增删改查系统");//增加application
//            System.out.println("2. 查看学生志愿录取情况");//查看admission
//            System.out.println("3. 查看学生分班情况");//查看classroom
//            System.out.println("4. 查看学生专业情况");//查看major
//            System.out.println("5. 查看学生专业课程");//查看courses
//            System.out.println("6. 查看所有大学");//查看universities
//            System.out.println("7. 查看大学下的所有专业");//查看 universities~major
//            System.out.println("8. 查看大学所有录取的学生");//universities~admissions/
//            System.out.println("9. 分配志愿");
//            System.out.println("10. 退出系统");
import static java.lang.Thread.sleep;

abstract public class Menu {
    public static void  MenuStart() {
        boolean left = true;
        //菜单栏
        while (left) {
            System.out.println("欢迎来到“模拟志愿填报系统”");
            System.out.println("1. 学生信息管理");//1.添加学生信息 2.修改学生信息 3.查看学生分班情况 4.查看学生专业情况 5.查看学生专业课程
            System.out.println("2. 学生志愿管理");//学生志愿信息增删改查系统
            System.out.println("3. 学生录取情况查询");//大学学生的admission
            System.out.println("4. 大学和专业查看");// 查看所有大学  查看大学下的所有专业 3.查看大学的录取名单
            System.out.println("5. 管理员系统功能");// 1.分配志愿信息  2.分配班级信息 3.添加大学信息 4.添加专业信息（外码参考）
            System.out.println("6. 退出系统");
            System.out.print("请选择: ");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    StudentController.StuController();//进入学生信息管理系统
                    break;
                case 2:
                    ApplicationControler.ApplicationControler();//进入志愿填报管理系统
                    break;
                case 3:
                    ViewAdmission.ViewAdmissionFunction();//
                    break;
                case 4:
                    UniversityController.UniversityControllerMenu();//进入大学管理的Menu
                    break;
                case 5:
                    ManagerController.ManagerControllerMenu();//进入管理员Menu
                    break;
                case 6:
                    left = false;
                    break;
                default:
                    System.out.println("输入有误，请重新输入");
                    break;
            }
        }
        System.out.println("欢迎下次使用志愿填报系统")  ;

    }

}




