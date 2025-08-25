package com.example.Pathway.Controllers;

import com.example.Pathway.Models.CourseModel;
import com.example.Pathway.Models.DepartmentModel;
import com.example.Pathway.Services.CourseService;
import com.example.Pathway.Services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pathway")
public class PathController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/TestAPI")
    public String testApi() throws Exception {

        return "All Good :)";

    }

    @GetMapping("/getDepartment")
    public DepartmentModel getDepartment(@RequestParam("deptId") Long deptId) throws Exception {

        return departmentService.getDepartment(deptId);

    }

    @GetMapping("/getCoursesByDepartment")
    public List<CourseModel> getCoursesByDepartment(@RequestParam("deptId") Long deptId) throws Exception {

        return courseService.getCoursesForDepartment(deptId);

    }

    @GetMapping("/getCourseRecommendation")
    public ArrayList<CourseModel> getCourseRecommendation(@RequestParam("deptId") Long deptId, @RequestParam("jobTitle") String jobTitle)
            throws Exception {

        return courseService.generateCourseRecommendation(deptId, jobTitle);

    }

}
