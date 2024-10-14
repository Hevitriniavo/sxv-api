package com.fresh.coding.schoolmanagementapi.sercices.students;

import com.fresh.coding.schoolmanagementapi.dto.StudentDTO;
import com.fresh.coding.schoolmanagementapi.dto.pagination.PageInfo;
import com.fresh.coding.schoolmanagementapi.dto.pagination.Paginate;
import com.fresh.coding.schoolmanagementapi.dto.searchs.StudentSearch;
import com.fresh.coding.schoolmanagementapi.entities.Student;
import com.fresh.coding.schoolmanagementapi.exceptions.HttpNotFoundException;
import com.fresh.coding.schoolmanagementapi.repositories.StudentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public Paginate<List<StudentDTO>> findAllStudents(StudentSearch studentSearch, int page, int size) {
        log.info("Fetching students with pagination: page {}, size {}", page, size);
        var pageRequest = PageRequest.of(page - 1, size);
        var spec =  StudentSpecification.filter(studentSearch);
        var studentPage = studentRepository.findAll(spec , pageRequest);
        var studentDTOs = studentPage.getContent()
                .stream()
                .map(this::toStudentSummarized)
                .collect(Collectors.toList());
        var pageInfo = new PageInfo(
                studentPage.hasNext(),
                studentPage.hasPrevious(),
                studentPage.getTotalPages(),
                studentPage.getNumber() + 1,
                (int) studentPage.getTotalElements()
        );

        return new Paginate<>(studentDTOs, pageInfo);
    }

    @Override
    public List<StudentDTO> findAllStudents(StudentSearch studentSearch) {
        log.info("Fetching all students based on search criteria without pagination...");

        var spec =  StudentSpecification.filter(studentSearch);
        var students = studentRepository.findAll(spec);

        return students.stream()
                .map(this::toStudentSummarized)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public StudentDTO save(StudentDTO toSave) {
        log.info("Saving student with ID: {}", toSave.getId() != null ? toSave.getId() : "New student");
        var student = studentRepository.findById(toSave.getId()).orElse(new Student());
        BeanUtils.copyProperties(toSave, student);

        student = studentRepository.save(student);
        log.info("Student saved successfully with ID: {}", student.getId());

        return toStudentSummarized(student);
    }

    @Override
    public void delete(String id) {
        log.info("Deleting student with ID: {}", id);
        if (!studentRepository.existsById(id)) {
            log.error("Student not found with ID: {}", id);
            throw new HttpNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
        log.info("Student deleted successfully with ID: {}", id);
    }

    private StudentDTO toStudentSummarized(@NonNull Student student) {
        log.debug("Converting student entity to DTO for student ID: {}", student.getId());
        var studentSummarized = new StudentDTO();
        BeanUtils.copyProperties(student, studentSummarized, "payments");
        return studentSummarized;
    }
}
