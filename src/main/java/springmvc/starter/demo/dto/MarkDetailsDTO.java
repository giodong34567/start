package springmvc.starter.demo.dto;

public class MarkDetailsDTO {
    private Double mark;
    private String markType;
    private String subjectName;

    public MarkDetailsDTO(Double mark, String markType, String subjectName) {
        this.mark = mark;
        this.markType = markType;
        this.subjectName = subjectName;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public String getMarkType() {
        return markType;
    }

    public void setMarkType(String markType) {
        this.markType = markType;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
