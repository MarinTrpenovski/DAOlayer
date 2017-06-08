package entity;

import java.util.List;

/**
 * Created by marin.trpenovski on 4/27/2017.
 */
public class University {

    private Long id;

    private String name;

    private String location;

    private List<Faculty> faculties;

    public University() {
    }

    public University(String name, String location){
        this.name = name;
        this.location = location;
    }

    public University(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        University that = (University) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!location.equals(that.location)) return false;
        return faculties.equals(that.faculties);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + faculties.hashCode();
        return result;
    }
}
