package monapp;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "course")
@SessionScoped
public class CourseControler {

    @EJB
    CourseManager cm;

    Course theCourse = new Course();

    @PostConstruct
    public void init() {
        System.out.println("Create " + this);
        if (cm.findCourses().size() == 0) {
            Course c1 = new Course();
            c1.setName("Architecture JEE");
            c1.setHours(60);
            c1.setDescription("Introduction à JEE.");
            cm.saveCourse(c1);
        }
    }

    public List<Course> getCourses() {
        return cm.findCourses();
    }

    public Course getTheCourse() {
        return theCourse;
    }

    public String show(Long n) {
        theCourse = cm.findCourse(n);
        return "showCourse";
    }

    public String save() {
        cm.saveCourse(theCourse);
        return "showCourse";
    }

    public String newCourse() {
        theCourse = new Course();
        return "editCourse?faces-redirect=true";
    }

}