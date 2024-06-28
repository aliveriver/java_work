package MenuPackage;
import model.*;
import DAO.UniversityDAO;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    public void MenuStart()
    {
        boolean left = true;
        //菜单栏
        while(left){
            System.out.println("欢迎来到“模拟志愿填报系统”");
            System.out.println("1. 学生填报志愿");//增加application
            System.out.println("2. 查看学生志愿录取情况");//查看admision
            System.out.println("3. 查看学生分班情况");//查看classroom
            System.out.println("4. 查看学生专业情况");//查看major
            System.out.println("5. 查看学生专业课程");//查看courses
            System.out.println("6. 查看所有大学");//查看universities
            System.out.println("7. 查看大学下的所有专业");//查看 universities~major
            System.out.println("8. 查看大学所有录取的学生");//universities~admissions/
            System.out.println("9. 提交所有志愿");
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
                    HandInAllApplications();
                    break;
                default:
                    break;
            }
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
        //存放所有学生ID
        ArrayList<Integer> StudentsIdList = new ArrayList<Integer>();
        // %这里用来接收志愿信息 Dependid 的意思是，基于id查找这个学生的所有志愿信息
        ArrayList<Application> ADI = new ArrayList<Application>(); //admission depends on id
        ArrayList<Application> PreAdmission = new ArrayList<Application>(); //预录取表
        ArrayList<Integer> MajorList = new ArrayList<Integer>(); //某院系下的专业id的数组
        //student size =0 的异常处理
        try{
            if(StudentsIdList.size()==0)
            {
                throw new Exception("There are no Students found on the table");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        for(int i=0;i<StudentsIdList.size();i++)
        {
            //遍历学生id信息。
            int id = StudentsIdList.get(i);
            int StuScores = 0;
            int u=0;//二重循环的光标
            // 获取这个学生的高考成绩
            // StuScores = StudentsIdList.get();

            // %这里用来接收志愿信息 Dependid 的意思是，基于id查找这个学生的所有志愿信息
            // ApplicationDependId = function(i);
            //假设已经获得了id的志愿信息表

            //如果有人没有填报信息则报错
            try{
                if(ADI.size()==0)
                {
                    throw new Exception("学生id: "+ id +"没有填报任何志愿信息");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            for(u=0;u<ADI.size();u++)
            {
                //遍历application_id 表示某个学生的n个志愿。
                int RequiredScore=0;
                //通过university_id,department_id,major_id 利用EnrollmentMark 来查找所需要的分数线
                // getInfo(ApplicationDependId().get(u)) //基于序号查找university,department,major
                // RequiredScore = function(university_id,department_id,major_id);

                if(StuScores>=RequiredScore)
                {
                    //这个志愿信息能够实现，预录取。
                    PreAdmission.add(ADI.get(u));
                }
            }
            //如果u== ADI.size()
            if(u == ADI.size())
            {
                System.out.println("学生id:"+id+"的同学没有被任何大学录取");//声明问题
            }
        }
        //此时已经获得了预选表


    }
}
