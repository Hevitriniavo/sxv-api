package com.fresh.coding.schoolmanagementapi.sercices.receipts;

import com.fresh.coding.schoolmanagementapi.dto.StudentPaymentDTO;

import java.util.UUID;

public interface ReceiptService {
    StudentPaymentDTO findStudentPaymentByStudentIdAndPaymentId(UUID studentId, Long paymentId);
}
