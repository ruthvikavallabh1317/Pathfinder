package com.example.Pathway.Services;

import com.example.Pathway.Models.CourseModel;
import com.example.Pathway.Repositories.CourseRepository;
import com.example.Pathway.Repositories.DepartmentRepository;
import com.example.Pathway.Util.AIUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<CourseModel> getCoursesForDepartment(Long deptId){

        List<CourseModel> courses = courseRepository.getCourseByDepartmentId(deptId);
        return courses;

    }

    public ArrayList<CourseModel> generateCourseRecommendation(Long deptId, String jobTitle) throws Exception {

        ArrayList<String> courseNames = new ArrayList<>();

        List<CourseModel> courses = courseRepository.getCourses();

        String deptName = departmentRepository.getDepartmentNameById(deptId);

        for(CourseModel course : courses){

            courseNames.add(course.getCourseName());

        }

        String prompt = "I want to become a " + jobTitle + " and i am pursuing a " + deptName +
                ". Out of the following courses which 10 courses should i study to help me get to my goal. " +
                "Please give me only the course name, i don't want any repeated values." +
                "I also want the output in the following format 'number. course_name' and nothing else " +
                "(such as an introductory line) should be there in the output: " + courseNames;

        String promptResponse = AIUtil.generateContent(prompt);

        List<String> suggestedCourses = AIUtil.extractCourseNames(promptResponse);

        ArrayList<CourseModel> response = new ArrayList<>();

        for(String currentCourse : suggestedCourses){

            CourseModel selectedCourse = courseRepository.findCourse(currentCourse);
            response.add(selectedCourse);

        }

        return response;
    }

}
