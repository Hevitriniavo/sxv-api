package com.fresh.coding.schoolmanagementapi.repositories;

import com.fresh.coding.schoolmanagementapi.dto.TotalPaymentsByClassDTO;
import com.fresh.coding.schoolmanagementapi.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String>, JpaSpecificationExecutor<Payment> {
    List<Payment> findAllByStudentId(String studentId);

    @Query("""
                 SELECT
                    NEW
                    com.fresh.coding.schoolmanagementapi.dto.TotalPaymentsByClassDTO(p.student.className, SUM(p.amount))
                        FROM
                        Payment p
                  GROUP BY
                  p.student.className
            
            """)
    List<TotalPaymentsByClassDTO> findTotalPaymentsByClass();
}
