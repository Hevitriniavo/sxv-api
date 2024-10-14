package com.fresh.coding.schoolmanagementapi.sercices.payments;

import com.fresh.coding.schoolmanagementapi.dto.PaymentDTO;
import com.fresh.coding.schoolmanagementapi.dto.StudentWithPaymentsDTO;
import com.fresh.coding.schoolmanagementapi.entities.Payment;
import com.fresh.coding.schoolmanagementapi.entities.Student;
import com.fresh.coding.schoolmanagementapi.exceptions.HttpNotFoundException;
import com.fresh.coding.schoolmanagementapi.repositories.PaymentRepository;
import com.fresh.coding.schoolmanagementapi.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<PaymentDTO> findAllPayments() {
        log.info("Fetching all payments...");
        List<PaymentDTO> payments = paymentRepository.findAll()
                .stream()
                .map(this::toPaymentDTO)
                .collect(Collectors.toList());
        log.info("Found {} payments", payments.size());
        return payments;
    }

    @Override
    public PaymentDTO save(PaymentDTO toSave) {
        log.info("Saving payment for student with ID: {}", toSave.getStudentId());
        var payment = toSave.getId() != null ?
                paymentRepository.findById(toSave.getId())
                        .orElse(new Payment())
                : new Payment();

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
    public void delete(Long id) {
        log.info("Deleting payment with ID: {}", id);
        if (!paymentRepository.existsById(id)) {
            log.error("Payment not found with ID: {}", id);
            throw new HttpNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
        log.info("Payment deleted successfully with ID: {}", id);
    }

    @Override
    public StudentWithPaymentsDTO findStudentWithPayments(UUID studentId) {
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
        public PaymentDTO findPaymentById(Long id) {
        log.info("Fetching payment with ID: {}", id);
        var payment = paymentRepository.findById(id)
                .orElseThrow(() -> new HttpNotFoundException("Payment not found with id: " + id));
        return toPaymentDTO(payment);
    }


}
