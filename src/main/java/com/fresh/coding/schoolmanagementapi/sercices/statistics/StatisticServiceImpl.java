package com.fresh.coding.schoolmanagementapi.sercices.statistics;

import com.fresh.coding.schoolmanagementapi.dto.TotalPaymentsByClassDTO;
import com.fresh.coding.schoolmanagementapi.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final PaymentRepository paymentRepository;

    @Override
    public List<TotalPaymentsByClassDTO> findTotalPaymentsByClass() {
        return paymentRepository.findTotalPaymentsByClass();
    }
}
