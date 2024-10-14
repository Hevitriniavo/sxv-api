package com.fresh.coding.schoolmanagementapi.controllers;


import com.fresh.coding.schoolmanagementapi.dto.TotalPaymentsByClassDTO;
import com.fresh.coding.schoolmanagementapi.sercices.statistics.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping("/total-payments-by-class")
    public List<TotalPaymentsByClassDTO> getTotalPaymentsByClass() {
        return statisticService.findTotalPaymentsByClass();
    }
}
