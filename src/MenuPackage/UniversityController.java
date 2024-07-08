package MenuPackage;

import Service.AdmissionService;
import Service.EnRollmentMarkService;
import Service.UniversityService;
import model.Major;
import model.Student;
import model.University;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

abstract public class UniversityController {
    public static void UniversityControllerMenu(){
        boolean left = true;// 1.查看所有大学  2.查看大学下的所有专业 3.查看大学的录取情况
        while(left)
        {
            System.out.println("1. 查看所有大学");
            System.out.println("2. 查看大学下的所有专业");
            System.out.println("3. 查看大学的录取情况");
            System.out.println("4. 返回主菜单");
            System.out.print("请选择");
            int Choice = 0;
            Scanner scanner = new Scanner(System.in);
            Choice = scanner.nextInt();
            switch(Choice){
                case 1:
                    FindAllUniversities();
                    break;
                case 2:
                    FindMajorsOfUniversity();
                    break;
                case 3:
                    FindUniversitiesAdmissionSituation();
                    break;
                case 4:
                    return;//退出系统
                default:
                    break;
            }
        }
    }
    private static void FindAllUniversities() {
        ArrayList<University> temp = new ArrayList<University>();
        temp = UniversityService.SelectAll();//把所有大学的ArrayList保存在Temp里面
        for (University u : temp)//遍历
        {
            System.out.print(u.getUniversity_id() + " ");
            System.out.print(u.getName() + " ");
            System.out.println(u.getLocation());
        }
    }
    private static void FindMajorsOfUniversity(){
        System.out.println("请输入要查找大学的id: ");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        ArrayList<Major> re= EnRollmentMarkService.SelectMajorByUniversity(i);
        for(int j=0;j<re.size();j++)
        {
            System.out.println(re.get(j).getMajor_id()+" "+re.get(j).getDepartment_id()+" "+re.get(j).getName());
        }
        try{sleep(3);}catch (Exception e){e.printStackTrace();}

    }
    private static void FindUniversitiesAdmissionSituation(){
        System.out.println("请输入要查找大学的id: ");
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        ArrayList<Student> r= AdmissionService.SelectStudentByUniversity(t);
        for(int j=0;j<r.size();j++)
        {
            System.out.println(r.get(j).getStudent_id()+" "+r.get(j).getName()+" "+r.get(j).getGender()+" "+
                    r.get(j).getAge()+" "+r.get(j).getScore());//暂时不需要classid
        }
        try{sleep(3);}catch (Exception e){e.printStackTrace();}
    }
}
