package com.fresh.coding.schoolmanagementapi.controllers;

import com.fresh.coding.schoolmanagementapi.dto.StudentPaymentDTO;
import com.fresh.coding.schoolmanagementapi.sercices.receipts.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/receipts")
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService paymentService;

    @GetMapping("/student/{studentId}/payment/{paymentId}")
    @ResponseStatus(HttpStatus.OK)
    public StudentPaymentDTO getReceiptByStudentIdAndPaymentId (
            @PathVariable UUID studentId,
            @PathVariable Long paymentId
    ) {
        return paymentService.findStudentPaymentByStudentIdAndPaymentId(studentId, paymentId);
    }
}
