package com.fresh.coding.schoolmanagementapi.sercices.statistics;

import com.fresh.coding.schoolmanagementapi.dto.TotalPaymentsByClassDTO;

import java.util.List;

public interface StatisticService {
    List<TotalPaymentsByClassDTO> findTotalPaymentsByClass();
}
