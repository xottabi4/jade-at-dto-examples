package lv.vea.jade.at.dto.examples.dtoexamples.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Teacher")
@XmlAccessorType(XmlAccessType.FIELD)
public class Teacher {

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Surname")
    private String surname;

    public Teacher() {
    }

    public Teacher(final String name, final String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }
}
