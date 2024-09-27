package springmvc.starter.demo.dto;

import java.util.List;

public class StudentDetailsDTO {
    private String studentName;
    private String email;
    private int age;
    private List<MarkDetailsDTO> markDetails;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<MarkDetailsDTO> getMarkDetails() {
        return markDetails;
    }

    public void setMarkDetails(List<MarkDetailsDTO> markDetails) {
        this.markDetails = markDetails;
    }
}
