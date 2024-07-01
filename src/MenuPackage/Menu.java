package MenuPackage;

import Service.*;
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
                        //HandInAllApplications();
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
        // 获取所有学生的ID列表
        ArrayList<Integer> studentsIdList = StudentService.SelectAllId();

        // 检查学生ID列表是否为空，如果为空则抛出异常
        if (studentsIdList.isEmpty()) {
            throw new Exception("学生表中没有学生记录");
        }

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
    private boolean handleStudentApplications(Student student, ArrayList<Application> applications, ArrayList<AdmS> adSituation, ArrayList<Application> preAdmission) throws Exception {
        int studentScore = student.getScore();
        boolean admitted = false;
        //便遍历志愿信息
        for (Application application : applications) {
            int universityId = application.getUniversity_id();
            int departmentId = application.getDepartment_id();
            int majorId = application.getMajor_id();

            // 获取录取要求和院系最大录取人数
            EnRollmentMark enrollmentMark = EnRollmentMarkService.SelectByOther(universityId, departmentId, majorId);
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
    private void handleMajorAdjustment(ArrayList<AdmS> adSituation, ArrayList<Application> preAdmission) throws Exception {
        for (AdmS adm : adSituation) {
            adm.SortStudentDependsOnScore(); // 根据学生分数对注册表中的学生进行降序排序

            // 获取该院系的所有专业列表
            ArrayList<Integer> majorIdList = MajorService.SelectByDepartment_id(adm.getDepartment_id());//基于院系信息得到专业表

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

            // 遍历每个专业的人数统计，进行调剂处理
            for (Map.Entry<Integer, Integer> entry : majorCount.entrySet()) {
                int majorId = entry.getKey();
                int number = entry.getValue(); // 当前专业的学生人数
                int requiredMajorNumber = majorRequirements.get(majorId); // 获取专业的需求人数
                int excessNumber = number - requiredMajorNumber; // 计算需要调剂的学生人数

                if (excessNumber > 0) { // 需要进行调剂处理
                    int startIndex = majorIdList.indexOf(majorId); // 获取当前专业在专业列表中的索引位置

                    // 寻找可以调剂到的专业
                    int toMajor = -1;
                    for (int i = 0; i < majorIdList.size(); i++) {
                        int index = (startIndex + i) % majorIdList.size(); // 确保从startIndex开始循环
                        int id = majorIdList.get(index);
                        if (id != majorId) {
                            toMajor = id; // 找到可调剂到的专业
                            break;
                        }
                    }

                    // 修改符合调剂条件的学生的专业信息
                    int changedCount = 0;
                    for (Application application : preAdmission) {
                        if (changedCount >= excessNumber) break; // 已经调剂了足够人数
                        if (application.getUniversity_id() == adm.getUniversity_id() && application.getDepartment_id() == adm.getDepartment_id() && application.getMajor_id() == majorId) {
                            application.setMajor_id(toMajor); // 调剂到新的专业
                            changedCount++;
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
    private void updateFinalAdmissions(ArrayList<Application> preAdmission) throws Exception {
        // 获取当前录取表中的记录数，用于生成新的录取ID
        int currentId = AdmissionService.SelectAll().size();

        // 遍历预录取表中的每条记录，生成最终录取表中的记录，并更新到数据库中
        for (Application application : preAdmission) {
            int id = application.getStudent_id();
            int majorId = application.getMajor_id();
            int universityId = application.getUniversity_id();
            int departmentId = application.getDepartment_id();

            // 创建新的录取记录并更新到数据库中
            Admission admission = new Admission(currentId++, id, universityId, majorId, departmentId);
            AdmissionService.Update(admission);
        }
    }
}




