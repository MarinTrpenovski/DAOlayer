package entity;

import java.util.List;

/**
 * Created by marin.trpenovski on 4/27/2017.
 */
public class Faculty {

    private Long id;

    private String name;

    private String address;

    private List<Student> studentList;

    public Faculty() {

    }

    public Faculty(Long id, String name, String address, List<Student> studentList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.studentList = studentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

}
