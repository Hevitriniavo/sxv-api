package com.fresh.coding.schoolmanagementapi.controllers;

import com.fresh.coding.schoolmanagementapi.dto.PaymentDTO;
import com.fresh.coding.schoolmanagementapi.dto.StudentWithPaymentsDTO;
import com.fresh.coding.schoolmanagementapi.dto.pagination.Paginate;
import com.fresh.coding.schoolmanagementapi.dto.searchs.PaymentSearch;
import com.fresh.coding.schoolmanagementapi.sercices.payments.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentDTO> getAllPayments(@ModelAttribute PaymentSearch paymentSearch) {
        return paymentService.findAllPayments(paymentSearch);
    }

    @GetMapping("/paginate")
    @ResponseStatus(HttpStatus.OK)
    public Paginate<List<PaymentDTO>> getAllPayments(
            @ModelAttribute PaymentSearch paymentSearch,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return paymentService.findAllPayments(paymentSearch, page, size);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentDTO getPayment(@PathVariable Long id){
        return paymentService.findPaymentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public PaymentDTO savePayment(@RequestBody PaymentDTO toSave){
        return paymentService.save(toSave);
    }


    @GetMapping("/student/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public StudentWithPaymentsDTO getPaymentsByStudentId(@PathVariable UUID studentId) {
        return paymentService.findStudentWithPayments(studentId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        paymentService.delete(id);
    }
}
