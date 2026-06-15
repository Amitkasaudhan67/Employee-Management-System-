package com.ems.service;

import com.ems.dto.EmployeeDto;
import com.ems.dto.EmployeeResponseDto;
import com.ems.entity.Employee;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repository.EmployeeRepository;
import com.ems.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void createEmployeeReturnsSavedEmployeeResponse() {
        EmployeeDto request = EmployeeDto.builder()
                .firstName("Amit")
                .lastName("Gupta")
                .email("amit@example.com")
                .department("IT")
                .salary(BigDecimal.valueOf(50000))
                .build();

        Employee savedEmployee = Employee.builder()
                .id(1L)
                .firstName("Amit")
                .lastName("Gupta")
                .email("amit@example.com")
                .department("IT")
                .salary(BigDecimal.valueOf(50000))
                .createdAt(LocalDateTime.of(2026, 1, 1, 10, 0))
                .build();

        when(employeeRepository.save(any(Employee.class))).thenReturn(savedEmployee);

        EmployeeResponseDto response = employeeService.createEmployee(request);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getEmail()).isEqualTo("amit@example.com");
        assertThat(response.getDepartment()).isEqualTo("IT");
    }

    @Test
    void getEmployeeByIdThrowsExceptionWhenEmployeeDoesNotExist() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.getEmployeeById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: 99");
    }

    @Test
    void deleteEmployeeDeletesExistingEmployee() {
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Amit")
                .lastName("Gupta")
                .email("amit@example.com")
                .department("IT")
                .salary(BigDecimal.valueOf(50000))
                .createdAt(LocalDateTime.now())
                .build();

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository).delete(employee);
    }
}
