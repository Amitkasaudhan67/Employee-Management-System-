package com.ems.service;

import com.ems.dto.EmployeeDto;
import com.ems.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeDto employeeDto);

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto getEmployeeById(Long id);

    EmployeeResponseDto updateEmployee(Long id, EmployeeDto employeeDto);

    void deleteEmployee(Long id);

    List<EmployeeResponseDto> searchEmployeesByDepartment(String department);
}
