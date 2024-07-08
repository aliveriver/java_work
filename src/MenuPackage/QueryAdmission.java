package MenuPackage;

import Service.AdmissionService;
import model.Admission;

import java.util.Scanner;
import java.util.ArrayList;

abstract public class QueryAdmission {
    public static void QueryAdmissionFunction() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入学生ID：");
        int studentId = scanner.nextInt();

        Admission admission = findAdmissionByStudentId(studentId);
        if (admission != null) {
            System.out.println("以下为该学生的录取信息：");
            displayAdmission(admission);
        } else {
            System.out.println("未找到该学生的录取信息！");
        }
    }

    private static Admission findAdmissionByStudentId(int studentId) {
        ArrayList<Admission> admissions = AdmissionService.SelectAll();
        for (Admission admission : admissions) {
            if (admission.getStudent_id() == studentId) {
                return admission;
            }
        }
        return null;
    }

    private static void displayAdmission(Admission admission) {
        System.out.println("录取ID: " + admission.getAdmission_id());
        System.out.println("学生ID: " + admission.getStudent_id());
        System.out.println("大学ID: " + admission.getUniversity_id());
        System.out.println("专业ID: " + admission.getMajor_id());
        System.out.println("院系ID: " + admission.getDepartment_id());
    }
}
