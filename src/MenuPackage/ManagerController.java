package MenuPackage;

import Service.*;
import model.Admission;
import model.Application;
import model.EnRollmentMark;
import model.Student;

import java.util.*;

abstract public class ManagerController {
    public static void ManagerControllerMenu() {
        // 1.分配志愿信息  2.分配班级信息 3.添加大学信息 4.添加专业信息（外码参考）
        System.out.println("请输入密钥:");
        Scanner scanner33 = new Scanner(System.in);
        String input = scanner33.nextLine();
        if(input.equals("123456")) {
            System.out.println("密码正确，欢迎进入管理员系统");
        }
        else {
            System.out.println("密码错误，已强制返回");
            return;
        }
        boolean left = true;
        while(left)
        {
            System.out.println("1. 分配志愿信息");
            System.out.println("2. 分配班级信息");
            System.out.println("3. 添加大学信息");
            System.out.println("4. 添加专业信息");//admission 表格
            System.out.println("5. 查看所有的录取信息");
            System.out.println("6. 返回主菜单");
            System.out.print("请选择");
            int Choice = 0;
            Scanner scanner = new Scanner(System.in);
            Choice = scanner.nextInt();
            switch(Choice){
                case 1:
                    try {
                        HandInAllApplications();
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    ViewAllStudentAdmissions();
                    break;
                case 6:
                    return;//退出系统
                default:
                    break;
            }
        }
    }
    public static void ViewAllStudentAdmissions() {
        ArrayList<Admission> admissions = AdmissionService.SelectAll();

        for (Admission admission : admissions) {
            System.out.print("录取ID: " + admission.getAdmission_id() + " ");
            System.out.print("学生ID: " + admission.getStudent_id() + " ");
            System.out.print("大学ID: " + admission.getUniversity_id() + " ");
            System.out.print("院系ID: " + admission.getDepartment_id() + " ");
            System.out.println("专业ID: " + admission.getMajor_id());
        }
    }
    public static void HandInAllApplications() throws Exception {
        // 获取所有学生的ID列表
        //ArrayList<Integer> studentsIdList = StudentService.SelectAllId();
        ArrayList<Integer> studentsIdList = new ArrayList<>();

        //把学生从大到小排序
        ArrayList<Student> studentstemp = StudentService.SelectAll();//获取所有学生
        // 检查学生ID列表是否为空，如果为空则抛出异常
        if (studentstemp.isEmpty()) {
            throw new Exception("学生表中没有学生记录");
        }

        Collections.sort(studentstemp);//从大到小排学生

        for(Student student : studentstemp)
        {
            studentsIdList.add(student.getStudent_id());
        }

        // 检查学生ID列表是否为空，如果为空则抛出异常


        // 批量获取学生信息和志愿信息
        Map<Integer, Student> studentsMap = StudentService.SelectByIds(studentsIdList); //前面的Integer都是学生id
        Map<Integer, ArrayList<Application>> applicationsMap = ApplicationService.SelectByStudentIds(studentsIdList);//前面的Integer都是学生id

        // 预录取表
        ArrayList<Application> preAdmission = new ArrayList<>();
        // 注册表
        ArrayList<AdmS> adSituation = new ArrayList<>();

        // 遍历每个学生ID
        for (int id : studentsIdList) {
            Student student = studentsMap.get(id); // 获取学生信息
            if (student == null) continue; // 如果学生信息为空，跳过

            int studentScore = student.getScore(); // 获取学生的高考成绩
            ArrayList<Application> applications = applicationsMap.get(id); // 获取学生的所有志愿信息

            // 如果没有填报志愿信息则记录并跳过
            if (applications == null || applications.isEmpty()) {
                System.out.println("学生id: " + id + " 没有填报任何志愿信息");
                continue;
            }

            boolean admitted = handleStudentApplications(student, applications, adSituation, preAdmission);

            if (!admitted) {
                System.out.println("学生id:" + id + " 的同学没有被任何大学录取");
            }
        }

        // 处理调剂志愿 调剂
        handleMajorAdjustment(adSituation, preAdmission);

        // 更新最终的录取表
        updateFinalAdmissions(preAdmission);

        System.out.println("调剂处理完成");
    }

    /**
     * 处理学生的志愿申请
     *
     * @param student      学生对象
     * @param applications 学生的志愿申请列表
     * @param adSituation  注册表
     * @param preAdmission 预录取表
     * @return 是否成功录取该学生
     * @throws Exception 如果处理过程中发生错误
     */
    private static boolean handleStudentApplications(Student student, ArrayList<Application> applications, ArrayList<AdmS> adSituation, ArrayList<Application> preAdmission) throws Exception {
        int studentScore = student.getScore();
        boolean admitted = false;
        //便遍历志愿信息
        for (Application application : applications) {
            int universityId = application.getUniversity_id();
            int departmentId = application.getDepartment_id();
            int majorId = application.getMajor_id();

            // 获取录取要求和院系最大录取人数
            EnRollmentMark enrollmentMark = new EnRollmentMark();
            enrollmentMark = EnRollmentMarkService.SelectByOther(universityId, departmentId, majorId);
            int requiredScore = enrollmentMark.getRequiredScore();
            int maxDepartmentCount = enrollmentMark.getDRequiredN();

            if (studentScore >= requiredScore) { // 符合录取要求
                boolean departmentFound = false;

                // 检查院系是否已在注册表中   同时处理注册表
                for (AdmS adm : adSituation) {
                    if (universityId == adm.getUniversity_id() && departmentId == adm.getDepartment_id()) {
                        departmentFound = true;

                        if (adm.getDcount() < maxDepartmentCount) {
                            adm.AddStudent(student); // 添加学生到院系注册表中
                            adm.setDcount(adm.getDcount() + 1); // 院系人数加1
                            preAdmission.add(application); // 加入预录取表
                            admitted = true; // 成功录取
                            break;
                        } else {
                            break; // 院系人数已满，不再继续处理该志愿
                        }
                    }
                }

                // 如果院系未在注册表中，则新建注册信息
                //同时认识到，这个信息肯定能加到预录取表中。
                if (!departmentFound) {
                    AdmS newAdm = new AdmS(universityId, departmentId);
                    newAdm.AddStudent(student);
                    newAdm.setDcount(1);
                    adSituation.add(newAdm); // 添加到注册表中
                    preAdmission.add(application); // 加入预录取表
                    admitted = true; // 成功录取
                    break; // 不再继续处理其他志愿
                }
            }

            if (admitted) {
                break; // 已成功录取，不再处理其他志愿
            }
        }

        return admitted;
    }

    /**
     * 处理调剂专业
     *
     * @param adSituation  注册表
     * @param preAdmission 预录取表
     * @throws Exception 如果处理过程中发生错误
     */
    private static void handleMajorAdjustment(ArrayList<AdmS> adSituation, ArrayList<Application> preAdmission) throws Exception {
        for (AdmS adm : adSituation) {
            adm.SortStudentDependsOnScore(); // 根据学生分数对注册表中的学生进行降序排序

            // 获取该院系的所有专业列表
            int temp = adm.getDepartment_id();//获取院系id
            ArrayList<Integer> majorIdList = MajorService.SelectByDepartment_id(temp);//基于院系信息得到专业表
            ArrayList<Student> Students = adm.getSlist();

            // 统计每个专业的人数需求
            Map<Integer, Integer> majorRequirements = new HashMap<>();
            for (Integer majorId : majorIdList) {
                EnRollmentMark enrollmentMark = EnRollmentMarkService.SelectByOther(adm.getUniversity_id(), adm.getDepartment_id(), majorId);
                int requiredMajorNumber = enrollmentMark.getMRequiredN(); // 获取专业的最大录取人数
                majorRequirements.put(majorId, requiredMajorNumber); // 记录专业需求人数
            }

            // 统计每个专业当前的学生人数
            Map<Integer, Integer> majorCount = new HashMap<>();
            for (Application application : preAdmission) {
                if (adm.getUniversity_id() == application.getUniversity_id() && adm.getDepartment_id() == application.getDepartment_id()) {
                    int majorId = application.getMajor_id();
                    majorCount.put(majorId, majorCount.getOrDefault(majorId, 0) + 1); // 统计专业当前人数
                }
            }
            //初始化marjor_count 遍历
            for (Integer majorId : majorIdList) {
                if(majorCount.containsKey(majorId)) continue;
                else majorCount.put(majorId,0);
            }


            // 遍历每个专业的人数统计，进行调剂处理
            for (Map.Entry<Integer, Integer> entry : majorCount.entrySet()) {
                int majorId = entry.getKey();// 1
                int number = entry.getValue(); // 当前专业的学生人数
                int requiredMajorNumber = majorRequirements.get(majorId); // 获取专业的需求人数
                int excessNumber = number - requiredMajorNumber; // 计算需要调剂的学生人数

                if (excessNumber > 0) { // 需要进行调剂处理
                    int startIndex = majorIdList.indexOf(majorId); // 获取当前专业在专业列表中的索引位置

                    // 寻找可以调剂到的专业
                    int toMajor = -1;
                    for (int i = startIndex+1; i < majorIdList.size(); i++) {
//                        int index = startIndex + i; // 确保从startIndex开始循环
                        int id = majorIdList.get(i);
                        if (id != majorId) {
                            toMajor = id; // 找到可调剂到的专业
                            break;
                        }
                    }

                    // 修改符合调剂条件的学生的专业信息
                    int changedCount = 0;
                    while(changedCount != excessNumber)
                    {
                        for (Application application : preAdmission) {
                            if (application.getStudent_id() == Students.get(Students.size()-excessNumber).getStudent_id()){
                                application.setMajor_id(toMajor); // 调剂到新的专业 找到后记得及时退出
                                excessNumber--;//超的人数
                                //修改
                                majorCount.put(majorId,majorCount.get(majorId)-1);
                                majorCount.put(toMajor,majorCount.get(toMajor)+1);
                                break;
                            }
                        }
                    }

                }
            }
        }
    }
    /**
     * 更新最终的录取表
     *
     * @param preAdmission 预录取表
     * @throws Exception 如果更新过程中发生错误
     */
    private static void updateFinalAdmissions(ArrayList<Application> preAdmission) throws Exception {
        // 获取当前录取表中的记录数，用于生成新的录取ID

        // 遍历预录取表中的每条记录，生成最终录取表中的记录，并更新到数据库中
        for (Application application : preAdmission) {
            int id = application.getStudent_id();
            int majorId = application.getMajor_id();
            int universityId = application.getUniversity_id();
            int departmentId = application.getDepartment_id();

            // 创建新的录取记录并更新到数据库中
            Admission admission = new Admission(0, id, universityId, majorId, departmentId);
            AdmissionService.Create(admission);
        }
    }
}
