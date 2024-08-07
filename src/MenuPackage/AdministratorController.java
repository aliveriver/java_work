package MenuPackage;

import Service.*;
import model.*;
import model.Class;

import java.util.*;

abstract public class AdministratorController {
    private static boolean Used = true;
    public static void AdministratorControllerMenu() {
        // 1.分配志愿信息  2.分配班级信息 3.添加大学信息 4.添加专业信息（外码参考）
        System.out.println("请输入管理员密码:");
        Scanner scanner33 = new Scanner(System.in);
        String input = scanner33.nextLine();
        if (input.equals("123456")) {
            System.out.println("密码正确，进入管理员系统");
        } else {
            System.out.println("密码错误！");
            return;
        }
        boolean left = true;
        while (left) {
            System.out.println("请注意，临时添加的大学、专业不会即时反映在录取简章和学生可选项中");
            System.out.println("1. 分配志愿信息");//先录取
            System.out.println("2. 分配班级");//后分班
            System.out.println("3. 添加大学信息");//id name location
            System.out.println("4. 添加专业信息");//注意department，默认不同大学里的相同专业内容相同   course
            System.out.println("5. 查看所有的录取信息");//所有学生的录取信息
            System.out.println("6. 返回主菜单");
            System.out.print("请选择");
            int Choice = 0;
            Scanner scanner = new Scanner(System.in);
            Choice = scanner.nextInt();
            switch (Choice) {
                case 1:
                    try {
                        if (Used)
                        {
                            HandInAllApplications();
                            Used = false;
                        }
                        else{
                            System.out.println("您已经调剂过啦！");
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2://怎么分班？？？？？？？？？？？？？
                    AssignStudentToClass();
                    break;
                case 3:
                    CreateUniversity();
                    break;
                case 4:
                    CreateMajor();
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

        for (Student student : studentstemp) {
            studentsIdList.add(student.getStudent_id());
        }

        // 检查学生ID列表是否为空，如果为空则抛出异常
        // 预录取表
        ArrayList<Application> preAdmission = new ArrayList<>();
        // 注册表
        ArrayList<AdmS> adSituation = new ArrayList<>();

        // 遍历每个学生ID
        for (int id : studentsIdList) {
            Student student = StudentService.SelectById(id); // 获取学生信息
            if (student == null) continue; // 如果学生信息为空，跳过

            int studentScore = student.getScore(); // 获取学生的高考成绩
            ArrayList<Application> applications = ApplicationService.SelectBystudent_id(id); // 获取学生的所有志愿信息

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
    private static int GetMajorIdFromPreadmission(ArrayList<Application> preAdmission,int stuid) {
        for(Application a :preAdmission)
        {
            if(a.getStudent_id() == stuid) return a.getMajor_id();
        }
        return -1;
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
            int maxMajorCount = enrollmentMark.getMRequiredN();//获得最大录取人数

            if (studentScore >= requiredScore) { // 符合录取要求
                boolean departmentFound = false;

                // 检查院系是否已在注册表中   同时处理注册表
                for (AdmS adm : adSituation) {
                    if (universityId == adm.getUniversity_id() && departmentId == adm.getDepartment_id()) {
                        departmentFound = true;
                        /*判断是否调剂*/
                        if (adm.getDcount() < maxDepartmentCount) //院系人数小于最大院系人数
                        {

                            //查找一个ADMS中这个专业的数量有没有超
                            /*判断是否调剂*/
                            //计算我想报的专业的人数
                            if(application.getIs_adjustment()==0)
                            {
                                int majorcount = 0;
                                ArrayList<Student> studentstemp = adm.getSlist();
                                for(Student test: studentstemp)
                                {
                                    int studentId = test.getStudent_id();
                                    int majorOfOthers =0;//这个学生的专业id
                                    majorOfOthers = GetMajorIdFromPreadmission(preAdmission, studentId);
                                    if(majorOfOthers==majorId) majorcount++;//如果别人的专业和我想报的专业一样，那么数量++
                                }
                                if(majorcount>=maxMajorCount)
                                {
                                    //无法调剂，不能录取，滑档
                                    System.out.println("学生id "+student.getStudent_id()+"的志愿");
                                    System.out.println("志愿id "+application.getApplication_id()+"大学id"+application.getUniversity_id()+"院系id"+application.getDepartment_id()+"专业id"+application.getMajor_id()+"滑档");
                                    return false;
                                }
                                //不满足说明能录取呀


                                else{
                                    adm.AddStudent(student); // 添加学生到院系注册表中
                                    adm.setDcount(adm.getDcount() + 1); // 院系人数加1
                                    preAdmission.add(application); // 加入预录取表
                                    admitted = true; // 成功录取
                                    break;
                                }


                            }
                            else{
                                adm.AddStudent(student); // 添加学生到院系注册表中
                                adm.setDcount(adm.getDcount() + 1); // 院系人数加1
                                preAdmission.add(application); // 加入预录取表
                                admitted = true; // 成功录取
                                break;
                            }

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
            //adm.SortStudentDependsOnScore(); // 根据学生分数对注册表中的学生进行降序排序

            // 获取该院系的所有专业列表
            int temp = adm.getDepartment_id();//获取院系id
            ArrayList<Integer> majorIdList = MajorService.SelectByDepartment_id(temp);//基于院系信息得到专业表
            ArrayList<Integer> majorTempIdList = MajorService.SelectByDepartment_id(temp);//用来改变调剂志愿的
            ArrayList<Student> Students = adm.getSlist();
            int RequiredDepartmentCount =0;
            // 统计每个专业的人数需求
            Map<Integer, Integer> majorRequirements = new HashMap<>();
            for (Integer majorId : majorIdList) {
                EnRollmentMark enrollmentMark = EnRollmentMarkService.SelectByOther(adm.getUniversity_id(), adm.getDepartment_id(), majorId);
                int requiredMajorNumber = enrollmentMark.getMRequiredN(); // 获取专业的最大录取人数
                RequiredDepartmentCount = enrollmentMark.getDRequiredN();//获取院系最大人数
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
                if (majorCount.containsKey(majorId))
                {
                    if(majorCount.get(majorId)>=majorRequirements.get(majorId))
                    {
                        majorTempIdList.remove(majorTempIdList.indexOf(majorId));//如果这个专业的人数已经大于等于所需人数了，那么它不能作为可调剂的id了。
                    }
                    continue;
                }
                else majorCount.put(majorId, 0);
            }


            // 遍历每个专业的人数统计，进行调剂处理
            for (Map.Entry<Integer, Integer> entry : majorCount.entrySet()) {
                int majorId = entry.getKey();// 1
                int number = entry.getValue(); // 当前专业的学生人数
                int requiredMajorNumber = majorRequirements.get(majorId); // 获取专业的需求人数
                int excessNumber = number - requiredMajorNumber; // 计算需要调剂的学生人数

                if (excessNumber > 0) { // 需要进行调剂处理
                    int toMajor = -1;
                    //旧版算法
                    //int startIndex = majorIdList.indexOf(majorId); // 获取当前专业在专业列表中的索引位置
                    // 寻找可以调剂到的专业

//                    for (int i = (startIndex + 1)%RequiredDepartmentCount; i < majorIdList.size(); i++) {
//// int index = startIndex + i; // 确保从startIndex开始循环
//                        int id = majorIdList.get(i);
//                        if (id != majorId) {
//                            toMajor = id; // 找到可调剂到的专业
//                            break;
//                        }
//                    }
                    for(Integer a :majorTempIdList)
                    {
                        if(a==majorId) continue;//似乎没有必要了
                        else {
                            toMajor = a;
                            break;
                        }
                    }

                    // 修改符合调剂条件的学生的专业信息
                    int changedCount = 0;
                    while (changedCount != excessNumber) {
                        for (Application application : preAdmission) {
                            if (application.getStudent_id() == Students.get(Students.size() - excessNumber).getStudent_id()) {
                                //均默认调剂
                                application.setMajor_id(toMajor); // 调剂到新的专业 找到后记得及时退出
                                excessNumber--;//超的人数
                                //修改
                                majorCount.put(majorId, majorCount.get(majorId) - 1);
                                majorCount.put(toMajor, majorCount.get(toMajor) + 1);
                                break;
                            }
                        }
                    }
                    if(majorCount.get(toMajor)>=majorRequirements.get(toMajor))
                    {
                        majorTempIdList.remove(majorTempIdList.indexOf(toMajor));//如果这个专业的人数已经大于等于所需人数了，那么它不能作为可调剂的id了。
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

    public static void AssignStudentToClass() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入大学名称：");
        String universityName = scanner.nextLine();

        System.out.print("请输入专业名称：");
        String majorName = scanner.nextLine();

        System.out.print("请输入要分配的每个班级的人数：");
        int classSize = scanner.nextInt();

        int universityId = getUniversityIdByName(universityName);
        int majorId = getMajorIdByName(majorName);

        ArrayList<Student> students = AdmissionService.SelectStudentByUniversityAndMajor(universityId, majorId);

        Collections.shuffle(students);

        int totalStudents = students.size();
        int classCount = (totalStudents + classSize - 1) / classSize;

        for (int i = 0; i < classCount; i++) {
            String className = String.format("%s专业%02d班", majorName, i + 1);
            Class newClass = new Class(majorId, className, universityId);
            int classId = ClassService.Create(newClass); // 创建新班级，并获取 class_id

            if (classId == -1) {
                System.out.println("班级创建失败！");
                continue;
            }

            newClass.setClass_id(classId); // 设置新班级的 class_id

            int start = i * classSize;
            int end = Math.min(start + classSize, totalStudents);
            for (int j = start; j < end; j++) {
                Student student = students.get(j);
                StudentService.UpdateStudentClass(student.getStudent_id(), newClass.getClass_id());
            }
        }

        System.out.println("分班完成！");
    }


    private static int getUniversityIdByName(String name) {
        return UniversityService.getUniversityIdByName(name);
    }

    private static int getMajorIdByName(String name) {
        return MajorService.getMajorIdByName(name);
    }

    /**
     * 创建大学信息
     */
    private static void CreateUniversity() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("请输入大学名称: ");
            String name = scanner.nextLine();
            System.out.print("请输入该大学所在省级行政区: ");
            String location = scanner.nextLine();

            ArrayList<University> universities = UniversityService.SelectAll(); //检查是否已存在同名的大学
            boolean exists = false;
            for (University university : universities) {
                if (university.getName().equalsIgnoreCase(name)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("该大学信息已经存在，请重新输入！");
            } else {
                University university = new University(0, name, location);
                UniversityService.Create(university);
                System.out.println("大学信息添加成功!");
                break;
            }
        }
    }

    private static void CreateMajor() {
        Scanner scanner = new Scanner(System.in);
        int department_id = 0;

        // 获取院系名称并查找对应的院系ID
        while (true) {
            System.out.print("请输入院系名称: ");
            String department_name = scanner.nextLine();

            ArrayList<Department> departments = DepartmentService.SelectAll();
            boolean found = false;
            for (Department department : departments) {
                if (department.getName().equalsIgnoreCase(department_name)) {
                    department_id = department.getDepartment_id();
                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            } else {
                System.out.println("未找到该院系，请重新输入！");
            }
        }

        // 输入专业名称并查重
        while (true) {
            System.out.print("请输入要添加的专业名称: ");
            String name = scanner.nextLine();

            ArrayList<Major> majors = MajorService.SelectAll();
            boolean exists = false;
            for (Major major : majors) {
                if (major.getName().equalsIgnoreCase(name) && major.getDepartment_id() == department_id) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("该专业已经存在，请重新输入！");
            } else {
                Major major = new Major(department_id, name);
                MajorService.Create(major);

                majors = MajorService.SelectAll();
                Major latestMajor = majors.get(majors.size() - 1);
                int major_id = latestMajor.getMajor_id(); // 获取最新的major

                System.out.println("请输入课程信息（输入 'end' 结束）:");
                ArrayList<Course> courses = new ArrayList<>();
                while (true) {
                    System.out.print("课程名称: ");
                    String course_name = scanner.nextLine();
                    if (course_name.equalsIgnoreCase("end")) {
                        break;
                    }
                    Course course = new Course(major_id, department_id, course_name);
                    courses.add(course);
                }

                for (Course course : courses) {
                    CourseService.Create(course);
                }

                System.out.println("专业及其课程信息新增成功!");
                break;
            }
        }
    }



}
