package monapp;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CourseManager {

    @PersistenceContext(unitName = "myData")
    EntityManager em;

    public List<Course> findCourses() {
        return em.createQuery("Select c From Course c", Course.class)
                .getResultList();
    }

    public Course findCourse(Long n) {
        return em.find(Course.class, n);
    }

    public Course saveCourse(Course c) {
        if (c.getId() == null) {
            em.persist(c);
        } else {
            c = em.merge(c);
        }
        return c;
    }

    public void deleteCourse(Course c) {
        c = em.merge(c);
        em.remove(c);
    }

}