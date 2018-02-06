package monapp;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue
    Long id;

    @Column
    @NotNull @Size(min = 5, max = 200)
    String name;

    @Column
    @NotNull @Min(5) @Max(600)
    Integer hours;

    @Column
    String level;

    @Lob
    @NotNull @Size(min = 10, max = 4000)
    String description;

    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
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

    public Integer getHours() {
        return hours;
    }
    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}