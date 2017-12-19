package lv.vea.jade.at.dto.examples.dtoexamples.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "Class")
@XmlAccessorType(XmlAccessType.FIELD)
public class SchoolClass {

    @XmlElement(name = "Year")
    private String year;

    @XmlElementWrapper(name = "Students")
    @XmlElement(name = "Student")
    private List<Student> students;

    @XmlElement(name = "Teacher")
    private Teacher teacher;

    public String getYear() {
        return year;
    }

    public void setYear(final String year) {
        this.year = year;
    }

    public List<Student> getStudents() {
        if (students == null) {
            students = new LinkedList<>();
        }
        return students;
    }

    public void setStudents(final List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(final Teacher teacher) {
        this.teacher = teacher;
    }
}
