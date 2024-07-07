package MenuPackage;
import Service.StudentService;
import Service.ApplicationService;
import model.Student;
import model.Application;
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
                    CreateApplication();
                    break;
                case 2:
                    UpdateApplication();
                    break;
                case 3:
                    DeleteApplication();
                    break;
                case 4:
                    FindApplication();
                    break;
                case 5:
                    return;
                default:
                    break;
            }
        }

    }
    private static void CreateApplication(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入志愿信息:");

        System.out.print("志愿ID: ");   //???????????????
        int application_id = scanner.nextInt();

        System.out.print("学生ID: ");
        int student_id = scanner.nextInt();

        System.out.print("大学ID: ");
        int university_id = scanner.nextInt();

        System.out.print("系ID: ");
        int department_id = scanner.nextInt();

        System.out.print("专业ID: ");
        int major_id = scanner.nextInt();

        System.out.print("是否调剂 (0-否, 1-是): ");//????????? 处理志愿信息忘了判断是否调剂
        int is_adjustment = scanner.nextInt();

        Application application = new Application(application_id, student_id, university_id, department_id, major_id, is_adjustment);

        ApplicationService.Create(application);

        System.out.println("志愿信息已成功添加！");
    }

    private static void UpdateApplication() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要修改的志愿ID:");
        int application_id = scanner.nextInt();

        Application existingApplication = ApplicationService.SelectById(application_id);
        if (existingApplication == null) {
            System.out.println("未找到该志愿信息！");
            return;
        }

        System.out.println("请输入新的志愿信息 (如果不需要修改，请输入原信息):");

        System.out.print("学生ID (" + existingApplication.getStudent_id() + "): ");
        int student_id = scanner.nextInt();

        System.out.print("大学ID (" + existingApplication.getUniversity_id() + "): ");
        int university_id = scanner.nextInt();

        System.out.print("系ID (" + existingApplication.getDepartment_id() + "): ");
        int department_id = scanner.nextInt();

        System.out.print("专业ID (" + existingApplication.getMajor_id() + "): ");
        int major_id = scanner.nextInt();

        System.out.print("是否调剂 (" + (existingApplication.getIs_adjustment() == 1 ? "是" : "否") + "): ");
        int is_adjustment = scanner.nextInt();

        Application updatedApplication = new Application(application_id, student_id, university_id, department_id, major_id, is_adjustment);

        ApplicationService.Update(updatedApplication);

        System.out.println("志愿信息已成功修改！");
    }

    private static void DeleteApplication() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除的志愿ID:");
        int application_id = scanner.nextInt();

        Application existingApplication = ApplicationService.SelectById(application_id);
        if (existingApplication == null) {
            System.out.println("未找到该志愿信息！");
            return;
        }

        ApplicationService.DeleteById(application_id);

        System.out.println("志愿信息已成功删除！");
    }

    private static void FindApplication() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查找的志愿ID:");
        int application_id = scanner.nextInt();

        Application application = ApplicationService.SelectById(application_id);
        if (application == null) {
            System.out.println("未找到该志愿信息！");
            return;
        }

        System.out.println("志愿信息如下:");
        System.out.println("志愿ID: " + application.getApplication_id());
        System.out.println("学生ID: " + application.getStudent_id());
        System.out.println("大学ID: " + application.getUniversity_id());
        System.out.println("系ID: " + application.getDepartment_id());
        System.out.println("专业ID: " + application.getMajor_id());
        System.out.println("是否调剂: " + (application.getIs_adjustment() == 1 ? "是" : "否"));
    }
}
