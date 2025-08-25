package com.example.Pathway.Repositories;

import com.example.Pathway.Models.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseModel, Long> {

    @Query(value = "select * from courses;", nativeQuery = true)
    List<CourseModel> getCourses();

    @Query(value = "select * from courses where dept_id in (?1);", nativeQuery = true)
    List<CourseModel> getCourseByDepartmentId(Long deptId);

    @Query(value = "select * from courses where course_name like %?1%;", nativeQuery = true)
    CourseModel findCourse(String course);

}
