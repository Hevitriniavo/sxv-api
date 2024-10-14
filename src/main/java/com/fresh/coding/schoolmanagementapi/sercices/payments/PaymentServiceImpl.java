package com.fresh.coding.schoolmanagementapi.sercices.payments;

import com.fresh.coding.schoolmanagementapi.dto.PaymentDTO;
import com.fresh.coding.schoolmanagementapi.dto.StudentWithPaymentsDTO;
import com.fresh.coding.schoolmanagementapi.dto.pagination.PageInfo;
import com.fresh.coding.schoolmanagementapi.dto.pagination.Paginate;
import com.fresh.coding.schoolmanagementapi.dto.searchs.PaymentSearch;
import com.fresh.coding.schoolmanagementapi.entities.Payment;
import com.fresh.coding.schoolmanagementapi.entities.Student;
import com.fresh.coding.schoolmanagementapi.exceptions.HttpNotFoundException;
import com.fresh.coding.schoolmanagementapi.repositories.PaymentRepository;
import com.fresh.coding.schoolmanagementapi.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;

    @Override
    public Paginate<List<PaymentDTO>> findAllPayments(PaymentSearch paymentSearch, int page, int size) {
        log.info("Fetching all payments with search criteria paginate...");
        var pageable = PageRequest.of(page - 1, size);
        var spec = PaymentSpecification.filter(paymentSearch);
        var paymentPage = paymentRepository.findAll(spec, pageable);

        var payments = paymentPage.getContent()
                .stream()
                .map(this::toPaymentDTO)
                .collect(Collectors.toList());

        var pageInfo = new PageInfo(
                paymentPage.hasNext(),
                paymentPage.hasPrevious(),
                paymentPage.getTotalPages(),
                paymentPage.getNumber(),
                (int) paymentPage.getTotalElements()
        );

        return new Paginate<>(payments, pageInfo);
    }

    @Override
    public List<PaymentDTO> findAllPayments(PaymentSearch paymentSearch) {
        log.info("Fetching all payments with search criteria...");
        var spec = PaymentSpecification.filter(paymentSearch);
        var payments = paymentRepository.findAll(spec);

        return payments
                .stream()
                .map(this::toPaymentDTO)
                .collect(Collectors.toList());

    }

    @Override
    public PaymentDTO save(PaymentDTO toSave) {
        log.info("Saving payment for student with ID: {}", toSave.getStudentId());
        var payment = paymentRepository.findById(toSave.getId()).orElse(new Payment());
        if (toSave.getStudentId() != null) {
            log.debug("Looking for student with ID: {}", toSave.getStudentId());
            Student student = studentRepository.findById(toSave.getStudentId())
                    .orElseThrow(() -> {
                        log.error("Student not found with ID: {}", toSave.getStudentId());
                        return new HttpNotFoundException("Student not found with id: " + toSave.getStudentId());
                    });
            payment.setStudent(student);
        }

        BeanUtils.copyProperties(toSave, payment, "student", "receipts");
        payment = paymentRepository.save(payment);
        log.info("Payment saved successfully with ID: {}", payment.getId());

        return toPaymentDTO(payment);
    }

    @Override
    public void delete(String id) {
        log.info("Deleting payment with ID: {}", id);
        if (!paymentRepository.existsById(id)) {
            log.error("Payment not found with ID: {}", id);
            throw new HttpNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
        log.info("Payment deleted successfully with ID: {}", id);
    }

    @Override
    public StudentWithPaymentsDTO findStudentWithPayments(String studentId) {
        log.info("Fetching payments for student with ID: {}", studentId);
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new HttpNotFoundException("Student not found with id: " + studentId));

        var payments = paymentRepository.findAllByStudentId(studentId);
        var paymentDTOs = payments.stream()
                .map(this::toPaymentDTO)
                .collect(Collectors.toList());

        var studentWithPaymentsDTO = new StudentWithPaymentsDTO();
        BeanUtils.copyProperties(student, studentWithPaymentsDTO);
        studentWithPaymentsDTO.setPayments(paymentDTOs);

        log.info("Found {} payments for student with ID: {}", paymentDTOs.size(), studentId);
        return studentWithPaymentsDTO;
    }


    private PaymentDTO toPaymentDTO(Payment payment) {
        log.debug("Converting payment entity to DTO for payment ID: {}", payment.getId());
        PaymentDTO paymentDTO = new PaymentDTO();
        BeanUtils.copyProperties(payment, paymentDTO, "receipts");

        if (payment.getStudent() != null) {
            paymentDTO.setStudentId(payment.getStudent().getId());
        }

        return paymentDTO;
    }


    @Override
    public PaymentDTO findPaymentById(String id) {
        log.info("Fetching payment with ID: {}", id);
        var payment = paymentRepository.findById(id)
                .orElseThrow(() -> new HttpNotFoundException("Payment not found with id: " + id));
        return toPaymentDTO(payment);
    }


}
