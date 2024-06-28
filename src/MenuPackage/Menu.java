package MenuPackage;
import model.*;
import DAO.UniversityDAO;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    public void MenuStart()
    {
        //菜单栏
        System.out.println("欢迎来到“模拟志愿填报系统”");
        System.out.println("1. 学生填报志愿");
        System.out.println("2. 查看学生志愿录取情况");
        System.out.println("3. 查看学生分班情况");
        System.out.println("4. 查看学生专业情况");
        System.out.println("5. 查看学生专业课程");
        System.out.println("6. 查看所有大学");
        System.out.println("7. 查看大学下的所有专业");
        System.out.println("8. 查看大学所有录取的学生");
        System.out.println("9. 查看所有学生录取情况");
        System.out.println("10. 提交所有志愿");
        System.out.print("请选择: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice)
        {
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
                FindAllUniversities();
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                HandInAllApplications();
                break;
            default:
                break;

        }
    }
    public  void FindAllUniversities()
    {
        UniversityDAO test = new UniversityDAO();
        ArrayList<University> temp = new ArrayList<University>();
        temp = test.SelectAll();//把所有大学的ArrayList保存在Temp里面
        for(University u : temp)//遍历
        {
            System.out.print(u.getUniversity_id()+" ");
            System.out.print(u.getName()+" ");
            System.out.println(u.getLocation());
        }
    }
    public  void HandInAllApplications()
    {
        //提交所有的志愿信息。
        // %这里用来接收志愿信息

    }
}
