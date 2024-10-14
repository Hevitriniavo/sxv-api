package com.fresh.coding.schoolmanagementapi;

//import com.fresh.coding.schoolmanagementapi.emuns.Gender;
//import com.fresh.coding.schoolmanagementapi.entities.Payment;
//import com.fresh.coding.schoolmanagementapi.entities.Student;
//import com.fresh.coding.schoolmanagementapi.repositories.PaymentRepository;
//import com.fresh.coding.schoolmanagementapi.repositories.StudentRepository;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;

@SpringBootApplication
public class SchoolManagementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolManagementApiApplication.class, args);
    }

//    @Bean
//    @Transactional
//    public CommandLineRunner initData(StudentRepository studentRepository, PaymentRepository paymentRepository) {
//        return args -> {
//            List<String[]> studentData = new ArrayList<>();
//            String[] classes = {"HEI", "ITC", "ENI", "ENS", "EMIT", "UCU"};
//            Random random = new Random();
//
//            for (int i = 0; i < 30; i++) {
//                String studentId = String.format("STD%05d", i + 1);
//                studentData.add(new String[]{
//                        studentId,
//                        "Student " + (i + 1),
//                        "FirstName " + (i + 1),
//                        classes[random.nextInt(classes.length)],
//                        "Address " + (i + 1),
//                        (i % 2 == 0) ? Gender.MALE.name() : Gender.FEMALE.name()
//                });
//            }
//            studentRepository.saveAll(studentData.stream().map(data -> Student.builder()
//                    .id(data[0])
//                    .name(data[1])
//                    .firstName(data[2])
//                    .className(data[3])
//                    .address(data[4])
//                    .gender(Gender.valueOf(data[5]))
//                    .build()).toList());
//
//            List<Payment> payments = new ArrayList<>();
//            for (int i = 0; i < 30; i++) {
//                for (int month = 16; month <= 39; month++) {
//                    String paymentId = String.format("PAYMENT%03d", payments.size() + 1);
//                    Payment payment = Payment.builder()
//                            .id(paymentId)
//                            .student(studentRepository.findById(String.format("STD%05d", i + 1)).orElse(null))
//                            .paymentName("Payment for month " + month)
//                            .paymentDate(LocalDate.now())
//                            .price(100 * month)
//                            .month(month)
//                            .amount(100 * month)
//                            .build();
//                    payments.add(payment);
//                }
//            }
//            paymentRepository.saveAll(payments);
//        };
//    }
}
