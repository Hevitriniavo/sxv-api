package com.fresh.coding.schoolmanagementapi.sercices.payments;

import com.fresh.coding.schoolmanagementapi.dto.searchs.PaymentSearch;
import com.fresh.coding.schoolmanagementapi.entities.Payment;
import org.springframework.data.jpa.domain.Specification;


public class PaymentSpecification {
    public static Specification<Payment> filter(PaymentSearch paymentSearch) {
        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction();

            if (paymentSearch.getPaymentName() != null && !paymentSearch.getPaymentName().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("paymentName")), "%" + paymentSearch.getPaymentName().toLowerCase() + "%"));
            }

            if (paymentSearch.getId() != null && !paymentSearch.getId().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("id")), "%" + paymentSearch.getId().toLowerCase() + "%"));
            }

            if (paymentSearch.getStartDate() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.greaterThanOrEqualTo(root.get("paymentDate"), paymentSearch.getStartDate()));
            }

            if (paymentSearch.getEndDate() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.lessThanOrEqualTo(root.get("paymentDate"), paymentSearch.getEndDate()));
            }

            if (paymentSearch.getMinPrice() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.greaterThanOrEqualTo(root.get("price"), paymentSearch.getMinPrice()));
            }

            if (paymentSearch.getMaxPrice() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.lessThanOrEqualTo(root.get("price"), paymentSearch.getMaxPrice()));
            }

            if (paymentSearch.getMonth() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("month"), paymentSearch.getMonth()));
            }


            return predicate;
        };
    }
}
