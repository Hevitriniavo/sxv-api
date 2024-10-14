package com.fresh.coding.schoolmanagementapi.sercices.receipts;

import com.fresh.coding.schoolmanagementapi.dto.PaymentDTO;
import com.fresh.coding.schoolmanagementapi.dto.StudentDTO;
import com.fresh.coding.schoolmanagementapi.dto.StudentPaymentDTO;
import com.fresh.coding.schoolmanagementapi.entities.Payment;
import com.fresh.coding.schoolmanagementapi.entities.Student;
import com.fresh.coding.schoolmanagementapi.exceptions.HttpNotFoundException;
import com.fresh.coding.schoolmanagementapi.repositories.PaymentRepository;
import com.fresh.coding.schoolmanagementapi.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;

    @Override
    public StudentPaymentDTO findStudentPaymentByStudentIdAndPaymentId(UUID studentId, Long paymentId) {
        log.info("Fetching payment for student ID: {} and payment ID: {}", studentId, paymentId);

        var student = getStudentById(studentId);
        var payment = getPaymentByIdAndStudent(paymentId, student);

        return mapToStudentPaymentDTO(student, payment);
    }

    private Student getStudentById(UUID studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new HttpNotFoundException("Student not found with id: " + studentId));
    }

    private Payment getPaymentByIdAndStudent(Long paymentId, Student student) {
        return paymentRepository.findById(paymentId)
                .filter(p -> p.getStudent().getId().equals(student.getId()))
                .orElseThrow(() -> new HttpNotFoundException("Payment not found with id: " + paymentId + " for student: " + student.getId()));
    }

    private StudentPaymentDTO mapToStudentPaymentDTO(Student student, Payment payment) {
        var paymentDTO = new PaymentDTO();
        BeanUtils.copyProperties(payment, paymentDTO);
        paymentDTO.setStudentId(student.getId());

        var studentDTO = new StudentDTO();
        BeanUtils.copyProperties(student, studentDTO);

        return new StudentPaymentDTO(paymentDTO, studentDTO);
    }
}
