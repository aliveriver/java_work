package MenuPackage;

import Service.MajorService;
import Service.StudentService;
import Service.UniversityService;
import model.*;
import java.util.HashMap; // 引入 HashMap 类
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;

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
                    try {
                        HandInAllApplications();
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

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

    public void HandInAllApplications() throws Exception {
        // 提交所有的志愿信息。
        ArrayList<Integer> studentsIdList = StudentService.SelectAllId(); // 获取所有学生ID

        // student size = 0 的异常处理
        if (studentsIdList.isEmpty()) {
            throw new Exception("There are no Students found on the table");
        }

        ArrayList<Application> preAdmission = new ArrayList<>();//预录取表格
        ArrayList<AdmS> adSituation = new ArrayList<>();//注册表
        for (int id : studentsIdList) {
            Student student = StudentService.SelectById(id); // 获取学生信息
            int studentScore = student.getScore(); // 获取学生的高考成绩
            ArrayList<Application> applications = ApplicationDAO.SelectById(id); // 获取学生的所有志愿信息

            // 如果没有填报志愿信息则报错
            if (applications.isEmpty()) {
                throw new Exception("学生id: " + id + "没有填报任何志愿信息");
            }

            boolean admitted = false;
            for (Application application : applications) { // 遍历志愿信息
                int universityId = application.getUniversity_id(); // 获取大学id
                int departmentId = application.getDepartment_id(); // 获取院系id
                int majorId = application.getMajor_id(); // 获取专业id
                EnRollmentMark enrollmentMark = new EnRollmentMark();//获取分数线、院系最大人数、专业最大人数
                enrollmentMark = EnRollmentMarkDAO.SelectByUDM(universityId, departmentId, majorId); // 获取分数和最大人数
                int requiredScore = enrollmentMark.getRequiredScore();
                int maxDepartmentCount = enrollmentMark.getDRequiredN();//获取院系最大人数

                if (studentScore >= requiredScore) { //说明能够录取
                    boolean departmentFound = false;
                    // 搜索注册表
                    for (AdmS adm : adSituation) {
                        if (universityId == adm.getUniversity_id() && departmentId == adm.getDepartment_id()) {
                            departmentFound = true;//注册表中找到
                            if (adm.getDcount() < maxDepartmentCount) {
                                adm.AddStudent(student); // 添加学生
                                adm.setDcount(adm.getDcount() + 1); // 数量加1
                                preAdmission.add(application); // 预录取这个志愿
                                admitted = true;//录取成功，其他志愿不再查看
                                break;
                            } else {
                                break; // 已超出最大人数，不再遍历其他志愿
                            }
                        }
                    }

                    if (!departmentFound) { // 没找到注册信息，肯定能录取
                        AdmS newAdm = new AdmS(universityId, departmentId);
                        newAdm.AddStudent(student); // 添加学生信息
                        newAdm.setDcount(1);
                        adSituation.add(newAdm); // 添加这一条录取信息
                        preAdmission.add(application); // 预录取这个志愿
                        admitted = true;
                        break; // 不再遍历其他志愿
                    }
                }

                if (admitted) {
                    break;//不再遍历其他志愿，转到下一人。
                }
            }

            if (!admitted) {
                System.out.println("学生id:" + id + "的同学没有被任何大学录取"); // 声明问题
            }
        }

        // 调剂志愿
        // Major
        for(AdmS adm : adSituation)//遍历学生情况
        {
            adm.SortStudentDependsOnScore();
            Map<Integer,Integer> majorCount = new HashMap<>();//计数使用的map 前面的是majorid 后面的是数量 这个是学生的表格4
            Map<Integer,Integer> MajorRequirements = new HashMap<>();//专业和对应的需求人数
            EnRollmentMark enrollmentMark = new EnRollmentMark();
            ArrayList<Integer> majorIdList = new ArrayList<>();
            majorIdList = MajorService.SelectDepartmentId(adm.getDepartment_id());//获取某个院系的所有专业
            for(Integer majorId : majorIdList) //添加院系信息
            {
                majorCount.put(majorId, 0);
            }
            for(Student Stu : adm.getSlist())
            {
                int id = Stu.getStudent_id();//获取这个学生id
                int major_id = -1;//获取学生的专业信息
                for(Application application : preAdmission)
                {
                    if(id==application.getStudent_id())
                    {
                        major_id = application.getMajor_id();
                        break;
                    }
                }
                enrollmentMark = EnRollmentMarkDAO.SelectByUDM(adm.getUniversity_id(), adm.getDepartment_id(), major_id); // 获取专业最大人数
                int RequiredMajorNumber = enrollmentMark.getMRequiredN();//获取专业人数
                MajorRequirements.put(major_id,RequiredMajorNumber);//需求表中填写需求人数
                if(major_id == -1 ) throw new Exception("there's no match student between AdmS and preAdmission");
                majorCount.put(major_id, majorCount.getOrDefault(major_id,0)+1);//给这个专业+1
            }
            //遍历完所有学生，给所有学生填报志愿的专业都有了数量。
            //基于学校和院系

            for (Map.Entry<Integer, Integer> entry : majorCount.entrySet())//遍历学生的
            {
                int majorId = entry.getKey();
                int Number = entry.getValue();//专业对应的人数
                int RequiredMajorNumber = MajorRequirements.get(majorId);//获取最大人数
                if(Number>RequiredMajorNumber)//需要调剂
                {
                    int ToMajor=-1;
                    int AdaptedNum = Number-RequiredMajorNumber;//需要调剂的学生数量
                    for(Integer ID :majorIdList)
                    {
                        if(ID!=majorId)//要调剂到的专业
                        {
                            ToMajor = ID;
                            break;
                        }
                    }

                }
            }

        }


    }


}

