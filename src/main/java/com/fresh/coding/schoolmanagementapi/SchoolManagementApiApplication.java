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
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;

@SpringBootApplication
public class SchoolManagementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolManagementApiApplication.class, args);
    }


//    @Bean
//    @Transactional
//    CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository) {
//        return args -> {
//            List<Student> students = IntStream.rangeClosed(1, 50)
//                    .mapToObj(i -> Student.builder()
//                            .name("StudentName" + i)
//                            .id("STD" + i)
//                            .firstName("FirstName" + i)
//                            .className("Class" + (i % 5 + 1))
//                            .address("Address" + i)
//                            .gender(i % 2 == 0 ? Gender.MALE : Gender.FEMALE)
//                            .build())
//                    .collect(Collectors.toList());
//
//            studentRepository.saveAll(students);
//
//            List<Payment> payments = IntStream.rangeClosed(1, 60)
//                    .mapToObj(j -> {
//                        Student student = students.get(j % students.size());
//                        return Payment.builder()
//                                .student(student)
//                                .id("PAYMENT" + 1)
//                                .paymentName("PaymentName" + j)
//                                .paymentDate(LocalDate.now().minusMonths(j % 5))
//                                .price(100 * (j % 5 + 1))
//                                .month((j % 12 + 1))
//                                .amount(100 * (j % 5 + 1))
//                                .build();
//                    })
//                    .collect(Collectors.toList());
//
//            paymentRepository.saveAll(payments);
//
//        };
//    }

}
