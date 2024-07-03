package MenuPackage;
import Service.StudentService;
import model.Student;

import java.util.*;
abstract public class ApplicationControler {
    public static void ApplicationControler(){
        boolean left = true;
        while(left)
        {
            System.out.println("欢迎来到志愿信息系统");
            System.out.println("请输入您的学号:");
            int Student_id = 0;
            Scanner scanner = new Scanner(System.in);
            Student_id = scanner.nextInt();
            //遍历stuid表格，看看是否正确
            boolean Located = false;
            ArrayList<Integer> students = StudentService.SelectAllId();
            for(Integer i : students){
                if(i == Student_id){
                    Located = true;
                    System.out.println("查找成功！");
                    left = false;//退出循环
                    break;
                }
            }
            if(!Located)
            {
                System.out.println("您输入的学生id"+Student_id+"未能够被查找到，请重新输入");
            }
        }
        left = true;
        while(left)
        {
            System.out.println("1. 添加志愿信息");
            System.out.println("2. 修改志愿信息");
            System.out.println("3. 删除志愿信息");
            System.out.println("4. 查找志愿信息");
            System.out.println("5. 返回主菜单");
            System.out.print("请选择");
            int Choice = 0;
            Scanner scanner2 = new Scanner(System.in);
            Choice = scanner2.nextInt();
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
                    return;
                default:
                    break;
            }
        }

    }
}
