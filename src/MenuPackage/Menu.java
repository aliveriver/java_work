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
                EnRollmentMark enrollmentMark = new EnRollmentMark();
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
        AdjustApplications(preAdmission, adSituation);
    }

    // 调剂志愿的方法
    private void AdjustApplications(ArrayList<Application> preAdmission, ArrayList<AdmS> adSituation) {
        // TODO: 实现调剂志愿的逻辑
        Map<Integer, Map<Integer, Integer>> departmentStudentCount = new HashMap<>();

        // 初始化Map
        for (AdmS adm : adSituation) {
            departmentStudentCount.putIfAbsent(adm.getUniversity_id(), new HashMap<>());
            departmentStudentCount.get(adm.getUniversity_id()).put(adm.getDepartment_id(), adm.getDcount());
        }

        // 遍历预录取表，进行调剂
        for (Application application : preAdmission) {
            int universityId = application.getUniversity_id();
            int departmentId = application.getDepartment_id();
            int majorId = application.getMajor_id();
            Student student = StudentService.SelectById(application.getStudent_id());

            // 检查是否还有名额
            EnrollmentMark enrollmentMark = EnRollmentMarkDAO.SelectByUDM(universityId, departmentId, majorId);
            int maxDepartmentCount = enrollmentMark.getDrequiredN();

            if (departmentStudentCount.get(universityId).get(departmentId) < maxDepartmentCount) {
                // 还有名额，录取
                departmentStudentCount.get(universityId).put(departmentId, departmentStudentCount.get(universityId).get(departmentId) + 1);
            } else {
                // 没有名额，尝试调剂到其他专业
                boolean foundAlternative = false;

                for (AdmS adm : adSituation) {
                    if (adm.getUniversity_id() == universityId && adm.getDcount() < maxDepartmentCount) {
                        adm.AddStudent(student);
                        adm.setDcount(adm.getDcount() + 1);
                        foundAlternative = true;
                        break;
                    }
                }

                if (!foundAlternative) {
                    System.out.println("学生id:" + student.getStudent_id() + "的同学没有被任何大学录取");
                }
            }
        }
    }



}

