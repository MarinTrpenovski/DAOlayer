package entity;

/**
 * Created by marin.trpenovski on 5/9/2017.
 */
public class Subject {

    private Long id;

    private String name;

    private Long credits;

    private Long semester;

    public Subject() {
    }

    public Subject(Long id, String name, Long credits, Long semester) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.semester = semester;
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

    public Long getCredits() {
        return credits;
    }

    public void setCredits(Long credits) {
        this.credits = credits;
    }

    public Long getSemester() {
        return semester;
    }

    public void setSemester(Long semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (!id.equals(subject.id)) return false;
        if (!name.equals(subject.name)) return false;
        if (!credits.equals(subject.credits)) return false;
        return semester.equals(subject.semester);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + credits.hashCode();
        result = 31 * result + semester.hashCode();
        return result;
    }
}
