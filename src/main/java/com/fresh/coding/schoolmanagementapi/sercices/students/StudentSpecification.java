package com.fresh.coding.schoolmanagementapi.sercices.students;

import com.fresh.coding.schoolmanagementapi.dto.searchs.StudentSearch;
import com.fresh.coding.schoolmanagementapi.entities.Student;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {

    public static Specification<Student> filter(StudentSearch search) {
        return (Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (search.getName() != null && !search.getName().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + search.getName().toLowerCase() + "%"));
            }

            if (search.getFirstName() != null && !search.getFirstName().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + search.getFirstName().toLowerCase() + "%"));
            }

            if (search.getClassName() != null && !search.getClassName().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("className"), search.getClassName()));
            }

            if (search.getAddress() != null && !search.getAddress().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + search.getAddress().toLowerCase() + "%"));
            }

            if (search.getGender() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("gender"), search.getGender()));
            }

            return predicate;
        };
    }
}
