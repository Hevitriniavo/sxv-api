package com.fresh.coding.schoolmanagementapi.sercices.payments;

import com.fresh.coding.schoolmanagementapi.dto.PaymentDTO;
import com.fresh.coding.schoolmanagementapi.dto.StudentWithPaymentsDTO;
import com.fresh.coding.schoolmanagementapi.dto.pagination.Paginate;
import com.fresh.coding.schoolmanagementapi.dto.searchs.PaymentSearch;

import java.util.List;

public interface PaymentService {
    Paginate<List<PaymentDTO>> findAllPayments(PaymentSearch paymentSearch, int page, int size);
    List<PaymentDTO> findAllPayments(PaymentSearch paymentSearch);

    PaymentDTO save(PaymentDTO toSave);

    void delete(String id);

    StudentWithPaymentsDTO findStudentWithPayments(String studentId);

    PaymentDTO findPaymentById(String id);
}
