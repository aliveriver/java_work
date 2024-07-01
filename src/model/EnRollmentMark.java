package model;

public class EnRollmentMark {
    private int university_id;
    private int major_id;
    private int department_id;
    private int Enrollment_id;
    private int RequiredScore;
    private int MRequiredN;
    private int DRequiredN;

    public EnRollmentMark() {
        university_id = 0;
        major_id = 0;
        department_id = 0;
        Enrollment_id = 0;
        RequiredScore = 0;
        MRequiredN = 0;
        DRequiredN = 0;

    }



    public EnRollmentMark(int university_id, int major_id, int department_id, int enrollment_id, int requiredScore, int MRequiredN, int DRequiredN) {
        this.university_id = university_id;
        this.major_id = major_id;
        this.department_id = department_id;
        Enrollment_id = enrollment_id;
        RequiredScore = requiredScore;
        this.MRequiredN = MRequiredN;
        this.DRequiredN = DRequiredN;
    }

    public int getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(int university_id) {
        this.university_id = university_id;
    }

    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
        this.major_id = major_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getEnrollment_id() {
        return Enrollment_id;
    }

    public void setEnrollment_id(int enrollment_id) {
        Enrollment_id = enrollment_id;
    }

    public int getRequiredScore() {
        return RequiredScore;
    }

    public void setRequiredScore(int requiredScore) {
        RequiredScore = requiredScore;
    }

    public int getMRequiredN() {
        return MRequiredN;
    }

    public void setMRequiredN(int MRequiredN) {
        this.MRequiredN = MRequiredN;
    }

    public int getDRequiredN() {
        return DRequiredN;
    }
    public void setDRequiredN(int DRequiredN) {
        this.DRequiredN = DRequiredN;
    }
}
