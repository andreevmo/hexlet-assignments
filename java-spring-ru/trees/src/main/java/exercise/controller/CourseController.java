package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCourses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous")
    public List<Course> getParentCourses(@PathVariable long id) {
        Course course = courseRepository.findById(id);
        List<Course> courses = new ArrayList<>();
        if (course != null && course.getPath() != null) {
            String[] ids = course.getPath().split("\\.");
            courses = courseRepository.findCoursesById(parseId(ids));
        }
        return courses;
    }

    private List<Long> parseId(String[] ids) {
        return Arrays.stream(ids)
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
    // END

}
