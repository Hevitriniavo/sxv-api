package com.fresh.coding.schoolmanagementapi.sercices.receipts;

import com.fresh.coding.schoolmanagementapi.dto.StudentPaymentDTO;

public interface ReceiptService {
    StudentPaymentDTO findStudentPaymentByStudentIdAndPaymentId(String studentId, String paymentId);
}
