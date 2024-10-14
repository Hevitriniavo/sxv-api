package com.fresh.coding.schoolmanagementapi.sercices.payments;

import com.fresh.coding.schoolmanagementapi.dto.PaymentDTO;
import com.fresh.coding.schoolmanagementapi.dto.StudentWithPaymentsDTO;
import com.fresh.coding.schoolmanagementapi.dto.pagination.Paginate;
import com.fresh.coding.schoolmanagementapi.dto.searchs.PaymentSearch;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    Paginate<List<PaymentDTO>> findAllPayments(PaymentSearch paymentSearch, int page, int size);
    List<PaymentDTO> findAllPayments(PaymentSearch paymentSearch);

    PaymentDTO save(PaymentDTO toSave);

    void delete(Long id);

    StudentWithPaymentsDTO findStudentWithPayments(UUID studentId);

    PaymentDTO findPaymentById(Long id);
}
