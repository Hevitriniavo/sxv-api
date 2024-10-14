package com.fresh.coding.schoolmanagementapi.controllers;

import com.fresh.coding.schoolmanagementapi.dto.StudentDTO;
import com.fresh.coding.schoolmanagementapi.dto.pagination.Paginate;
import com.fresh.coding.schoolmanagementapi.dto.searchs.StudentSearch;
import com.fresh.coding.schoolmanagementapi.sercices.students.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/paginate")
    @ResponseStatus(HttpStatus.OK)
    public Paginate<List<StudentDTO>> getAllStudents(
            @ModelAttribute StudentSearch studentSearch,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return studentService.findAllStudents(studentSearch, page, size);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> getAllStudents( @ModelAttribute StudentSearch studentSearch){
        return studentService.findAllStudents(studentSearch);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO saveStudent(@RequestBody StudentDTO toSave){
        return studentService.save(toSave);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        studentService.delete(id);
    }
}
