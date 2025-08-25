package com.example.Pathway.Services;

import com.example.Pathway.Models.CourseModel;

import java.util.ArrayList;
import java.util.List;

public interface CourseService {

    public List<CourseModel> getCoursesForDepartment(Long deptId);
    public ArrayList<CourseModel> generateCourseRecommendation(Long deptId, String jobTitle) throws Exception;

}
