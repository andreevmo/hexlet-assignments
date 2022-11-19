package exercise.repository;

import exercise.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    Course findById(long id);
    @Query(value = "SELECT * FROM courses WHERE id IN ?1", nativeQuery = true)
    List<Course> findCoursesById(List<Long> ids);
}
