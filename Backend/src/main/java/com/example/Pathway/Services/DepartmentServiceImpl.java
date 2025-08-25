package com.example.Pathway.Services;

import com.example.Pathway.Models.DepartmentModel;
import com.example.Pathway.Repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentModel getDepartment(Long deptId){

        DepartmentModel dept = departmentRepository.getDepartmentById(deptId);
        return dept;

    }

}
