package entity;

import java.util.List;

/**
 * Created by marin.trpenovski on 4/27/2017.
 */
public class Faculty {

    private Long id;

    private Long parentId;

    private Long parentIndex;

    private String numericalMapping;

    private Long depth;

    private String name;

    private String address;

    private List<Student> studentList;

    private Long universityId;

    public Faculty() {

    }

    public Faculty (Long id, Long parentId, String name){
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public Faculty(Long id, Long parentId, Long parentIndex, String numericalMapping, Long depth, String name, String address, Long universityId) {
        this.id = id;
        this.parentId = parentId;
        this.parentIndex = parentIndex;
        this.numericalMapping = numericalMapping;
        this.depth = depth;
        this.name = name;
        this.address = address;
        this.universityId = universityId;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getDepth() {
        return depth;
    }

    public void setDepth(Long depth) {
        this.depth = depth;
    }

    public Long getParentIndex() {
        return parentIndex;
    }

    public void setParentIndex(Long parentIndex) {
        this.parentIndex = parentIndex;
    }

    public String getNumericalMapping() {
        return numericalMapping;
    }

    public void setNumericalMapping(String numericalMapping) {
        this.numericalMapping = numericalMapping;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }
}
