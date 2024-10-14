package com.fresh.coding.schoolmanagementapi.sercices.payments;

import com.fresh.coding.schoolmanagementapi.dto.PaymentDTO;
import com.fresh.coding.schoolmanagementapi.dto.StudentWithPaymentsDTO;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    List<PaymentDTO> findAllPayments();

    PaymentDTO save(PaymentDTO toSave);

    void delete(Long id);

    StudentWithPaymentsDTO findStudentWithPayments(UUID studentId);

    PaymentDTO findPaymentById(Long id);
}
