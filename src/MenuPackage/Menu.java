package MenuPackage;

import Service.StudentService;
import Service.UniversityService;
import model.*;

import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    public void MenuStart() {
        boolean left = true;
        //菜单栏
        while (left) {
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
            System.out.println("10. 退出系统");
            System.out.print("请选择: ");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
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

    public void FindAllUniversities() {
        ArrayList<University> temp = new ArrayList<University>();
        temp = UniversityService.SelectAll();//把所有大学的ArrayList保存在Temp里面
        for (University u : temp)//遍历
        {
            System.out.print(u.getUniversity_id() + " ");
            System.out.print(u.getName() + " ");
            System.out.println(u.getLocation());
        }
    }

    public void HandInAllApplications() {
        //提交所有的志愿信息。
        //存放所有学生ID
        ArrayList<Integer> StudentsIdList = new ArrayList<Integer>();
        ArrayList<Application> ADI = new ArrayList<Application>(); //admission depends on id 基于学生id 的志愿申请表
        ArrayList<Application> PreAdmission = new ArrayList<Application>(); //预录取表
        ArrayList<Integer> MajorList = new ArrayList<Integer>(); //某院系下的专业id的数组. 基于大学和院系id下的
        ArrayList<Integer> UDMStuid = new ArrayList<Integer>(); //University departments major students id list 预录取表中选取某个专业的学生
        ArrayList<AdmS> ADSituation = new ArrayList<AdmS>(); //存放大学院系专业的录取情况

        StudentsIdList = StudentService.SelectAllId();//只要所有的id

        //student size =0 的异常处理
        try {
            if (StudentsIdList.size() == 0) {
                throw new Exception("There are no Students found on the table");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < StudentsIdList.size(); i++) {
            //遍历学生id信息。
            final int id = StudentsIdList.get(i);
            Student student_Temp = new Student();
            student_Temp = StudentDAO.SelectById(id);  //赋值 student
            final int StuScores = student_Temp.getScore();     // 获取这个学生的高考成绩
            int u = 0; //二重循环的光标
            ADI = ApplicationDAO.SelectById(id);
            // %这里用来接收志愿信息 Dependid 的意思是，基于id查找这个学生的所有志愿信息

            //如果有人没有填报信息则报错
            try {
                if (ADI.size() == 0) {
                    throw new Exception("学生id: " + id + "没有填报任何志愿信息");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (u = 0; u < ADI.size(); u++) {
                int token = 0;//退出循环的钥匙
                //遍历application_id 表示某个学生的n个志愿。
                int RequiredScore = 0;
                //通过university_id,department_id,major_id 利用EnrollmentMark 来查找所需要的分数线
                int university_id = ADI.get(u).getUniversity_id(); //获取这个志愿的大学id
                int department_id = ADI.get(u).getDepartment_id();//获取这个志愿的院系id
                int major_id = ADI.get(u).getMajor_id(); //获取这个志愿的专业id
                RequiredScore = EnRollmentMarkDAO.SelectByUDM(university_id, department_id, major_id).getRequiredScore();
                if (StuScores >= RequiredScore) {
                    //这个志愿信息有效，预录取。
                    //检查院系录取情况
                    // DRNMax 代表这个学生的这个志愿的报的院系的 最大人数
                    int DRNMax = EnRollmentMarkDAO.SelectByUDM(university_id, department_id, major_id).getDrequiredN();
                    int j = 0;
                    //搜索注册表
                    for (; j < ADSituation.size(); j++) {
                        if (university_id == ADSituation.get(j).getUniversity_id() &&
                                department_id == ADSituation.get(j).getDepartment_id()) {
                            //找到信息了
                            if (ADSituation.get(j).getDcount() < DRNMax)  //已录取人数小于最大需求人数
                            {
                                ADSituation.get(j).AddStudent(student_Temp);//添加学生
                                ADSituation.get(j).setDcount(ADSituation.get(j).getDcount() + 1);//数量上+1

                                PreAdmission.add(ADI.get(u)); //预录取这个志愿

                                token = 1;
                                break;//不再遍历其他志愿
                            } else break;//找到一样的信息且超了，说明该院系不能收人了。
                        }
                    }
                    if (j == ADSituation.size())//说明没找到注册信息 肯定能录取
                    {
                        AdmS test2 = new AdmS(); //注册新的录取信息
                        test2.setUniversity_id(university_id);
                        test2.setDepartment_id(department_id);
                        test2.setMajor_id(major_id);
                        test2.setDcount(1);
                        test2.AddStudent(student_Temp);//添加学生信息
                        ADSituation.add(test2);// 添加这一条录取信息

                        PreAdmission.add(ADI.get(u)); //预录取这个志愿

                        break;//不再遍历其他志愿
                    }
                }
                if (token == 1) break;

            }
            //如果u== ADI.size()
            if (u == ADI.size()) {
                System.out.println("学生id:" + id + "的同学没有被任何大学录取");//声明问题
            }
        }
        //此时已经获得了预选表 1.从高到低排序选取某个专业的人 2.志愿调剂
        //接下来的任务

    }



}

