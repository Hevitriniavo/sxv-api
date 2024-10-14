package com.fresh.coding.schoolmanagementapi.sercices.students;

import com.fresh.coding.schoolmanagementapi.dto.StudentDTO;
import com.fresh.coding.schoolmanagementapi.dto.pagination.Paginate;
import com.fresh.coding.schoolmanagementapi.dto.searchs.StudentSearch;

import java.util.List;

public interface StudentService {

    Paginate<List<StudentDTO>> findAllStudents(StudentSearch studentSearch, int page, int size);
    List<StudentDTO> findAllStudents(StudentSearch studentSearch);

    StudentDTO save(StudentDTO toSave);

    void delete(String id);
}
