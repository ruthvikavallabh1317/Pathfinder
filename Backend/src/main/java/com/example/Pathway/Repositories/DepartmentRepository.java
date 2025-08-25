package com.example.Pathway.Repositories;

import com.example.Pathway.Models.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<DepartmentModel, Long>{

    @Query(value = "select * from department where dept_id = ?1;", nativeQuery = true)
    DepartmentModel getDepartmentById(Long deptId);

    @Query(value = "SELECT department_name FROM `department` where dept_id in (?1);", nativeQuery = true)
    String getDepartmentNameById(Long deptId);

}

